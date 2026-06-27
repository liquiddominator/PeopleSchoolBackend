# IAM Service REST API

Base URL local por defecto: `http://localhost:8080`

La API esta protegida con JWT cuando `security.jwt.enabled=true`. Los endpoints publicos son:

- `POST /api/v1/auth/login`
- `POST /api/v1/auth/refresh`
- Actuator health/info
- OpenAPI/Swagger

Todos los demas endpoints requieren:

```http
Authorization: Bearer <accessToken>
```

Los endpoints multi-tenant pueden requerir:

```http
X-Tenant-Id: 00000000-0000-0000-0000-000000000000
```

Para el flujo de tokens ver [AUTH-TOKENS.md](./AUTH-TOKENS.md).

## Endpoints

| Metodo | Path | Auth | Descripcion |
| --- | --- | --- | --- |
| `POST` | `/api/v1/auth/login` | No | Autentica usuario y emite access/refresh tokens. |
| `POST` | `/api/v1/auth/refresh` | No | Rota refresh token y emite un nuevo par de tokens. |
| `GET` | `/api/v1/auth/me` | Si | Devuelve el usuario autenticado. |
| `POST` | `/api/v1/auth/change-password` | Si | Cambia el password del usuario autenticado. |
| `POST` | `/api/v1/auth/logout` | Si | Revoca refresh token y access token actual. |
| `GET` | `/api/v1/users` | Si | Lista usuarios visibles. |
| `GET` | `/api/v1/users/{id}` | Si | Obtiene un usuario por ID. |
| `POST` | `/api/v1/users` | Si | Crea usuario, credencial, roles y scopes. |
| `PUT` | `/api/v1/users/{id}` | Si | Actualiza usuario, roles y scopes. |
| `GET` | `/api/v1/roles` | Si | Lista roles visibles. |
| `POST` | `/api/v1/roles` | Si | Crea un rol. |
| `GET` | `/api/v1/permissions` | Si | Lista permisos visibles. |
| `GET` | `/api/v1/branches` | Si | Lista branches del tenant. |
| `POST` | `/api/v1/branches` | Si | Crea un branch. |
| `PUT` | `/api/v1/roles/{roleId}/permissions` | Si | Asigna permisos a un rol. |

## Auth

### POST /api/v1/auth/login

Headers:

| Header | Requerido | Descripcion |
| --- | --- | --- |
| `Content-Type: application/json` | Si | Body JSON. |
| `X-Tenant-Id` | Si | Tenant donde se autentica el usuario. |

Body:

```json
{
  "username": "admin.global",
  "password": "password"
}
```

Response `200`: `AuthTokenResponse`

```json
{
  "accessToken": "<jwt-access>",
  "refreshToken": "<jwt-refresh>",
  "tokenType": "Bearer",
  "expiresIn": 900,
  "refreshExpiresIn": 604800,
  "tenantId": "00000000-0000-0000-0000-000000000000",
  "userId": 1,
  "username": "admin.global",
  "roles": ["ADMIN_GLOBAL"],
  "scopes": ["GLOBAL"]
}
```

Errores:

- `400 IAM_TENANT_REQUIRED`
- `400 IAM_TENANT_INVALID`
- `401 IAM_INVALID_CREDENTIALS`
- `401 IAM_USER_INACTIVE`
- `429` por rate limit

### POST /api/v1/auth/refresh

Body:

```json
{
  "refreshToken": "<jwt-refresh>"
}
```

Response `200`: `AuthTokenResponse`.

Errores:

- `400 VALIDATION_ERROR`
- `401 IAM_INVALID_REFRESH_TOKEN`
- `401 IAM_REFRESH_TOKEN_EXPIRED`
- `429` por rate limit

### GET /api/v1/auth/me

Headers:

```http
Authorization: Bearer <accessToken>
```

Response `200`: `AuthMeResponse`

```json
{
  "userId": 1,
  "username": "admin.global",
  "email": "admin@example.com",
  "active": true,
  "tenantId": "00000000-0000-0000-0000-000000000000",
  "roles": ["ADMIN_GLOBAL"],
  "scopes": ["GLOBAL"]
}
```

### POST /api/v1/auth/change-password

Headers:

```http
Authorization: Bearer <accessToken>
Content-Type: application/json
```

Body:

```json
{
  "currentPassword": "old-password",
  "newPassword": "new-password"
}
```

Validaciones:

- `currentPassword` requerido.
- `newPassword` requerido.
- `newPassword` debe tener entre 8 y 120 caracteres.
- `newPassword` debe ser distinto al password actual.

Response `204`: sin body.

### POST /api/v1/auth/logout

Headers:

```http
Authorization: Bearer <accessToken>
Content-Type: application/json
```

Body:

```json
{
  "refreshToken": "<jwt-refresh>"
}
```

Response `204`: sin body.

## Users

### GET /api/v1/users

Lista usuarios visibles para el actor autenticado.

Headers:

| Header | Requerido | Descripcion |
| --- | --- | --- |
| `Authorization` | Si | `Bearer <accessToken>`. |
| `X-Tenant-Id` | Depende | Tenant para filtrar. Global admins pueden omitirlo. |

Response `200`:

```json
[
  {
    "id": 1,
    "username": "admin.global",
    "email": "admin@example.com",
    "active": true,
    "tenantId": "00000000-0000-0000-0000-000000000000",
    "roleIds": [1],
    "scopes": [
      {
        "scopeType": "GLOBAL",
        "branchId": null,
        "branchCode": null
      }
    ]
  }
]
```

### GET /api/v1/users/{id}

Path params:

| Param | Descripcion |
| --- | --- |
| `id` | ID del usuario. |

Response `200`: `UserResponse`.

Errores:

- `401 UNAUTHORIZED`
- `403 FORBIDDEN`
- `404` usuario no encontrado

### POST /api/v1/users

Crea un usuario con credencial, roles y scopes.

Body:

```json
{
  "username": "jane.admin",
  "email": "jane@example.com",
  "password": "password-123",
  "tenantId": "00000000-0000-0000-0000-000000000000",
  "active": true,
  "roleIds": [1],
  "scopeType": "GLOBAL",
  "branchIds": []
}
```

Validaciones:

- `username`: requerido, 3 a 100 caracteres.
- `email`: requerido, formato email.
- `password`: requerido, 8 a 72 caracteres.
- `roleIds`: requerido, no vacio, maximo 50 roles.
- `scopeType`: requerido. Valores esperados por dominio: `GLOBAL` o `BRANCH`.
- `branchIds`: maximo 100 branches.

Response `201`: `UserResponse`.

### PUT /api/v1/users/{id}

Actualiza email, estado, roles y scopes.

Body:

```json
{
  "email": "jane.updated@example.com",
  "active": true,
  "roleIds": [1, 2],
  "scopeType": "BRANCH",
  "branchIds": [10, 11]
}
```

Todos los campos son opcionales, pero las validaciones aplican si vienen presentes.

Response `200`: `UserResponse`.

## Roles

### GET /api/v1/roles

Lista roles visibles.

Headers:

- `Authorization: Bearer <accessToken>`
- `X-Tenant-Id` opcional segun el actor

Response `200`:

```json
[
  {
    "id": 1,
    "name": "ADMIN_GLOBAL",
    "description": "Global administrator",
    "tenantId": "00000000-0000-0000-0000-000000000000",
    "permissionIds": [1, 2],
    "hierarchyLevel": 100,
    "parentRoleId": null
  }
]
```

### POST /api/v1/roles

Body:

```json
{
  "name": "COORDINATOR",
  "description": "School coordinator",
  "tenantId": "00000000-0000-0000-0000-000000000000",
  "hierarchyLevel": 50,
  "parentRoleId": 1
}
```

Validaciones:

- `name`: requerido, 3 a 50 caracteres.
- `description`: maximo 255 caracteres.
- `tenantId`: puede venir en body o resolverse por `X-Tenant-Id`, segun permisos del actor.

Response `201`: `RoleResponse`.

## Permissions

### GET /api/v1/permissions

Lista permisos visibles.

Response `200`:

```json
[
  {
    "id": 1,
    "roleId": 1,
    "moduleId": 1,
    "resourceId": 1,
    "actionId": 1,
    "fieldId": null,
    "tenantId": "00000000-0000-0000-0000-000000000000"
  }
]
```

## Branches

### GET /api/v1/branches

Lista branches del tenant resuelto. Si el actor no esta tenant-scoped, `X-Tenant-Id` es requerido.

Response `200`:

```json
[
  {
    "id": 10,
    "tenantId": "00000000-0000-0000-0000-000000000000",
    "code": "MAIN",
    "name": "Main campus",
    "active": true
  }
]
```

### POST /api/v1/branches

Body:

```json
{
  "code": "NORTH",
  "name": "North campus",
  "tenantId": "00000000-0000-0000-0000-000000000000",
  "active": true
}
```

Validaciones:

- `code`: requerido, maximo 100 caracteres.
- `name`: requerido, maximo 100 caracteres.
- `active`: opcional. Si se omite, el servicio puede aplicar el default de dominio.

Response `201`: `BranchResponse`.

## Role Permission Assignment

### PUT /api/v1/roles/{roleId}/permissions

Asigna permisos a un rol dentro del tenant.

Headers:

```http
Authorization: Bearer <accessToken>
X-Tenant-Id: 00000000-0000-0000-0000-000000000000
Content-Type: application/json
```

Path params:

| Param | Descripcion |
| --- | --- |
| `roleId` | ID del rol a modificar. Debe ser positivo. |

Body:

```json
{
  "permissionIds": [1, 2, 3, 4]
}
```

Validaciones:

- `permissionIds`: requerido, no vacio.
- Cada ID debe ser no nulo.

Response `200`:

```json
{
  "id": 1,
  "name": "ADMIN",
  "description": "Administrator role",
  "permissionIds": [1, 2, 3, 4]
}
```

## Errores

Formato general:

```json
{
  "errorCode": "VALIDATION_ERROR",
  "timestamp": "2026-06-23T10:30:00Z",
  "path": "/api/v1/users",
  "details": {
    "username": "username is required"
  },
  "correlationId": null
}
```

Codigos HTTP comunes:

| HTTP | Uso |
| --- | --- |
| `400` | Validacion, tenant invalido, regla de negocio. |
| `401` | Token ausente, invalido, expirado, o credenciales invalidas. |
| `403` | Actor autenticado sin permisos suficientes. |
| `404` | Recurso no encontrado. |
| `429` | Rate limit en login/refresh. |
| `500` | Error inesperado. |

## Ejemplos rapidos

Login:

```powershell
$login = Invoke-RestMethod `
  -Uri "http://localhost:8080/api/v1/auth/login" `
  -Method POST `
  -ContentType "application/json" `
  -Headers @{ "X-Tenant-Id" = "00000000-0000-0000-0000-000000000000" } `
  -Body '{"username":"admin.global","password":"password"}'

$accessToken = $login.accessToken
$refreshToken = $login.refreshToken
```

Llamar endpoint protegido:

```powershell
Invoke-RestMethod `
  -Uri "http://localhost:8080/api/v1/auth/me" `
  -Method GET `
  -Headers @{ "Authorization" = "Bearer $accessToken" }
```

Refresh:

```powershell
$refresh = Invoke-RestMethod `
  -Uri "http://localhost:8080/api/v1/auth/refresh" `
  -Method POST `
  -ContentType "application/json" `
  -Body (@{ refreshToken = $refreshToken } | ConvertTo-Json)

$accessToken = $refresh.accessToken
$refreshToken = $refresh.refreshToken
```
