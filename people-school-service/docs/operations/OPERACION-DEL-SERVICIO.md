# Operacion del servicio

## Requisitos

- Java 21.
- Maven 3.8.1 o superior, o Maven Wrapper incluido.
- PostgreSQL accesible.
- Redis accesible cuando la revocacion JWT esta habilitada.
- Artefacto local `com.solveria:core-platform:1.0.0-SNAPSHOT` instalado; el build Docker lo instala desde `core-plataform`.

## Ejecucion

Desde `people-school-service`:

```powershell
.\mvnw.cmd spring-boot:run
```

Con el stack Docker, desde el mismo directorio:

```powershell
docker compose up --build
```

El puerto HTTP por defecto es `8082`. Docker publica PostgreSQL en el puerto documentado por `docker-compose.yml`.

## Configuracion principal

| Variable | Proposito | Valor local por defecto |
|---|---|---|
| `PEOPLE_SCHOOL_DATASOURCE_URL` | JDBC PostgreSQL | `jdbc:postgresql://localhost:5432/person_registry` |
| `PEOPLE_SCHOOL_DATASOURCE_USERNAME` | Usuario DB | Configuracion de `application.yml` |
| `PEOPLE_SCHOOL_DATASOURCE_PASSWORD` | Clave DB | Configuracion de `application.yml` |
| `PEOPLE_SCHOOL_REDIS_HOST` | Host Redis | `localhost` |
| `PEOPLE_SCHOOL_REDIS_PORT` | Puerto Redis | `6379` |
| `IAM_JWT_SECRET` | Secreto HS256 compartido con IAM | Solo desarrollo si existe default |
| `IAM_JWT_ISSUER` | Issuer exacto de IAM | Configuracion local |
| `SERVER_PORT` | Puerto HTTP | `8082` |

Consultar `application.yml` y `application-docker.yml` para nombres adicionales y valores efectivos. En produccion, inyectar secretos desde el gestor de secretos de la plataforma.

## Salud y observabilidad

- `/actuator/health`: salud publica para probes.
- `/actuator/info`: informacion publica habilitada.
- `/actuator/metrics`: capacidades de Micrometer segun exposicion configurada.
- `/actuator/prometheus`: metricas protegidas con `operations.read` o admin global.
- Logs de SQL y niveles de aplicacion se controlan mediante configuracion Spring.

## Inicio y migraciones

Al arrancar, Flyway valida el historial y ejecuta migraciones pendientes antes de habilitar el servicio. Una migracion fallida debe resolverse en la base y en el SQL correspondiente; nunca editar una migracion que ya se aplico en ambientes compartidos.

## Cierre

La aplicacion es stateless respecto de sesiones HTTP. El cierre ordenado debe permitir finalizar requests y conexiones. PostgreSQL conserva el negocio y Redis solo participa en la validacion de revocacion.
