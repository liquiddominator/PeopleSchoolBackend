# People School Service - Documentacion tecnica

Esta carpeta documenta el comportamiento implementado actualmente por `people-school-service`. El codigo y las migraciones Flyway son la fuente de verdad; estos documentos describen esa implementacion, no funcionalidades futuras.

## Indice

| Tema | Documento | Contenido |
|---|---|---|
| API | [api/API-REST-ACTUAL.md](api/API-REST-ACTUAL.md) | Las 92 operaciones REST, autenticacion, parametros, cuerpos y respuestas |
| Arquitectura | [architecture/ARQUITECTURA-MODULAR-HEXAGONAL.md](architecture/ARQUITECTURA-MODULAR-HEXAGONAL.md) | Monolito modular hexagonal, capas, dependencias y flujo de una peticion |
| Dominio | [domain/DOMINIO-FUNCIONAL.md](domain/DOMINIO-FUNCIONAL.md) | Alcance real de los cuatro modulos y sus casos de uso |
| Base de datos | [database/BASE-DE-DATOS-POSTGRESQL.md](database/BASE-DE-DATOS-POSTGRESQL.md) | Modelo PostgreSQL, migraciones, tablas y uso del script consolidado |
| SQL consolidado | [database/PeopleSchoolDb.txt](database/PeopleSchoolDb.txt) | Esquema reproducido en orden desde `V1` hasta `V31` |
| Seguridad | [security/SEGURIDAD-JWT-Y-TENANCY.md](security/SEGURIDAD-JWT-Y-TENANCY.md) | Validacion de JWT de IAM, permisos, tenant y revocacion |
| Integraciones | [integrations/INTEGRACIONES.md](integrations/INTEGRACIONES.md) | IAM, PostgreSQL, Redis y `core-platform` |
| Levantamiento | [operations/LEVANTAMIENTO-PEOPLE-SCHOOL-SERVICE.md](operations/LEVANTAMIENTO-PEOPLE-SCHOOL-SERVICE.md) | Requisitos, comandos, URL, puertos y pruebas de arranque |
| Operacion | [operations/OPERACION-DEL-SERVICIO.md](operations/OPERACION-DEL-SERVICIO.md) | Configuracion, salud y observabilidad |
| Pruebas | [testing/PRUEBAS-Y-VALIDACION.md](testing/PRUEBAS-Y-VALIDACION.md) | Estrategia y comandos de verificacion |
| Decisiones | [decisions/DECISIONES-ARQUITECTONICAS.md](decisions/DECISIONES-ARQUITECTONICAS.md) | Decisiones arquitectonicas vigentes |
| Runbooks | [runbooks/RUNBOOKS-OPERATIVOS.md](runbooks/RUNBOOKS-OPERATIVOS.md) | Diagnostico y migraciones |
| Estructura | [STRUCTURE_GUIDE.md](STRUCTURE_GUIDE.md) | Guia navegable del repositorio y paquetes |

## Alcance del desplegable

El servicio contiene cuatro modulos de negocio dentro de un unico proceso Spring Boot y una unica base PostgreSQL:

1. `institutionalpersonhouseholdregistry`: personas, hogares y relaciones maestras (capitulo 0).
2. `governanceregistry`: estructura institucional, sedes, niveles, roles y gestiones (capitulo 1).
3. `schoolregulationsregistry`: registro basico de normativa escolar (capitulo 2).
4. `studentregistry`: perfil maestro inicial del estudiante (capitulo 3).

Los capitulos 2 y 3 estan implementados con una superficie menor que los capitulos 0 y 1. La descripcion detallada de esa diferencia esta en [domain/DOMINIO-FUNCIONAL.md](domain/DOMINIO-FUNCIONAL.md).

## Referencias ejecutables

- OpenAPI JSON: `GET /v3/api-docs`
- Swagger UI: `/swagger-ui.html`
- Migraciones: `src/main/resources/db/migration`
- Reglas arquitectonicas: `src/test/java/com/solveria/peopleschool/architecture`
- Configuracion: `src/main/resources/application.yml`
