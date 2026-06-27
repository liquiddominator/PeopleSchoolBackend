# API REST actual

Catalogo de todas las operaciones expuestas por los controladores del servicio. La ruta base funcional es `/api/v1`; Swagger se publica en `/swagger-ui.html` y el contrato generado en `/v3/api-docs`.

## Convenciones

- Todas las operaciones `/api/v1/**` requieren `Authorization: Bearer <access-token>` emitido por IAM.
- `Content-Type` y `Accept`: `application/json` cuando existe cuerpo.
- Todo acceso queda aislado por `tenant_id`. Solo `ADMIN_GLOBAL` puede seleccionar otro tenant mediante `X-Tenant-Id`.
- `POST` de creacion devuelve `201 Created`; consultas y cambios devuelven `200 OK`; eliminaciones devuelven `204 No Content`.
- Identificadores internos son `Long`. Identificadores de tenant son UUID representados como texto.
- Fechas usan ISO-8601: `YYYY-MM-DD`; fecha y hora usan ISO-8601, por ejemplo `2026-06-27T14:30:00`.
- Listados no paginados devuelven arreglos JSON. Personas, hogares y miembros devuelven el objeto `Page` de Spring.

## Inventario exacto de operaciones

Esta tabla se deriva de las anotaciones de los 31 controladores y enumera las 92 operaciones activas. Las secciones siguientes documentan sus parametros y contratos por agregado.

| Metodo | Ruta | Controlador |
|---|---|---|
| `GET` | `/api/v1/conflicts` | `ConflictAdminController` |
| `PATCH` | `/api/v1/conflicts/{id}/status` | `ConflictAdminController` |
| `GET` | `/api/v1/governance/appointment-catalog` | `AppointmentCatalogAdminController` |
| `POST` | `/api/v1/governance/appointment-catalog` | `AppointmentCatalogAdminController` |
| `DELETE` | `/api/v1/governance/appointment-catalog/{id}` | `AppointmentCatalogAdminController` |
| `PUT` | `/api/v1/governance/appointment-catalog/{id}` | `AppointmentCatalogAdminController` |
| `GET` | `/api/v1/governance/education-levels` | `EducationLevelAdminController` |
| `POST` | `/api/v1/governance/education-levels` | `EducationLevelAdminController` |
| `DELETE` | `/api/v1/governance/education-levels/{id}` | `EducationLevelAdminController` |
| `PUT` | `/api/v1/governance/education-levels/{id}` | `EducationLevelAdminController` |
| `PUT` | `/api/v1/governance/education-units/{id}` | `EducationUnitAdminController` |
| `GET` | `/api/v1/governance/education-units/current` | `EducationUnitAdminController` |
| `GET` | `/api/v1/governance/organizational-units` | `OrganizationalUnitAdminController` |
| `POST` | `/api/v1/governance/organizational-units` | `OrganizationalUnitAdminController` |
| `DELETE` | `/api/v1/governance/organizational-units/{id}` | `OrganizationalUnitAdminController` |
| `PUT` | `/api/v1/governance/organizational-units/{id}` | `OrganizationalUnitAdminController` |
| `POST` | `/api/v1/governance/org-groups` | `OrgGroupAdminController` |
| `PUT` | `/api/v1/governance/org-groups/{id}` | `OrgGroupAdminController` |
| `GET` | `/api/v1/governance/org-groups/current` | `OrgGroupAdminController` |
| `GET` | `/api/v1/governance/role-catalog` | `RoleCatalogAdminController` |
| `POST` | `/api/v1/governance/role-catalog` | `RoleCatalogAdminController` |
| `DELETE` | `/api/v1/governance/role-catalog/{id}` | `RoleCatalogAdminController` |
| `PUT` | `/api/v1/governance/role-catalog/{id}` | `RoleCatalogAdminController` |
| `GET` | `/api/v1/governance/school-years` | `SchoolYearAdminController` |
| `POST` | `/api/v1/governance/school-years` | `SchoolYearAdminController` |
| `DELETE` | `/api/v1/governance/school-years/{id}` | `SchoolYearAdminController` |
| `PUT` | `/api/v1/governance/school-years/{id}` | `SchoolYearAdminController` |
| `POST` | `/api/v1/governance/school-years/{id}/activate` | `SchoolYearAdminController` |
| `GET` | `/api/v1/governance/sites` | `UnitSiteAdminController` |
| `POST` | `/api/v1/governance/sites` | `UnitSiteAdminController` |
| `DELETE` | `/api/v1/governance/sites/{id}` | `UnitSiteAdminController` |
| `PUT` | `/api/v1/governance/sites/{id}` | `UnitSiteAdminController` |
| `GET` | `/api/v1/households` | `HouseholdAdminController` |
| `POST` | `/api/v1/households` | `HouseholdAdminController` |
| `GET` | `/api/v1/households/{householdId}/members` | `HouseholdMemberController` |
| `POST` | `/api/v1/households/{householdId}/members` | `HouseholdMemberController` |
| `DELETE` | `/api/v1/households/{householdId}/members/{membershipId}` | `HouseholdMemberController` |
| `PUT` | `/api/v1/households/{householdId}/members/{membershipId}` | `HouseholdMemberController` |
| `DELETE` | `/api/v1/households/{id}` | `HouseholdAdminController` |
| `GET` | `/api/v1/households/{id}` | `HouseholdAdminController` |
| `PUT` | `/api/v1/households/{id}` | `HouseholdAdminController` |
| `GET` | `/api/v1/persons` | `PersonAdminController` |
| `POST` | `/api/v1/persons` | `PersonAdminController` |
| `DELETE` | `/api/v1/persons/{id}` | `PersonAdminController` |
| `GET` | `/api/v1/persons/{id}` | `PersonAdminController` |
| `PUT` | `/api/v1/persons/{id}` | `PersonAdminController` |
| `GET` | `/api/v1/persons/{personId}/addresses` | `PersonAddressController` |
| `POST` | `/api/v1/persons/{personId}/addresses` | `PersonAddressController` |
| `DELETE` | `/api/v1/persons/{personId}/addresses/{id}` | `PersonAddressController` |
| `PUT` | `/api/v1/persons/{personId}/addresses/{id}` | `PersonAddressController` |
| `GET` | `/api/v1/persons/{personId}/affiliations` | `PersonAffiliationController` |
| `POST` | `/api/v1/persons/{personId}/affiliations` | `PersonAffiliationController` |
| `GET` | `/api/v1/persons/{personId}/appointments` | `PersonAppointmentController` |
| `POST` | `/api/v1/persons/{personId}/appointments` | `PersonAppointmentController` |
| `GET` | `/api/v1/persons/{personId}/audit-snapshots` | `PersonAuditSnapshotController` |
| `GET` | `/api/v1/persons/{personId}/conflicts` | `PersonConflictController` |
| `GET` | `/api/v1/persons/{personId}/contacts` | `PersonContactController` |
| `POST` | `/api/v1/persons/{personId}/contacts` | `PersonContactController` |
| `DELETE` | `/api/v1/persons/{personId}/contacts/{id}` | `PersonContactController` |
| `PUT` | `/api/v1/persons/{personId}/contacts/{id}` | `PersonContactController` |
| `GET` | `/api/v1/persons/{personId}/document-references` | `PersonDocumentReferenceController` |
| `POST` | `/api/v1/persons/{personId}/document-references` | `PersonDocumentReferenceController` |
| `GET` | `/api/v1/persons/{personId}/emergency-contacts` | `PersonEmergencyContactController` |
| `POST` | `/api/v1/persons/{personId}/emergency-contacts` | `PersonEmergencyContactController` |
| `GET` | `/api/v1/persons/{personId}/evidence-relations` | `PersonEvidenceRelationController` |
| `POST` | `/api/v1/persons/{personId}/evidence-relations` | `PersonEvidenceRelationController` |
| `GET` | `/api/v1/persons/{personId}/financial-responsibles` | `PersonFinancialResponsibleController` |
| `POST` | `/api/v1/persons/{personId}/financial-responsibles` | `PersonFinancialResponsibleController` |
| `GET` | `/api/v1/persons/{personId}/guardian-relationships` | `PersonGuardianRelationshipController` |
| `POST` | `/api/v1/persons/{personId}/guardian-relationships` | `PersonGuardianRelationshipController` |
| `GET` | `/api/v1/persons/{personId}/identity-links` | `PersonIdentityLinkController` |
| `POST` | `/api/v1/persons/{personId}/identity-links` | `PersonIdentityLinkController` |
| `GET` | `/api/v1/persons/{personId}/legal-identities` | `PersonLegalIdentityController` |
| `POST` | `/api/v1/persons/{personId}/legal-identities` | `PersonLegalIdentityController` |
| `DELETE` | `/api/v1/persons/{personId}/legal-identities/{id}` | `PersonLegalIdentityController` |
| `PUT` | `/api/v1/persons/{personId}/legal-identities/{id}` | `PersonLegalIdentityController` |
| `GET` | `/api/v1/persons/{personId}/pickup-authorizations` | `PersonPickupAuthorizationController` |
| `POST` | `/api/v1/persons/{personId}/pickup-authorizations` | `PersonPickupAuthorizationController` |
| `GET` | `/api/v1/persons/{personId}/roles` | `PersonRoleAssignmentController` |
| `POST` | `/api/v1/persons/{personId}/roles` | `PersonRoleAssignmentController` |
| `GET` | `/api/v1/persons/{personId}/sensitive-coverages` | `PersonSensitiveCoverageController` |
| `POST` | `/api/v1/persons/{personId}/sensitive-coverages` | `PersonSensitiveCoverageController` |
| `GET` | `/api/v1/persons/{personId}/sensitive-profile` | `PersonSensitiveProfileController` |
| `POST` | `/api/v1/persons/{personId}/sensitive-profile` | `PersonSensitiveProfileController` |
| `PUT` | `/api/v1/persons/{personId}/sensitive-profile/{profileId}` | `PersonSensitiveProfileController` |
| `GET` | `/api/v1/persons/stats` | `PersonAdminController` |
| `GET` | `/api/v1/regulations/school-regulations` | `SchoolRegulationAdminController` |
| `POST` | `/api/v1/regulations/school-regulations` | `SchoolRegulationAdminController` |
| `DELETE` | `/api/v1/regulations/school-regulations/{id}` | `SchoolRegulationAdminController` |
| `PUT` | `/api/v1/regulations/school-regulations/{id}` | `SchoolRegulationAdminController` |
| `GET` | `/api/v1/students` | `StudentProfileAdminController` |
| `POST` | `/api/v1/students` | `StudentProfileAdminController` |

## Autorizacion

| Operacion | Autoridad aceptada |
|---|---|
| Cualquier GET funcional | `ROLE_ADMIN_GLOBAL` o `SCOPE_people-school.read` |
| POST, PUT, PATCH o DELETE funcional | `ROLE_ADMIN_GLOBAL` o `SCOPE_people-school.write` |
| GET de perfil/cobertura sensible | `ROLE_ADMIN_GLOBAL` o `SCOPE_people-school.sensitive.read` |
| POST/PUT de perfil/cobertura sensible | `ROLE_ADMIN_GLOBAL` o `SCOPE_people-school.sensitive.write` |
| `GET /actuator/prometheus` | `ROLE_ADMIN_GLOBAL` o `SCOPE_operations.read` |

## Personas y hogares (capitulo 0)

### Personas

| Metodo y ruta | Entrada | Respuesta | Comportamiento |
|---|---|---|---|
| `POST /api/v1/persons` | `CreatePersonRequest` | `PersonResponse` | Crea la persona maestra del tenant. |
| `GET /api/v1/persons` | Query de filtros y paginacion | `Page<PersonSummaryResponse>` | Lista personas del tenant. |
| `GET /api/v1/persons/stats` | Sin cuerpo | `RegistryStatsResponse` | Totales agregados para el tablero del registro. |
| `GET /api/v1/persons/{id}` | `id` | `PersonResponse` | Recupera una persona del tenant. |
| `PUT /api/v1/persons/{id}` | `UpdatePersonRequest` | `PersonResponse` | Sustituye los datos editables de la persona. |
| `DELETE /api/v1/persons/{id}` | `id` | Sin cuerpo | Elimina la persona segun las restricciones del dominio y DB. |

Filtros de `GET /persons`: `personTypeCode`, `coreStatus`, `search`, `page`, `size` y `sort`. Los tres primeros son opcionales; la paginacion usa `size=20` y orden `createdAt` por defecto.

### Hogares y miembros

| Metodo y ruta | Entrada | Respuesta | Comportamiento |
|---|---|---|---|
| `POST /api/v1/households` | `CreateHouseholdRequest` | `HouseholdResponse` | Crea un hogar. |
| `GET /api/v1/households` | Filtros y paginacion | `Page<HouseholdSummaryResponse>` | Lista hogares por tipo o estado. |
| `GET /api/v1/households/{id}` | `id` | `HouseholdResponse` | Obtiene el hogar. |
| `PUT /api/v1/households/{id}` | `UpdateHouseholdRequest` | `HouseholdResponse` | Actualiza el hogar. |
| `DELETE /api/v1/households/{id}` | `id` | Sin cuerpo | Elimina el hogar. |
| `POST /api/v1/households/{householdId}/members` | `AddMemberRequest` | `HouseholdMemberResponse` | Incorpora una persona al hogar. |
| `GET /api/v1/households/{householdId}/members` | Paginacion | `Page<HouseholdMemberResponse>` | Lista membresias del hogar. |
| `PUT /api/v1/households/{householdId}/members/{membershipId}` | `UpdateMemberRequest` | `HouseholdMemberResponse` | Cambia rol, vigencia o estado de la membresia. |
| `DELETE /api/v1/households/{householdId}/members/{membershipId}` | IDs | Sin cuerpo | Retira la membresia. |

Filtros de hogares: `householdTypeCode`, `householdStatus`, `page`, `size`, `sort`. Los listados paginados usan `size=20` por defecto.

### Datos de persona con CRUD completo

| Recurso relativo a `/api/v1/persons/{personId}` | GET | POST | PUT | DELETE |
|---|---|---|---|---|
| `/legal-identities` | Lista `LegalIdentityResponse` | `LegalIdentityRequest` | `/{id}` con `LegalIdentityRequest` | `/{id}` |
| `/contacts` | Lista `ContactResponse` | `ContactRequest` | `/{id}` con `ContactRequest` | `/{id}` |
| `/addresses` | Lista `AddressResponse` | `AddressRequest` | `/{id}` con `AddressRequest` | `/{id}` |

Los `POST` devuelven el recurso creado; los `PUT`, el recurso actualizado. La identidad legal principal queda sujeta a la unicidad documental definida por la migracion `V30`.

### Relaciones y registros anexos

Cada fila expone `GET` para listar y `POST` para registrar. No hay actualmente endpoints HTTP de edicion o eliminacion para estos recursos.

| Ruta relativa | Cuerpo del POST | Respuesta |
|---|---|---|
| `/guardian-relationships` | `CreateGuardianRelationshipRequest` | `GuardianRelationshipResponse` |
| `/emergency-contacts` | `CreateEmergencyContactRequest` | `EmergencyContactResponse` |
| `/financial-responsibles` | `CreateFinancialResponsibleRequest` | `FinancialResponsibleResponse` |
| `/pickup-authorizations` | `CreatePickupAuthorizationRequest` | `PickupAuthorizationResponse` |
| `/roles` | `CreateRoleAssignmentRequest` | `RoleAssignmentResponse` |
| `/appointments` | `CreateAppointmentRequest` | `AppointmentResponse` |
| `/affiliations` | `CreateAffiliationRequest` | `AffiliationResponse` |
| `/identity-links` | `CreateIdentityLinkRequest` | `IdentityLinkResponse` |
| `/document-references` | `CreateDocumentReferenceRequest` | `DocumentReferenceResponse` |
| `/evidence-relations` | `CreateEvidenceRelationRequest` | `EvidenceRelationResponse` |

La ruta completa de cada fila es `/api/v1/persons/{personId}<ruta-relativa>`. El `personId` del path es la persona propietaria; cuando el DTO tambien contiene `personId`, el controlador aplica el valor de la ruta como contexto canonico.

### Datos sensibles, auditoria y conflictos

| Metodo y ruta | Entrada | Respuesta | Comportamiento |
|---|---|---|---|
| `GET /api/v1/persons/{personId}/sensitive-profile` | IDs | `SensitiveProfileResponse` | Obtiene el perfil restringido. |
| `POST /api/v1/persons/{personId}/sensitive-profile` | `CreateSensitiveProfileRequest` | `SensitiveProfileResponse` | Crea el perfil restringido. |
| `PUT /api/v1/persons/{personId}/sensitive-profile/{profileId}` | `UpdateSensitiveProfileRequest` | `SensitiveProfileResponse` | Actualiza el perfil restringido. |
| `GET /api/v1/persons/{personId}/sensitive-coverages` | IDs | Lista `SensitiveCoverageResponse` | Lista coberturas restringidas. |
| `POST /api/v1/persons/{personId}/sensitive-coverages` | `CreateSensitiveCoverageRequest` | `SensitiveCoverageResponse` | Registra una cobertura. |
| `GET /api/v1/persons/{personId}/audit-snapshots` | `personId` | Lista `AuditSnapshotResponse` | Consulta instantaneas historicas. |
| `GET /api/v1/persons/{personId}/conflicts` | `personId` | Lista `ConflictResponse` | Consulta conflictos de la persona. |
| `GET /api/v1/conflicts` | Sin cuerpo | Lista `ConflictResponse` | Lista todos los conflictos del tenant. |
| `PATCH /api/v1/conflicts/{id}/status` | `UpdateConflictStatusRequest` | `ConflictResponse` | Cambia el estado de resolucion. |

## Gobierno institucional (capitulo 1)

| Recurso base | POST | GET | PUT | DELETE | Operaciones especiales |
|---|---|---|---|---|---|
| `/api/v1/governance/org-groups` | `CreateOrgGroupRequest` | `/current` | `/{id}` `UpdateOrgGroupRequest` | No | `GET /current` obtiene o crea el grupo raiz del tenant. |
| `/api/v1/governance/education-units` | No | `/current` | `/{id}` `UpdateEducationUnitRequest` | No | `GET /current` obtiene o crea la unidad educativa. |
| `/api/v1/governance/sites` | `CreateUnitSiteRequest` | Lista | `/{id}` `UpdateUnitSiteRequest` | `/{id}` | Sedes fisicas de la unidad. |
| `/api/v1/governance/organizational-units` | `CreateOrganizationalUnitRequest` | Lista | `/{id}` `UpdateOrganizationalUnitRequest` | `/{id}` | Arbol organizacional mediante `parentId`. |
| `/api/v1/governance/education-levels` | `CreateEducationLevelRequest` | Lista | `/{id}` `UpdateEducationLevelRequest` | `/{id}` | Catalogo ordenado de niveles. |
| `/api/v1/governance/role-catalog` | `CreateRoleCatalogRequest` | Lista | `/{id}` `UpdateRoleCatalogRequest` | `/{id}` | Catalogo institucional de roles. |
| `/api/v1/governance/appointment-catalog` | `CreateAppointmentCatalogRequest` | Lista | `/{id}` `UpdateAppointmentCatalogRequest` | `/{id}` | Catalogo de designaciones. |
| `/api/v1/governance/school-years` | `CreateSchoolYearRequest` | Lista | `/{id}` `UpdateSchoolYearRequest` | `/{id}` | `POST /{id}/activate` activa una gestion y devuelve `SchoolYearResponse`. |

Las creaciones devuelven `201`; listados y modificaciones `200`; eliminaciones `204`. Los recursos `current` son deliberadamente idempotentes desde la perspectiva del consumidor, aunque pueden persistir el valor inicial cuando no existe.

## Normativa y estudiantes (capitulos 2 y 3)

| Metodo y ruta | Entrada | Respuesta | Comportamiento |
|---|---|---|---|
| `POST /api/v1/regulations/school-regulations` | `CreateSchoolRegulationRequest` | `SchoolRegulationResponse` | Registra una norma escolar. |
| `GET /api/v1/regulations/school-regulations` | Sin cuerpo | Lista `SchoolRegulationResponse` | Lista normas del tenant. |
| `PUT /api/v1/regulations/school-regulations/{id}` | `UpdateSchoolRegulationRequest` | `SchoolRegulationResponse` | Actualiza la norma. |
| `DELETE /api/v1/regulations/school-regulations/{id}` | `id` | Sin cuerpo | Elimina la norma. |
| `POST /api/v1/students` | `CreateStudentProfileRequest` | `StudentProfileResponse` | Crea el perfil de estudiante enlazado a una persona. |
| `GET /api/v1/students` | Sin cuerpo | Lista `StudentProfileResponse` | Lista perfiles de estudiante del tenant. |

## Contratos de escritura

Campos marcados con `*` son obligatorios. Todos los enum se envian por su nombre literal.

### Persona y hogar

| DTO | Campos |
|---|---|
| `CreatePersonRequest` | `personCode*`, `personTypeCode*`, `coreStatus*`, `photoAssetId`, `firstName`, `middleName`, `paternalSurname`, `maternalSurname` |
| `UpdatePersonRequest` | Mismos datos editables de persona; respeta las validaciones del DTO. |
| `CreateHouseholdRequest` | `householdCode*`, `householdName*`, `householdTypeCode*`, `householdStatus*`, `primaryBillingAddressId` |
| `UpdateHouseholdRequest` | Nombre, tipo, estado y direccion principal de facturacion. |
| `AddMemberRequest` | `personId*`, `membershipRoleCode*`, `effectiveFrom*`, `effectiveTo`, `isPrimaryGuardianGroup` |
| `UpdateMemberRequest` | Rol, estado, vigencia y marca de grupo tutor principal. |
| `LegalIdentityRequest` | `tipo*`, `numero*`, `paisEmisor*`, `estado*`, `esPrincipal*` |
| `ContactRequest` | `tipo*`, `valor*`, `uso*`, `esPrincipal*` |
| `AddressRequest` | `pais*`, `ciudad*`, `linea1*`, `linea2`, `esPrincipal*` |

### Relaciones de persona

| DTO | Campos principales |
|---|---|
| `CreateGuardianRelationshipRequest` | `studentPersonId*`, `guardianPersonId*`, `householdId`, `guardianTypeCode*`, `legalAuthorityStatus*`, `schoolAuthorityStatus*`, vigencia, notas |
| `CreateEmergencyContactRequest` | `personId*`, `contactPersonId`, `priority*`, `relationshipLabel`, `status*` |
| `CreateFinancialResponsibleRequest` | `beneficiaryPersonId*`, `responsiblePersonId*`, `householdId`, `status*`, vigencia, notas |
| `CreatePickupAuthorizationRequest` | `studentPersonId*`, `authorizedPersonId*`, `authorizationStatus*`, vigencia, alcance, notas |
| `CreateRoleAssignmentRequest` | `personId*`, `roleCode*`, `roleStatus*`, vigencia |
| `CreateAppointmentRequest` | `personId*`, `appointmentCode*`, `organizationalUnitId`, `appointmentStatus*`, `startedAt*`, `endedAt` |
| `CreateAffiliationRequest` | `personId*`, `affiliationTypeCode*`, `siteId`, `organizationalUnitId`, `affiliationStatus*`, vigencia |
| `CreateIdentityLinkRequest` | `personId*`, `iamSubjectId*`, `identityProviderCode`, `identityLinkStatus*`, `linkedAt*`, `linkedBy` |
| `CreateDocumentReferenceRequest` | `personId*`, `documentReferenceTypeCode*`, `assetId*`, `referenceStatus*`, `notes` |
| `CreateEvidenceRelationRequest` | `personId`, `householdId`, `relatedContextType*`, `relatedContextRefId*`, `evidenceRoleCode*`, `assetId*`, `evidenceStatus*` |
| `CreateSensitiveProfileRequest` | `personId*`, grupo sanguineo/notas opcionales, `profileStatus*`, datos de revision opcionales |
| `UpdateSensitiveProfileRequest` | Grupo sanguineo, notas, estado y revision. |
| `CreateSensitiveCoverageRequest` | `profileId*`, `personId*`, `coverageTypeCode*`, proveedor/poliza, `coverageStatus*`, vigencia, notas |
| `UpdateConflictStatusRequest` | `status*` |

### Gobierno, normativa y estudiante

| DTO | Campos principales |
|---|---|
| `CreateOrgGroupRequest` | `tenantCode*`, `legalName*`, `commercialName`, `taxIdentifier`, `countryCode*`, `defaultCurrencyCode*`, `timezone*`, `status*` |
| `CreateUnitSiteRequest` | `educationUnitId`, `siteCode*`, `siteName*`, direccion, ciudad, departamento, telefono, email, `isMain`, `siteStatus*` |
| `CreateOrganizationalUnitRequest` | `code*`, `name*`, `type*`, `parentId`, `displayOrder`, `status*` |
| `CreateEducationLevelRequest` | `levelCode*`, `levelName*`, `officialReferenceCode`, `levelSequence`, `levelStatus*` |
| `CreateRoleCatalogRequest` | `roleCode*`, `roleName*`, `roleFamily*`, `assignable`, `status*`, `displayOrder` |
| `CreateAppointmentCatalogRequest` | `appointmentCode*`, `appointmentName*`, `orgUnitTypeScope`, `requiresDocumentSupport`, `status*`, `displayOrder` |
| `CreateSchoolYearRequest` | `code*`, `name*`, `startDate*`, `endDate*`, `lifecycleStatus*` |
| `CreateSchoolRegulationRequest` | `regulationCode*`, `title*`, `description`, `regulationType*`, `issuingAuthorityTypeCode`, `criticality*` |
| `CreateStudentProfileRequest` | `personId*`, `studentCode*`, `studentStatus*`, `schoolEntryDate`, `firstSchoolYearId` |

Los DTO `Update...` equivalentes modifican los campos editables del mismo recurso. Limites exactos de longitud y rango estan publicados por Bean Validation en OpenAPI y se aplican antes de ejecutar el caso de uso.

## Valores enum aceptados

- Persona: `STUDENT`, `STAFF`, `GUARDIAN`, `EXTERNAL`, `UNKNOWN`; estado `ACTIVE`, `INACTIVE`, `SUSPENDED`, `ARCHIVED`.
- Hogar: `NUCLEAR`, `EXTENDED`, `SINGLE_PARENT`, `BLENDED`, `OTHER`; membresia `HEAD`, `SPOUSE`, `CHILD`, `GUARDIAN`, `OTHER`.
- Documento: `DNI`, `PASAPORTE`, `CE`, `RUC`, `OTRO`; estado `VIGENTE`, `VENCIDO`, `ANULADO`.
- Contacto: tipo `EMAIL`, `TELEFONO`, `CELULAR`, `WHATSAPP`; uso `PERSONAL`, `LABORAL`, `INSTITUCIONAL`, `EMERGENCIA`.
- Conflicto: `POSIBLE_DUPLICADO`, `DATO_INCONGRUENTE`, `DOC_REPETIDO`, `EMAIL_COMPARTIDO`; estado `ABIERTO`, `EN_PROCESO`, `RESUELTO`, `DESCARTADO`.
- Gestion: `DRAFT`, `ACTIVE`, `CLOSED`, `ARCHIVED`.
- Familia de rol: `DOCENTE`, `ADMINISTRATIVO`, `DIRECTIVO`, `APOYO`, `OTRO`.
- Tipo de unidad: `SEDE`, `DEPARTAMENTO`, `AREA`, `SECCION`, `COMISION`.
- Normativa: `REGLAMENTO_INTERNO`, `MANUAL_CONVIVENCIA`, `PROTOCOLO`, `RESOLUCION`, `CIRCULAR`, `INSTRUCTIVO`, `LINEAMIENTO`, `OTRO`; criticidad `CRITICA`, `ALTA`, `MEDIA`, `BAJA`.
- Estudiante: `ACTIVE`, `INACTIVE`, `PAUSED`, `WITHDRAWN`, `GRADUATED`.
- Los demas estados usan las variantes `ACTIVE`, `INACTIVE`, `CLOSED`, `ARCHIVED`, `SUSPENDED`, `REVOKED`, `EXPIRED` o `CANCELLED` segun el schema OpenAPI del DTO.

## Respuestas de error

Errores manejados por la aplicacion usan:

```json
{
  "status": 400,
  "code": "VALIDATION_ERROR",
  "message": "Validation failed",
  "args": { "field": "detalle" },
  "path": "/api/v1/persons",
  "timestamp": "2026-06-27T18:00:00Z"
}
```

| HTTP | Origen habitual |
|---|---|
| `400` | JSON invalido, Bean Validation o tenant UUID invalido |
| `401` | JWT ausente, vencido, firma/issuer/tipo/claims invalidos o token revocado |
| `403` | Scope/rol insuficiente o seleccion de tenant no permitida |
| `404` | Entidad no encontrada dentro del tenant |
| `409` | Regla de negocio o unicidad en conflicto |
| `500` | Error interno no clasificado |

## Endpoints tecnicos

| Ruta | Acceso | Uso |
|---|---|---|
| `GET /actuator/health` | Publico | Estado de salud. |
| `GET /actuator/info` | Publico | Informacion expuesta del servicio. |
| `GET /actuator/prometheus` | Protegido | Metricas para Prometheus. |
| `GET /v3/api-docs` | Publico | OpenAPI JSON generado. |
| `GET /swagger-ui.html` | Publico | Interfaz Swagger. |
