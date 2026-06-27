# Base de datos PostgreSQL

## Fuente de verdad

El schema ejecutable se define en `src/main/resources/db/migration`. Flyway aplica las 31 migraciones en orden numerico. El archivo [PeopleSchoolDb.txt](PeopleSchoolDb.txt) es una copia consolidada y ordenada de esas migraciones para revision, instalacion de referencia y documentacion.

El TXT abarca los capitulos 0, 1, 2 y 3. Las migraciones individuales prevalecen ante cualquier discrepancia.

## Inventario

El schema contiene 30 tablas de negocio con prefijo `pc_`.

| Modulo | Tablas |
|---|---|
| Personas y hogares | `pc_person`, `pc_household`, `pc_household_membership`, `pc_person_conflict`, `pc_person_legal_identity`, `pc_person_contact`, `pc_person_address`, `pc_guardian_relationship`, `pc_emergency_contact`, `pc_financial_responsible_relationship`, `pc_pickup_authorization`, `pc_institutional_role_assignment`, `pc_school_organizational_appointment`, `pc_institutional_affiliation`, `pc_person_identity_link`, `pc_person_document_reference`, `pc_person_sensitive_profile`, `pc_person_sensitive_coverage`, `pc_person_audit_snapshot`, `pc_person_evidence_relation` |
| Gobierno | `pc_org_group`, `pc_education_unit`, `pc_unit_site`, `pc_school_year`, `pc_education_level`, `pc_institutional_role_catalog`, `pc_appointment_catalog`, `pc_organizational_unit` |
| Normativa | `pc_school_regulation` |
| Estudiantes | `pc_student_profile` |

## Historia de migraciones

| Version | Cambio principal |
|---:|---|
| V1-V3 | Persona, hogar y membresia. |
| V4-V9 | Unidad educativa, gestion, niveles, catalogos y estructura organizacional. |
| V10-V13 | Conflictos, identidad legal, contactos y direcciones. |
| V14 | Nombres estructurados en persona. |
| V15 | Normativa escolar. |
| V16 | Perfil de estudiante. |
| V17-V20 | Tutores, emergencia, responsables financieros y retiro autorizado. |
| V21-V25 | Roles, designaciones, afiliaciones, IAM y documentos. |
| V26-V29 | Perfil/cobertura sensible, auditoria y evidencia. |
| V30 | Unicidad de documento legal por tenant, tipo, numero y pais. |
| V31 | Grupo organizacional y sedes; enlaza la unidad educativa con el grupo. |

## Convenciones del modelo

- `tenant_id` implementa aislamiento logico y forma parte de indices/unicidades relevantes.
- IDs primarios son `BIGSERIAL`/`BIGINT`.
- Fechas de auditoria se almacenan como `TIMESTAMP WITH TIME ZONE` cuando asi lo declara la migracion.
- `version` soporta control optimista en las entidades que lo incluyen.
- Estados y tipos se guardan como texto para corresponder con enums Java.
- Las claves foraneas expresan pertenencia y relaciones; los adaptadores tambien verifican tenant.
- Los indices `idx_*` aceleran filtros por tenant, estado, referencia y fechas.

## Relaciones centrales

```text
pc_org_group
  -> pc_education_unit
       -> pc_unit_site
       -> pc_school_year
       -> pc_education_level
       -> pc_organizational_unit

pc_person
  -> pc_person_legal_identity / contact / address
  -> pc_guardian_relationship / emergency_contact
  -> pc_institutional_affiliation / role_assignment / appointment
  -> pc_person_sensitive_profile -> pc_person_sensitive_coverage
  -> pc_student_profile

pc_household
  -> pc_household_membership -> pc_person
```

El TXT contiene todas las claves y acciones concretas; este diagrama solo resume las relaciones dominantes.

## Uso del script consolidado

Para crear una base vacia de referencia puede ejecutarse con `psql`:

```powershell
psql -h localhost -U person_registry -d person_registry -f docs/database/PeopleSchoolDb.txt
```

No se debe ejecutar el consolidado sobre una base ya administrada por Flyway: intentaria volver a crear objetos. En ejecucion normal, iniciar la aplicacion y dejar que Flyway aplique solamente las versiones pendientes.

## Regeneracion

El archivo se genera concatenando `V*.sql` por version numerica, con un encabezado y separadores. Tras agregar una migracion:

1. No modificar versiones ya aplicadas.
2. Crear el siguiente `V<n>__descripcion.sql`.
3. Ejecutar pruebas con una base limpia y una base actualizada.
4. Regenerar el TXT en el mismo orden.
5. Actualizar el inventario y la API si cambia el contrato.

La fecha y el listado del encabezado permiten auditar de que migraciones se genero la copia.
