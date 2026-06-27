# Dominio implementado

Este documento delimita lo que existe hoy. El hecho de que los cuatro capitulos vivan en el mismo desplegable no convierte sus modelos en un unico dominio; cada uno mantiene propiedad y lenguaje propios.

## Capitulo 0: personas y hogares

Modulo `institutionalpersonhouseholdregistry`. Es la superficie mas extensa y actua como registro maestro transversal.

| Area | Capacidades actuales |
|---|---|
| Persona | Crear, consultar, buscar, paginar, actualizar, eliminar y obtener estadisticas. |
| Identidad legal | Listar y mantener documentos legales, incluido documento principal y control de duplicados. |
| Contacto y direccion | CRUD de canales de contacto y direcciones, con indicador principal. |
| Hogar | CRUD de hogares y administracion paginada de sus membresias y vigencia. |
| Tutela | Registrar tutores/apoderados y autoridad legal/escolar. |
| Emergencia y finanzas | Registrar contactos de emergencia y responsables financieros. |
| Retiro | Registrar personas autorizadas para recoger al estudiante. |
| Institucion | Afiliaciones, roles institucionales y designaciones organizacionales. |
| Identidad IAM | Enlazar una persona con un `iamSubjectId`; no almacena credenciales. |
| Evidencia/documentos | Referencias a activos documentales y relaciones de evidencia. |
| Datos sensibles | Perfil y coberturas con permisos separados de lectura/escritura. |
| Calidad y auditoria | Conflictos de datos, cambio de estado e instantaneas de auditoria. |

Entidades principales persistidas: `Person`, `PersonLegalIdentity`, `PersonContact`, `PersonAddress`, `Household`, `HouseholdMembership`, `GuardianRelationship`, `EmergencyContact`, `FinancialResponsibleRelationship`, `PickupAuthorization`, `InstitutionalRoleAssignment`, `SchoolOrganizationalAppointment`, `InstitutionalAffiliation`, `PersonIdentityLink`, `PersonDocumentReference`, `PersonSensitiveProfile`, `PersonSensitiveCoverage`, `PersonConflict`, `PersonAuditSnapshot` y `PersonEvidenceRelation`.

## Capitulo 1: gobierno institucional

Modulo `governanceregistry`.

| Area | Capacidades actuales |
|---|---|
| Raiz organizacional | Crear/actualizar el grupo institucional y obtener el grupo actual. |
| Unidad educativa | Obtener o inicializar la unidad del tenant y actualizarla. |
| Sedes | CRUD de sedes, incluida sede principal y datos de ubicacion/contacto. |
| Organizacion | CRUD de unidades jerarquicas con unidad padre. |
| Niveles | CRUD del catalogo ordenado de niveles educativos. |
| Roles | CRUD del catalogo de roles institucionales. |
| Designaciones | CRUD del catalogo de nombramientos/cargos. |
| Gestion escolar | CRUD y activacion de una gestion como predeterminada. |

El grupo y la unidad `current` se obtienen por tenant. Estos endpoints pueden inicializar un registro cuando aun no existe, comportamiento que debe considerarse al auditar escrituras.

## Capitulo 2: normativa

Modulo `schoolregulationsregistry`. La implementacion actual cubre un agregado inicial `SchoolRegulation`:

- crear una norma;
- listar normas del tenant;
- actualizarla;
- eliminarla;
- validar su referencia institucional mediante un puerto hacia gobierno.

No estan implementados todavia otros posibles conceptos regulatorios, flujos de publicacion, versiones documentales, firmas o casos de convivencia. La documentacion evita presentarlos como existentes.

## Capitulo 3: estudiantes

Modulo `studentregistry`. La implementacion actual cubre `StudentProfile`:

- crear un perfil vinculado a una persona;
- asignar codigo y estado de estudiante;
- registrar fecha de ingreso y primera gestion opcionales;
- listar los perfiles del tenant.

No hay actualmente endpoints de detalle, actualizacion o eliminacion del perfil, ni matricula, trayectoria academica, cursos o evaluacion. Esas capacidades requeriran casos de uso y contratos propios.

## Reglas transversales

- Toda entidad de negocio pertenece a un `tenant_id`.
- Las consultas y mutaciones reciben el tenant desde el contexto autenticado.
- Las referencias por ID se validan dentro del tenant, no solo por existencia global.
- Los datos sensibles tienen scopes dedicados.
- Las credenciales y sesiones pertenecen a IAM; este dominio solo conserva el enlace de identidad.
- PostgreSQL es la fuente de verdad y Flyway controla la evolucion del schema.

## Casos de uso

Los nombres de clases en `application/usecase` son el inventario ejecutable. Capitulo 0 incluye operaciones `Create`, `Get`, `List`, `Update`, `Delete`, `Register`, `Assign` y `Link`; capitulo 1 incluye CRUD de catalogos, obtencion de recursos actuales y activacion de gestion; capitulo 2 contiene cuatro casos de uso CRUD; capitulo 3 contiene creacion y listado.

La matriz HTTP que los expone se encuentra en [../api/API-REST-ACTUAL.md](../api/API-REST-ACTUAL.md).
