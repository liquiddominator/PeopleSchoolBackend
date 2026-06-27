# Runbooks

## Servicio no inicia

1. Revisar que Java 21 este activo: `java -version`.
2. Confirmar acceso a PostgreSQL y credenciales JDBC.
3. Revisar el error Flyway y `flyway_schema_history`.
4. Confirmar que `core-platform` esta disponible en Maven local si se ejecuta fuera de Docker.
5. Si falla Redis y la revocacion es obligatoria, restaurar Redis antes de aceptar trafico.

## API devuelve 401

1. Comprobar que se envia un access token, no refresh token.
2. Verificar expiracion, issuer y que IAM/servicio compartan secreto HS256.
3. Confirmar claims `jti`, `sub`, `tenant_id` y `token_type=access`.
4. Buscar el `jti` en el namespace de revocacion Redis.

## API devuelve 403

1. Para GET, verificar `people-school.read`; para escritura, `people-school.write`.
2. Para datos sensibles, usar los scopes `people-school.sensitive.*`.
3. Solo `ADMIN_GLOBAL` puede operar con `X-Tenant-Id` distinto.
4. Confirmar que `tenant_id` y el header, si aplica, sean UUID validos.

## Flyway falla

1. Detener despliegues paralelos.
2. Identificar la version fallida sin borrar el historial a ciegas.
3. Comparar la DB con el SQL individual de esa version.
4. Corregir mediante una migracion posterior cuando la version ya fue compartida.
5. Validar creacion limpia y actualizacion desde la version previa antes de redesplegar.

## Diagnostico de datos cruzados

1. Registrar `sub`, `jti`, tenant del token y `X-Tenant-Id` sin registrar el token completo.
2. Confirmar si el principal tiene `ADMIN_GLOBAL`.
3. Consultar el registro con filtro explicito por `tenant_id`.
4. Revisar el repositorio/adaptador responsable; toda consulta debe incluir tenant.
5. Tratar cualquier exposicion entre tenants como incidente de seguridad.

## Actualizar documentacion

Cuando cambien controladores, DTO, seguridad o migraciones, actualizar en el mismo cambio `docs/api`, `docs/security`, `docs/database` y el TXT consolidado correspondiente. `/v3/api-docs` sirve para contrastar la documentacion manual con el runtime.
