# Frontend Integration Guide

Esta guia resume como un frontend debe consumir `iam-service` y otros servicios que validan el JWT emitido por IAM.

## Estado minimo de sesion

Guardar despues de `POST /api/v1/auth/login` y de cada `POST /api/v1/auth/refresh`:

```ts
type SessionState = {
  accessToken: string;
  refreshToken: string;
  tokenType: "Bearer";
  expiresAt: number;
  refreshExpiresAt: number;
  tenantId: string;
  userId: number;
  username: string;
  roles: string[];
  scopes: string[];
};
```

`expiresAt` debe calcularse con `Date.now() + expiresIn * 1000`.

## Headers

Para IAM protegido:

```http
Authorization: Bearer <accessToken>
Content-Type: application/json
```

Para endpoints multi-tenant:

```http
Authorization: Bearer <accessToken>
X-Tenant-Id: <tenantId>
Content-Type: application/json
```

Para servicios externos que validan el JWT de IAM, por ejemplo `people-school-service`, enviar siempre el mismo `Authorization`.

## Algoritmo de refresh recomendado

1. Antes de una request protegida, si el access token expira en menos de 60 segundos, intentar refresh.
2. Si una request protegida responde `401`, intentar refresh una sola vez.
3. Si refresh devuelve `200`, guardar el nuevo access token y el nuevo refresh token.
4. Reintentar la request original con el access token nuevo.
5. Si refresh devuelve `401`, limpiar sesion y enviar al usuario a login.
6. Si una request responde `403`, mostrar falta de permisos. No refrescar por `403`.

Pseudocodigo:

```ts
async function authenticatedRequest(input: RequestInfo, init: RequestInit = {}) {
  let response = await fetch(input, withAccessToken(init));

  if (response.status !== 401) {
    return response;
  }

  const refreshed = await refreshOnce();
  if (!refreshed) {
    clearSession();
    redirectToLogin();
    throw new Error("Session expired");
  }

  response = await fetch(input, withAccessToken(init));
  return response;
}
```

## Concurrencia

Si varias requests reciben `401` al mismo tiempo, el frontend debe compartir una sola promesa de refresh. Esto evita usar dos veces el mismo refresh token, porque IAM rota y revoca el refresh token usado.

Ejemplo conceptual:

```ts
let refreshPromise: Promise<boolean> | null = null;

async function refreshOnce(): Promise<boolean> {
  if (!refreshPromise) {
    refreshPromise = refresh().finally(() => {
      refreshPromise = null;
    });
  }
  return refreshPromise;
}
```

## Importante para servicios secundarios

No excluir servicios secundarios del flujo de refresh.

Si `people-school-service` responde `401` porque el access token expiro, el frontend debe refrescar con IAM y reintentar la request original.

Separacion recomendada:

- `401`: token ausente, invalido o expirado. Intentar refresh.
- `403`: token valido, pero sin permisos. No intentar refresh.
