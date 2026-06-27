# Seguridad, JWT y tenants

## Responsabilidad

`people-school-service` consume access tokens de IAM como OAuth2 Resource Server. No autentica contrasenas, no renueva tokens y no realiza logout: esas operaciones pertenecen a IAM y al cliente. Este servicio decide si un access token es valido y si sus autoridades permiten cada endpoint.

## Validacion del token

El decoder usa HMAC SHA-256 (`HS256`) con el secreto configurado. La cadena valida:

1. firma criptografica;
2. fechas estandar, incluida expiracion;
3. issuer exacto (`IAM_JWT_ISSUER`);
4. `token_type=access`, evitando usar refresh tokens como bearer;
5. claims obligatorios `jti`, `sub` y `tenant_id`;
6. revocacion del `jti` en Redis cuando esta habilitada.

Los scopes estandar se convierten a autoridades `SCOPE_<scope>` y cada entrada de `roles` a `ROLE_<role>`. El principal preferido es el claim `username`, con fallback al subject del JWT.

## Claims esperados

| Claim | Uso |
|---|---|
| `iss` | Debe coincidir con el issuer configurado. |
| `sub` | Identidad IAM estable. |
| `username` | Nombre del principal para Spring Security. |
| `jti` | Identificador usado para comprobar revocacion. |
| `tenant_id` | Tenant efectivo normal. Debe ser UUID. |
| `token_type` | Debe ser `access`. |
| `roles` | Lista de roles, incluido `ADMIN_GLOBAL` cuando corresponde. |
| `scope`/`scp` | Permisos `people-school.*` y `operations.read`. |

## Matriz de permisos

| Autoridad | Acceso |
|---|---|
| `ROLE_ADMIN_GLOBAL` | Todas las APIs y metricas; puede seleccionar tenant. |
| `SCOPE_people-school.read` | GET funcional no sensible. |
| `SCOPE_people-school.write` | POST, PUT, PATCH y DELETE funcional no sensible. |
| `SCOPE_people-school.sensitive.read` | GET de perfil y coberturas sensibles. |
| `SCOPE_people-school.sensitive.write` | POST/PUT de perfil y coberturas sensibles. |
| `SCOPE_operations.read` | Prometheus. |

Los endpoints de salud, info, OpenAPI, Swagger y error son publicos. Cualquier ruta no declarada se deniega.

## Resolucion de tenant

`CurrentTenantResolver` aplica estas reglas:

1. `tenant_id` siempre debe existir y ser UUID valido.
2. Un usuario ordinario opera exclusivamente sobre el tenant de su token; `X-Tenant-Id` no lo cambia.
3. `ADMIN_GLOBAL` puede enviar `X-Tenant-Id` con otro UUID.
4. Si el administrador global omite el header, se usa el tenant del token, normalmente el tenant global `00000000-0000-0000-0000-000000000000`.

Ejemplo de operacion global sobre un colegio:

```http
GET /api/v1/persons HTTP/1.1
Authorization: Bearer <access-token-admin-global>
X-Tenant-Id: 7d8146a1-e725-4ed9-907d-4bd1438180db
```

## Revocacion

El validador consulta Redis mediante el patron configurado en `people-school.security.jwt.revocation-key-prefix`. Debe usar el mismo convenio de clave y el mismo Redis que IAM. Si la comprobacion se marca como obligatoria y Redis no esta disponible, el token se rechaza; esa politica evita aceptar una sesion potencialmente revocada durante una falla.

## CORS y CSRF

La API es stateless y deshabilita CSRF. CORS permite actualmente todos los origenes, headers y metodos soportados, sin credenciales de navegador. En produccion conviene restringir `allowedOriginPatterns` a los frontends desplegados.

## Secretos

- No versionar `IAM_JWT_SECRET` ni credenciales de PostgreSQL/Redis.
- El secreto HS256 debe coincidir con IAM y tener longitud criptografica suficiente.
- Rotar el secreto exige coordinacion entre emisor y consumidores. Una futura migracion a firma asimetrica/JWKS reduciria el reparto de secretos.
