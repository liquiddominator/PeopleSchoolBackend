# Levantamiento de IAM Service

## URLs y puertos

| Recurso | URL o puerto |
|---|---|
| API | `http://localhost:8080` |
| Swagger UI | `http://localhost:8080/swagger-ui.html` |
| OpenAPI JSON | `http://localhost:8080/v3/api-docs` |
| Salud | `http://localhost:8080/actuator/health` |
| Prometheus | `http://localhost:8080/actuator/prometheus` |
| PostgreSQL Docker | `localhost:5432` |
| Redis Docker | `localhost:6379` |

## Requisitos

- Docker Desktop para el stack recomendado.
- Java 21 para ejecucion local.
- Puertos `8080`, `5432` y `6379` libres.
- Acceso al proyecto hermano `core-plataform`, requerido durante el build.

## Opcion recomendada: Docker

Desde `PeopleSchoolBackend/iam-service`:

```powershell
docker compose up -d --build
docker compose ps
```

El compose construye `core-platform` e IAM, inicia PostgreSQL 16 y Redis 7, y aplica las migraciones Flyway. Verificar:

```powershell
Invoke-RestMethod http://localhost:8080/actuator/health
```

Ver logs si el estado no es saludable:

```powershell
docker compose logs -f iam-service
```

## Login de desarrollo

Las migraciones crean `admin.global` en el tenant global. El ejemplo documentado usa la credencial inicial `password`; debe cambiarse fuera del entorno local.

```powershell
$headers = @{ 'X-Tenant-Id' = '00000000-0000-0000-0000-000000000000' }
$login = Invoke-RestMethod `
  -Uri 'http://localhost:8080/api/v1/auth/login' `
  -Method Post `
  -Headers $headers `
  -ContentType 'application/json' `
  -Body '{"username":"admin.global","password":"password"}'

$login.accessToken
```

## Opcion local con Maven

Instalar primero `core-platform`:

```powershell
cd ..\core-plataform
.\mvnw.cmd install
cd ..\iam-service
.\mvnw.cmd spring-boot:run
```

El perfil predeterminado `dev` usa H2 en memoria, deshabilita Flyway y no carga el usuario bootstrap de PostgreSQL. Sirve para arrancar y desarrollar, pero el flujo completo de login debe probarse con Docker o con un perfil PostgreSQL preparado.

Para ejecutar el JAR:

```powershell
.\mvnw.cmd clean package
java -jar .\target\iam-service-1.0.0-SNAPSHOT.jar
```

## Variables principales

| Variable | Uso | Docker local |
|---|---|---|
| `IAM_DB_URL` | JDBC PostgreSQL | `jdbc:postgresql://iam-postgres:5432/iam_service` |
| `IAM_DB_USERNAME` | Usuario DB | `iam_user` |
| `IAM_DB_PASSWORD` | Clave DB | `iam_password` |
| `IAM_REDIS_HOST` | Host Redis | `iam-redis` |
| `IAM_REDIS_PORT` | Puerto Redis | `6379` |
| `IAM_JWT_ISSUER` | Issuer de tokens | `iam-service` |
| `IAM_JWT_SECRET` | Secreto HS256 compartido | Valor local del compose |
| `IAM_ACCESS_TOKEN_MINUTES` | Vida del access token | `15` |
| `IAM_REFRESH_TOKEN_DAYS` | Vida del refresh token | `7` |

People School debe usar exactamente el mismo issuer, secreto y Redis para validar y revocar access tokens.

## Detener

```powershell
docker compose down
```

Para conservar usuarios y sesiones, no utilizar `docker compose down -v`. El flag `-v` elimina los volumenes de PostgreSQL y Redis.
