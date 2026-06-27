# Levantamiento de People School Service

## URLs y puertos

| Recurso | URL o puerto |
|---|---|
| API | `http://localhost:8082` |
| Swagger UI | `http://localhost:8082/swagger-ui.html` |
| OpenAPI JSON | `http://localhost:8082/v3/api-docs` |
| Salud | `http://localhost:8082/actuator/health` |
| Metricas Prometheus | `http://localhost:8082/actuator/prometheus` |
| PostgreSQL por Docker | `localhost:5434` |
| Redis de IAM | `localhost:6379` |

Todas las rutas `/api/v1/**` requieren un access token de IAM. El servicio valida el mismo issuer, secreto y registro de revocacion que IAM.

## Requisitos

- Java 21 y Docker Desktop.
- `core-platform` instalado localmente si se ejecuta con Maven.
- IAM levantado en `http://localhost:8080` y su Redis disponible.
- Puertos libres `8082` y `5434`.

## Opcion recomendada: Docker

Desde la raiz `PeopleSchoolBackend`, primero levantar IAM:

```powershell
cd .\iam-service
docker compose up -d --build
```

Luego levantar People School. El compose usa un volumen externo heredado; crearlo una sola vez si aun no existe:

```powershell
docker volume create person-registry-service_person-registry-postgres-data
cd ..\people-school-service
docker compose up -d --build
```

Comprobar el arranque:

```powershell
docker compose ps
Invoke-RestMethod http://localhost:8082/actuator/health
```

El contenedor escucha internamente en `8080`, pero Docker lo publica como `8082` en el host. PostgreSQL se publica como `5434`.

## Opcion local con Maven

Instalar primero la libreria comun:

```powershell
cd ..\core-plataform
.\mvnw.cmd install
```

Levantar IAM y Redis antes de continuar. Para usar el PostgreSQL del compose sin ejecutar el contenedor de la aplicacion:

```powershell
cd ..\people-school-service
docker compose up -d people-school-postgres
$env:PEOPLE_SCHOOL_DATASOURCE_URL='jdbc:postgresql://localhost:5434/person_registry'
$env:PEOPLE_SCHOOL_DATASOURCE_USERNAME='person_registry'
$env:PEOPLE_SCHOOL_DATASOURCE_PASSWORD='person_registry'
$env:PEOPLE_SCHOOL_REDIS_HOST='localhost'
$env:PEOPLE_SCHOOL_REDIS_PORT='6379'
$env:IAM_JWT_ISSUER='iam-service'
$env:IAM_JWT_SECRET='dev-secret-key-change-me-please-32-bytes-minimum'
.\mvnw.cmd spring-boot:run
```

Sin el override JDBC, `application.yml` intenta conectarse a PostgreSQL en `localhost:5432`.

## Prueba autenticada

Obtener un token en IAM y consultar una ruta protegida:

```powershell
$login = Invoke-RestMethod `
  -Uri 'http://localhost:8080/api/v1/auth/login' `
  -Method Post `
  -ContentType 'application/json' `
  -Body '{"username":"admin.global","password":"<password>"}'

$headers = @{ Authorization = "Bearer $($login.accessToken)" }
Invoke-RestMethod 'http://localhost:8082/api/v1/persons' -Headers $headers
```

Usar el nombre real del campo de access token publicado por IAM si el response cambia. Para administrar otro tenant con `ADMIN_GLOBAL`, agregar `X-Tenant-Id` al header.

## Detener

```powershell
docker compose down
```

No agregar `-v` salvo que se quiera borrar deliberadamente la informacion persistida.

## Conflicto con AI Service

AI y People School publican PostgreSQL en `5434` por defecto. Al levantarlos juntos, conservar `5434` para People School y arrancar PostgreSQL de AI en `5435`, como se explica en la guia de AI.
