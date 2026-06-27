-- Flyway Migration V6: Rename Person Registry permissions after the modular monolith rename.

UPDATE iam_resource
SET code = 'people-school',
    name = 'People School'
WHERE tenant_id = '00000000-0000-0000-0000-000000000000'
  AND code = 'person-registry';

UPDATE iam_resource
SET code = 'people-school.sensitive',
    name = 'People School Sensitive Data'
WHERE tenant_id = '00000000-0000-0000-0000-000000000000'
  AND code = 'person-registry.sensitive';

UPDATE iam_module
SET code = 'people-school',
    name = 'People School'
WHERE tenant_id = '00000000-0000-0000-0000-000000000000'
  AND code = 'person-registry';

UPDATE iam_module
SET code = 'people-school.sensitive',
    name = 'People School Sensitive Data'
WHERE tenant_id = '00000000-0000-0000-0000-000000000000'
  AND code = 'person-registry.sensitive';
