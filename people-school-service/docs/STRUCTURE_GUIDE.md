# Guia de estructura

## Raiz

| Ruta | Responsabilidad |
|---|---|
| `pom.xml` | Java 21, Spring Boot, dependencias, Enforcer y Spotless. |
| `Dockerfile` | Build del servicio junto al artefacto local `core-platform`. |
| `docker-compose.yml` | Stack local del servicio y sus dependencias declaradas. |
| `DOCUMENTACION-PEOPLE-SCHOOL-SERVICE.md` | Introduccion del proyecto. |
| `STRUCTURE.md` | Arbol generado del proyecto. |
| `docs` | Documentacion tecnica mantenida junto al codigo. |
| `scripts` | Automatizaciones repetibles del servicio. |

## Codigo Java

Raiz: `src/main/java/com/solveria/peopleschool`.

```text
PeopleSchoolServiceApplication.java
institutionalpersonhouseholdregistry/
governanceregistry/
schoolregulationsregistry/
studentregistry/
shared/
```

Cada modulo puede contener:

| Carpeta | Papel hexagonal |
|---|---|
| `api/admin` | Adaptadores REST de entrada. |
| `application/usecase` | Operaciones de aplicacion. |
| `application/port/out` | Contratos requeridos por los casos de uso. |
| `domain` | Entidades, enums, invariantes y valores. |
| `infrastructure/persistence/postgres` | Entidades JPA, repositorios, mappers y adaptadores. |
| `infrastructure/integration` | Implementacion de colaboraciones externas o entre modulos. |
| `bootstrap` | Ensamblaje/configuracion propia del modulo. |

`shared/security` contiene Resource Server, validadores JWT y conversion de autoridades. `shared/tenancy` contiene la resolucion uniforme del tenant. Ninguna de estas carpetas debe absorber reglas de dominio.

## Recursos

| Ruta | Contenido |
|---|---|
| `src/main/resources/application.yml` | Configuracion principal. |
| `src/main/resources/application-docker.yml` | Overrides del perfil Docker. |
| `src/main/resources/db/migration` | Migraciones Flyway `V1` a `V31`. |

## Pruebas

`src/test/java` replica los paquetes funcionales y contiene pruebas unitarias, integracion, seguridad y arquitectura. `architecture/ModularMonolithArchitectureTest` protege los limites entre modulos.

## Documentacion

El indice canonico es [DOCUMENTACION-PEOPLE-SCHOOL.md](DOCUMENTACION-PEOPLE-SCHOOL.md). Cada carpeta documenta una preocupacion con un nombre descriptivo: `api`, `architecture`, `database`, `domain`, `security`, `integrations`, `operations`, `testing`, `decisions` y `runbooks`.

## Regla de mantenimiento

Una nueva capacidad debe actualizar su caso de uso, adaptadores, pruebas y documentacion correspondiente. Una nueva tabla se agrega con una migracion inmutable; un nuevo endpoint se agrega al catalogo de API y al OpenAPI generado.
