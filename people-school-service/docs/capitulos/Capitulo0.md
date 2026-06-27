# PeopleCole

## Módulo Transversal Backend: Registro Maestro de Personas, Familias, Apoderados, Relaciones, Roles, Cargos y Datos Sensibles Controlados para Colegios Bolivianos

*Diseño enterprise fundacional para colegios privados bolivianos, con integración limpia con académico, admisiones, portal de padres, RUDE, finanzas, bienestar, documental e IAM*

## 1. Naturaleza de este módulo y por qué este sí debe existir como capacidad transversal del core-platform

Este documento diseña el **módulo transversal Registro Maestro de Personas, Familias, Apoderados, Relaciones, Roles, Cargos y Datos Sensibles Controlados** como una de las piezas más importantes de toda la plataforma **PeopleCole**.

Este módulo no debe entenderse como una tabla de personas. Tampoco como un catálogo de contactos. Tampoco como una extensión de RRHH. Tampoco como un simple “maestro de estudiantes”. Y menos todavía como un apéndice de IAM. Debe entenderse como la **fuente institucional única de identidad civil, familiar, escolar e institucional** de todas las personas que participan de una u otra forma en el ecosistema del colegio.

En un colegio privado boliviano real, la institución interactúa con un universo humano más amplio que el de estudiantes y empleados. Existen postulantes, estudiantes activos, estudiantes antiguos, padres, madres, apoderados, tutores, responsables de pago, contactos de emergencia, docentes, administrativos, directores de nivel, rectoría, enfermería, psicopedagogía, convivencia, choferes, transportistas, proveedores de servicios extracurriculares, personal de apoyo, exalumnos, responsables legales y múltiples actores más. Intentar modelar cada uno de esos mundos con tablas separadas y datos duplicados es una receta segura para la incoherencia, la mala trazabilidad, la pérdida de control documental y el colapso arquitectónico.

Por eso, este módulo debe nacer como una **capacidad transversal base del core-platform de PeopleCole**. No debe vivir dentro de RRHH, porque no toda persona relevante para el colegio es empleada. No debe vivir dentro de IAM, porque una persona no es lo mismo que una cuenta digital. No debe vivir dentro del módulo académico, porque una persona puede existir antes de ser postulante, antes de ser estudiante o incluso sin convertirse jamás en estudiante. No debe vivir dentro del módulo financiero, porque la persona puede existir antes de tener responsabilidad económica. Y no debe reducirse a una tabla de “familias” o “apoderados”, porque su responsabilidad es mucho más profunda: debe modelar **persona, hogar/familia, relaciones humanas, tutela, apoderamiento, contacto, identificación, cargos institucionales, evidencia documental y acceso controlado a atributos sensibles**.

En lenguaje enterprise brutal, este módulo es el **Institutional Party Registry + Household & Guardian Registry** del colegio. Y debe convertirse en el punto de verdad del que dependan todos los demás dominios funcionales.

## 2. Propósito real del módulo dentro de PeopleCole

El propósito real de este módulo es construir la **fuente oficial única de identidad institucional y relacional para todo el colegio**.

Si los módulos académicos gobiernan matrícula, paralelos, subgrupos, asistencia, evaluación y boletines; si admisiones gobierna postulaciones; si cuentas por cobrar gobierna deuda y planes de pago; si el módulo regulatorio gobierna RUDE y salida oficial; si documental gobierna archivos y evidencias; y si IAM gobierna identidad digital y permisos, este módulo gobierna la pregunta más básica y poderosa de toda la plataforma:

**¿Quién es esta persona, cómo la identifico correctamente, con qué familia u hogar está relacionada, quién la representa o tutela, quién responde económicamente por ella, qué roles cumple en el colegio, qué cargos ocupa, cómo la contacto, qué documentación la respalda y qué dominios funcionales dependen de ella?**

Este módulo debe poder responder preguntas como:

- quién es la persona;
- con qué documento se identifica;
- cuáles son sus nombres oficiales y variantes relevantes;
- cuáles son sus medios de contacto vigentes;
- cuáles son sus direcciones y usos;
- a qué familia, hogar o household pertenece;
- quiénes son sus padres, madres, tutores o apoderados;
- quién puede retirar al estudiante;
- quién es su responsable de pago;
- quién es su contacto de emergencia;
- si tiene vínculos institucionales como postulante, estudiante, docente, administrativo o autoridad;
- qué cargos formales ocupa y durante qué periodo;
- qué documentos personales y familiares tiene asociados;
- qué datos sensibles requieren visibilidad mínima;
- y cómo se enlaza todo eso con IAM, admisiones, académico, finanzas, bienestar, convivencia, enfermería, regulatorio y documental.

Si el backend no puede responder este tipo de preguntas con claridad, trazabilidad y consistencia, la plataforma nunca será realmente enterprise.

## 3. La decisión arquitectónica más importante: Persona no es IAM, Persona no es Estudiante, Persona no es Apoderado, Persona no es Cargo, Persona no es Familia

Este punto es crítico y debe quedar brutalmente claro para todo el equipo.

### 3.1 Persona no es IAM

IAM gobierna identidad digital: usuario, credenciales, autenticación, autorización, MFA, sesiones, bloqueo y políticas de acceso. Una persona puede existir sin cuenta digital o antes de tenerla. Por tanto, IAM debe enlazarse con la persona, no reemplazarla.

### 3.2 Persona no es Estudiante

Estudiante es un rol o perfil académico que cuelga de una persona. La persona puede ser postulante, luego estudiante, luego exalumno, luego apoderado de otro estudiante y seguir siendo la misma entidad humana.

### 3.3 Persona no es Padre, Madre, Tutor o Apoderado

Padre, madre, tutor y apoderado no son tipos esenciales de persona; son **relaciones y capacidades institucionales** que una persona puede asumir respecto de otra, con vigencia, documentos de respaldo y alcance definido.

### 3.4 Persona no es Responsable Financiero

La responsabilidad económica es una relación entre una persona y una obligación o beneficiario. No debe confundirse con parentesco ni con tutoría.

### 3.5 Persona no es Docente ni Administrativo

Docente y administrativo son afiliaciones o perfiles funcionales sobre la misma persona. Una persona puede ser ambas cosas en distintos momentos o incluso simultáneamente.

### 3.6 Persona no es Cargo

Director general, director académico, director de inicial, director de primaria, director de secundaria, coordinador, psicopedagoga, enfermera, responsable de convivencia o cajero no son tipos de persona. Son nombramientos o assignments sobre una persona.

### 3.7 Persona no es Familia ni Household

El hogar, grupo familiar o household es una **entidad de agrupación relacional** en torno a una o varias personas. Una persona puede pertenecer a un household, pero no es el household.

Esta separación te salva media plataforma.

## 4. Contexto institucional correcto para colegios privados bolivianos

Este módulo debe modelarse bajo la realidad ya construida para PeopleCole:

- una institución educativa o grupo educativo parametrizable;
- posibilidad de múltiples sedes o campus si el colegio las tiene;
- niveles como inicial, primaria y secundaria;
- una fuerte centralidad de padres, madres, tutores y apoderados;
- necesidad de household o grupo familiar para cobro, comunicación y tutela;
- fuerte exigencia de trazabilidad, documentación y seguridad;
- necesidad de operar con procesos como postulaciones, matrícula, licencias, asistencia, boletines, cuentas por cobrar, RUDE y salida oficial.

Este módulo no debe introducir multiinstitución artificial si el caso de negocio inmediato no la exige. Pero sí debe quedar lo suficientemente limpio como para que PeopleCole pueda parametrizar distintos colegios bolivianos con variaciones reales en estructura, roles y reglas familiares.

También debe reconocer una realidad concreta de colegios privados bolivianos: las personas cambian de rol, pueden acumular roles, pueden tener relaciones familiares complejas, pueden existir múltiples apoderados, responsables de pago, restricciones de retiro, autorizaciones especiales, documentos judiciales o de tutela, y pueden requerir manejo de información sensible bajo estrictos controles.

## 5. Finalidad funcional del módulo

La finalidad funcional de este módulo es permitir que el colegio pueda **crear, identificar, mantener, relacionar, auditar y consumir de manera segura** toda la información transversal de personas, familias, apoderados, roles y vínculos institucionales.

Aquí deben convertirse en una fuente oficial, única, trazable y resoluble elementos como:

- persona base;
- identidad legal/documental;
- nombres y variantes de nombre;
- múltiples correos y teléfonos;
- múltiples direcciones y usos;
- household o grupo familiar;
- miembros del household;
- padres, madres, tutores y apoderados;
- contactos de emergencia;
- responsables económicos;
- personas autorizadas para recojo o retiro;
- documentos personales y familiares;
- fotografía institucional;
- afiliaciones institucionales;
- roles funcionales;
- cargos o nombramientos formales;
- enlaces con IAM;
- enlaces con admisiones, académico y finanzas;
- acceso controlado a atributos sensibles.

Eso quiere decir que el backend no debe modelar a una persona como una tabla plana con 200 columnas. Debe modelar **objetos institucionales de persona y relación**: persona, identidad legal, puntos de contacto, dirección, household, membresía del household, relación de apoderado, contacto de emergencia, responsable financiero, afiliación institucional, rol, nombramiento, documento personal y perfil sensible restringido.

## 6. Qué sí hace y qué no hace este módulo

### 6.1 Qué sí hace

Este módulo sí debe:

- crear y mantener la persona como entidad maestra;
- gestionar identidades legales y documentales;
- soportar múltiples puntos de contacto;
- soportar múltiples direcciones y su propósito;
- gestionar households o grupos familiares;
- registrar membresías familiares;
- registrar relaciones de padre, madre, tutor, apoderado y responsable de pago;
- registrar contactos de emergencia;
- registrar personas autorizadas para retiro o recogida cuando aplique;
- registrar afiliaciones institucionales;
- registrar roles institucionales;
- registrar cargos y nombramientos con vigencia;
- enlazar con IAM;
- enlazar con admisiones;
- enlazar con módulos académicos;
- enlazar con CxC;
- soportar documentación personal y familiar;
- controlar acceso a datos sensibles;
- auditar todos los cambios;
- exponer APIs limpias para módulos consumidores;
- emitir eventos de dominio relevantes.

### 6.2 Qué no hace

Este módulo no debe:

- convertirse en RRHH completo;
- convertirse en IAM;
- contener lógica académica específica de notas, asistencia o boletines;
- contener el workflow completo de admisión o matrícula;
- contener deuda, recaudaciones o contabilidad;
- contener expediente clínico completo;
- ni exponer indiscriminadamente datos sensibles a todo el ecosistema.

### 6.3 Qué significa esto arquitectónicamente

Este módulo es el **nodo raíz de identidad humana, familiar e institucional**. IAM, admisiones, académico, finanzas, bienestar, comunicación, regulatorio y documental consumen esta raíz. La persona nace aquí; los perfiles y vínculos sectoriales viven en sus dominios respectivos, pero se apoyan sobre esta base.

## 7. Actores y dominios consumidores de este módulo

Este módulo será consumido intensamente por otros módulos y también por usuarios institucionales autorizados.

### 7.1 Consumidores funcionales

- postulaciones y admisiones;
- matrícula e inscripción;
- estructura académica;
- horarios;
- asistencia y licencias;
- evaluación y boletines;
- portal de padres, estudiantes y docentes;
- cuentas por cobrar;
- comunicación institucional;
- RUDE e interoperabilidad regulatoria;
- enfermería, psicopedagogía y convivencia;
- documental y activos digitales;
- IAM.

### 7.2 Actores humanos típicos con acceso directo parcial

- secretaría académica;
- admisiones;
- operadores de datos maestros;
- cobranzas y caja;
- dirección académica;
- dirección de inicial, primaria o secundaria;
- coordinación disciplinaria;
- enfermería o bienestar, bajo permisos específicos;
- auditoría interna;
- y ciertos usuarios autorizados del portal administrativo.

## 8. Objetivo técnico del equipo backend

El equipo backend debe entregar un componente robusto que permita:

- crear y consultar personas maestras;
- gestionar identidades legales;
- gestionar nombres y alias relevantes;
- gestionar emails, teléfonos y direcciones múltiples;
- gestionar households y membresías de household;
- registrar relaciones entre personas;
- registrar contactos de emergencia;
- registrar responsables de tutela, retiro o pago;
- registrar afiliaciones institucionales;
- registrar roles institucionales activos e históricos;
- registrar cargos y nombramientos con vigencia;
- vincular la persona con perfiles de postulante, estudiante, docente, empleado, exalumno o tercero institucional;
- vincular la persona con IAM;
- vincular documentación personal y familiar con el módulo documental y de activos digitales;
- separar datos sensibles en un subdominio restringido;
- auditar todos los cambios;
- exponer APIs limpias para módulos consumidores;
- emitir eventos de dominio relevantes.

Además, el equipo backend debe integrar este módulo con:

- iam-service;
- core-platform;
- módulos académicos ya construidos;
- admisiones y postulaciones;
- cuentas por cobrar y estado de cuenta;
- regulatorio y RUDE;
- módulo documental y activos digitales;
- observabilidad fuerte, porque este módulo será consumido por prácticamente todo el ecosistema.

## 9. Capacidades transversales obligatorias del módulo

### 9.1 Multiidioma desde el inicio

El módulo debe manejar multiidioma al menos para catálogos de tipos de relación, tipos de household, tipos de contacto, tipos de dirección, roles institucionales y estados.

### 9.2 Parametrización real para colegios bolivianos

Debe poder adaptarse a distintos colegios con distintas combinaciones de roles familiares, tipos de apoderado, reglas de contacto y políticas de autorización.

### 9.3 Multisede si el colegio lo requiere

Las afiliaciones, cargos y algunos vínculos institucionales deben poder contextualizarse por sede.

### 9.4 Multiplicidad de roles sobre la misma persona

Una persona puede ser:

- postulante;
- estudiante;
- exalumno;
- padre o madre;
- tutor;
- apoderado;
- responsable de pago;
- contacto de emergencia;
- docente;
- administrativo;
- autoridad;
- y más de una cosa a la vez.

### 9.5 Separación estricta entre persona, rol, cargo, household y cuenta digital

Esto no es opcional. Debe quedar modelado de forma explícita.

### 9.6 Gestión documental integrada desde el inicio

Toda persona o relación familiar debe poder vincular:

- documento de identidad;
- fotografía institucional;
- documentos de tutela o representación;
- autorizaciones de retiro;
- respaldos de responsabilidad financiera;
- pólizas o coberturas cuando aplique;
- y evidencias regulatorias o familiares.

### 9.7 Datos sensibles con acceso restringido

Tipo de sangre, restricciones críticas, contactos delicados, cobertura de seguro, observaciones médicas mínimas relevantes para emergencia u otra información sensible deben vivir en un subdominio restringido con seguridad fuerte, auditoría y mínimo privilegio.

## 10. La gran decisión de diseño: núcleo general + hogar/familia + subdominio sensible restringido

La arquitectura correcta no es crear una sola tabla monstruosa de personas con 300 columnas. La arquitectura correcta es dividir el dominio en tres capas:

### 10.1 Núcleo general de persona e identidad institucional

Aquí vive todo lo que puede ser consumido por múltiples dominios de forma relativamente general, bajo permisos razonables:

- persona;
- identidad legal;
- nombres;
- contactos;
- direcciones;
- relaciones básicas;
- roles;
- cargos;
- documentos generales;
- vínculo con IAM.

### 10.2 Núcleo relacional de familia/household/apoderados

Aquí vive todo lo que hace que el colegio funcione realmente como colegio boliviano:

- household o grupo familiar;
- miembros del household;
- relaciones padre/madre/tutor/apoderado;
- responsable de pago;
- contacto de emergencia;
- autorización de retiro;
- convivencia de múltiples responsables con distintas capacidades.

### 10.3 Subdominio sensible restringido

Aquí vive lo que requiere acceso mínimo, trazabilidad fuerte y segmentación de permisos:

- tipo de sangre;
- alertas médicas de emergencia;
- coberturas;
- restricciones críticas;
- observaciones relevantes para atención urgente;
- y documentación altamente sensible.

Esto permite un diseño limpio, seguro y escalable.

## 11. Relación estratégica con IAM, admisiones, académico, finanzas, regulatorio, bienestar y documental

### 11.1 Relación con IAM

IAM no crea la persona. IAM se enlaza con la persona. Una persona puede existir sin cuenta. Una cuenta puede suspenderse sin eliminar la persona.

### 11.2 Relación con admisiones

Admisiones no crea una persona “de mentira”. Consume o crea la persona base y luego crea el perfil de postulante sobre ella.

### 11.3 Relación con académico

Académico no crea una persona desde cero. Crea perfiles como estudiante apoyándose sobre la persona y sobre sus relaciones familiares.

### 11.4 Relación con cuentas por cobrar

CxC no define quién es la persona. Consume a la persona y a las relaciones de responsabilidad financiera para facturar, cobrar, mostrar estado de cuenta y construir planes de pago.

### 11.5 Relación con RUDE e interoperabilidad regulatoria

El módulo regulatorio no redefine a la familia ni al estudiante. Consume desde este módulo las identidades, households y relaciones necesarias para construir la salida oficial.

### 11.6 Relación con bienestar, enfermería, convivencia y protección

Estos módulos pueden consumir el subdominio sensible bajo permisos estrictos y visibilidad mínima.

### 11.7 Relación con documental y activos digitales

La documentación personal, familiar, de apoderamiento, de tutela y la documentación sensible controlada deben apoyarse en el módulo documental y en el módulo transversal digital para custodia técnica.

## 12. Ubicación dentro del monolito multimodelo hexagonal

Una ubicación natural sería:

```text
core-platform
  └── institutional-person-household-registry
       ├── domain
       │   ├── person
       │   ├── personlegalidentity
       │   ├── personnameprofile
       │   ├── personcontactpoint
       │   ├── personaddress
       │   ├── household
       │   ├── householdmembership
       │   ├── guardianrelationship
       │   ├── emergencycontact
       │   ├── financialresponsiblerelationship
       │   ├── pickupauthorization
       │   ├── institutionalaffiliation
       │   ├── institutionalroleassignment
       │   ├── schoolorganizationalappointment
       │   ├── personidentitylink
       │   ├── persondocumentreference
       │   ├── personsensitiveprofile
       │   ├── personsensitivecoverage
       │   ├── personconflict
       │   ├── auditisnapshot
       │   ├── personevidencerelation
       │   └── valueobject
       ├── application
       │   ├── usecase
       │   ├── service
       │   ├── dto
       │   └── policy
       ├── infrastructure
       │   ├── persistence
       │   │   └── postgres
       │   ├── projection
       │   │   └── mongo
       │   ├── cache
       │   │   └── redis
       │   ├── iam
       │   ├── documentgovernance
       │   ├── digitalasset
       │   ├── mastercatalogs
       │   ├── events
       │   └── observability
       ├── api
       │   ├── admin
       │   ├── internal
       │   └── restricted
       └── bootstrap
```

La lógica de identidad humana y familiar no debe quedar repartida entre admisiones, académico, finanzas e IAM. Debe vivir donde corresponde: en una base transversal y controlada.

## 13. Lenguaje ubicuo oficial del módulo

El equipo debe cerrar un vocabulario oficial y consistente. Términos recomendados:

- Person;
- Legal Identity;
- Name Profile;
- Contact Point;
- Address;
- Household;
- Household Membership;
- Guardian Relationship;
- Emergency Contact;
- Financial Responsible Relationship;
- Pickup Authorization;
- Institutional Affiliation;
- Role Assignment;
- School Organizational Appointment;
- Identity Link;
- Sensitive Profile;
- Coverage Record;
- Historical Person Reconstruction.

En español:

- persona;
- identidad legal;
- perfil nominal;
- punto de contacto;
- dirección;
- household o grupo familiar;
- membresía de household;
- relación de apoderado/tutoría;
- contacto de emergencia;
- relación de responsabilidad financiera;
- autorización de retiro;
- afiliación institucional;
- asignación de rol;
- nombramiento organizacional escolar;
- vínculo de identidad digital;
- perfil sensible;
- registro de cobertura;
- reconstrucción histórica de persona.

Todo el equipo debe usar exactamente este vocabulario para no mezclar persona, usuario, estudiante, padre, apoderado, tutor, responsable, household o cargo como si fueran lo mismo.

## 14. Casos de uso de negocio del módulo

**Casos de uso de comando**

- CreatePerson
- RegisterLegalIdentity
- UpdatePersonCoreProfile
- AddPersonContactPoint
- AddPersonAddress
- CreateHousehold
- AddHouseholdMembership
- RegisterGuardianRelationship
- RegisterEmergencyContact
- RegisterFinancialResponsibleRelationship
- RegisterPickupAuthorization
- AssignInstitutionalRole
- CreateSchoolOrganizationalAppointment
- LinkPersonToIamIdentity
- RegisterPersonDocumentReference
- RegisterSensitiveProfileData
- UpdateSensitiveCoverage
- AssociatePersonEvidence
- SimulatePersonMergeImpact

**Casos de uso de consulta**

- GetPersonById
- FindPersonByDocument
- GetPersonContacts
- GetPersonAddresses
- GetHouseholdByPerson
- GetHouseholdMembers
- GetGuardianRelationships
- GetEmergencyContacts
- GetFinancialResponsibles
- GetPickupAuthorizations
- GetPersonInstitutionalRoles
- GetPersonAppointments
- GetPersonIdentityLink
- GetPersonSensitiveProfile
- ExplainPersonInstitutionalState
- SearchPersons
- GetHistoricalPersonState

Cada caso de uso debe tener entrada, validaciones, permisos, salida y eventos asociados.

## 15. Diseño de dominio recomendado

### 15.1 Aggregate Root principal: Person

Representa a la persona base como entidad institucional maestra. No es usuario, no es estudiante, no es padre y no es cargo. Es la entidad humana de referencia para toda la plataforma.

### 15.2 Entidad hija: PersonLegalIdentity

Representa la identidad documental/legal principal y sus variantes o registros asociados.

### 15.3 Entidad hija: PersonNameProfile

Representa nombres oficiales, nombres alternativos, nombres preferidos o variantes necesarias según el negocio.

### 15.4 Entidad hija: PersonContactPoint

Representa correos, teléfonos u otros medios de contacto, con tipo, preferencia y estado.

### 15.5 Entidad hija: PersonAddress

Representa direcciones físicas o de referencia, con propósito y vigencia.

### 15.6 Aggregate complementario: Household

Representa el grupo familiar u hogar funcional con relevancia escolar. No reemplaza relaciones entre personas, pero las organiza dentro de un agregado institucional muy útil para colegio.

### 15.7 Entidad hija: HouseholdMembership

Representa la pertenencia de una persona a un household, con rol dentro del hogar y vigencia.

### 15.8 Entidad hija especializada: GuardianRelationship

Representa relaciones de padre, madre, tutor, apoderado o representante respecto del estudiante, con capacidad legal o institucional explícita.

### 15.9 Entidad hija especializada: EmergencyContact

Representa el contacto de emergencia de la persona, con prioridad y vigencia.

### 15.10 Entidad hija especializada: FinancialResponsibleRelationship

Representa la relación con una persona responsable de obligaciones económicas.

### 15.11 Entidad hija especializada: PickupAuthorization

Representa la autorización de retiro o recogida del estudiante por una persona concreta.

### 15.12 Entidad hija: InstitutionalAffiliation

Representa la afiliación general de una persona al colegio, sede o unidad.

### 15.13 Entidad hija: InstitutionalRoleAssignment

Representa el rol institucional funcional que la persona cumple: postulante, estudiante, docente, administrativo, autoridad, apoderado, responsable de pago, etc.

### 15.14 Entidad hija: SchoolOrganizationalAppointment

Representa el cargo o nombramiento formal que la persona ocupa, con unidad, vigencia y soporte documental.

### 15.15 Entidad hija: PersonIdentityLink

Representa el enlace entre persona e identidad digital en IAM.

### 15.16 Entidad hija: PersonDocumentReference

Representa documentos personales o familiares relevantes vinculados al documental y activos digitales.

### 15.17 Aggregate restringido: PersonSensitiveProfile

Representa el subdominio sensible: sangre, alertas críticas, coberturas y datos mínimos de emergencia.

### 15.18 Entidad hija restringida: PersonSensitiveCoverage

Representa cobertura de seguro o protección relevante.

### 15.19 Entidad de soporte: PersonConflict

Representa inconsistencias detectadas entre identidades, duplicados, relaciones o vínculos institucionales.

### 15.20 Entidad de soporte: PersonAuditSnapshot

Guarda snapshots completos del estado de la persona o de partes sensibles en momentos relevantes.

### 15.21 Entidad de soporte: PersonEvidenceRelation

Representa la relación canónica entre la persona o el household y activos/documentos que la respaldan.

## 16. Reglas de negocio críticas

- toda persona debe poder existir aunque aún no tenga usuario IAM;
- una persona puede tener múltiples roles institucionales a lo largo del tiempo o simultáneamente;
- un cargo institucional no puede modelarse como tipo de persona;
- toda identidad documental legal debe validarse bajo reglas de unicidad razonables;
- los contactos y direcciones deben soportar múltiples registros con propósito y vigencia;
- las relaciones entre personas deben registrar tipo, vigencia y trazabilidad;
- un estudiante puede tener múltiples apoderados o responsables, pero con capacidades distintas;
- la responsabilidad financiera debe quedar separada de la tutela o del parentesco;
- la autorización de retiro debe ser explícita y controlable;
- los datos sensibles deben quedar fuera del acceso general;
- ninguna funcionalidad sectorial debe duplicar innecesariamente los datos maestros de persona;
- todo nombramiento institucional debe tener vigencia y soporte documental cuando aplique;
- todo vínculo con IAM debe ser trazable;
- todo conflicto de persona o duplicidad debe quedar registrado y no resolverse en silencio.

## 17. Diseño físico principal en PostgreSQL

A continuación se presenta el diseño físico principal del módulo transversal para PeopleCole. Después de cada tabla se explica para qué sirve y luego se documenta cada atributo de forma clara.

**Nota previa importante**
Este módulo es transversal. Sus tablas no deben usarse como dumping de información ajena. Académico, admisiones, CxC, regulatorio, documental e IAM consumen esta base, pero no deben contaminarla con lógica sectorial que no le corresponde.

### 17.1 Tabla pc_person

```sql
CREATE TABLE pc_person (
    id                              BIGSERIAL PRIMARY KEY,
    person_code                     VARCHAR(80) NOT NULL,
    person_type_code                VARCHAR(50) NOT NULL,
    core_status                     VARCHAR(50) NOT NULL,
    primary_photo_asset_id          BIGINT,
    created_at                      TIMESTAMP NOT NULL DEFAULT NOW(),
    created_by                      VARCHAR(100),
    updated_at                      TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_by                      VARCHAR(100),
    version                         BIGINT NOT NULL DEFAULT 0,
    CONSTRAINT uk_pc_person_code UNIQUE (person_code)
);

CREATE INDEX idx_pc_person_type_status ON pc_person (person_type_code, core_status);
```

**¿Para qué sirve esta tabla?**

Esta tabla representa la persona base institucional. Es la raíz maestra del módulo y el punto de verdad transversal de identidad humana dentro de la plataforma PeopleCole.

Su función principal es permitir que todas las demás capacidades se apoyen en una sola entidad de persona, en lugar de duplicar individuos por dominio.

También sirve como frontera limpia entre la persona y sus perfiles, roles, cargos, households y relaciones especializadas.

**Explicación atributo por atributo**

- id: identificador técnico único de la persona.
- person_code: código funcional interno de la persona dentro del ecosistema institucional.
- person_type_code: tipo base o clasificación general de la persona. No debe confundirse con rol o cargo; sirve para clasificaciones de alto nivel y control del dominio.
- core_status: estado principal de la persona dentro del registro maestro.
- primary_photo_asset_id: referencia al activo digital de la fotografía institucional principal, si existe.
- created_at: fecha y hora de creación del registro maestro de persona.
- created_by: actor que creó el registro.
- updated_at: fecha y hora de última modificación.
- updated_by: actor de última modificación.
- version: campo de concurrencia optimista para @Version.

### 17.2 Tabla pc_person_legal_identity

```sql
CREATE TABLE pc_person_legal_identity (
    id                              BIGSERIAL PRIMARY KEY,
    person_id                       BIGINT NOT NULL REFERENCES pc_person(id),
    document_type_code              VARCHAR(50) NOT NULL,
    document_number                 VARCHAR(120) NOT NULL,
    issuing_country_code            VARCHAR(20),
    issuing_region_code             VARCHAR(50),
    legal_identity_status           VARCHAR(50) NOT NULL,
    is_primary                      BOOLEAN NOT NULL DEFAULT TRUE,
    verified_at                     TIMESTAMP,
    verified_by                     VARCHAR(100),
    version                         BIGINT NOT NULL DEFAULT 0,
    CONSTRAINT uk_pc_person_legal_identity UNIQUE (document_type_code, document_number)
);

CREATE INDEX idx_pc_person_legal_identity_person ON pc_person_legal_identity (person_id);
CREATE INDEX idx_pc_person_legal_identity_status ON pc_person_legal_identity (legal_identity_status);
```

**¿Para qué sirve esta tabla?**

Esta tabla representa la identidad legal/documental principal o secundaria de la persona. Es crucial porque el colegio debe identificar formalmente a la persona de manera unívoca y trazable.

Su función principal es separar la persona abstracta de sus documentos legales concretos, permitiendo validaciones, verificaciones y control de duplicidad.

También permite soportar escenarios donde la persona tenga más de un documento relevante o requiera verificación posterior.

**Explicación atributo por atributo**

- id: identificador técnico único de la identidad legal.
- person_id: persona a la que pertenece esta identidad legal.
- document_type_code: tipo de documento legal o civil.
- document_number: número del documento legal.
- issuing_country_code: país emisor del documento.
- issuing_region_code: región, departamento o entidad emisora cuando aplique.
- legal_identity_status: estado de esta identidad dentro del sistema.
- is_primary: indica si este es el documento principal de referencia.
- verified_at: fecha y hora de verificación institucional.
- verified_by: actor que validó o verificó el documento.
- version: concurrencia optimista.

### 17.3 Tabla pc_person_name_profile

```sql
CREATE TABLE pc_person_name_profile (
    id                              BIGSERIAL PRIMARY KEY,
    person_id                       BIGINT NOT NULL REFERENCES pc_person(id),
    name_type_code                  VARCHAR(50) NOT NULL,
    first_name                      VARCHAR(150) NOT NULL,
    middle_name                     VARCHAR(150),
    last_name                       VARCHAR(150) NOT NULL,
    second_last_name                VARCHAR(150),
    display_name                    VARCHAR(255),
    is_primary                      BOOLEAN NOT NULL DEFAULT TRUE,
    version                         BIGINT NOT NULL DEFAULT 0
);

CREATE INDEX idx_pc_person_name_profile_person ON pc_person_name_profile (person_id);
CREATE INDEX idx_pc_person_name_profile_type ON pc_person_name_profile (name_type_code, is_primary);
```

**¿Para qué sirve esta tabla?**

Esta tabla representa el perfil nominal de la persona. Es importante porque una persona puede requerir más de una representación de nombre: oficial, preferida, abreviada o social, según la política institucional.

Su función principal es desacoplar los nombres de la persona base, permitiendo flexibilidad sin ensuciar el agregado raíz.

También ayuda a construir una experiencia institucional más correcta y a la vez más humana.

**Explicación atributo por atributo**

- id: identificador técnico único del perfil de nombre.
- person_id: persona a la que pertenece este perfil nominal.
- name_type_code: tipo de nombre o representación nominal.
- first_name: primer nombre de la persona.
- middle_name: segundo nombre o nombre intermedio.
- last_name: primer apellido de la persona.
- second_last_name: segundo apellido de la persona.
- display_name: forma de presentación recomendada del nombre completo.
- is_primary: indica si este perfil nominal es el principal.
- version: concurrencia optimista.

### 17.4 Tabla pc_person_contact_point

```sql
CREATE TABLE pc_person_contact_point (
    id                              BIGSERIAL PRIMARY KEY,
    person_id                       BIGINT NOT NULL REFERENCES pc_person(id),
    contact_type_code               VARCHAR(50) NOT NULL,
    contact_value                   VARCHAR(255) NOT NULL,
    usage_code                      VARCHAR(50),
    contact_status                  VARCHAR(50) NOT NULL,
    is_primary                      BOOLEAN NOT NULL DEFAULT FALSE,
    verified_at                     TIMESTAMP,
    version                         BIGINT NOT NULL DEFAULT 0
);

CREATE INDEX idx_pc_person_contact_point_person ON pc_person_contact_point (person_id);
CREATE INDEX idx_pc_person_contact_point_type ON pc_person_contact_point (contact_type_code, contact_status, is_primary);
```

**¿Para qué sirve esta tabla?**

Esta tabla representa los puntos de contacto de la persona, como correo electrónico, teléfono o futuros canales homologables. El colegio necesita múltiples contactos, no uno solo.

Su función principal es evitar que los medios de contacto queden embebidos rígidamente dentro de la persona base.

También permite controlar prioridad, verificación, vigencia y propósito del contacto.

**Explicación atributo por atributo**

- id: identificador técnico único del punto de contacto.
- person_id: persona a la que pertenece el contacto.
- contact_type_code: tipo de contacto, como EMAIL o PHONE.
- contact_value: valor concreto del contacto.
- usage_code: uso del contacto, como PERSONAL, INSTITUTIONAL, EMERGENCY o FINANCIAL.
- contact_status: estado del contacto dentro del sistema.
- is_primary: indica si es el contacto principal para su tipo o uso.
- verified_at: fecha y hora de verificación del contacto.
- version: concurrencia optimista.

### 17.5 Tabla pc_person_address

```sql
CREATE TABLE pc_person_address (
    id                              BIGSERIAL PRIMARY KEY,
    person_id                       BIGINT NOT NULL REFERENCES pc_person(id),
    address_type_code               VARCHAR(50) NOT NULL,
    country_code                    VARCHAR(20),
    region_name                     VARCHAR(120),
    city_name                       VARCHAR(120),
    district_name                   VARCHAR(120),
    address_line_1                  VARCHAR(255) NOT NULL,
    address_line_2                  VARCHAR(255),
    postal_code                     VARCHAR(30),
    is_primary                      BOOLEAN NOT NULL DEFAULT FALSE,
    address_status                  VARCHAR(50) NOT NULL,
    version                         BIGINT NOT NULL DEFAULT 0
);

CREATE INDEX idx_pc_person_address_person ON pc_person_address (person_id);
CREATE INDEX idx_pc_person_address_type ON pc_person_address (address_type_code, address_status, is_primary);
```

**¿Para qué sirve esta tabla?**

Esta tabla representa las direcciones de la persona. El colegio necesita soportar múltiples domicilios o ubicaciones de referencia, cada uno con un uso distinto.

Su función principal es separar el domicilio o dirección de la persona base y permitir más de una dirección con vigencia funcional.

También ayuda a relaciones con notificación, facturación, emergencias o logística.

**Explicación atributo por atributo**

- id: identificador técnico único de la dirección.
- person_id: persona a la que pertenece la dirección.
- address_type_code: tipo de dirección, como HOME, BILLING, EMERGENCY_REFERENCE o TEMPORARY.
- country_code: país de la dirección.
- region_name: región, departamento o estado.
- city_name: ciudad de la dirección.
- district_name: zona, barrio o distrito.
- address_line_1: línea principal de dirección.
- address_line_2: línea complementaria de dirección.
- postal_code: código postal, si aplica.
- is_primary: indica si es la dirección principal para su uso.
- address_status: estado de la dirección dentro del sistema.
- version: concurrencia optimista.

### 17.6 Tabla pc_household

```sql
CREATE TABLE pc_household (
    id                              BIGSERIAL PRIMARY KEY,
    household_code                  VARCHAR(80) NOT NULL,
    household_name                  VARCHAR(220) NOT NULL,
    household_type_code             VARCHAR(50) NOT NULL,
    household_status                VARCHAR(50) NOT NULL,
    primary_billing_address_id      BIGINT REFERENCES pc_person_address(id),
    created_at                      TIMESTAMP NOT NULL DEFAULT NOW(),
    created_by                      VARCHAR(100),
    updated_at                      TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_by                      VARCHAR(100),
    version                         BIGINT NOT NULL DEFAULT 0,
    CONSTRAINT uk_pc_household_code UNIQUE (household_code)
);
```

**¿Para qué sirve esta tabla?**

Esta tabla representa el household o grupo familiar funcional del colegio. Es una entidad estratégica para colegios bolivianos porque agrupa personas relacionadas para fines de tutoría, comunicación, cobro y gobierno institucional.

Su función principal es permitir que el colegio no tenga que reconstruir la familia en cada módulo de manera informal o inconsistente.

También prepara el terreno para portales de apoderados, estado de cuenta familiar, comunicación y reglas de retiro o responsabilidad.

**Explicación atributo por atributo**

- id: identificador técnico único del household.
- household_code: código funcional interno del grupo familiar.
- household_name: nombre visible del household.
- household_type_code: tipo de household o agrupación familiar.
- household_status: estado operativo del household.
- primary_billing_address_id: dirección principal de facturación si se centraliza a nivel household.
- created_at: fecha y hora de creación.
- created_by: actor creador.
- updated_at: fecha y hora de última modificación.
- updated_by: actor de última modificación.
- version: concurrencia optimista.

### 17.7 Tabla pc_household_membership

```sql
CREATE TABLE pc_household_membership (
    id                              BIGSERIAL PRIMARY KEY,
    household_id                    BIGINT NOT NULL REFERENCES pc_household(id),
    person_id                       BIGINT NOT NULL REFERENCES pc_person(id),
    membership_role_code            VARCHAR(50) NOT NULL,
    membership_status               VARCHAR(50) NOT NULL,
    effective_from                  DATE,
    effective_to                    DATE,
    is_primary_guardian_group       BOOLEAN NOT NULL DEFAULT FALSE,
    version                         BIGINT NOT NULL DEFAULT 0,
    CONSTRAINT uk_pc_household_membership UNIQUE (household_id, person_id)
);

CREATE INDEX idx_pc_household_membership_household ON pc_household_membership (household_id);
CREATE INDEX idx_pc_household_membership_person ON pc_household_membership (person_id);
```

**¿Para qué sirve esta tabla?**

Esta tabla representa la pertenencia de una persona a un household. Es lo que permite modelar formalmente que una familia o grupo funcional está compuesto por varias personas.

Su función principal es separar la agrupación familiar de las relaciones específicas entre personas, permitiendo que una persona esté asociada a un hogar con un rol determinado.

También sirve como base para portales familiares, estados de cuenta y reglas de contacto o visibilidad.

**Explicación atributo por atributo**

- id: identificador técnico único de la membresía.
- household_id: household al que pertenece la persona.
- person_id: persona integrante del household.
- membership_role_code: rol de la persona dentro del household.
- membership_status: estado de la membresía.
- effective_from: fecha de inicio de vigencia.
- effective_to: fecha de fin de vigencia.
- is_primary_guardian_group: indica si esta membresía participa del grupo primario de guardianes o referentes del estudiante.
- version: concurrencia optimista.

### 17.8 Tabla pc_guardian_relationship

```sql
CREATE TABLE pc_guardian_relationship (
    id                              BIGSERIAL PRIMARY KEY,
    student_person_id               BIGINT NOT NULL REFERENCES pc_person(id),
    guardian_person_id              BIGINT NOT NULL REFERENCES pc_person(id),
    household_id                    BIGINT REFERENCES pc_household(id),
    guardian_type_code              VARCHAR(50) NOT NULL,
    legal_authority_status          VARCHAR(50) NOT NULL,
    school_authority_status         VARCHAR(50) NOT NULL,
    effective_from                  DATE,
    effective_to                    DATE,
    notes                           TEXT,
    version                         BIGINT NOT NULL DEFAULT 0
);

CREATE INDEX idx_pc_guardian_relationship_student ON pc_guardian_relationship (student_person_id);
CREATE INDEX idx_pc_guardian_relationship_guardian ON pc_guardian_relationship (guardian_person_id);
```

**¿Para qué sirve esta tabla?**

Esta tabla representa la relación formal de padre, madre, tutor, apoderado o representante respecto del estudiante. Es mucho más importante en colegio que en universidad, por eso debe ser entidad explícita.

Su función principal es distinguir parentesco biológico, representación legal y autoridad escolar, que pueden no coincidir.

También permite controlar vigencia, documentación y alcance de la representación.

**Explicación atributo por atributo**

- id: identificador técnico único de la relación de apoderado o tutor.
- student_person_id: persona beneficiaria o estudiante representado.
- guardian_person_id: persona que ejerce el rol de tutor/apoderado.
- household_id: household asociado si aplica.
- guardian_type_code: tipo de relación de guardianía.
- legal_authority_status: estado de autoridad legal.
- school_authority_status: estado de autoridad reconocida por el colegio.
- effective_from: fecha de inicio.
- effective_to: fecha de fin.
- notes: observaciones complementarias.
- version: concurrencia optimista.

### 17.9 Tabla pc_emergency_contact

```sql
CREATE TABLE pc_emergency_contact (
    id                              BIGSERIAL PRIMARY KEY,
    person_id                       BIGINT NOT NULL REFERENCES pc_person(id),
    contact_person_id               BIGINT REFERENCES pc_person(id),
    emergency_priority              INTEGER NOT NULL DEFAULT 1,
    relationship_label              VARCHAR(100),
    emergency_status                VARCHAR(50) NOT NULL,
    version                         BIGINT NOT NULL DEFAULT 0
);

CREATE INDEX idx_pc_emergency_contact_person ON pc_emergency_contact (person_id);
CREATE INDEX idx_pc_emergency_contact_priority ON pc_emergency_contact (emergency_priority, emergency_status);
```

**¿Para qué sirve esta tabla?**

Esta tabla representa el contacto de emergencia de la persona. Aunque parte de esta relación puede reflejarse en otras tablas, el dominio exige una entidad más explícita y operativa.

Su función principal es permitir respuesta rápida y clara en escenarios de contingencia.

También facilita orden de prioridad y control de vigencia sin sobrecargar otras relaciones genéricas.

**Explicación atributo por atributo**

- id: identificador técnico único del contacto de emergencia.
- person_id: persona protegida por este contacto de emergencia.
- contact_person_id: persona vinculada como contacto de emergencia cuando también existe en el registro maestro.
- emergency_priority: prioridad del contacto frente a otros posibles contactos.
- relationship_label: etiqueta descriptiva de la relación.
- emergency_status: estado del contacto de emergencia.
- version: concurrencia optimista.

### 17.10 Tabla pc_financial_responsible_relationship

```sql
CREATE TABLE pc_financial_responsible_relationship (
    id                              BIGSERIAL PRIMARY KEY,
    beneficiary_person_id           BIGINT NOT NULL REFERENCES pc_person(id),
    responsible_person_id           BIGINT NOT NULL REFERENCES pc_person(id),
    household_id                    BIGINT REFERENCES pc_household(id),
    responsibility_status           VARCHAR(50) NOT NULL,
    effective_from                  DATE,
    effective_to                    DATE,
    notes                           TEXT,
    version                         BIGINT NOT NULL DEFAULT 0
);

CREATE INDEX idx_pc_financial_responsible_beneficiary ON pc_financial_responsible_relationship (beneficiary_person_id);
CREATE INDEX idx_pc_financial_responsible_responsible ON pc_financial_responsible_relationship (responsible_person_id);
```

**¿Para qué sirve esta tabla?**

Esta tabla representa la relación de responsabilidad económica entre personas. Es extremadamente importante en colegios privados, especialmente para estudiantes menores o familias con distintos responsables.

Su función principal es separar la responsabilidad financiera del simple parentesco o tutela.

También prepara el terreno para integración limpia con cuentas por cobrar y cobranzas.

**Explicación atributo por atributo**

- id: identificador técnico único de la relación financiera.
- beneficiary_person_id: persona beneficiaria o representada económicamente.
- responsible_person_id: persona responsable de las obligaciones económicas.
- household_id: household asociado si aplica.
- responsibility_status: estado actual de la relación.
- effective_from: fecha de inicio de vigencia.
- effective_to: fecha de fin de vigencia.
- notes: observaciones complementarias.
- version: concurrencia optimista.

### 17.11 Tabla pc_pickup_authorization

```sql
CREATE TABLE pc_pickup_authorization (
    id                              BIGSERIAL PRIMARY KEY,
    student_person_id               BIGINT NOT NULL REFERENCES pc_person(id),
    authorized_person_id            BIGINT NOT NULL REFERENCES pc_person(id),
    authorization_status            VARCHAR(50) NOT NULL,
    effective_from                  DATE,
    effective_to                    DATE,
    authorization_scope_code        VARCHAR(50),
    notes                           TEXT,
    version                         BIGINT NOT NULL DEFAULT 0
);

CREATE INDEX idx_pc_pickup_authorization_student ON pc_pickup_authorization (student_person_id);
CREATE INDEX idx_pc_pickup_authorization_authorized ON pc_pickup_authorization (authorized_person_id);
```

**¿Para qué sirve esta tabla?**

Esta tabla representa la autorización de retiro o recogida del estudiante por una persona concreta. Es una necesidad muy propia del entorno escolar.

Su función principal es permitir control institucional y de seguridad sobre quién puede retirar al estudiante.

También ayuda a integración futura con portales de apoderados, seguridad y eventos diarios de salida.

**Explicación atributo por atributo**

- id: identificador técnico único de la autorización.
- student_person_id: estudiante al que aplica la autorización.
- authorized_person_id: persona autorizada para retiro.
- authorization_status: estado de la autorización.
- effective_from: fecha de inicio.
- effective_to: fecha de fin.
- authorization_scope_code: alcance de la autorización.
- notes: observaciones complementarias.
- version: concurrencia optimista.

### 17.12 Tabla pc_institutional_affiliation

```sql
CREATE TABLE pc_institutional_affiliation (
    id                              BIGSERIAL PRIMARY KEY,
    person_id                       BIGINT NOT NULL REFERENCES pc_person(id),
    affiliation_type_code           VARCHAR(50) NOT NULL,
    site_id                         BIGINT,
    organizational_unit_id          BIGINT,
    affiliation_status              VARCHAR(50) NOT NULL,
    effective_from                  DATE,
    effective_to                    DATE,
    version                         BIGINT NOT NULL DEFAULT 0
);

CREATE INDEX idx_pc_institutional_affiliation_person ON pc_institutional_affiliation (person_id);
CREATE INDEX idx_pc_institutional_affiliation_type_status ON pc_institutional_affiliation (affiliation_type_code, affiliation_status);
```

**¿Para qué sirve esta tabla?**

Esta tabla representa la afiliación institucional general de la persona. No define rol específico de negocio, pero sí el hecho de que la persona se encuentra vinculada a una sede o unidad del colegio.

Su función principal es modelar vínculo institucional de forma transversal.

También es muy útil para historial y segmentación sin obligar a depender de módulos sectoriales específicos.

**Explicación atributo por atributo**

- id: identificador técnico único de la afiliación.
- person_id: persona vinculada.
- affiliation_type_code: tipo de afiliación institucional.
- site_id: sede asociada a la afiliación.
- organizational_unit_id: unidad organizacional asociada.
- affiliation_status: estado de la afiliación.
- effective_from: fecha de inicio de vigencia.
- effective_to: fecha de fin de vigencia.
- version: concurrencia optimista.

### 17.13 Tabla pc_institutional_role_assignment

```sql
CREATE TABLE pc_institutional_role_assignment (
    id                              BIGSERIAL PRIMARY KEY,
    person_id                       BIGINT NOT NULL REFERENCES pc_person(id),
    role_code                       VARCHAR(50) NOT NULL,
    site_id                         BIGINT,
    organizational_unit_id          BIGINT,
    role_status                     VARCHAR(50) NOT NULL,
    effective_from                  DATE,
    effective_to                    DATE,
    version                         BIGINT NOT NULL DEFAULT 0
);

CREATE INDEX idx_pc_institutional_role_assignment_person ON pc_institutional_role_assignment (person_id);
CREATE INDEX idx_pc_institutional_role_assignment_role_status ON pc_institutional_role_assignment (role_code, role_status);
```

**¿Para qué sirve esta tabla?**

Esta tabla representa la asignación de rol institucional funcional de la persona. Aquí vive la idea de postulante, estudiante, docente, administrativo, autoridad, apoderado, responsable financiero, etc.

Su función principal es separar claramente la persona del papel que cumple en el ecosistema.

También permite una misma persona con múltiples roles simultáneos o históricos sin duplicidad absurda.

**Explicación atributo por atributo**

- id: identificador técnico único de la asignación de rol.
- person_id: persona a la que se asigna el rol.
- role_code: código del rol funcional institucional.
- site_id: sede sobre la cual aplica el rol cuando corresponda.
- organizational_unit_id: unidad organizacional vinculada al rol cuando corresponda.
- role_status: estado actual del rol asignado.
- effective_from: fecha de inicio del rol.
- effective_to: fecha de fin del rol.
- version: concurrencia optimista.

### 17.14 Tabla pc_school_organizational_appointment

```sql
CREATE TABLE pc_school_organizational_appointment (
    id                              BIGSERIAL PRIMARY KEY,
    person_id                       BIGINT NOT NULL REFERENCES pc_person(id),
    appointment_code                VARCHAR(50) NOT NULL,
    site_id                         BIGINT,
    organizational_unit_id          BIGINT,
    appointment_status              VARCHAR(50) NOT NULL,
    started_at                      TIMESTAMP NOT NULL,
    ended_at                        TIMESTAMP,
    appointment_document_asset_id   BIGINT,
    version                         BIGINT NOT NULL DEFAULT 0
);

CREATE INDEX idx_pc_school_organizational_appointment_person ON pc_school_organizational_appointment (person_id);
CREATE INDEX idx_pc_school_organizational_appointment_code_status ON pc_school_organizational_appointment (appointment_code, appointment_status);
```

**¿Para qué sirve esta tabla?**

Esta tabla representa el cargo o nombramiento formal que la persona ocupa. Aquí viven director general, director académico, director de nivel, coordinador, responsable de convivencia, enfermería, psicopedagogía y otros cargos equivalentes.

Su función principal es separar el cargo formal del rol general de la persona.

También permite vigencias, cambios de gestión y trazabilidad documental correcta de los nombramientos.

**Explicación atributo por atributo**

- id: identificador técnico único del nombramiento.
- person_id: persona nombrada.
- appointment_code: código del cargo o nombramiento.
- site_id: sede sobre la que aplica el nombramiento cuando corresponda.
- organizational_unit_id: unidad organizacional vinculada al nombramiento.
- appointment_status: estado del nombramiento.
- started_at: fecha y hora de inicio del cargo.
- ended_at: fecha y hora de fin del cargo.
- appointment_document_asset_id: activo digital que respalda el nombramiento formal, si existe.
- version: concurrencia optimista.

### 17.15 Tabla pc_person_identity_link

```sql
CREATE TABLE pc_person_identity_link (
    id                              BIGSERIAL PRIMARY KEY,
    person_id                       BIGINT NOT NULL REFERENCES pc_person(id),
    iam_subject_id                  VARCHAR(150) NOT NULL,
    identity_provider_code          VARCHAR(50),
    identity_link_status            VARCHAR(50) NOT NULL,
    linked_at                       TIMESTAMP NOT NULL,
    linked_by                       VARCHAR(100),
    version                         BIGINT NOT NULL DEFAULT 0,
    CONSTRAINT uk_pc_person_identity_link_subject UNIQUE (iam_subject_id)
);

CREATE INDEX idx_pc_person_identity_link_person ON pc_person_identity_link (person_id);
CREATE INDEX idx_pc_person_identity_link_status ON pc_person_identity_link (identity_link_status);
```

**¿Para qué sirve esta tabla?**

Esta tabla representa el enlace entre persona e identidad digital en IAM. Es crítica porque la persona no debe confundirse con el usuario, pero sí necesita una relación formal cuando existe acceso digital.

Su función principal es separar identidad humana e identidad tecnológica, manteniendo trazabilidad entre ambas.

También prepara la plataforma para SSO, federación y cambios de proveedor de identidad sin romper la persona.

**Explicación atributo por atributo**

- id: identificador técnico único del vínculo de identidad.
- person_id: persona enlazada a la identidad digital.
- iam_subject_id: identificador del sujeto o usuario en IAM.
- identity_provider_code: proveedor o fuente de identidad digital.
- identity_link_status: estado del vínculo de identidad.
- linked_at: fecha y hora de creación del vínculo.
- linked_by: actor o proceso que realizó el enlace.
- version: concurrencia optimista.

### 17.16 Tabla pc_person_document_reference

```sql
CREATE TABLE pc_person_document_reference (
    id                              BIGSERIAL PRIMARY KEY,
    person_id                       BIGINT NOT NULL REFERENCES pc_person(id),
    document_reference_type_code    VARCHAR(50) NOT NULL,
    asset_id                        BIGINT NOT NULL,
    reference_status                VARCHAR(50) NOT NULL,
    notes                           TEXT,
    created_at                      TIMESTAMP NOT NULL DEFAULT NOW(),
    created_by                      VARCHAR(100),
    version                         BIGINT NOT NULL DEFAULT 0
);

CREATE INDEX idx_pc_person_document_reference_person ON pc_person_document_reference (person_id);
CREATE INDEX idx_pc_person_document_reference_type_status ON pc_person_document_reference (document_reference_type_code, reference_status);
CREATE INDEX idx_pc_person_document_reference_asset ON pc_person_document_reference (asset_id);
```

**¿Para qué sirve esta tabla?**

Esta tabla representa las referencias documentales personales o familiares vinculadas a la persona. Aquí no vive el binario, sino la referencia institucional hacia documental y activos digitales.

Su función principal es mantener a la persona conectada con su documentación relevante sin capturar el archivo dentro del dominio.

También fortalece la integración con el módulo documental y el módulo transversal digital.

**Explicación atributo por atributo**

- id: identificador técnico único de la referencia documental.
- person_id: persona a la que pertenece el documento.
- document_reference_type_code: tipo de documento personal o familiar referenciado.
- asset_id: activo digital asociado.
- reference_status: estado de la referencia documental.
- notes: observaciones complementarias.
- created_at: fecha y hora de creación.
- created_by: actor que creó la referencia.
- version: concurrencia optimista.

### 17.17 Tabla pc_person_sensitive_profile

```sql
CREATE TABLE pc_person_sensitive_profile (
    id                              BIGSERIAL PRIMARY KEY,
    person_id                       BIGINT NOT NULL REFERENCES pc_person(id),
    blood_type_code                 VARCHAR(20),
    emergency_medical_notes         TEXT,
    sensitive_profile_status        VARCHAR(50) NOT NULL,
    last_reviewed_at                TIMESTAMP,
    last_reviewed_by                VARCHAR(100),
    version                         BIGINT NOT NULL DEFAULT 0,
    CONSTRAINT uk_pc_person_sensitive_profile_person UNIQUE (person_id)
);

CREATE INDEX idx_pc_person_sensitive_profile_status ON pc_person_sensitive_profile (sensitive_profile_status);
```

**¿Para qué sirve esta tabla?**

Esta tabla representa el perfil sensible restringido de la persona. Aquí deben vivir solo aquellos datos cuya exposición indiscriminada sería incorrecta o riesgosa.

Su función principal es separar claramente los datos sensibles del núcleo general de persona.

También permite aplicar seguridad fuerte, auditoría y mínimo privilegio de forma coherente.

**Explicación atributo por atributo**

- id: identificador técnico único del perfil sensible.
- person_id: persona a la que pertenece el perfil sensible.
- blood_type_code: tipo de sangre de la persona, si el negocio justifica almacenarlo.
- emergency_medical_notes: notas médicas críticas mínimas relevantes para atención de emergencia, no expediente clínico completo.
- sensitive_profile_status: estado del perfil sensible.
- last_reviewed_at: fecha y hora de última revisión autorizada.
- last_reviewed_by: actor autorizado que realizó la última revisión.
- version: concurrencia optimista.

### 17.18 Tabla pc_person_sensitive_coverage

```sql
CREATE TABLE pc_person_sensitive_coverage (
    id                              BIGSERIAL PRIMARY KEY,
    person_sensitive_profile_id     BIGINT NOT NULL REFERENCES pc_person_sensitive_profile(id),
    coverage_type_code              VARCHAR(50) NOT NULL,
    provider_name                   VARCHAR(255),
    policy_number                   VARCHAR(120),
    coverage_status                 VARCHAR(50) NOT NULL,
    effective_from                  DATE,
    effective_to                    DATE,
    notes                           TEXT,
    version                         BIGINT NOT NULL DEFAULT 0
);

CREATE INDEX idx_pc_person_sensitive_coverage_profile ON pc_person_sensitive_coverage (person_sensitive_profile_id);
CREATE INDEX idx_pc_person_sensitive_coverage_status ON pc_person_sensitive_coverage (coverage_status, coverage_type_code);
```

**¿Para qué sirve esta tabla?**

Esta tabla representa la cobertura sensible o seguro relevante asociado a la persona. Es especialmente útil si la institución maneja seguros estudiantiles, coberturas de salud o protección.

Su función principal es separar la cobertura institucional de la información general de persona.

También prepara el terreno para módulos futuros de bienestar, enfermería o protección escolar.

**Explicación atributo por atributo**

- id: identificador técnico único del registro de cobertura.
- person_sensitive_profile_id: perfil sensible al que pertenece la cobertura.
- coverage_type_code: tipo de cobertura o seguro.
- provider_name: nombre del proveedor de la cobertura.
- policy_number: número de póliza o referencia equivalente.
- coverage_status: estado actual de la cobertura.
- effective_from: fecha de inicio de vigencia.
- effective_to: fecha de fin de vigencia.
- notes: observaciones complementarias.
- version: concurrencia optimista.

### 17.19 Tabla pc_person_conflict

```sql
CREATE TABLE pc_person_conflict (
    id                              BIGSERIAL PRIMARY KEY,
    left_person_id                  BIGINT NOT NULL REFERENCES pc_person(id),
    right_person_id                 BIGINT REFERENCES pc_person(id),
    conflict_type_code              VARCHAR(100) NOT NULL,
    conflict_status                 VARCHAR(50) NOT NULL,
    conflict_notes                  TEXT,
    detected_at                     TIMESTAMP NOT NULL DEFAULT NOW(),
    detected_by                     VARCHAR(100),
    version                         BIGINT NOT NULL DEFAULT 0
);

CREATE INDEX idx_pc_person_conflict_left ON pc_person_conflict (left_person_id);
CREATE INDEX idx_pc_person_conflict_right ON pc_person_conflict (right_person_id);
CREATE INDEX idx_pc_person_conflict_type_status ON pc_person_conflict (conflict_type_code, conflict_status);
```

**¿Para qué sirve esta tabla?**

Esta tabla registra conflictos de persona, como posibles duplicados, inconsistencias de identidad o colisiones entre perfiles, guardians y households.

Su función principal es convertir el problema de calidad de datos en objeto institucional visible y gobernable.

También es una pieza muy valiosa para data stewardship, seguridad y auditoría.

**Explicación atributo por atributo**

- id: identificador técnico único del conflicto.
- left_person_id: persona principal afectada por el conflicto.
- right_person_id: segunda persona involucrada cuando el conflicto es de duplicidad o colisión.
- conflict_type_code: tipo de conflicto detectado.
- conflict_status: estado actual del conflicto.
- conflict_notes: notas explicativas del conflicto.
- detected_at: fecha y hora de detección.
- detected_by: actor o proceso que detectó el conflicto.
- version: concurrencia optimista.

### 17.20 Tabla pc_person_audit_snapshot

```sql
CREATE TABLE pc_person_audit_snapshot (
    id                              BIGSERIAL PRIMARY KEY,
    aggregate_id                    BIGINT NOT NULL,
    aggregate_type                  VARCHAR(100) NOT NULL,
    snapshot_json                   JSONB NOT NULL,
    captured_at                     TIMESTAMP NOT NULL DEFAULT NOW(),
    captured_by                     VARCHAR(100),
    version                         BIGINT NOT NULL DEFAULT 0
);

CREATE INDEX idx_pc_person_audit_snapshot_aggregate ON pc_person_audit_snapshot (aggregate_type, aggregate_id);
CREATE INDEX idx_pc_person_audit_snapshot_captured_at ON pc_person_audit_snapshot (captured_at);
```

**¿Para qué sirve esta tabla?**

Esta tabla permite guardar snapshots o fotografías completas del estado de la persona o sus subcomponentes en momentos relevantes.

Su función principal es conservar evidencia de cómo estaba la persona y sus relaciones en un instante concreto.

También es una medida fuerte de defensa institucional frente a auditorías, conflictos familiares o disputas posteriores.

**Explicación atributo por atributo**

- id: identificador técnico único del snapshot.
- aggregate_id: identificador del agregado auditado.
- aggregate_type: tipo de agregado auditado, por ejemplo Person, Household o GuardianRelationship.
- snapshot_json: contenido serializado del estado capturado.
- captured_at: fecha y hora de captura.
- captured_by: actor o proceso que generó la captura.
- version: concurrencia optimista.

### 17.21 Tabla pc_person_evidence_relation

```sql
CREATE TABLE pc_person_evidence_relation (
    id                              BIGSERIAL PRIMARY KEY,
    person_id                       BIGINT REFERENCES pc_person(id),
    household_id                    BIGINT REFERENCES pc_household(id),
    related_context_type            VARCHAR(60) NOT NULL,
    related_context_ref_id          VARCHAR(120) NOT NULL,
    evidence_role_code              VARCHAR(60) NOT NULL,
    asset_id                        BIGINT NOT NULL,
    evidence_status                 VARCHAR(50) NOT NULL,
    created_at                      TIMESTAMP NOT NULL DEFAULT NOW(),
    created_by                      VARCHAR(100),
    version                         BIGINT NOT NULL DEFAULT 0
);

CREATE INDEX idx_pc_person_evidence_relation_person ON pc_person_evidence_relation (person_id);
CREATE INDEX idx_pc_person_evidence_relation_household ON pc_person_evidence_relation (household_id);
CREATE INDEX idx_pc_person_evidence_relation_asset ON pc_person_evidence_relation (asset_id);
```

**¿Para qué sirve esta tabla?**

Esta tabla representa la relación canónica entre una persona o household y activos/documentos que la respaldan. Es la pieza que vuelve serio el gobierno documental transversal del módulo.

Su función principal es evitar referencias documentales dispersas sin semántica de negocio.

También permite gobernar evidencia de identidad, tutela, retiro, cobertura, responsabilidad financiera y otras necesidades del colegio.

**Explicación atributo por atributo**

- id: identificador técnico único de la relación de evidencia.
- person_id: persona asociada a la evidencia cuando corresponda.
- household_id: household asociado a la evidencia cuando corresponda.
- related_context_type: tipo de contexto relacionado, por ejemplo LEGAL_IDENTITY, GUARDIAN_RELATIONSHIP, PICKUP_AUTHORIZATION.
- related_context_ref_id: identificador lógico del contexto relacionado.
- evidence_role_code: rol semántico de la evidencia.
- asset_id: activo digital asociado.
- evidence_status: estado de la evidencia.
- created_at: fecha y hora de creación.
- created_by: actor que creó la relación.
- version: concurrencia optimista.

## 18. Consideraciones técnicas fuertes para PostgreSQL

- usar índices compuestos para identidad legal, households, guardianías, roles, cargos y vínculos IAM;
- mapear version con @Version;
- no delegar la lógica de persona, household, guardianía, retiro o vínculo IAM al frontend;
- tratar relaciones, autorizaciones, nombramientos y enlaces IAM como comportamiento de dominio/aplicación, no como SQL mágico;
- y restringir por diseño el acceso a tablas sensibles.

## 19. Diseño físico complementario en MongoDB

MongoDB no debe usarse aquí para reemplazar el núcleo relacional del registro maestro. Su rol más razonable es servir como proyección enriquecida para búsquedas rápidas y vistas de perfil compuesto.

### 19.1 Colección sugerida: person_household_profile_projection

```json
{
  "personId": 1001,
  "document": {
    "type": "CI",
    "number": "1234567"
  },
  "displayName": "María Fernanda Suárez López",
  "contacts": [
    {"type": "EMAIL", "value": "maria@example.com", "primary": true},
    {"type": "PHONE", "value": "+59170000000", "primary": true}
  ],
  "households": [
    {
      "householdId": 501,
      "role": "MOTHER"
    }
  ],
  "roles": ["GUARDIAN", "FINANCIAL_RESPONSIBLE"],
  "appointments": [],
  "sites": ["CENTRAL"],
  "updatedAt": "2026-05-02T10:00:00Z"
}
```

**¿Para qué sirve esta colección?**

Sirve para consultas rápidas y enriquecidas del perfil compuesto de la persona, sin recalcular todo desde el modelo relacional.

También desacopla lectura de escritura y ayuda a que múltiples módulos consuman vistas eficientes.

Y prepara el terreno para búsquedas inteligentes, portales familiares y asistentes institucionales.

## 20. Diseño físico complementario en Redis

Redis debe utilizarse como caché para acelerar búsquedas frecuentes y resolución rápida de persona por documento, vínculo IAM, profile compuesto o household.

### 20.1 Claves sugeridas

- person-by-document:{documentType}:{documentNumber}
- person-profile:{personId}
- person-iam-link:{iamSubjectId}
- person-roles:{personId}
- household-profile:{householdId}
- student-guardians:{studentPersonId}

### 20.2 Valor sugerido en caché para perfil

```json
{
  "personId": 1001,
  "displayName": "María Fernanda Suárez López",
  "primaryEmail": "maria@example.com",
  "primaryPhone": "+59170000000",
  "roles": ["GUARDIAN", "FINANCIAL_RESPONSIBLE"],
  "updatedAt": "2026-05-02T10:15:00Z"
}
```

**¿Para qué sirve este caché?**

Sirve para reducir carga sobre búsquedas masivas del registro maestro y acelerar operaciones transversales del ecosistema.

**Políticas recomendadas**

- TTL corto o mediano;
- invalidación por cambio de identidad, contacto, dirección, household, guardianía, rol, cargo o vínculo IAM;
- invalidación selectiva por persona, household y documento.

## 21. Engine de persona, familias, apoderados, roles, cargos e identidad digital

Este es el corazón técnico del módulo. Mucha gente se enfoca en una tabla maestra, pero el verdadero valor está en su capacidad de resolver identidad institucional, familiar y escolar de forma limpia y durable.

### 21.1 Engine de creación y deduplicación de persona

Debe ser capaz de crear persona base o detectar si ya existe una que coincide razonablemente según documento, identidad y reglas institucionales.

### 21.2 Engine de identidad legal

Debe soportar registro, verificación y control de unicidad de documentos legales.

### 21.3 Engine de contacto y dirección

Debe soportar multiplicidad, prioridad, propósito y vigencia.

### 21.4 Engine de household y guardianías

Debe poder crear households, agregar miembros, designar guardianes, distinguir autoridad legal y escolar, y resolver responsables principales.

### 21.5 Engine de retiro y emergencia

Debe permitir contactos de emergencia y autorizaciones de retiro con vigencia y seguridad.

### 21.6 Engine de roles y cargos

Debe separar claramente:

- rol institucional funcional;
- cargo o nombramiento formal;
- afiliación general;
- rol familiar/escolar.

### 21.7 Engine de enlace IAM

Debe permitir vincular o desvincular persona e identidad digital sin colapsar la integridad del dominio.

### 21.8 Engine sensible restringido

Debe permitir registrar y consultar información sensible bajo políticas muy estrictas de autorización y auditoría.

La respuesta del engine debería poder devolver:

- identidad principal;
- nombre de presentación;
- contactos principales;
- household principal;
- guardianes activos;
- responsables financieros activos;
- autorizaciones de retiro activas;
- roles activos;
- nombramientos vigentes;
- vínculo IAM;
- y estado general institucional de la persona.

## 22. Flujo de vida típico de la persona en la plataforma

El sistema debe soportar, como mínimo, ciclos de vida como estos:

- creación de persona;
- registro de identidad legal;
- asignación de contactos y direcciones;
- incorporación a household;
- vinculación con guardianes y responsables;
- vinculación documental;
- alta de rol institucional;
- alta de cargo o nombramiento;
- vinculación con IAM;
- consumo desde admisiones, académico, finanzas, regulatorio y bienestar;
- desactivación o cierre de ciertos roles sin destruir la persona.

La persona debe sobrevivir a sus cambios de rol. Eso es clave.

## 23. Simulación de impacto de merge o deduplicación de persona

Este módulo debería poder simular casos como:

- posible duplicidad por documento;
- fusión de dos personas candidatas a ser la misma;
- impacto de mover relaciones familiares o guardianías de un registro a otro;
- impacto de cambiar identidad principal;
- impacto de relinkear cuenta IAM;
- impacto sobre households, roles y responsables financieros.

Cuando la simulación produzca un resultado relevante, el sistema debe poder generar una evidencia exportable y asociarla canónicamente mediante el módulo documental y el módulo transversal digital.

## 24. Seguridad y permisos

Permisos mínimos sugeridos:

- core.person.read
- core.person.create
- core.person.update
- core.person.identity.manage
- core.person.contact.manage
- core.person.address.manage
- core.household.manage
- core.guardian.manage
- core.emergency.manage
- core.financial.responsible.manage
- core.pickup.authorization.manage
- core.person.role.assign
- core.person.appointment.manage
- core.person.iam.link
- core.person.document.reference.manage
- core.person.sensitive.read
- core.person.sensitive.write
- core.person.audit.read
- core.person.simulate
- core.person.evidence.associate

Además:

- acceso restringido por dominio consumidor y necesidad funcional;
- fuerte segregación para datos sensibles;
- auditoría completa sobre lectura y escritura de datos sensibles;
- trazabilidad completa de actor, fecha y acción.

## 25. Eventos de dominio sugeridos

- PersonCreated
- LegalIdentityRegistered
- PersonCoreProfileUpdated
- ContactPointAdded
- AddressAdded
- HouseholdCreated
- HouseholdMembershipAdded
- GuardianRelationshipRegistered
- EmergencyContactRegistered
- FinancialResponsibleRelationshipRegistered
- PickupAuthorizationRegistered
- InstitutionalRoleAssigned
- SchoolOrganizationalAppointmentCreated
- PersonLinkedToIamIdentity
- PersonDocumentReferenceRegistered
- SensitiveProfileRegistered
- SensitiveCoverageUpdated
- PersonConflictDetected
- PersonMergeImpactSimulationRequested
- PersonEvidenceAssociated

Estos eventos ayudan a desacoplar observabilidad, proyecciones, caché y capacidades futuras.

## 26. Observabilidad, auditoría y trazabilidad

Toda operación relevante debe dejar:

- correlationId;
- actorId;
- personId;
- householdId cuando aplique;
- documentType y documentNumber cuando aplique;
- guardianTypeCode o roleCode o appointmentCode cuando aplique;
- iamSubjectId cuando aplique;
- resultado de la operación;
- timestamp;
- motivo o explicación cuando aplique;
- y referencia documental asociada cuando exista.

**Métricas sugeridas**

- persons created;
- legal identities registered;
- contacts added;
- addresses added;
- households created;
- guardian relationships registered;
- financial responsible relationships registered;
- pickup authorizations registered;
- roles assigned;
- appointments created;
- iam links created;
- sensitive profile reads/writes;
- conflicts detected;
- cache hit/miss;
- optimistic locking failures.

**Logs estructurados mínimos**

- person_created
- legal_identity_registered
- contact_point_added
- household_created
- guardian_relationship_registered
- institutional_role_assigned
- school_organizational_appointment_created
- person_iam_link_created
- sensitive_profile_accessed
- person_conflict_detected
- person_evidence_associated
- permission_denied

**Trazas relevantes**

- create person;
- resolve by document;
- assign guardian;
- assign financial responsible;
- assign role;
- create appointment;
- link iam identity;
- read sensitive profile;
- update sensitive coverage;
- simulate merge impact;
- associate evidence asset;
- cache read/write/invalidate.

## 27. Estrategia de pruebas backend

**Unit tests**

- creación correcta de persona;
- unicidad de identidad legal;
- adición correcta de contactos y direcciones;
- creación correcta de household;
- membresía correcta en household;
- registro correcto de guardianes y responsables;
- asignación correcta de roles institucionales;
- creación correcta de nombramientos;
- vínculo correcto con IAM;
- separación correcta entre persona general y perfil sensible;
- asociación correcta de evidencia documental.

**Integration tests**

- persistencia real;
- búsquedas por documento;
- queries complejas por guardianía, household, rol, cargo y afiliación;
- control de permisos;
- eventos emitidos;
- optimistic locking;
- endpoints REST;
- integración con IAM, documental y activos digitales;
- integración con módulos consumidores académicos, administrativos y financieros.

**Pruebas de contrato**

- admisiones;
- académico;
- cuentas por cobrar;
- RUDE/regulatorio;
- comunicación;
- bienestar o enfermería;
- documental.

## 28. API sugerida del módulo

**Endpoints administrativos y operativos sugeridos**

- POST /api/core-persons
- POST /api/core-persons/{personId}/legal-identities
- PUT /api/core-persons/{personId}
- POST /api/core-persons/{personId}/contacts
- POST /api/core-persons/{personId}/addresses
- POST /api/core-households
- POST /api/core-households/{householdId}/members
- POST /api/core-persons/{personId}/guardian-relationships
- POST /api/core-persons/{personId}/emergency-contacts
- POST /api/core-persons/{personId}/financial-responsibles
- POST /api/core-persons/{personId}/pickup-authorizations
- POST /api/core-persons/{personId}/institutional-affiliations
- POST /api/core-persons/{personId}/roles
- POST /api/core-persons/{personId}/appointments
- POST /api/core-persons/{personId}/iam-links
- POST /api/core-persons/{personId}/document-references
- POST /api/core-persons/{personId}/sensitive-profile
- POST /api/core-persons/{personId}/sensitive-coverages
- POST /api/core-persons/{personId}/evidences
- POST /api/core-persons/simulations/merge-impact
- GET /api/core-persons/{personId}
- GET /api/core-persons/search
- GET /api/core-persons/by-document
- GET /api/core-persons/{personId}/roles
- GET /api/core-persons/{personId}/appointments
- GET /api/core-persons/{personId}/guardian-relationships
- GET /api/core-persons/{personId}/financial-responsibles
- GET /api/core-households/{householdId}
- GET /api/core-persons/{personId}/sensitive-profile

**Endpoints internos para módulos consumidores**

- GET /internal/core-persons/{personId}/profile
- GET /internal/core-persons/by-document
- GET /internal/core-persons/{personId}/roles
- GET /internal/core-persons/{personId}/appointments
- GET /internal/core-persons/{personId}/iam-link
- GET /internal/core-persons/{personId}/households
- GET /internal/core-persons/{personId}/guardians
- GET /internal/core-persons/{personId}/financial-responsibles

La API debe reflejar casos de uso, no tablas.

## 29. Prompts brutales sugeridos para IntelliJ IDEA / Codex / Claude

#### Prompt 1. Estructura base del módulo

Act as a principal enterprise Java architect. Create a production-ready backend transversal module called institutional-person-household-registry for the PeopleCole school suite using Spring Boot 3, Java 21, hexagonal architecture, and clean DDD boundaries.
This is a school-oriented module for Bolivian private schools. It must support master person records, legal identities, name profiles, contact points, addresses, households, household memberships, guardian relationships, emergency contacts, financial responsible relationships, pickup authorizations, institutional affiliations, institutional role assignments, school organizational appointments, person-to-IAM identity links, person document references, restricted sensitive profiles, restricted sensitive coverages, Redis cache for person and household lookup, MongoDB read projections, iam-service integration, academic consumer integrations, admissions integrations, accounts-receivable integrations, regulatory RUDE consumer integrations, academic-document-governance integration, and canonical evidence relations through the transversal digital-asset module.
Do not place business logic in controllers and do not put JPA annotations in domain entities.

#### Prompt 2. Entidades de dominio e invariantes

Create domain entities and value objects for a backend module called institutional-person-household-registry: Person, PersonLegalIdentity, PersonNameProfile, PersonContactPoint, PersonAddress, Household, HouseholdMembership, GuardianRelationship, EmergencyContact, FinancialResponsibleRelationship, PickupAuthorization, InstitutionalAffiliation, InstitutionalRoleAssignment, SchoolOrganizationalAppointment, PersonIdentityLink, PersonDocumentReference, PersonSensitiveProfile, PersonSensitiveCoverage, PersonConflict, PersonAuditSnapshot, and PersonEvidenceRelation.
Add invariants for person-vs-role-vs-appointment separation, unique legal identity constraints, controlled multiple roles, auditable IAM linking, guardian authority separation, household consistency, sensitive data segregation, and allowed documentary evidence relations.
Use expressive Java 21 domain code.

#### Prompt 3. Persistencia PostgreSQL

Create the PostgreSQL persistence layer for the institutional-person-household-registry module of PeopleCole.
Implement JPA entities, repositories, and mappers for pc_person, pc_person_legal_identity, pc_person_name_profile, pc_person_contact_point, pc_person_address, pc_household, pc_household_membership, pc_guardian_relationship, pc_emergency_contact, pc_financial_responsible_relationship, pc_pickup_authorization, pc_institutional_affiliation, pc_institutional_role_assignment, pc_school_organizational_appointment, pc_person_identity_link, pc_person_document_reference, pc_person_sensitive_profile, pc_person_sensitive_coverage, pc_person_conflict, pc_person_audit_snapshot, and pc_person_evidence_relation.
Use optimistic locking, JSONB mappings for audit snapshots where appropriate, indexes for document lookup, household lookup, guardian lookup, appointment lookup, and IAM-link lookup, and clean naming conventions aligned with a school-oriented multisite model.

#### Prompt 4. Engine de persona, familias y apoderados

Implement the institutional-person-household-registry engine for the PeopleCole school suite.
The engine must create master persons, register legal identities, manage name profiles, manage multiple contact points and addresses, manage households and household memberships, register guardian relationships, register emergency contacts, register financial responsible relationships, register pickup authorizations, assign institutional roles, create school organizational appointments, link IAM identities, manage person document references, and strictly separate general person data from restricted sensitive profile data.
Return explainable results including legal identity state, display-name resolution, household resolution, primary contacts, active guardians, active financial responsibles, active roles, active appointments, IAM-link state, and documentary references when available.
Prepare the engine for Redis caching, Mongo projections, admissions integration, accounts-receivable integration, regulatory RUDE integration, and transversal digital-asset evidence relations.

#### Prompt 5. Integración documental y activos digitales

Create adapters for integrating the institutional-person-household-registry module with the academic document-governance module and the transversal digital-asset module.
The module must attach documentary evidence such as identity documents, institutional photos, guardianship evidence, financial-responsible evidence, pickup authorization evidence, appointment documents, and restricted sensitive-support evidence using canonical evidence relations, validate allowed relation types, retrieve safe asset metadata, and never treat the business layer as raw binary storage owner.
Supported relation types include IDENTITY_DOCUMENT, PERSON_PHOTO, GUARDIANSHIP_SUPPORT, FINANCIAL_RESPONSIBLE_SUPPORT, PICKUP_AUTHORIZATION_SUPPORT, APPOINTMENT_DOCUMENT, SENSITIVE_SUPPORT, SUPPORTING_DOCUMENT, and LEGAL_EVIDENCE.

#### Prompt 6. Seguridad con IAM

Create an IAM integration adapter for the institutional-person-household-registry module.
It must validate permissions such as core.person.read, core.person.create, core.person.update, core.person.identity.manage, core.person.contact.manage, core.person.address.manage, core.household.manage, core.guardian.manage, core.emergency.manage, core.financial.responsible.manage, core.pickup.authorization.manage, core.person.role.assign, core.person.appointment.manage, core.person.iam.link, core.person.document.reference.manage, core.person.sensitive.read, core.person.sensitive.write, core.person.audit.read, core.person.simulate, and core.person.evidence.associate.
Support fine-grained visibility for admission staff, academic operators, finance operators, wellbeing or nurse roles, compliance operators, and auditors, plus special safeguards for restricted sensitive data access.

#### Prompt 7. Redis y Mongo

Create a MongoDB projection model and a Redis caching strategy for the institutional-person-household-registry module.
MongoDB must store enriched person-household-profile projections optimized for cross-domain consumption, person search, family resolution, and institutional profile dashboards.
Redis must cache lookup by legal identity, person profile, household profile, IAM links, active guardians, and active roles using document-based, person-based, household-based, and iam-subject-based cache keys.
Implement invalidation triggered by identity changes, contacts, addresses, household memberships, guardian relationships, financial responsible relationships, pickup authorizations, roles, appointments, IAM links, and restricted sensitive-profile updates.

#### Prompt 8. Tests enterprise

Generate a complete backend test suite for the institutional-person-household-registry module.
Include unit tests for person creation, legal identity uniqueness, contact and address management, household creation, guardian relationship registration, financial responsible registration, pickup authorization, institutional role assignment, school organizational appointment creation, IAM identity linking, restricted sensitive-profile access control, and documentary evidence policy; integration tests for PostgreSQL persistence, Redis cache, Mongo projection, IAM authorization, admissions integration, academic integration, accounts-receivable integration, regulatory RUDE consumer integration, academic-document-governance integration, transversal digital-asset adapter interactions, and cross-domain consumer interactions.
Use realistic school scenarios for Bolivia, including families with multiple guardians and multiple students in one household.

#### Prompt 9. Revisión arquitectónica con ChatGPT

Act as a principal enterprise software architect reviewing a backend module called institutional-person-household-registry for PeopleCole.
Validate whether the module correctly models persons, legal identities, households, guardian relationships, financial responsible relationships, pickup authorizations, institutional roles, school organizational appointments, IAM links, and restricted sensitive-profile separation as a transversal institutional identity domain rather than as admissions-owned, academic-owned, finance-owned or IAM-owned data.
Review multisite support, explicit person-vs-role-vs-appointment modeling, household modeling, guardian authority modeling, legal-identity uniqueness, sensitive-data isolation, Redis caching, Mongo projections, IAM integration, clean integration with admissions, academic, finance, regulatory and document domains, observability readiness, and long-term maintainability.
Identify weak points, hidden coupling, policy gaps, or future scalability risks.

## 30. Definition of Done del módulo

Este módulo solo puede considerarse terminado cuando exista:

- un dominio modelado con claridad;
- soporte de persona, identidad, contacto, dirección, household, guardianías, responsables, roles y cargos;
- separación correcta entre persona, household, rol, cargo y cuenta digital;
- soporte de perfil sensible restringido;
- auditoría y trazabilidad completas;
- seguridad integrada con IAM;
- integración documental y con activos digitales bien resuelta;
- eventos de dominio publicados;
- caché e invalidación razonablemente definidos;
- pruebas automatizadas relevantes;
- y documentación técnica suficiente para los módulos consumidores.

Si solo existe una tabla de estudiantes con nombre, CI y teléfono del padre, este módulo no está terminado. Apenas estaría empezando.

## 31. Cierre estratégico del módulo

Este módulo no es una comodidad de modelado. Es una pieza fundacional de arquitectura. Si se modela mal, toda la plataforma se fragmenta en estudiantes duplicados, padres duplicados, apoderados ambiguos, cuentas IAM mal enlazadas, cargos rígidos, datos sensibles mal expuestos, caos documental y conflictos financieros innecesarios. Si se modela bien, se convierte en una base institucional poderosa y limpia sobre la que puede crecer todo PeopleCole.

La decisión más importante que este diseño deja correctamente resuelta es esta:

- la persona nace aquí;
- el household nace aquí;
- los guardianes y responsables cuelgan de aquí;
- los roles cuelgan de aquí;
- los cargos cuelgan de aquí;
- IAM se enlaza aquí;
- admisiones consume desde aquí;
- académico consume desde aquí;
- finanzas consume desde aquí;
- RUDE consume desde aquí;
- y los datos sensibles viven aquí, pero en una frontera restringida.

Eso es exactamente lo que necesitas para un colegio privado boliviano que quiere operar como en las grandes ligas y sostener durante mucho tiempo una ventaja que no sea fácil de copiar.