# IAM Database

Esta carpeta documenta la base PostgreSQL definida por las migraciones de Flyway del `iam-service`.

- `iam_postgresql_schema.txt`: esquema consolidado de tablas, indices y datos bootstrap. Es una referencia de lectura; las migraciones en `src/main/resources/db/migration` siguen siendo la fuente ejecutable.

## Modelo Principal

`iam_user` guarda usuarios por `tenant_id`. Las credenciales BCrypt viven en `iam_user_credential`, separadas del perfil de usuario. `iam_refresh_token` persiste refresh tokens emitidos y permite rotarlos o revocarlos.

`iam_role` define roles por tenant. Incluye `hierarchy_level` para controlar asignaciones jerarquicas y `parent_role_id` para futuras relaciones entre roles. La tabla `iam_user_roles` conecta usuarios con roles.

`iam_module`, `iam_resource`, `iam_action` e `iam_field` forman el catalogo de permisos. `iam_permission` asigna una combinacion de modulo, recurso, accion y campo opcional a un rol.

`iam_branch` representa sedes o unidades operativas dentro de un tenant. `iam_user_scope` indica si un usuario opera globalmente o limitado a branches.

`audit_log` registra eventos de auditoria. Mantiene columnas historicas (`event_type`, `entity_type`, `timestamp`) y columnas alineadas al modelo actual (`action`, `entity`, `occurred_at`).

## Bootstrap

Las migraciones crean un tenant global con id `00000000-0000-0000-0000-000000000000`.

Ese tenant recibe:

- Rol `ADMIN_GLOBAL` con `hierarchy_level = 0`.
- Usuario `admin.global`.
- Credencial inicial hasheada.
- Scope `GLOBAL`.
- Permisos completos sobre los recursos IAM actuales:
  - `iam.users.read`
  - `iam.users.write`
  - `iam.roles.read`
  - `iam.roles.write`
  - `iam.permissions.read`
  - `iam.permissions.write`
  - `iam.branches.read`
  - `iam.branches.write`
  - `people-school.read`
  - `people-school.write`
  - `people-school.sensitive.read`
  - `people-school.sensitive.write`
  - `operations.read`

## Notas De Diseno

Los permisos se almacenan como filas normalizadas para que puedan consultarse y administrarse desde la API. Al emitir tokens, el servicio transforma cada permiso en scopes OAuth usando el formato `<module>.<action>`.

El rol `ADMIN_GLOBAL` tambien se reconoce por nombre en la capa de seguridad para garantizar acceso administrativo aunque el catalogo de permisos este evolucionando. La migracion de permisos mantiene la base alineada con ese comportamiento.
