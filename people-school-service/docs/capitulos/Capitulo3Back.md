# PeopleCole

## Capítulo 3 Recalibrado del Backend: Estudiante Escolar, Resolución Operativa Familiar y Consumo del Registro Maestro Transversal

*Versión brutalmente distinta, sin solapamiento con el Módulo Transversal de Personas, Familias y Apoderados*

## 1. Introducción brutal del recalibrado

Este documento reemplaza arquitectónicamente al antiguo **Capítulo 3: Personas, Estudiantes, Familias, Apoderados y Responsabilidades**.

La razón del reemplazo es brutal y correcta: después de diseñar el **Módulo Transversal Backend - Registro Maestro de Personas, Familias y Apoderados**, ya no es aceptable que el Capítulo 3 siga intentando ser dueño de:

- persona;
- identidad legal;
- household;
- guardianía;
- responsabilidad financiera;
- autorización de retiro;
- cargos institucionales;
- vínculo IAM;
- documentos personales;
- o datos sensibles.

Si el Capítulo 3 continuara modelando esas mismas piezas, el sistema quedaría duplicado, ambiguo y arquitectónicamente sucio. Y un sistema enterprise brutal no puede darse ese lujo.

Por eso, este nuevo Capítulo 3 se redefine con una frontera clarísima:

**El Módulo Transversal es el dueño de la identidad humana, familiar e institucional.**
**El Capítulo 3 es el dueño del estudiante como sujeto escolar-operativo y del consumo escolar de esa identidad transversal.**

Eso significa que este capítulo ya no vuelve a crear:

- pc_person
- pc_person_legal_identity
- pc_person_name_profile
- pc_person_contact_point
- pc_person_address
- pc_household
- pc_household_membership
- pc_guardian_relationship
- pc_financial_responsible_relationship
- pc_pickup_authorization
- pc_institutional_affiliation
- pc_institutional_role_assignment
- pc_school_organizational_appointment
- pc_person_identity_link
- pc_person_document_reference
- pc_person_sensitive_profile
- pc_person_sensitive_coverage
- pc_person_conflict
- pc_person_evidence_relation

Todas esas capacidades ya pertenecen al módulo transversal y deben quedarse allí.

Entonces, ¿qué sí debe hacer este capítulo?

Debe construir una cosa distinta, poderosísima y 100% escolar:

- el **perfil escolar del estudiante**;
- su **identidad académica interna dentro del colegio**;
- su **ciclo de vida escolar**;
- la **resolución operativa de qué household, qué referentes y qué contextos familiares consume el colegio para la operación diaria**;
- la **resolución escolar de contacto y seguimiento**;
- la **visión escolar anual del estudiante**;
- la **orquestación de lectura de familia, guardianes, responsables y autorizaciones desde el núcleo transversal**;
- y la **proyección consumible por matrícula, asistencia, evaluación, boletines, comunicación, RUDE, licencias, convivencia y bienestar**.

Dicho sin rodeos: este capítulo ya no es “maestro de personas”. Este capítulo ahora es el **School Student Domain and Operational Family Resolution Layer**.

Y eso lo vuelve muchísimo más limpio, muchísimo más enterprise y muchísimo más difícil de romper.

## 2. Propósito real del nuevo Capítulo 3

El propósito real de este capítulo es construir el backend del **estudiante como actor escolar formal** dentro del colegio, apoyado sobre el registro maestro transversal de personas y familias.

PeopleCole debe poder responder aquí preguntas como estas:

- ¿esta persona ya tiene perfil escolar de estudiante o solo existe como persona maestra?
- ¿cuál es su código interno de estudiante dentro del colegio?
- ¿cuál es su estado escolar actual?
- ¿cuál es su trayectoria escolar histórica dentro de la institución?
- ¿qué household consume operativamente el colegio para esta gestión?
- ¿qué referentes escolares deben ser priorizados para seguimiento académico y convivencia?
- ¿qué relaciones familiares existen en el core y cuáles fueron resueltas operativamente para consumo escolar?
- ¿qué contexto familiar anual queda congelado como referencia operativa?
- ¿qué eventos del ciclo de vida escolar tuvo el estudiante?
- ¿qué módulos pueden consumir su contexto escolar sin volver a redefinir personas, tutores ni household?
- ¿cómo se proyecta este estudiante a matrícula, asistencia, notas, comunicación, cuentas por cobrar y RUDE sin solapamientos?

Este capítulo, por tanto, no es dueño de la persona humana. Es dueño de la **manifestación escolar del estudiante**.

## 3. Límite arquitectónico brutal entre el Módulo Transversal y este Capítulo 3

### 3.1 Lo que ES dueño el Módulo Transversal

El módulo transversal de personas, familias y apoderados es dueño de:

- persona base;
- identidad legal;
- nombres;
- contactos;
- direcciones;
- household;
- membresía de household;
- guardianía y tutela;
- contacto de emergencia;
- responsabilidad financiera;
- autorización de retiro;
- roles institucionales generales;
- cargos o nombramientos;
- enlace IAM;
- documentos personales y familiares;
- datos sensibles restringidos.

### 3.2 Lo que ES dueño este nuevo Capítulo 3

Este capítulo es dueño de:

- perfil escolar del estudiante;
- identidad interna del estudiante dentro del colegio;
- ciclo de vida escolar del estudiante;
- estado escolar y trazabilidad de ese estado;
- resolución escolar operativa del contexto familiar;
- resolución anual de referentes escolares consumidos por módulos operativos;
- snapshot escolar anual del contexto humano del estudiante;
- eventos escolares del estudiante;
- proyecciones de lectura optimizadas para módulos académicos y administrativos.

### 3.3 Lo que este capítulo puede consumir pero no redefinir

Este capítulo puede leer del módulo transversal:

- persona;
- household;
- guardianía;
- responsables financieros;
- autorizaciones de retiro;
- contacto de emergencia;
- identidad legal;
- documentos y evidencias;
- IAM link.

Pero **no debe persistir nuevamente esas verdades como fuente primaria**.

### 3.4 Regla arquitectónica obligatoria

Si un desarrollador intenta agregar en este capítulo otra tabla fuente de verdad de persona, apoderado, household o retiro, está rompiendo la arquitectura.

## 4. Qué gana PeopleCole con este recalibrado

Al recalibrar el capítulo de esta manera, PeopleCole gana:

- **Cero duplicidad arquitectónica** entre persona y estudiante.
- **Separación brutalmente clara** entre identidad humana y sujeto escolar.
- **Mayor reutilización** del módulo transversal por todos los capítulos.
- **Más facilidad para RUDE, cuentas por cobrar, comunicación, bienestar y convivencia**, porque todos consumen un mismo núcleo humano.
- **Menos riesgo de inconsistencias** entre household, apoderados, responsables y vistas escolares.
- **Más facilidad para resolver casos complejos** como estudiantes con múltiples hogares funcionales, cambios de tutor o múltiples responsables.
- **Mejor base para frontend 360 del estudiante**, porque el perfil del estudiante ya no es una copia de la persona, sino una capa de consumo escolar bien definida.

## 5. Nuevo nombre funcional recomendado del capítulo

Para evitar toda confusión, este capítulo debe dejar de llamarse simplemente “Personas, Estudiantes, Familias, Apoderados y Responsabilidades”.

El nombre correcto recomendado es:

**Capítulo 3 Recalibrado: Estudiante Escolar, Resolución Operativa Familiar y Consumo del Registro Maestro Transversal**

Ese nombre deja claro que:

- aquí ya no nace la persona;
- aquí ya no nace la familia;
- aquí ya no nace el apoderado;
- aquí nace el estudiante escolar como objeto institucional de consumo operativo.

## 6. Tesis arquitectónica del nuevo capítulo

### 6.1 Persona y Estudiante ya no son el mismo agregado

La persona vive en el core transversal. El estudiante vive aquí como agregado escolar.

### 6.2 Household no es propiedad del estudiante

El estudiante consume households y relaciones desde el core, pero puede tener una **resolución operativa anual o activa** distinta según la lógica escolar.

### 6.3 Guardianía legal no equivale a referente escolar operativo

Una cosa es quién tiene autoridad legal o escolar según el core. Otra es a quién contacta primero el colegio para acompañamiento académico, disciplina o seguimiento diario. Esa resolución sí puede vivir aquí como consumo escolar.

### 6.4 Este capítulo debe trabajar con snapshots escolares, no con redefinición de personas

El colegio muchas veces necesita congelar contexto humano consumido en una gestión o evento escolar. Esa fotografía operativa anual sí es legítima aquí.

### 6.5 El estudiante es un actor de ciclo de vida

Este capítulo debe modelar ingreso, activación, continuidad, pausa, retiro, egreso, egreso parcial, reingreso u otros hitos escolares.

### 6.6 Este capítulo es una bisagra de consumo para todo el backend escolar

Matrícula, asistencia, evaluación, boletines, comunicación, RUDE, convivencia, licencias, bienestar y CxC deben consumir desde aquí al estudiante escolar, y desde el transversal al mundo humano base.

### 6.7 Concurrencia optimista obligatoria

Toda entidad mutable de este capítulo debe contemplar version BIGINT NOT NULL DEFAULT 0 para @Version.

## 7. Bounded contexts internos del nuevo capítulo

### 7.1 student-profile-governance

Gestiona el perfil escolar del estudiante.

### 7.2 student-school-identity

Gestiona códigos e identidad interna escolar del estudiante.

### 7.3 student-lifecycle-engine

Gestiona estados y eventos del ciclo de vida escolar.

### 7.4 student-operational-family-resolution

Gestiona la resolución escolar operativa del household y referentes escolares.

### 7.5 student-consumer-projections

Gestiona proyecciones y snapshots de lectura para módulos consumidores.

### 7.6 student-document-and-context-binding

Gestiona bindings documentales escolares del estudiante que no sustituyen al documental base transversal.

### 7.7 student-observability

Gestiona métricas y salud operativa del dominio escolar del estudiante.

## 8. Casos de uso críticos del capítulo

- Crear perfil escolar del estudiante a partir de una persona existente.
- Asignar identidad escolar interna del estudiante.
- Activar o desactivar estado escolar.
- Registrar evento del ciclo de vida escolar.
- Resolver household operativo para una gestión.
- Resolver referentes escolares prioritarios para acompañamiento.
- Construir snapshot escolar anual del contexto humano del estudiante.
- Exponer perfil 360 escolar del estudiante a módulos consumidores.
- Detectar si el contexto humano consumido quedó stale respecto al módulo transversal.
- Asociar documentos escolares del estudiante que no sustituyen los documentos maestros del core.

## 9. DISEÑO FÍSICO EN POSTGRESQL

Este capítulo puede referenciar tablas del módulo transversal, pero no puede volver a definirlas.

### 9.1 Tabla: pc_student_profile

```sql
CREATE TABLE pc_student_profile (
    id                              BIGSERIAL PRIMARY KEY,
    person_id                       BIGINT NOT NULL REFERENCES pc_person(id),
    student_code                    VARCHAR(80) NOT NULL,
    student_status                  VARCHAR(40) NOT NULL,
    school_entry_date               DATE,
    first_school_year_id            BIGINT REFERENCES pc_school_year(id),
    current_operational_status      VARCHAR(40) NOT NULL,
    created_at                      TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at                      TIMESTAMP NOT NULL DEFAULT NOW(),
    created_by_user_id              VARCHAR(120),
    updated_by_user_id              VARCHAR(120),
    version                         BIGINT NOT NULL DEFAULT 0,
    CONSTRAINT uk_pc_student_profile_person UNIQUE (person_id),
    CONSTRAINT uk_pc_student_profile_code UNIQUE (student_code)
);
```

**¿Para qué sirve esta tabla?**

Esta tabla representa el perfil escolar del estudiante dentro del colegio. No es la persona, sino la proyección de esa persona al mundo escolar.

Su función principal es permitir que una persona del registro maestro se convierta formalmente en estudiante dentro de la institución.

También permite separar estado escolar, código estudiantil y cronología escolar del núcleo humano transversal.

**Explicación amplia de cada atributo**

- id: identificador técnico del perfil escolar del estudiante.
- person_id: referencia a la persona base del módulo transversal.
- student_code: código interno único del estudiante dentro del colegio.
- student_status: estado general del estudiante dentro del dominio escolar.
- school_entry_date: fecha en la que la persona ingresó como estudiante al colegio.
- first_school_year_id: gestión en la que el estudiante comenzó formalmente en la institución.
- current_operational_status: estado operativo consumible por otros módulos.
- created_at: fecha y hora de creación del perfil escolar.
- updated_at: fecha y hora de última modificación.
- created_by_user_id: usuario creador.
- updated_by_user_id: usuario de última modificación.
- version: concurrencia optimista.

### 9.2 Tabla: pc_student_school_identity

```sql
CREATE TABLE pc_student_school_identity (
    id                              BIGSERIAL PRIMARY KEY,
    student_profile_id              BIGINT NOT NULL REFERENCES pc_student_profile(id),
    identity_type_code              VARCHAR(50) NOT NULL,
    identity_value                  VARCHAR(120) NOT NULL,
    identity_status                 VARCHAR(40) NOT NULL,
    is_primary                      BOOLEAN NOT NULL DEFAULT FALSE,
    created_at                      TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at                      TIMESTAMP NOT NULL DEFAULT NOW(),
    version                         BIGINT NOT NULL DEFAULT 0,
    CONSTRAINT uk_pc_student_school_identity UNIQUE (identity_type_code, identity_value)
);
```

**¿Para qué sirve esta tabla?**

Representa identidades internas escolares del estudiante distintas a la identidad legal humana: código legado, matrícula histórica interna, código de biblioteca escolar u otras referencias institucionales.

Su función principal es separar la identidad escolar interna del documento civil de la persona.

**Explicación amplia de cada atributo**

- id: identificador técnico de la identidad escolar.
- student_profile_id: perfil escolar del estudiante.
- identity_type_code: tipo de identidad interna escolar.
- identity_value: valor específico de la identidad interna.
- identity_status: estado de esa identidad interna.
- is_primary: indica si es la identidad escolar principal.
- created_at: creación técnica.
- updated_at: actualización técnica.
- version: concurrencia optimista.

### 9.3 Tabla: pc_student_lifecycle_event

```sql
CREATE TABLE pc_student_lifecycle_event (
    id                              BIGSERIAL PRIMARY KEY,
    student_profile_id              BIGINT NOT NULL REFERENCES pc_student_profile(id),
    school_year_id                  BIGINT REFERENCES pc_school_year(id),
    lifecycle_event_code            VARCHAR(80) NOT NULL,
    lifecycle_event_type            VARCHAR(50) NOT NULL,
    lifecycle_event_status          VARCHAR(40) NOT NULL,
    occurred_at                     TIMESTAMP NOT NULL,
    actor_user_id                   VARCHAR(120),
    event_note                      VARCHAR(1500),
    created_at                      TIMESTAMP NOT NULL DEFAULT NOW(),
    version                         BIGINT NOT NULL DEFAULT 0,
    CONSTRAINT uk_pc_student_lifecycle_event UNIQUE (lifecycle_event_code)
);
```

**¿Para qué sirve esta tabla?**

Representa los hitos del ciclo de vida escolar del estudiante: alta, activación, continuidad, pausa, retiro, reingreso, egreso o cualquier otro evento institucional relevante.

Su función principal es construir una historia escolar auditable sin volver a mezclar a la persona con su trayectoria escolar.

**Explicación amplia de cada atributo**

- id: identificador técnico del evento.
- student_profile_id: estudiante afectado.
- school_year_id: gestión asociada al evento cuando aplique.
- lifecycle_event_code: código único del evento.
- lifecycle_event_type: tipo de hito escolar.
- lifecycle_event_status: estado del evento.
- occurred_at: momento en que ocurrió.
- actor_user_id: usuario o actor responsable del evento.
- event_note: observación ampliada del evento.
- created_at: creación técnica del registro.
- version: concurrencia optimista.

### 9.4 Tabla: pc_student_operational_household_context

```sql
CREATE TABLE pc_student_operational_household_context (
    id                              BIGSERIAL PRIMARY KEY,
    student_profile_id              BIGINT NOT NULL REFERENCES pc_student_profile(id),
    household_id                    BIGINT NOT NULL REFERENCES pc_household(id),
    school_year_id                  BIGINT REFERENCES pc_school_year(id),
    context_type_code               VARCHAR(50) NOT NULL,
    context_status                  VARCHAR(40) NOT NULL,
    is_primary_operational_context  BOOLEAN NOT NULL DEFAULT FALSE,
    resolved_at                     TIMESTAMP NOT NULL DEFAULT NOW(),
    resolved_by_user_id             VARCHAR(120),
    resolution_note                 VARCHAR(1200),
    created_at                      TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at                      TIMESTAMP NOT NULL DEFAULT NOW(),
    version                         BIGINT NOT NULL DEFAULT 0
);
```

**¿Para qué sirve esta tabla?**

Esta tabla no redefine household. Lo que hace es registrar qué household del core fue resuelto como contexto operativo escolar del estudiante para una gestión o caso de uso.

Su función principal es resolver la realidad escolar de que un estudiante puede tener múltiples relaciones familiares en el core, pero el colegio necesita saber con qué household operará prioritariamente en una gestión concreta.

**Explicación amplia de cada atributo**

- id: identificador técnico del contexto operativo.
- student_profile_id: estudiante escolar asociado.
- household_id: household maestro consumido desde el módulo transversal.
- school_year_id: gestión para la que aplica la resolución operativa.
- context_type_code: tipo de contexto operativo, por ejemplo PRIMARY_FAMILY_CONTEXT, SCHOOL_CONTACT_CONTEXT, BILLING_REFERENCE_CONTEXT.
- context_status: estado de esta resolución operativa.
- is_primary_operational_context: indica si este household es el contexto principal para ese alcance.
- resolved_at: momento de resolución.
- resolved_by_user_id: usuario que resolvió o confirmó el contexto.
- resolution_note: nota explicativa de por qué se eligió ese contexto.
- created_at: creación técnica.
- updated_at: actualización técnica.
- version: concurrencia optimista.

### 9.5 Tabla: pc_student_operational_contact_resolution

```sql
CREATE TABLE pc_student_operational_contact_resolution (
    id                              BIGSERIAL PRIMARY KEY,
    student_profile_id              BIGINT NOT NULL REFERENCES pc_student_profile(id),
    related_person_id               BIGINT NOT NULL REFERENCES pc_person(id),
    school_year_id                  BIGINT REFERENCES pc_school_year(id),
    resolution_scope_code           VARCHAR(50) NOT NULL,
    resolution_priority             INTEGER NOT NULL DEFAULT 1,
    source_relationship_type        VARCHAR(60) NOT NULL,
    resolution_status               VARCHAR(40) NOT NULL,
    resolved_at                     TIMESTAMP NOT NULL DEFAULT NOW(),
    resolved_by_user_id             VARCHAR(120),
    resolution_note                 VARCHAR(1200),
    created_at                      TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at                      TIMESTAMP NOT NULL DEFAULT NOW(),
    version                         BIGINT NOT NULL DEFAULT 0
);
```

**¿Para qué sirve esta tabla?**

Esta tabla no crea guardianes ni apoderados. Consume relaciones del módulo transversal y registra cómo el colegio resolvió operativamente qué personas serán prioridad para contacto escolar, seguimiento académico o acompañamiento en una gestión.

Su función principal es distinguir la relación humana base de la prioridad operativa escolar.

**Explicación amplia de cada atributo**

- id: identificador técnico de la resolución operativa.
- student_profile_id: estudiante asociado.
- related_person_id: persona relacionada consumida desde el core.
- school_year_id: gestión asociada.
- resolution_scope_code: alcance de la resolución, por ejemplo ACADEMIC_FOLLOW_UP, DAILY_CONTACT, DISCIPLINE_CONTACT.
- resolution_priority: prioridad operativa dentro del alcance.
- source_relationship_type: tipo de relación fuente consumida desde el core.
- resolution_status: estado de la resolución.
- resolved_at: momento de resolución.
- resolved_by_user_id: usuario responsable de la resolución.
- resolution_note: nota explicativa.
- created_at: creación técnica.
- updated_at: actualización técnica.
- version: concurrencia optimista.

### 9.6 Tabla: pc_student_operational_snapshot

```sql
CREATE TABLE pc_student_operational_snapshot (
    id                              BIGSERIAL PRIMARY KEY,
    student_profile_id              BIGINT NOT NULL REFERENCES pc_student_profile(id),
    school_year_id                  BIGINT REFERENCES pc_school_year(id),
    snapshot_type_code              VARCHAR(50) NOT NULL,
    snapshot_status                 VARCHAR(40) NOT NULL,
    snapshot_payload                JSONB NOT NULL,
    captured_at                     TIMESTAMP NOT NULL DEFAULT NOW(),
    captured_by_user_id             VARCHAR(120),
    version                         BIGINT NOT NULL DEFAULT 0
);
```

**¿Para qué sirve esta tabla?**

Representa una fotografía escolar del contexto operativo del estudiante: household resuelto, referentes prioritarios, identidad escolar y otros elementos consumidos desde el core para una gestión o momento operativo.

Su función principal es congelar contexto escolar sin duplicar permanentemente la fuente maestra.

**Explicación amplia de cada atributo**

- id: identificador técnico del snapshot.
- student_profile_id: estudiante asociado.
- school_year_id: gestión asociada.
- snapshot_type_code: tipo de snapshot escolar.
- snapshot_status: estado del snapshot.
- snapshot_payload: contenido JSON del contexto operativo resuelto.
- captured_at: fecha y hora de captura.
- captured_by_user_id: actor que generó la captura.
- version: concurrencia optimista.

### 9.7 Tabla: pc_student_consumer_projection_status

```sql
CREATE TABLE pc_student_consumer_projection_status (
    id                              BIGSERIAL PRIMARY KEY,
    student_profile_id              BIGINT NOT NULL REFERENCES pc_student_profile(id),
    projection_code                 VARCHAR(80) NOT NULL,
    consumer_module_code            VARCHAR(60) NOT NULL,
    projection_status               VARCHAR(40) NOT NULL,
    last_projected_at               TIMESTAMP,
    source_staleness_status         VARCHAR(40) NOT NULL,
    created_at                      TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at                      TIMESTAMP NOT NULL DEFAULT NOW(),
    version                         BIGINT NOT NULL DEFAULT 0,
    CONSTRAINT uk_pc_student_consumer_projection_status UNIQUE (student_profile_id, projection_code, consumer_module_code)
);
```

**¿Para qué sirve esta tabla?**

Representa el estado de las proyecciones del estudiante consumidas por otros módulos. Es crítica para saber si matrícula, comunicación, RUDE, asistencia u otros están trabajando con contexto fresco o stale.

**Explicación amplia de cada atributo**

- id: identificador técnico del estado de proyección.
- student_profile_id: estudiante asociado.
- projection_code: código de la proyección.
- consumer_module_code: módulo consumidor.
- projection_status: estado de la proyección.
- last_projected_at: último momento de proyección.
- source_staleness_status: indica si el core transversal cambió desde la última proyección.
- created_at: creación técnica.
- updated_at: actualización técnica.
- version: concurrencia optimista.

### 9.8 Tabla: pc_student_school_document_binding

```sql
CREATE TABLE pc_student_school_document_binding (
    id                              BIGSERIAL PRIMARY KEY,
    student_profile_id              BIGINT NOT NULL REFERENCES pc_student_profile(id),
    digital_asset_id                BIGINT NOT NULL REFERENCES pc_digital_asset(id),
    school_context_type             VARCHAR(60) NOT NULL,
    school_context_ref_id           VARCHAR(120) NOT NULL,
    document_role                   VARCHAR(60) NOT NULL,
    binding_status                  VARCHAR(40) NOT NULL,
    is_primary                      BOOLEAN NOT NULL DEFAULT FALSE,
    created_at                      TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at                      TIMESTAMP NOT NULL DEFAULT NOW(),
    version                         BIGINT NOT NULL DEFAULT 0
);
```

**¿Para qué sirve esta tabla?**

Esta tabla no reemplaza documentos personales del core. Lo que hace es vincular activos digitales específicamente al contexto escolar del estudiante: por ejemplo ficha escolar interna, documento de trayectoria, acta operativa o evidencia escolar del perfil del estudiante.

**Explicación amplia de cada atributo**

- id: identificador técnico del vínculo documental.
- student_profile_id: estudiante asociado.
- digital_asset_id: asset digital asociado.
- school_context_type: tipo de contexto escolar asociado.
- school_context_ref_id: referencia lógica del contexto.
- document_role: rol del documento dentro del contexto escolar.
- binding_status: estado del vínculo documental.
- is_primary: indica si es el principal para ese contexto.
- created_at: creación técnica.
- updated_at: actualización técnica.
- version: concurrencia optimista.

### 9.9 Tabla: pc_student_metric_registry

```sql
CREATE TABLE pc_student_metric_registry (
    id                              BIGSERIAL PRIMARY KEY,
    school_year_id                  BIGINT REFERENCES pc_school_year(id),
    metric_code                     VARCHAR(80) NOT NULL,
    metric_name                     VARCHAR(180) NOT NULL,
    metric_scope                    VARCHAR(40) NOT NULL,
    metric_value                    NUMERIC(18,4),
    measured_at                     TIMESTAMP NOT NULL DEFAULT NOW(),
    metric_status                   VARCHAR(30) NOT NULL,
    created_at                      TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at                      TIMESTAMP NOT NULL DEFAULT NOW(),
    version                         BIGINT NOT NULL DEFAULT 0
);
```

**¿Para qué sirve esta tabla?**

Consolida métricas del dominio escolar del estudiante: perfiles creados, snapshots generados, resoluciones operativas, stale contexts detectados y salud general del consumo escolar del registro maestro.

**Explicación amplia de cada atributo**

- id: identificador del registro métrico.
- school_year_id: gestión asociada si aplica.
- metric_code: código de la métrica.
- metric_name: nombre visible de la métrica.
- metric_scope: alcance de la métrica.
- metric_value: valor cuantitativo observado.
- measured_at: momento de medición.
- metric_status: estado del registro.
- created_at: creación técnica.
- updated_at: actualización técnica.
- version: concurrencia optimista.

## 10. DISEÑO FÍSICO EN MONGODB

### 10.1 Colección: student_school_profile_documents

**¿Para qué sirve esta colección?**

Guarda la vista enriquecida del estudiante escolar como actor consumible por el ecosistema: datos base, household operativo resuelto, referentes prioritarios, trayectoria y documentos escolares.

**Estructura sugerida**

```json
{
  "_id": "STU-PROFILE-2026-0001",
  "studentProfileId": 1001,
  "personId": 501,
  "displayName": "María Fernanda Suárez López",
  "studentCode": "ALU-2026-001",
  "schoolIdentity": {},
  "operationalHouseholds": [],
  "operationalContacts": [],
  "lifecycle": [],
  "attachments": [7001],
  "version": 1
}
```

**Explicación amplia de campos**

- _id: identificador documental del perfil escolar enriquecido.
- studentProfileId: perfil escolar asociado.
- personId: persona maestra asociada.
- displayName: nombre proyectado para consumo escolar.
- studentCode: código interno escolar.
- schoolIdentity: identidades escolares relevantes.
- operationalHouseholds: households resueltos operativamente.
- operationalContacts: referentes escolares priorizados.
- lifecycle: hitos del ciclo de vida escolar.
- attachments: assets escolares vinculados.
- version: versión documental.

### 10.2 Colección: student_operational_resolution_documents

**¿Para qué sirve esta colección?**

Guarda la narrativa enriquecida de cómo se resolvió el contexto familiar-operativo del estudiante para el colegio.

**Estructura sugerida**

```json
{
  "_id": "STU-RESOLUTION-2026-01",
  "studentProfileId": 1001,
  "schoolYearId": 22,
  "resolvedHousehold": {},
  "resolvedContacts": [],
  "resolutionNarrative": {},
  "version": 1
}
```

**Explicación amplia de campos**

- _id: identificador documental de la resolución.
- studentProfileId: estudiante asociado.
- schoolYearId: gestión asociada.
- resolvedHousehold: household operativo adoptado.
- resolvedContacts: referentes operativos priorizados.
- resolutionNarrative: explicación enriquecida de la resolución.
- version: versión documental.

### 10.3 Colección: student_lifecycle_documents

**¿Para qué sirve esta colección?**

Guarda la historia enriquecida del ciclo de vida del estudiante para trazabilidad analítica y operativa.

**Estructura sugerida**

```json
{
  "_id": "STU-LIFE-1001",
  "studentProfileId": 1001,
  "events": [],
  "currentState": "ACTIVE",
  "version": 1
}
```

**Explicación amplia de campos**

- _id: identificador documental del lifecycle.
- studentProfileId: estudiante asociado.
- events: eventos enriquecidos del ciclo de vida.
- currentState: estado escolar actual proyectado.
- version: versión documental.

### 10.4 Colección: student_consumer_projection_documents

**¿Para qué sirve esta colección?**

Guarda proyecciones listas para módulos consumidores como matrícula, asistencia, comunicación, RUDE o bienestar.

**Estructura sugerida**

```json
{
  "_id": "STU-CONSUMER-PROJ-1001",
  "studentProfileId": 1001,
  "consumerModules": {
    "ENROLLMENT": {"status": "CURRENT"},
    "COMMUNICATION": {"status": "STALE"}
  },
  "version": 1
}
```

**Explicación amplia de campos**

- _id: identificador documental de la proyección.
- studentProfileId: estudiante asociado.
- consumerModules: estado de consumo por módulo.
- version: versión documental.

### 10.5 Colección: student_observability_documents

**¿Para qué sirve esta colección?**

Guarda análisis enriquecidos sobre la salud del dominio del estudiante escolar: stale contexts, households sin resolución, perfiles incompletos y rupturas con módulos consumidores.

**Estructura sugerida**

```json
{
  "_id": "STU-OBS-0001",
  "analysisType": "STALE_OPERATIONAL_CONTEXT",
  "details": {
    "message": "El snapshot escolar del estudiante quedó desactualizado respecto al módulo transversal"
  },
  "status": "OPEN",
  "version": 1
}
```

**Explicación amplia de campos**

- _id: identificador del análisis.
- analysisType: tipo de hallazgo.
- details: detalle enriquecido del problema.
- status: estado del hallazgo.
- version: versión documental.

## 11. DISEÑO DE REDIS

### 11.1 Keyspace: pc:student-profile:{studentProfileId}

**¿Para qué sirve?**

Resume rápidamente el perfil escolar del estudiante.

**Ejemplo**

```
{
  "personId": 501,
  "studentCode": "ALU-2026-001",
  "status": "ACTIVE"
}
```

### 11.2 Keyspace: pc:student:{studentProfileId}:operational-household

**¿Para qué sirve?**

Resuelve rápidamente el household operativo principal del estudiante.

**Ejemplo**

```
{
  "householdId": 8001,
  "schoolYearId": 22,
  "scope": "PRIMARY_FAMILY_CONTEXT"
}
```

### 11.3 Keyspace: pc:student:{studentProfileId}:operational-contacts

**¿Para qué sirve?**

Resuelve rápidamente a quién debe contactar el colegio en la operación diaria escolar.

**Ejemplo**

```
[
  {"personId": 701, "priority": 1, "scope": "ACADEMIC_FOLLOW_UP"},
  {"personId": 702, "priority": 2, "scope": "ACADEMIC_FOLLOW_UP"}
]
```

### 11.4 Keyspace: pc:student:{studentProfileId}:projection-health

**¿Para qué sirve?**

Resume el estado de salud de las proyecciones del estudiante para módulos consumidores.

**Ejemplo**

```
{
  "ENROLLMENT": "CURRENT",
  "COMMUNICATION": "STALE",
  "RUDE": "CURRENT"
}
```

### 11.5 Keyspace: pc:student:health-summary:{schoolYearId}

**¿Para qué sirve?**

Resume señales rápidas de salud del dominio estudiantil escolar.

**Ejemplo**

```
{
  "studentProfiles": 1120,
  "staleOperationalContexts": 14,
  "missingOperationalHouseholds": 7,
  "consumerProjectionIssues": 5
}
```

## 12. Reglas de negocio críticas del capítulo

- Un estudiante no puede existir aquí sin una persona existente en el módulo transversal.
- El capítulo no debe volver a crear ni mutar la fuente primaria de household, guardianía o responsabilidad financiera.
- El household operativo escolar puede resolverse aquí, pero su fuente de verdad vive en el core transversal.
- Los referentes escolares prioritarios pueden resolverse aquí, pero no sustituyen la relación humana base.
- El ciclo de vida escolar del estudiante es propiedad de este capítulo.
- La identidad interna escolar del estudiante es propiedad de este capítulo.
- Los snapshots escolares pueden congelar contexto, pero no reemplazan la verdad maestra.
- Toda proyección consumida por otros módulos debe poder marcarse como stale.
- Los documentos escolares del estudiante no reemplazan documentos personales/familiares del core.
- Este capítulo debe dejar clarísima la frontera entre “dato humano maestro” y “resolución escolar operativa”.

## 13. Integración con el Módulo Transversal de Personas

Este capítulo depende críticamente del módulo transversal para:

- persona base;
- household maestro;
- guardianías;
- responsables financieros;
- autorizaciones de retiro;
- identidad legal;
- roles y cargos generales;
- documentos personales;
- datos sensibles según políticas de acceso.

**Regla estratégica**

Este capítulo solo puede consumir por API interna, adaptador, repositorio de lectura autorizado o proyección controlada. No puede apropiarse del dominio transversal.

## 14. Integración con iam-service

Este capítulo depende de IAM para:

- controlar quién crea perfil escolar del estudiante;
- controlar quién resuelve household operativo;
- controlar quién define prioridades de contacto escolar;
- controlar quién accede a proyecciones sensibles del estudiante;
- controlar quién fuerza refrescos o reconstrucción de snapshots escolares.

**Roles sugeridos:**

- STUDENT_PROFILE_MANAGER
- STUDENT_OPERATIONAL_CONTEXT_OPERATOR
- STUDENT_AUDITOR
- STUDENT_READ_ONLY_REVIEWER

## 15. Integración con ai-service

Este capítulo puede usar AI-service para:

- detectar inconsistencias entre household core y household operativo escolar;
- sugerir referentes escolares prioritarios según historial de interacción;
- detectar snapshots stale o incoherentes;
- explicar por qué cierto contexto escolar quedó desactualizado;
- ayudar a priorizar casos complejos de resolución operativa.

**Lo que la IA no debe hacer sola**

- redefinir guardianías del módulo transversal;
- cambiar household maestro;
- alterar responsabilidades financieras base;
- tomar decisiones de autoridad legal o escolar sin política explícita.

## 16. Integración con activos digitales y documentos

Este capítulo consume el hub digital para documentos escolares del estudiante, pero debe diferenciar claramente:

- documentos personales y familiares del core transversal;
- documentos escolares operativos del estudiante;
- snapshots escolares exportables;
- evidencias escolares de consumo operativo.

**Regla arquitectónica obligatoria**

No guardar binarios en las tablas del capítulo. Solo metadata y digital_asset_id.

## 17. Observabilidad del capítulo

### 17.1 Métricas técnicas sugeridas

- tiempo promedio de creación de student profile;
- latencia de resolución de household operativo;
- tiempo de generación de snapshots escolares;
- tiempo de refresco de proyección para módulos consumidores;
- tasa de conflictos de concurrencia optimista.

### 17.2 Métricas funcionales sugeridas

- estudiantes con perfil escolar creado;
- estudiantes con household operativo resuelto;
- estudiantes con referentes operativos definidos;
- snapshots escolares por gestión;
- proyecciones stale por módulo consumidor;
- perfiles escolares sin identidad interna principal.

### 17.3 Alertas críticas sugeridas

- estudiante sin household operativo principal para la gestión;
- estudiante con household operativo stale respecto al core transversal;
- student profile sin identidad escolar principal;
- proyección crítica stale en módulos consumidores;
- documento escolar principal faltante cuando la política lo exige.

## 18. Riesgos de diseño que este capítulo debe evitar

- Volver a redefinir persona o household aquí.
- Confundir referente escolar con tutor legal.
- Duplicar responsabilidad financiera que ya vive en el módulo transversal.
- Tratar snapshots escolares como nueva fuente maestra.
- Mezclar identidad escolar con identidad legal.
- No marcar proyecciones stale cuando el core cambió.
- Volver a modelar autorización de retiro como tabla fuente primaria.
- No diferenciar documentos escolares de documentos personales.
- Hacer que este capítulo sea “un segundo módulo de personas” disfrazado.
- No dejar claro a qué módulos sirve este capítulo como capa consumible.

## 19. Prompts sugeridos para implementación

### 19.1 Prompt general del módulo

Actúa como arquitecto backend enterprise experto en Java 21, Spring Boot, DDD, Arquitectura Hexagonal y monolito multimodelo. Implementa el Capítulo 3 recalibrado de PeopleCole como un módulo de estudiante escolar y consumo del registro maestro transversal. Reglas obligatorias:
1. NO redefinir persona, household, guardianía, responsabilidad financiera, retiro, roles generales, IAM link ni documentos personales; eso vive en el módulo transversal.
2. SÍ modelar student_profile, student_school_identity, student_lifecycle_event, student_operational_household_context, student_operational_contact_resolution, student_operational_snapshot, student_consumer_projection_status y student_school_document_binding.
3. usar PostgreSQL para la persistencia principal;
4. usar MongoDB para proyecciones enriquecidas y snapshots de consumo escolar;
5. usar Redis para resolución rápida de student profile, household operativo, contactos operativos y health summary;
6. aplicar @Version en toda entidad mutable;
7. integrar con core transversal, IAM, digital asset hub y módulos consumidores como matrícula, comunicación y RUDE;
8. incorporar observabilidad enterprise desde el diseño.

### 19.2 Prompt para entidades JPA

Genera las entidades JPA del Capítulo 3 recalibrado de PeopleCole usando Java 21 y Spring Boot. Usa @Version en toda entidad mutable. Modela pc_student_profile, pc_student_school_identity, pc_student_lifecycle_event, pc_student_operational_household_context, pc_student_operational_contact_resolution, pc_student_operational_snapshot, pc_student_consumer_projection_status, pc_student_school_document_binding y pc_student_metric_registry. Prohíbe redefinir tablas del módulo transversal de personas y households.

### 19.3 Prompt para observabilidad

Diseña la observabilidad enterprise del Capítulo 3 recalibrado de PeopleCole. Incluye logs estructurados con correlation_id, métricas de student profile creation, operational household resolution, operational contacts resolution, snapshot generation, stale projections and cross-module student projection health. Correlaciona PostgreSQL, MongoDB, Redis, core-person-household-registry, IAM y digital-asset hub.

## 20. Cierre brutal del recalibrado

Con este recalibrado, el Capítulo 3 deja de competir contra el módulo transversal y pasa a hacer exactamente lo que un sistema enterprise escolar debe hacer: **convertir a la persona maestra en estudiante escolar consumible por toda la operación del colegio**.

Eso significa que:

- la persona vive en el core transversal;
- la familia vive en el core transversal;
- los apoderados viven en el core transversal;
- la responsabilidad financiera vive en el core transversal;
- la autorización de retiro vive en el core transversal;
- y este capítulo solo se dedica a la capa escolar del estudiante y a la resolución operativa de ese contexto para los módulos consumidores.

Eso es brutalmente mejor, brutalmente más limpio y brutalmente más enterprise.

Este nuevo Capítulo 3 ya no tiene por qué parecerse al módulo transversal. Ahora sí queda distinto. Ahora sí queda sin solapamiento. Ahora sí cumple una función propia.

## 21. Siguiente paso recomendado

Una vez aceptado este recalibrado, el siguiente movimiento correcto es:

**recalibrar también el frontend del Capítulo 3**

para que el frontend ya no trate a estudiante/familia/apoderado como si el capítulo fuera dueño del registro maestro, sino como un **frontend escolar consumidor del núcleo transversal de personas, families y apoderados**.