# Levantamiento de AI Service

## Estado del despliegue local

AI Service es un proyecto Maven multimodulo. `docker-compose.yml` levanta solamente PostgreSQL con pgvector; la aplicacion se ejecuta localmente desde `ai-bootstrap`.

## URLs y puertos

| Recurso | URL o puerto |
|---|---|
| API | `http://localhost:8091` |
| Swagger UI en perfil dev | `http://localhost:8091/swagger-ui.html` |
| OpenAPI JSON en perfil dev | `http://localhost:8091/v3/api-docs` |
| Salud | `http://localhost:8091/actuator/health` |
| Informacion | `http://localhost:8091/actuator/info` |
| PostgreSQL/pgvector aislado | `localhost:5434` |
| PostgreSQL junto a People School | `localhost:5435` recomendado |

## Requisitos

- Java 21 y Docker Desktop.
- Puertos `8091` y el puerto elegido para PostgreSQL libres.
- `OPENAI_API_KEY` solamente si se activa el perfil `openai`.

## Levantar PostgreSQL

Si People School no esta activo:

```powershell
docker compose up -d postgres
```

Si People School ya usa `5434`, publicar AI en `5435`:

```powershell
$env:AI_PG_HOST_PORT='5435'
docker compose up -d postgres
$env:AI_DB_PORT='5435'
```

El contenedor crea la base `ai_service` con usuario/clave `postgres` por defecto y ejecuta los scripts de `docker/postgres/init` solo al inicializar el volumen.

## Compilar y ejecutar

Desde `PeopleSchoolBackend/ai-service`:

```powershell
.\mvnw.cmd clean package
java -jar .\modules\ai-bootstrap\target\ai-bootstrap-1.0.0-SNAPSHOT.jar --spring.profiles.active=dev
```

Comprobar:

```powershell
Invoke-RestMethod http://localhost:8091/actuator/health
```

Tambien puede ejecutarse con Maven una vez compilados los modulos:

```powershell
.\mvnw.cmd -pl modules/ai-bootstrap spring-boot:run -Dspring-boot.run.profiles=dev
```

## Activar OpenAI

```powershell
$env:OPENAI_API_KEY='<api-key>'
java -jar .\modules\ai-bootstrap\target\ai-bootstrap-1.0.0-SNAPSHOT.jar --spring.profiles.active=dev,openai
```

Sin el perfil `openai`, los modelos chat y embedding permanecen deshabilitados. El servicio puede arrancar y exponer salud/documentacion, pero las operaciones que requieren modelo no tendran proveedor OpenAI activo.

## Seguridad actual

Salud e info son publicos. Swagger/OpenAPI son publicos solo en `dev`; el resto requiere Bearer JWT por la cadena de Spring Security. La configuracion dev apunta actualmente a `http://localhost:8080/realms/solveria` como `issuer-uri`, que presupone un proveedor OIDC con discovery. El IAM propio usa issuer `iam-service`, por lo que esa integracion debe alinearse antes de considerar que los endpoints AI aceptan directamente sus tokens.

## Endpoints funcionales actuales

- `POST /api/v1/ai/complete`
- `POST /api/v1/ai/rag/qa`

## Detener

Detener la aplicacion con `Ctrl+C` y PostgreSQL con:

```powershell
docker compose down
```
