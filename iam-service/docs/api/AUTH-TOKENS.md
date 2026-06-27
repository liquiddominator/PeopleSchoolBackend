# IAM Auth and Token Guide

Esta guia describe el contrato de autenticacion del `iam-service` y como debe integrarse un cliente frontend.

## Configuracion por defecto

La configuracion vive en `src/main/resources/application.yml`.

| Propiedad | Valor por defecto | Descripcion |
| --- | --- | --- |
| `security.jwt.enabled` | `true` | Activa proteccion JWT para endpoints privados. |
| `security.token.issuer` | `iam-service` | Issuer esperado en los JWT. |
| `security.token.access-token-minutes` | `15` | TTL del access token. |
| `security.token.refresh-token-days` | `7` | TTL del refresh token. |
| `iam.tenant-header` | `X-Tenant-Id` | Header usado para resolver tenant en operaciones multi-tenant. |

## Tipos de token

El servicio emite dos JWT firmados con HS256:

- `accessToken`: se envia en `Authorization: Bearer <accessToken>` para acceder a endpoints protegidos.
- `refreshToken`: se envia solo al endpoint `POST /api/v1/auth/refresh` para obtener un nuevo par de tokens.

Claims relevantes del access token:

```json
{
  "sub": "1",
  "iss": "iam-service",
  "token_type": "access",
  "tenant_id": "00000000-0000-0000-0000-000000000000",
  "username": "admin.global",
  "roles": ["ADMIN_GLOBAL"],
  "scope": "iam.users.read iam.users.write",
  "scopes": ["GLOBAL"]
}
```

Claims relevantes del refresh token:

```json
{
  "sub": "1",
  "iss": "iam-service",
  "token_type": "refresh",
  "tenant_id": "00000000-0000-0000-0000-000000000000"
}
```

## Login

`POST /api/v1/auth/login` valida usuario y password dentro del tenant indicado por `X-Tenant-Id`.

Request:

```http
POST /api/v1/auth/login
Content-Type: application/json
X-Tenant-Id: 00000000-0000-0000-0000-000000000000

{
  "username": "admin.global",
  "password": "password"
}
```

Response `200`:

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

## Refresh

`POST /api/v1/auth/refresh` rota el refresh token. El token usado queda revocado y la respuesta trae un nuevo `accessToken` y un nuevo `refreshToken`.

Request:

```http
POST /api/v1/auth/refresh
Content-Type: application/json

{
  "refreshToken": "<jwt-refresh>"
}
```

Response `200`: mismo formato que login.

Errores comunes:

- `401 IAM_INVALID_REFRESH_TOKEN`: refresh token invalido o revocado.
- `401 IAM_REFRESH_TOKEN_EXPIRED`: refresh token expirado.

## Logout

`POST /api/v1/auth/logout` revoca el refresh token indicado y registra el access token actual como revocado hasta su expiracion natural.

Request:

```http
POST /api/v1/auth/logout
Content-Type: application/json
Authorization: Bearer <jwt-access>

{
  "refreshToken": "<jwt-refresh>"
}
```

Response `204`: sin body.

## Current User

`GET /api/v1/auth/me` devuelve datos del usuario autenticado.

Request:

```http
GET /api/v1/auth/me
Authorization: Bearer <jwt-access>
```

Response `200`:

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

## Change Password

`POST /api/v1/auth/change-password` cambia el password del usuario autenticado.

Request:

```http
POST /api/v1/auth/change-password
Content-Type: application/json
Authorization: Bearer <jwt-access>

{
  "currentPassword": "old-password",
  "newPassword": "new-password"
}
```

Response `204`: sin body.

Errores comunes:

- `400 VALIDATION_ERROR`: `newPassword` no cumple validaciones.
- `400 IAM_PASSWORD_REUSE_NOT_ALLOWED`: el password nuevo es igual al actual.
- `401 IAM_INVALID_CURRENT_PASSWORD`: password actual incorrecto.

## Reglas para clientes frontend

1. Guardar el `accessToken`, `refreshToken`, `tenantId` y `expiresIn` devueltos por login/refresh.
2. Enviar `Authorization: Bearer <accessToken>` en todos los endpoints protegidos.
3. Enviar `X-Tenant-Id` en endpoints multi-tenant cuando el backend lo requiera o cuando el usuario pueda operar mas de un tenant.
4. Si cualquier endpoint protegido responde `401`, intentar una sola llamada a `POST /api/v1/auth/refresh`.
5. Si el refresh responde `200`, reemplazar ambos tokens y reintentar la request original.
6. Si el refresh responde `401`, limpiar sesion local y redirigir a login.
7. No usar el refresh token como Bearer token.
8. Tratar `403` como falta de permisos, no como sesion expirada.

## Respuesta de error estandar

```json
{
  "errorCode": "UNAUTHORIZED",
  "timestamp": "2026-06-23T10:30:00Z",
  "path": "/api/v1/auth/me",
  "details": null,
  "correlationId": null
}
```
