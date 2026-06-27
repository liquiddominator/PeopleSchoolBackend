# Arquitectura del servicio

## Estilo

`people-school-service` es un monolito modular con arquitectura hexagonal. Se despliega como un unico proceso Spring Boot y comparte una base PostgreSQL, pero divide el negocio en cuatro modulos con paquetes, casos de uso y adaptadores propios.

Este diseño conserva transacciones locales simples y un unico despliegue, a la vez que evita mezclar directamente el modelo de personas, gobierno, normativa y estudiantes.

```text
Cliente HTTP
    |
    v
api (adaptador de entrada)
    |
    v
application/usecase (puertos de entrada y orquestacion)
    |
    v
domain (entidades, enums e invariantes)
    |
    v
application/port/out
    |
    v
infrastructure (JPA, Redis o adaptador entre modulos)
```

## Modulos

| Modulo Java | Capitulo | Responsabilidad |
|---|---:|---|
| `institutionalpersonhouseholdregistry` | 0 | Fuente maestra de personas, hogares, identidades, contacto y relaciones. |
| `governanceregistry` | 1 | Grupo institucional, unidad educativa, sedes, estructura, catalogos y gestiones. |
| `schoolregulationsregistry` | 2 | Normativa escolar y referencia validada a la unidad educativa. |
| `studentregistry` | 3 | Perfil inicial de estudiante vinculado a persona y gestion. |
| `shared` | Transversal | Seguridad JWT y resolucion del tenant; no contiene negocio escolar. |

## Capas por modulo

### `api`

Adaptadores REST de entrada. Sus controladores:

- traducen path, query y JSON a DTO;
- obtienen el `Jwt` autenticado;
- resuelven el tenant mediante `CurrentTenantResolver`;
- invocan un caso de uso;
- convierten el resultado a un response HTTP.

No deben implementar persistencia ni reglas de negocio. `GlobalExceptionHandler` estandariza errores de validacion, entidad no encontrada, conflicto de negocio y fallos internos.

### `application`

Contiene los casos de uso que expresan operaciones del sistema. Coordina repositorios/puertos, verifica precondiciones, aplica el tenant y delimita transacciones. Los DTO de API no son entidades de dominio.

Cuando un modulo necesita informacion de otro, la dependencia se expresa como un puerto de salida dentro del modulo consumidor. Ejemplo vigente: normativa declara `EducationUnitReferencePort`; `GovernanceEducationUnitAdapter` lo implementa en infraestructura consultando gobierno.

### `domain`

Contiene entidades, enumeraciones y comportamiento independiente de HTTP, Spring MVC o JPA. Es el nucleo del modulo. Las reglas de dependencia impiden que dominios de modulos distintos se importen entre si.

### `infrastructure`

Implementa puertos de salida y detalles tecnicos:

- entidades y repositorios Spring Data JPA;
- mappers entre persistencia y dominio;
- adaptadores PostgreSQL;
- integracion entre modulos en el mismo proceso;
- puntos reservados para cache, eventos y proyecciones.

MongoDB esta presente como dependencia y su arbol de paquetes esta reservado, pero su autoconfiguracion esta excluida y no existe una proyeccion Mongo activa. PostgreSQL es actualmente la fuente persistente de verdad.

### `bootstrap`

Espacio de configuracion y ensamblaje propio del modulo. Spring realiza la inyeccion final; la clase raiz es `PeopleSchoolServiceApplication`.

## Reglas de dependencia

Las pruebas ArchUnit de `src/test/java/com/solveria/peopleschool/architecture` protegen estas reglas:

1. Dominio no depende de API, infraestructura ni de otro modulo.
2. Aplicacion no depende directamente de API o implementaciones de infraestructura.
3. Un modulo no consume el `domain` o `application` de otro modulo desde su propio negocio.
4. Las colaboraciones entre modulos pasan por un puerto del consumidor y un adaptador de infraestructura.
5. `shared` se limita a capacidades tecnicas realmente comunes.

## Flujo de una peticion

Ejemplo: crear una norma escolar.

1. Spring Security verifica firma HS256, issuer, expiracion, `token_type=access`, claims requeridos y revocacion.
2. La cadena de autorizacion exige `people-school.write` o `ADMIN_GLOBAL`.
3. `SchoolRegulationAdminController` valida el JSON con Bean Validation.
4. `CurrentTenantResolver` obtiene `tenant_id`; un administrador global puede indicar `X-Tenant-Id`.
5. `CreateSchoolRegulationUseCase` consulta `EducationUnitReferencePort` y aplica reglas de negocio.
6. El adaptador de gobierno valida la unidad educativa sin introducir una dependencia de negocio entre modulos.
7. El adaptador JPA persiste `pc_school_regulation` dentro de la transaccion.
8. El controlador devuelve `201 Created` con `SchoolRegulationResponse`.

## Persistencia y transacciones

- Una sola base PostgreSQL y un solo historial Flyway (`V1` a `V31`).
- Cada tabla usa prefijo `pc_` y conserva `tenant_id` para aislamiento logico.
- Las claves foraneas mantienen integridad dentro del esquema compartido.
- Los casos de uso de escritura son el limite natural de transaccion.
- Las entidades persistentes usan auditoria y versionado donde la migracion lo define.
- Los modulos son propietarios logicos de sus tablas aunque compartan schema fisico.

La propiedad de tablas esta detallada en [../database/BASE-DE-DATOS-POSTGRESQL.md](../database/BASE-DE-DATOS-POSTGRESQL.md).

## Seguridad y tenancy

El servicio es OAuth2 Resource Server, no emite tokens. IAM autentica y emite el access token; este servicio lo valida localmente. El tenant nunca se toma de un cuerpo JSON para autorizar acceso: procede del JWT, salvo el override explicito permitido a `ADMIN_GLOBAL`.

Detalles: [../security/SEGURIDAD-JWT-Y-TENANCY.md](../security/SEGURIDAD-JWT-Y-TENANCY.md).

## Topologia de ejecucion

```text
Frontend/API client ---> IAM (login, refresh, logout)
          |
          +-----------> people-school-service :8082
                              |       |
                              |       +--> Redis (revocacion JWT)
                              +----------> PostgreSQL (modelo transaccional)
```

`core-platform` se consume como libreria Maven local y aporta tipos transversales como entidad base, excepciones y soporte comun. IAM y AI siguen siendo servicios independientes.

## Evolucion sin romper la modularidad

Para una capacidad nueva:

1. Elegir el modulo propietario del dato y la regla.
2. Modelar la operacion en `application/usecase` y el dominio correspondiente.
3. Declarar puertos de salida en el modulo consumidor.
4. Implementarlos en `infrastructure`.
5. Exponer HTTP solamente desde `api`.
6. Agregar la siguiente migracion Flyway, sin editar migraciones aplicadas.
7. Actualizar API, dominio y base de datos en `docs` y ampliar las pruebas ArchUnit si aparece una colaboracion nueva.
