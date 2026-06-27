-- Flyway Migration V5: Seed permission catalog for ADMIN_GLOBAL.
-- Description: Gives the bootstrap global administrator every IAM permission currently enforced by the API.

INSERT INTO iam_module (tenant_id, code, name, version)
SELECT '00000000-0000-0000-0000-000000000000', 'iam.users', 'IAM Users', 0
WHERE NOT EXISTS (
    SELECT 1 FROM iam_module
    WHERE tenant_id = '00000000-0000-0000-0000-000000000000'
      AND code = 'iam.users'
);

INSERT INTO iam_module (tenant_id, code, name, version)
SELECT '00000000-0000-0000-0000-000000000000', 'iam.roles', 'IAM Roles', 0
WHERE NOT EXISTS (
    SELECT 1 FROM iam_module
    WHERE tenant_id = '00000000-0000-0000-0000-000000000000'
      AND code = 'iam.roles'
);

INSERT INTO iam_module (tenant_id, code, name, version)
SELECT '00000000-0000-0000-0000-000000000000', 'iam.permissions', 'IAM Permissions', 0
WHERE NOT EXISTS (
    SELECT 1 FROM iam_module
    WHERE tenant_id = '00000000-0000-0000-0000-000000000000'
      AND code = 'iam.permissions'
);

INSERT INTO iam_module (tenant_id, code, name, version)
SELECT '00000000-0000-0000-0000-000000000000', 'iam.branches', 'IAM Branches', 0
WHERE NOT EXISTS (
    SELECT 1 FROM iam_module
    WHERE tenant_id = '00000000-0000-0000-0000-000000000000'
      AND code = 'iam.branches'
);

INSERT INTO iam_module (tenant_id, code, name, version)
SELECT '00000000-0000-0000-0000-000000000000', 'operations', 'Operations', 0
WHERE NOT EXISTS (
    SELECT 1 FROM iam_module
    WHERE tenant_id = '00000000-0000-0000-0000-000000000000'
      AND code = 'operations'
);

INSERT INTO iam_module (tenant_id, code, name, version)
SELECT '00000000-0000-0000-0000-000000000000', 'person-registry', 'Person Registry', 0
WHERE NOT EXISTS (
    SELECT 1 FROM iam_module
    WHERE tenant_id = '00000000-0000-0000-0000-000000000000'
      AND code = 'person-registry'
);

INSERT INTO iam_module (tenant_id, code, name, version)
SELECT '00000000-0000-0000-0000-000000000000', 'person-registry.sensitive', 'Person Registry Sensitive Data', 0
WHERE NOT EXISTS (
    SELECT 1 FROM iam_module
    WHERE tenant_id = '00000000-0000-0000-0000-000000000000'
      AND code = 'person-registry.sensitive'
);

INSERT INTO iam_resource (tenant_id, code, name, module_id, version)
SELECT m.tenant_id, m.code, m.name, m.id, 0
FROM iam_module m
WHERE m.tenant_id = '00000000-0000-0000-0000-000000000000'
  AND m.code IN (
      'iam.users',
      'iam.roles',
      'iam.permissions',
      'iam.branches',
      'operations',
      'person-registry',
      'person-registry.sensitive'
  )
  AND NOT EXISTS (
    SELECT 1 FROM iam_resource r
    WHERE r.tenant_id = m.tenant_id
      AND r.code = m.code
);

INSERT INTO iam_action (tenant_id, code, name, version)
SELECT '00000000-0000-0000-0000-000000000000', 'read', 'Read', 0
WHERE NOT EXISTS (
    SELECT 1 FROM iam_action
    WHERE tenant_id = '00000000-0000-0000-0000-000000000000'
      AND code = 'read'
);

INSERT INTO iam_action (tenant_id, code, name, version)
SELECT '00000000-0000-0000-0000-000000000000', 'write', 'Write', 0
WHERE NOT EXISTS (
    SELECT 1 FROM iam_action
    WHERE tenant_id = '00000000-0000-0000-0000-000000000000'
      AND code = 'write'
);

INSERT INTO iam_permission (tenant_id, role_id, module_id, resource_id, action_id, field_id, version)
SELECT r.tenant_id, r.id, m.id, res.id, a.id, NULL, 0
FROM iam_role r
JOIN iam_module m
  ON m.tenant_id = r.tenant_id
 AND m.code IN (
     'iam.users',
     'iam.roles',
     'iam.permissions',
     'iam.branches',
     'person-registry',
     'person-registry.sensitive'
 )
JOIN iam_resource res
  ON res.tenant_id = r.tenant_id
 AND res.code = m.code
JOIN iam_action a
  ON a.tenant_id = r.tenant_id
 AND a.code IN ('read', 'write')
WHERE r.tenant_id = '00000000-0000-0000-0000-000000000000'
  AND LOWER(r.name) = LOWER('ADMIN_GLOBAL')
  AND NOT EXISTS (
    SELECT 1 FROM iam_permission p
    WHERE p.tenant_id = r.tenant_id
      AND p.role_id = r.id
      AND p.module_id = m.id
      AND p.resource_id = res.id
      AND p.action_id = a.id
      AND p.field_id IS NULL
);

INSERT INTO iam_permission (tenant_id, role_id, module_id, resource_id, action_id, field_id, version)
SELECT r.tenant_id, r.id, m.id, res.id, a.id, NULL, 0
FROM iam_role r
JOIN iam_module m
  ON m.tenant_id = r.tenant_id
 AND m.code = 'operations'
JOIN iam_resource res
  ON res.tenant_id = r.tenant_id
 AND res.code = m.code
JOIN iam_action a
  ON a.tenant_id = r.tenant_id
 AND a.code = 'read'
WHERE r.tenant_id = '00000000-0000-0000-0000-000000000000'
  AND LOWER(r.name) = LOWER('ADMIN_GLOBAL')
  AND NOT EXISTS (
    SELECT 1 FROM iam_permission p
    WHERE p.tenant_id = r.tenant_id
      AND p.role_id = r.id
      AND p.module_id = m.id
      AND p.resource_id = res.id
      AND p.action_id = a.id
      AND p.field_id IS NULL
);
