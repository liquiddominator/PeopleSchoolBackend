# PeopleCole

## Capítulo 1 Recalibrado del Backend: Gobierno Institucional, Temporal, Organizacional y Documental Maestro

*Versión brutalmente alineada con el Módulo Transversal de Personas, Familias y Apoderados, sin solapamiento arquitectónico*

## 1. Introducción brutal del recalibrado

Este documento reemplaza arquitectónicamente al Capítulo 1 anterior para dejarlo totalmente alineado con el **Módulo Transversal Backend: Registro Maestro de Personas, Familias y Apoderados**.

La recalibración era necesaria porque, una vez que el módulo transversal ya quedó definido como la capacidad base del core-platform para gobernar:

- persona;
- identidad legal;
- household;
- guardianía;
- responsabilidad financiera;
- autorizaciones de retiro;
- roles institucionales asignados a personas;
- cargos o nombramientos sobre personas;
- vínculo con IAM;
- documentos personales/familiares;
- datos sensibles controlados;

entonces el Capítulo 1 ya no puede seguir siendo un “capítulo general” ambiguo que insinúe propiedad sobre piezas humanas o relacionales que no le corresponden.

Por tanto, este nuevo Capítulo 1 queda definido con una frontera clarísima:

**El Módulo Transversal es el dueño de la identidad humana, familiar e institucional individual.**
**El Capítulo 1 es el dueño del gobierno institucional, temporal, organizacional, normativo, calendárico y documental maestro sobre el que operan todos los demás módulos, incluido el transversal.**

Eso significa que este capítulo NO vuelve a crear ni a gobernar:

- personas;
- familias;
- apoderados;
- responsables financieros;
- autorizaciones de retiro;
- perfiles sensibles humanos;
- roles asignados a personas;
- cargos asignados a personas.

Lo que sí gobierna este capítulo es algo distinto y absolutamente crítico:

- organización institucional;
- unidad educativa;
- sedes;
- estructura organizacional maestra;
- catálogo base de niveles educativos;
- catálogo institucional de roles funcionales;
- catálogo institucional de cargos o appointments;
- gestiones escolares múltiples en paralelo;
- reglas de resolución de contexto por gestión;
- periodización por gestión y por nivel;
- ventanas operativas;
- calendario escolar transversal;
- políticas institucionales versionadas;
- canales de integración;
- políticas de rollover o migración de gestión;
- documentos maestros de la gobernanza;
- y bindings formales entre contexto institucional y activos digitales.

Dicho de forma brutal: este capítulo deja de ser un “capítulo introductorio general” y pasa a ser el **Institutional Governance Engine + Time Governance Engine + Organizational Master Catalog Engine + Governance Document Binding Engine** de PeopleCole.

Y eso lo vuelve mucho más poderoso, mucho más limpio y mucho más enterprise.

## 2. Propósito real del capítulo

El propósito real del Capítulo 1 recalibrado es construir la **base institucional maestra** que todos los demás módulos consumen para operar correctamente.

PeopleCole debe poder responder aquí preguntas como:

- ¿qué organización educativa está operando?
- ¿qué unidad educativa concreta está habilitada?
- ¿qué sedes existen y cuál es la principal?
- ¿qué estructura organizacional maestra tiene el colegio?
- ¿qué niveles educativos reconoce institucionalmente?
- ¿qué roles institucionales pueden asignarse a personas desde el módulo transversal?
- ¿qué cargos o nombramientos existen como catálogo organizacional?
- ¿qué gestión escolar es la actual y cuáles están visibles?
- ¿qué reglas de contexto temporal aplican por módulo?
- ¿qué periodización corresponde y cómo se distribuye por nivel?
- ¿qué ventanas de operación están abiertas o cerradas?
- ¿qué calendario transversal bloquea o habilita procesos?
- ¿qué políticas institucionales están activas?
- ¿qué integraciones están habilitadas?
- ¿qué documentos maestros respaldan una política, una sede, una integración o una gestión?
- ¿qué snapshots institucionales fueron congelados para auditoría?

Este capítulo, entonces, no habla de “quién es la persona”. Habla de “en qué institución, bajo qué reglas, bajo qué estructura, bajo qué gestión, bajo qué ventanas, bajo qué políticas y bajo qué documentos maestros opera esa persona y opera el colegio entero”.

## 3. Frontera arquitectónica brutal entre este capítulo y el módulo transversal

### 3.1 Lo que ES dueño el Módulo Transversal

El módulo transversal es dueño de:

- persona base;
- identidad legal;
- nombres;
- contactos;
- direcciones;
- household;
- membresía de household;
- guardianía;
- contacto de emergencia;
- responsabilidad financiera;
- autorización de retiro;
- asignación de rol a persona;
- nombramiento o cargo asignado a persona;
- vínculo IAM;
- documentos personales y familiares;
- datos sensibles.

### 3.2 Lo que ES dueño este Capítulo 1

Este capítulo es dueño de:

- organización institucional;
- unidad educativa;
- sedes;
- estructura organizacional base;
- catálogo de niveles educativos;
- catálogo institucional de roles;
- catálogo institucional de cargos/appointments;
- multi-gestión escolar;
- contexto temporal y reglas de resolución;
- periodización;
- periodos académicos;
- ventanas operativas;
- calendario escolar transversal;
- políticas institucionales versionadas;
- canales de integración;
- política base de rollover;
- gobierno documental maestro;
- métricas y observabilidad de gobernanza.

### 3.3 Relación correcta entre ambos

El módulo transversal **consume** de este capítulo:

- pc_org_group
- pc_education_unit
- pc_unit_site
- pc_organizational_unit
- pc_education_level
- pc_institutional_role_catalog
- pc_appointment_catalog
- pc_school_year y su contexto temporal cuando corresponda

Pero el módulo transversal **no es dueño** de esas tablas.

### 3.4 Regla arquitectónica obligatoria

Si un desarrollador intenta crear en el módulo transversal un catálogo duplicado de sede, unidad organizacional, rol institucional, cargo institucional o gestión escolar, está rompiendo la arquitectura.

## 4. Qué gana PeopleCole con este recalibrado

Con este recalibrado, PeopleCole gana:

- **Separación limpia entre gobierno institucional y gobierno humano.**
- **Menos duplicidad** entre estructuras maestras y asignaciones de personas.
- **Mayor reutilización** del Capítulo 1 por el módulo transversal, Capítulo 3, regulatorio, financiero y demás módulos.
- **Un punto único de verdad** para sedes, niveles, gestiones, periodización, políticas, integraciones y catálogos organizacionales.
- **Una base documental maestra** asociable a políticas, calendarios, integraciones, sedes y snapshots de gestión.
- **Más facilidad para parametrizar distintos colegios bolivianos** sin contaminar otros módulos con catálogos repetidos.
- **Mayor coherencia para el frontend administrativo**, porque las decisiones de estructura y tiempo ya tienen dueño único.

## 5. Nuevo nombre funcional recomendado del capítulo

El nombre correcto recomendado es:

**Capítulo 1 Recalibrado: Gobierno Institucional, Temporal, Organizacional y Documental Maestro**

Este nombre deja claro que:

- aquí no nacen personas;
- aquí no nacen familias;
- aquí no nacen apoderados;
- aquí nace la estructura maestra del colegio sobre la que todo lo demás opera.

## 6. Tesis arquitectónica del capítulo

### 6.1 La gobernanza institucional es una capacidad first-class

La organización, la gestión, las políticas, la estructura organizacional, los niveles y el calendario no son “tablas auxiliares”. Son el motor rector del ERP.

### 6.2 La estructura organizacional y los catálogos de rol/cargo no pertenecen al módulo de personas

El módulo transversal asigna roles y cargos a personas, pero el significado institucional de esos roles y cargos nace aquí.

### 6.3 Multi-gestión no es solo un filtro de reportes

La gestión escolar debe poder coexistir con otras gestiones visibles, archivadas, editables o reportables, y resolver contexto por defecto sin confusión.

### 6.4 Periodización, ventanas y calendario son una sola familia de gobierno temporal

No deben tratarse como piezas sueltas. Juntas gobiernan cuándo se puede operar, consolidar, publicar o bloquear.

### 6.5 La gobernanza institucional tiene una dimensión documental inseparable

No basta con filas de configuración. Políticas, calendarios, integraciones y snapshots deben poder respaldarse formalmente con documentos maestros.

### 6.6 La gobernanza debe poder observarse

La plataforma debe saber si:

- falta una gestión actual;
- hay políticas activas sin documento primario;
- un calendario crítico no tiene circular asociada;
- un canal de integración está habilitado sin soporte documental;
- una estructura organizacional quedó inconsistente.

### 6.7 Concurrencia optimista obligatoria

Toda entidad mutable de este capítulo debe contemplar version BIGINT NOT NULL DEFAULT 0 para @Version.

## 7. Bounded contexts internos del monolito

### 7.1 institution-governance

Gestiona grupo organizacional, unidad educativa y sedes.

### 7.2 organization-structure-governance

Gestiona unidades organizacionales, catálogos de rol institucional y catálogos de cargos/appointments.

### 7.3 school-year-governance

Gestiona gestiones escolares, contexto actual, visibilidad y editabilidad.

### 7.4 academic-periodization

Gestiona niveles educativos, esquemas de periodización, periodos y ventanas.

### 7.5 school-calendar-engine

Gestiona calendario transversal y sus efectos operativos.

### 7.6 policy-registry

Gestiona políticas institucionales versionadas.

### 7.7 integration-governance

Gestiona canales de integración y su estado.

### 7.8 rollover-governance

Gestiona reglas base de migración entre gestiones.

### 7.9 governance-document-binding

Gestiona bindings entre contexto institucional y activos digitales/documentos maestros.

### 7.10 governance-observability

Gestiona métricas, anomalías y salud de la capa maestra.

## 8. Casos de uso críticos del capítulo

- Registrar organización educativa.
- Registrar unidad educativa.
- Registrar sede.
- Registrar unidad organizacional.
- Registrar catálogo institucional de roles.
- Registrar catálogo institucional de cargos.
- Crear gestión escolar.
- Marcar gestión actual.
- Cambiar visibilidad y editabilidad de una gestión.
- Configurar niveles educativos.
- Configurar esquema de periodización.
- Configurar reglas por nivel.
- Generar periodos académicos.
- Configurar ventanas operativas.
- Registrar evento del calendario transversal.
- Activar política institucional.
- Habilitar canal de integración.
- Definir política de rollover.
- Asociar documento maestro a gestión, política, sede, calendario o integración.
- Registrar snapshot institucional.
- Publicar anomalía de gobernanza.

## 9. DISEÑO FÍSICO EN POSTGRESQL

Se asume la existencia de pc_digital_asset, pc_digital_asset_version, pc_digital_asset_binding, pc_document_review y pc_digital_asset_access_audit del Capítulo 0 reforzado. Este capítulo no las duplica, las consume.

### 9.1 Tabla: pc_org_group

```sql
CREATE TABLE pc_org_group (
    id                              BIGSERIAL PRIMARY KEY,
    tenant_code                     VARCHAR(40) NOT NULL UNIQUE,
    legal_name                      VARCHAR(220) NOT NULL,
    commercial_name                 VARCHAR(220),
    tax_identifier                  VARCHAR(60),
    country_code                    VARCHAR(10) NOT NULL DEFAULT 'BO',
    default_currency_code           VARCHAR(10) NOT NULL DEFAULT 'BOB',
    default_timezone                VARCHAR(80) NOT NULL DEFAULT 'America/La_Paz',
    org_group_status                VARCHAR(30) NOT NULL,
    created_at                      TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at                      TIMESTAMP NOT NULL DEFAULT NOW(),
    version                         BIGINT NOT NULL DEFAULT 0
);
```

**¿Para qué sirve esta tabla?**

Representa la raíz organizacional superior del ERP. Es el contenedor institucional máximo bajo el cual viven la unidad educativa, las sedes, las políticas, las gestiones, la estructura organizacional y los documentos maestros de alto nivel.

Su función principal es evitar que el resto del sistema opere sin un contexto institucional superior claro. Incluso si hoy el proyecto arranca con un solo colegio, esta tabla deja bien resuelto el punto de entrada organizacional del tenant.

También permite que módulos como el transversal, el financiero, el regulatorio y el documental sepan desde qué entidad institucional superior deben resolver defaults, moneda, zona horaria y código de tenant.

**Explicación amplia de cada atributo**

- id: identificador interno del grupo organizacional.
- tenant_code: código único del tenant dentro de la plataforma.
- legal_name: razón social o nombre legal de la organización.
- commercial_name: nombre comercial o marca visible.
- tax_identifier: identificador tributario institucional.
- country_code: país base de operación.
- default_currency_code: moneda institucional por defecto.
- default_timezone: zona horaria oficial del tenant.
- org_group_status: estado operativo de la organización.
- created_at: creación técnica del registro.
- updated_at: actualización técnica del registro.
- version: concurrencia optimista.

### 9.2 Tabla: pc_education_unit

```sql
CREATE TABLE pc_education_unit (
    id                              BIGSERIAL PRIMARY KEY,
    org_group_id                    BIGINT NOT NULL REFERENCES pc_org_group(id),
    unit_code                       VARCHAR(50) NOT NULL,
    official_name                   VARCHAR(220) NOT NULL,
    short_name                      VARCHAR(120),
    ministry_rue_code               VARCHAR(80),
    administrative_dependency       VARCHAR(40) NOT NULL,
    institutional_type              VARCHAR(40) NOT NULL,
    service_type                    VARCHAR(40) NOT NULL,
    unit_status                     VARCHAR(30) NOT NULL,
    opened_on                       DATE,
    closed_on                       DATE,
    created_at                      TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at                      TIMESTAMP NOT NULL DEFAULT NOW(),
    version                         BIGINT NOT NULL DEFAULT 0,
    CONSTRAINT uk_pc_education_unit UNIQUE (org_group_id, unit_code)
);
```

**¿Para qué sirve esta tabla?**

Representa la unidad educativa formal del colegio sobre la cual operan personas, estudiantes, finanzas, políticas, calendario, RUDE y demás módulos. Es el verdadero centro institucional del colegio dentro del tenant.

Su función principal es permitir que la plataforma tenga una entidad educativa concreta y oficialmente identificable, separada del grupo organizacional superior.

También es la pieza que consumen el módulo transversal para afiliaciones y roles, el Capítulo 3 para estudiantes, el regulatorio para salidas oficiales y el financiero para contexto operativo del colegio.

**Explicación amplia de cada atributo**

- id: identificador de la unidad educativa.
- org_group_id: organización superior a la que pertenece.
- unit_code: código interno único de la unidad educativa.
- official_name: nombre oficial del colegio o unidad.
- short_name: nombre corto o abreviado.
- ministry_rue_code: referencia oficial institucional si aplica.
- administrative_dependency: dependencia administrativa del establecimiento.
- institutional_type: tipo institucional.
- service_type: modalidad o tipo de servicio educativo.
- unit_status: estado operativo de la unidad.
- opened_on: fecha de apertura institucional.
- closed_on: fecha de cierre, si alguna vez ocurre.
- created_at: creación técnica.
- updated_at: actualización técnica.
- version: concurrencia optimista.

### 9.3 Tabla: pc_unit_site

```sql
CREATE TABLE pc_unit_site (
    id                              BIGSERIAL PRIMARY KEY,
    education_unit_id               BIGINT NOT NULL REFERENCES pc_education_unit(id),
    site_code                       VARCHAR(50) NOT NULL,
    site_name                       VARCHAR(180) NOT NULL,
    address_line                    VARCHAR(250),
    city_name                       VARCHAR(120),
    department_name                 VARCHAR(120),
    phone_number                    VARCHAR(50),
    email_address                   VARCHAR(150),
    is_main_site                    BOOLEAN NOT NULL DEFAULT FALSE,
    site_status                     VARCHAR(30) NOT NULL,
    created_at                      TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at                      TIMESTAMP NOT NULL DEFAULT NOW(),
    version                         BIGINT NOT NULL DEFAULT 0,
    CONSTRAINT uk_pc_unit_site UNIQUE (education_unit_id, site_code)
);
```

**¿Para qué sirve esta tabla?**

Representa sedes, campus o sitios operativos de la unidad educativa. Es fundamental para colegios que operan con una sede principal y una o varias sedes complementarias, anexos o recintos.

Su función principal es permitir que toda la plataforma opere con contexto territorial claro: personas afiliadas, estudiantes asignados, roles por sede, calendario por sede y métricas por sede.

También es el punto natural para asociar mapas, croquis, reglamentos locales, activos visuales o documentos de soporte específicos de una sede mediante el hub digital.

**Explicación amplia de cada atributo**

- id: identificador de la sede.
- education_unit_id: unidad educativa a la que pertenece.
- site_code: código único de sede.
- site_name: nombre visible de la sede.
- address_line: dirección principal.
- city_name: ciudad de la sede.
- department_name: departamento boliviano asociado.
- phone_number: teléfono de contacto de la sede.
- email_address: correo de la sede.
- is_main_site: indica si es la sede principal.
- site_status: estado operativo de la sede.
- created_at: creación técnica.
- updated_at: actualización técnica.
- version: concurrencia optimista.

### 9.4 Tabla: pc_organizational_unit

```sql
CREATE TABLE pc_organizational_unit (
    id                              BIGSERIAL PRIMARY KEY,
    education_unit_id               BIGINT NOT NULL REFERENCES pc_education_unit(id),
    site_id                         BIGINT REFERENCES pc_unit_site(id),
    parent_organizational_unit_id   BIGINT REFERENCES pc_organizational_unit(id),
    organizational_unit_code        VARCHAR(60) NOT NULL,
    organizational_unit_name        VARCHAR(180) NOT NULL,
    organizational_unit_type        VARCHAR(50) NOT NULL,
    display_order                   INTEGER NOT NULL DEFAULT 0,
    unit_scope_status               VARCHAR(30) NOT NULL,
    created_at                      TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at                      TIMESTAMP NOT NULL DEFAULT NOW(),
    version                         BIGINT NOT NULL DEFAULT 0,
    CONSTRAINT uk_pc_organizational_unit UNIQUE (education_unit_id, organizational_unit_code)
);
```

**¿Para qué sirve esta tabla?**

Representa la estructura organizacional maestra del colegio: rectoría, dirección académica, dirección de nivel, coordinación, bienestar, finanzas, enfermería, convivencia, biblioteca u otras áreas.

Su función principal es dar un catálogo estructural y jerárquico que luego consumen el módulo transversal para afiliaciones/cargos, el financiero para centros operativos y la observabilidad institucional para cortes analíticos.

También evita que cada módulo invente su propia estructura organizacional informal o que cada cargo cargue texto libre de unidad sin control maestro.

**Explicación amplia de cada atributo**

- id: identificador de la unidad organizacional.
- education_unit_id: colegio al que pertenece.
- site_id: sede asociada cuando la unidad es local a una sede.
- parent_organizational_unit_id: unidad organizacional padre si existe jerarquía.
- organizational_unit_code: código único de la unidad organizacional.
- organizational_unit_name: nombre visible.
- organizational_unit_type: tipo de unidad organizacional.
- display_order: orden sugerido de visualización.
- unit_scope_status: estado operativo de la unidad.
- created_at: creación técnica.
- updated_at: actualización técnica.
- version: concurrencia optimista.

### 9.5 Tabla: pc_education_level

```sql
CREATE TABLE pc_education_level (
    id                              BIGSERIAL PRIMARY KEY,
    education_unit_id               BIGINT NOT NULL REFERENCES pc_education_unit(id),
    level_code                      VARCHAR(40) NOT NULL,
    level_name                      VARCHAR(120) NOT NULL,
    official_reference_code         VARCHAR(60),
    level_sequence                  INTEGER NOT NULL,
    level_status                    VARCHAR(30) NOT NULL,
    created_at                      TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at                      TIMESTAMP NOT NULL DEFAULT NOW(),
    version                         BIGINT NOT NULL DEFAULT 0,
    CONSTRAINT uk_pc_education_level UNIQUE (education_unit_id, level_code)
);
```

**¿Para qué sirve esta tabla?**

Representa el catálogo maestro de niveles educativos reconocidos institucionalmente, como inicial, primaria y secundaria. Es una dimensión maestra que otros capítulos consumen, pero cuyo dueño correcto es la gobernanza institucional.

Su función principal es evitar que el nivel educativo aparezca como texto libre disperso en varios módulos, especialmente en periodización, estructura académica, matrículas y reportes.

También sirve para que el módulo transversal, el Capítulo 3 y el regulatorio entiendan de forma consistente sobre qué niveles operan personas, estudiantes, periodos y contextos oficiales.

**Explicación amplia de cada atributo**

- id: identificador del nivel educativo.
- education_unit_id: colegio al que pertenece el catálogo.
- level_code: código único del nivel.
- level_name: nombre visible del nivel.
- official_reference_code: código oficial de referencia si aplica.
- level_sequence: orden de aparición institucional.
- level_status: estado operativo del nivel.
- created_at: creación técnica.
- updated_at: actualización técnica.
- version: concurrencia optimista.

### 9.6 Tabla: pc_institutional_role_catalog

```sql
CREATE TABLE pc_institutional_role_catalog (
    id                              BIGSERIAL PRIMARY KEY,
    education_unit_id               BIGINT NOT NULL REFERENCES pc_education_unit(id),
    role_code                       VARCHAR(60) NOT NULL,
    role_name                       VARCHAR(180) NOT NULL,
    role_family                     VARCHAR(60) NOT NULL,
    is_assignable_to_person         BOOLEAN NOT NULL DEFAULT TRUE,
    role_catalog_status             VARCHAR(30) NOT NULL,
    display_order                   INTEGER NOT NULL DEFAULT 0,
    created_at                      TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at                      TIMESTAMP NOT NULL DEFAULT NOW(),
    version                         BIGINT NOT NULL DEFAULT 0,
    CONSTRAINT uk_pc_institutional_role_catalog UNIQUE (education_unit_id, role_code)
);
```

**¿Para qué sirve esta tabla?**

Representa el catálogo institucional de roles que el colegio reconoce. No asigna el rol a una persona; solo define qué roles existen como referencia maestra.

Su función principal es separar claramente el catálogo del rol de la asignación del rol. El catálogo vive aquí; la asignación a persona vive en el módulo transversal.

También permite parametrización real entre colegios bolivianos, porque no todos usan exactamente la misma taxonomía de roles funcionales o familiares-institucionales.

**Explicación amplia de cada atributo**

- id: identificador del rol de catálogo.
- education_unit_id: colegio al que pertenece el catálogo.
- role_code: código único del rol.
- role_name: nombre visible del rol.
- role_family: familia funcional del rol.
- is_assignable_to_person: indica si el rol puede asignarse a una persona.
- role_catalog_status: estado del catálogo.
- display_order: orden sugerido de visualización.
- created_at: creación técnica.
- updated_at: actualización técnica.
- version: concurrencia optimista.

### 9.7 Tabla: pc_appointment_catalog

```sql
CREATE TABLE pc_appointment_catalog (
    id                              BIGSERIAL PRIMARY KEY,
    education_unit_id               BIGINT NOT NULL REFERENCES pc_education_unit(id),
    appointment_code                VARCHAR(60) NOT NULL,
    appointment_name                VARCHAR(180) NOT NULL,
    organizational_unit_type_scope  VARCHAR(60),
    requires_document_support       BOOLEAN NOT NULL DEFAULT FALSE,
    appointment_catalog_status      VARCHAR(30) NOT NULL,
    display_order                   INTEGER NOT NULL DEFAULT 0,
    created_at                      TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at                      TIMESTAMP NOT NULL DEFAULT NOW(),
    version                         BIGINT NOT NULL DEFAULT 0,
    CONSTRAINT uk_pc_appointment_catalog UNIQUE (education_unit_id, appointment_code)
);
```

**¿Para qué sirve esta tabla?**

Representa el catálogo institucional de cargos o nombramientos posibles. Igual que en el caso de roles, aquí no se asigna el cargo a una persona; se define el catálogo maestro de appointments.

Su función principal es permitir que el módulo transversal asigne nombramientos sobre una taxonomía institucional limpia y controlada, en lugar de texto libre o listas duplicadas por módulo.

También permite reglas como si cierto cargo exige documento de respaldo obligatorio, lo cual se conecta naturalmente con el hub documental y la auditoría institucional.

**Explicación amplia de cada atributo**

- id: identificador del cargo de catálogo.
- education_unit_id: colegio al que pertenece el catálogo.
- appointment_code: código único del cargo.
- appointment_name: nombre visible del cargo.
- organizational_unit_type_scope: alcance sugerido de tipo de unidad organizacional.
- requires_document_support: indica si el nombramiento exige documento respaldo.
- appointment_catalog_status: estado del catálogo.
- display_order: orden sugerido de visualización.
- created_at: creación técnica.
- updated_at: actualización técnica.
- version: concurrencia optimista.

### 9.8 Tabla: pc_school_year

```sql
CREATE TABLE pc_school_year (
    id                              BIGSERIAL PRIMARY KEY,
    education_unit_id               BIGINT NOT NULL REFERENCES pc_education_unit(id),
    school_year_code                VARCHAR(20) NOT NULL,
    school_year_name                VARCHAR(120) NOT NULL,
    start_date                      DATE NOT NULL,
    end_date                        DATE NOT NULL,
    lifecycle_status                VARCHAR(30) NOT NULL,
    is_current_default              BOOLEAN NOT NULL DEFAULT FALSE,
    is_visible_for_query            BOOLEAN NOT NULL DEFAULT TRUE,
    is_editable                     BOOLEAN NOT NULL DEFAULT TRUE,
    is_reportable                   BOOLEAN NOT NULL DEFAULT TRUE,
    is_archived                     BOOLEAN NOT NULL DEFAULT FALSE,
    context_priority                INTEGER NOT NULL DEFAULT 0,
    cloned_from_school_year_id      BIGINT REFERENCES pc_school_year(id),
    created_at                      TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at                      TIMESTAMP NOT NULL DEFAULT NOW(),
    version                         BIGINT NOT NULL DEFAULT 0,
    CONSTRAINT uk_pc_school_year UNIQUE (education_unit_id, school_year_code)
);
```

**¿Para qué sirve esta tabla?**

Representa la gestión escolar como dimensión maestra del tiempo institucional del ERP. Desde aquí se gobierna qué gestión está activa, visible, editable, reportable o archivada.

Su función principal es permitir multi-gestión real, donde el sistema puede seguir operando sobre la gestión actual mientras conserva consulta, cierre y trazabilidad de gestiones anteriores.

También es la tabla que consumen transversal, estudiantes, académicos, regulatorio, financiero y comunicación para resolver contexto temporal por defecto o histórico.

**Explicación amplia de cada atributo**

- id: identificador de la gestión escolar.
- education_unit_id: colegio al que pertenece.
- school_year_code: código corto de la gestión.
- school_year_name: nombre visible.
- start_date: fecha de inicio formal.
- end_date: fecha de cierre formal.
- lifecycle_status: estado del ciclo de vida de la gestión.
- is_current_default: indica si es la gestión actual por defecto.
- is_visible_for_query: indica si es visible para consultas.
- is_editable: indica si admite edición operativa.
- is_reportable: indica si sigue siendo reportable.
- is_archived: indica si fue archivada.
- context_priority: prioridad de resolución de contexto.
- cloned_from_school_year_id: referencia a gestión origen si fue clonada.
- created_at: creación técnica.
- updated_at: actualización técnica.
- version: concurrencia optimista.

### 9.9 Tabla: pc_school_year_context_rule

```sql
CREATE TABLE pc_school_year_context_rule (
    id                              BIGSERIAL PRIMARY KEY,
    school_year_id                  BIGINT NOT NULL REFERENCES pc_school_year(id),
    module_code                     VARCHAR(60) NOT NULL,
    resolution_mode                 VARCHAR(30) NOT NULL,
    allows_historical_query         BOOLEAN NOT NULL DEFAULT TRUE,
    allows_operational_edit         BOOLEAN NOT NULL DEFAULT FALSE,
    status                          VARCHAR(30) NOT NULL,
    created_at                      TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at                      TIMESTAMP NOT NULL DEFAULT NOW(),
    version                         BIGINT NOT NULL DEFAULT 0,
    CONSTRAINT uk_pc_school_year_context_rule UNIQUE (school_year_id, module_code)
);
```

**¿Para qué sirve esta tabla?**

Define reglas de resolución de contexto temporal por módulo. No todos los módulos deben comportarse igual frente a una gestión: unos pueden consultar histórico, otros pueden editar solo la actual.

Su función principal es evitar lógica temporal dura dispersa por todo el sistema y convertir esa decisión en gobierno explícito, auditable y parametrizable.

También condiciona cómo otros módulos —incluido el transversal— resuelven visibilidad, consulta histórica o edición sobre la gestión.

**Explicación amplia de cada atributo**

- id: identificador de la regla.
- school_year_id: gestión a la que aplica la regla.
- module_code: módulo afectado por la regla.
- resolution_mode: modo de resolución de contexto.
- allows_historical_query: permite consulta histórica.
- allows_operational_edit: permite edición operativa en esa gestión.
- status: estado de la regla.
- created_at: creación técnica.
- updated_at: actualización técnica.
- version: concurrencia optimista.

### 9.10 Tabla: pc_periodization_scheme

```sql
CREATE TABLE pc_periodization_scheme (
    id                              BIGSERIAL PRIMARY KEY,
    school_year_id                  BIGINT NOT NULL REFERENCES pc_school_year(id),
    scheme_code                     VARCHAR(50) NOT NULL,
    scheme_name                     VARCHAR(180) NOT NULL,
    scheme_type                     VARCHAR(30) NOT NULL,
    default_period_count            INTEGER NOT NULL,
    scheme_status                   VARCHAR(30) NOT NULL,
    created_at                      TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at                      TIMESTAMP NOT NULL DEFAULT NOW(),
    version                         BIGINT NOT NULL DEFAULT 0,
    CONSTRAINT uk_pc_periodization_scheme UNIQUE (school_year_id, scheme_code)
);
```

**¿Para qué sirve esta tabla?**

Representa el esquema maestro de periodización de una gestión. Aquí se define si la gestión trabaja con bimestres, trimestres u otra modalidad institucional.

Su función principal es gobernar el patrón base temporal de evaluación y operación académica, sobre el que luego se especializa por nivel.

También sirve como ancla para documentos oficiales de periodización, actas o resoluciones de la gestión asociados mediante el hub digital.

**Explicación amplia de cada atributo**

- id: identificador del esquema.
- school_year_id: gestión asociada.
- scheme_code: código único del esquema.
- scheme_name: nombre visible.
- scheme_type: tipo de periodización.
- default_period_count: cantidad base de periodos.
- scheme_status: estado operativo del esquema.
- created_at: creación técnica.
- updated_at: actualización técnica.
- version: concurrencia optimista.

### 9.11 Tabla: pc_periodization_level_rule

```sql
CREATE TABLE pc_periodization_level_rule (
    id                              BIGSERIAL PRIMARY KEY,
    periodization_scheme_id         BIGINT NOT NULL REFERENCES pc_periodization_scheme(id),
    education_level_id              BIGINT NOT NULL REFERENCES pc_education_level(id),
    period_count                    INTEGER NOT NULL,
    level_rule_status               VARCHAR(30) NOT NULL,
    created_at                      TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at                      TIMESTAMP NOT NULL DEFAULT NOW(),
    version                         BIGINT NOT NULL DEFAULT 0,
    CONSTRAINT uk_pc_periodization_level_rule UNIQUE (periodization_scheme_id, education_level_id)
);
```

**¿Para qué sirve esta tabla?**

Especializa el esquema de periodización por nivel educativo. Esto es clave en Bolivia, porque una misma gestión puede trabajar con diferencias por nivel o con fechas particulares para inicial, primaria y secundaria.

Su función principal es permitir una periodización institucional flexible sin sacrificar control maestro.

También evita que los niveles educativos queden desacoplados de la estructura temporal real que el colegio decidió para la gestión.

**Explicación amplia de cada atributo**

- id: identificador de la regla por nivel.
- periodization_scheme_id: esquema base al que pertenece.
- education_level_id: nivel educativo afectado.
- period_count: cantidad de periodos para ese nivel.
- level_rule_status: estado operativo de la regla.
- created_at: creación técnica.
- updated_at: actualización técnica.
- version: concurrencia optimista.

### 9.12 Tabla: pc_academic_period

```sql
CREATE TABLE pc_academic_period (
    id                              BIGSERIAL PRIMARY KEY,
    school_year_id                  BIGINT NOT NULL REFERENCES pc_school_year(id),
    periodization_scheme_id         BIGINT NOT NULL REFERENCES pc_periodization_scheme(id),
    education_level_id              BIGINT REFERENCES pc_education_level(id),
    period_code                     VARCHAR(50) NOT NULL,
    period_name                     VARCHAR(180) NOT NULL,
    period_sequence                 INTEGER NOT NULL,
    start_date                      DATE NOT NULL,
    end_date                        DATE NOT NULL,
    period_status                   VARCHAR(30) NOT NULL,
    created_at                      TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at                      TIMESTAMP NOT NULL DEFAULT NOW(),
    version                         BIGINT NOT NULL DEFAULT 0,
    CONSTRAINT uk_pc_academic_period UNIQUE (school_year_id, education_level_id, period_code)
);
```

**¿Para qué sirve esta tabla?**

Representa cada periodo académico concreto generado a partir de un esquema de periodización. Es una entidad temporal operativa que luego consumen evaluación, boletines, asistencia, ventanas y regulatorio.

Su función principal es convertir la política de periodización en periodos realmente operables por el sistema.

También sirve como base para asociar ventanas, publicaciones, cierres y documentación específica del periodo cuando sea necesario.

**Explicación amplia de cada atributo**

- id: identificador del periodo académico.
- school_year_id: gestión asociada.
- periodization_scheme_id: esquema del que deriva.
- education_level_id: nivel afectado si aplica.
- period_code: código único del periodo.
- period_name: nombre visible.
- period_sequence: orden del periodo en la secuencia anual.
- start_date: fecha de inicio.
- end_date: fecha de cierre.
- period_status: estado del periodo.
- created_at: creación técnica.
- updated_at: actualización técnica.
- version: concurrencia optimista.

### 9.13 Tabla: pc_period_operational_window

```sql
CREATE TABLE pc_period_operational_window (
    id                              BIGSERIAL PRIMARY KEY,
    academic_period_id              BIGINT NOT NULL REFERENCES pc_academic_period(id),
    window_code                     VARCHAR(60) NOT NULL,
    window_type                     VARCHAR(40) NOT NULL,
    window_name                     VARCHAR(180) NOT NULL,
    opens_at                        TIMESTAMP NOT NULL,
    closes_at                       TIMESTAMP NOT NULL,
    allows_teacher_partial_save     BOOLEAN NOT NULL DEFAULT TRUE,
    allows_teacher_consolidation    BOOLEAN NOT NULL DEFAULT FALSE,
    blocks_grade_edit_after_close   BOOLEAN NOT NULL DEFAULT TRUE,
    window_status                   VARCHAR(30) NOT NULL,
    created_at                      TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at                      TIMESTAMP NOT NULL DEFAULT NOW(),
    version                         BIGINT NOT NULL DEFAULT 0,
    CONSTRAINT uk_pc_period_operational_window UNIQUE (academic_period_id, window_code)
);
```

**¿Para qué sirve esta tabla?**

Modela las ventanas operativas reales del periodo, como carga parcial, consolidación, publicación u otras ventanas académicas controladas.

Su función principal es permitir que el sistema haga cumplir las reglas temporales de trabajo docente y administrativo sin lógica dura dispersa por el backend.

También permite que el calendario y la publicación de boletines interactúen coherentemente con el tiempo operativo institucional.

**Explicación amplia de cada atributo**

- id: identificador de la ventana.
- academic_period_id: periodo académico al que pertenece.
- window_code: código único de la ventana.
- window_type: tipo funcional de ventana.
- window_name: nombre visible de la ventana.
- opens_at: fecha y hora de apertura.
- closes_at: fecha y hora de cierre.
- allows_teacher_partial_save: permite guardado parcial.
- allows_teacher_consolidation: permite consolidación dentro de la ventana.
- blocks_grade_edit_after_close: bloquea edición después del cierre.
- window_status: estado operativo.
- created_at: creación técnica.
- updated_at: actualización técnica.
- version: concurrencia optimista.

### 9.14 Tabla: pc_school_calendar_event

```sql
CREATE TABLE pc_school_calendar_event (
    id                              BIGSERIAL PRIMARY KEY,
    education_unit_id               BIGINT NOT NULL REFERENCES pc_education_unit(id),
    school_year_id                  BIGINT NOT NULL REFERENCES pc_school_year(id),
    event_code                      VARCHAR(60) NOT NULL,
    event_name                      VARCHAR(220) NOT NULL,
    event_type                      VARCHAR(50) NOT NULL,
    event_scope                     VARCHAR(40) NOT NULL,
    start_date                      DATE NOT NULL,
    end_date                        DATE NOT NULL,
    blocks_academic_activity        BOOLEAN NOT NULL DEFAULT FALSE,
    blocks_attendance               BOOLEAN NOT NULL DEFAULT FALSE,
    blocks_grade_capture            BOOLEAN NOT NULL DEFAULT FALSE,
    blocks_teaching_execution       BOOLEAN NOT NULL DEFAULT FALSE,
    blocks_financial_operation      BOOLEAN NOT NULL DEFAULT FALSE,
    calendar_event_status           VARCHAR(30) NOT NULL,
    created_at                      TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at                      TIMESTAMP NOT NULL DEFAULT NOW(),
    version                         BIGINT NOT NULL DEFAULT 0,
    CONSTRAINT uk_pc_school_calendar_event UNIQUE (school_year_id, event_code)
);
```

**¿Para qué sirve esta tabla?**

Representa eventos del calendario escolar transversal, como feriados, actos, actividades especiales, recesos, jornadas institucionales o cualquier evento que impacte la operación.

Su función principal es permitir que el calendario sea una autoridad transversal real y no solo una agenda visual decorativa.

También es el punto natural para asociar circulares, resoluciones, anexos o documentos de respaldo del evento mediante el hub documental.

**Explicación amplia de cada atributo**

- id: identificador del evento de calendario.
- education_unit_id: colegio asociado.
- school_year_id: gestión asociada.
- event_code: código único del evento.
- event_name: nombre visible.
- event_type: tipo del evento.
- event_scope: alcance del evento.
- start_date: fecha de inicio.
- end_date: fecha de fin.
- blocks_academic_activity: bloquea actividad académica.
- blocks_attendance: bloquea asistencia.
- blocks_grade_capture: bloquea captura de notas.
- blocks_teaching_execution: bloquea ejecución pedagógica.
- blocks_financial_operation: bloquea ciertas operaciones financieras.
- calendar_event_status: estado operativo del evento.
- created_at: creación técnica.
- updated_at: actualización técnica.
- version: concurrencia optimista.

### 9.15 Tabla: pc_school_calendar_binding

```sql
CREATE TABLE pc_school_calendar_binding (
    id                              BIGSERIAL PRIMARY KEY,
    school_calendar_event_id        BIGINT NOT NULL REFERENCES pc_school_calendar_event(id),
    binding_type                    VARCHAR(40) NOT NULL,
    binding_ref_id                  VARCHAR(120) NOT NULL,
    binding_status                  VARCHAR(30) NOT NULL,
    created_at                      TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at                      TIMESTAMP NOT NULL DEFAULT NOW(),
    version                         BIGINT NOT NULL DEFAULT 0
);
```

**¿Para qué sirve esta tabla?**

Asocia un evento de calendario a objetivos o alcances específicos, como una sede, un nivel educativo, una unidad organizacional o algún otro ámbito controlado del colegio.

Su función principal es evitar que todo evento del calendario se trate como universal si en realidad su alcance es parcial o segmentado.

También permite enriquecer el evento con evidencia documental contextual y trazabilidad de a qué espacios institucionales aplica realmente.

**Explicación amplia de cada atributo**

- id: identificador del vínculo de calendario.
- school_calendar_event_id: evento origen.
- binding_type: tipo de objetivo vinculado.
- binding_ref_id: identificador del objetivo.
- binding_status: estado del vínculo.
- created_at: creación técnica.
- updated_at: actualización técnica.
- version: concurrencia optimista.

### 9.16 Tabla: pc_policy_registry

```sql
CREATE TABLE pc_policy_registry (
    id                              BIGSERIAL PRIMARY KEY,
    school_year_id                  BIGINT NOT NULL REFERENCES pc_school_year(id),
    policy_code                     VARCHAR(60) NOT NULL,
    policy_name                     VARCHAR(220) NOT NULL,
    policy_type                     VARCHAR(50) NOT NULL,
    policy_version_label            VARCHAR(50) NOT NULL,
    mongo_document_ref              VARCHAR(150) NOT NULL,
    is_active                       BOOLEAN NOT NULL DEFAULT FALSE,
    effective_from                  DATE,
    effective_to                    DATE,
    policy_status                   VARCHAR(30) NOT NULL,
    created_at                      TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at                      TIMESTAMP NOT NULL DEFAULT NOW(),
    version                         BIGINT NOT NULL DEFAULT 0,
    CONSTRAINT uk_pc_policy_registry UNIQUE (school_year_id, policy_code, policy_version_label)
);
```

**¿Para qué sirve esta tabla?**

Es el registro relacional maestro de políticas institucionales versionadas por gestión. Aquí viven reglas maestras como evaluación, asistencia, publicación, integraciones, licencias o cualquier política formal que requiera vigencia controlada.

Su función principal es separar el documento rico de política del registro transaccional y versionado que permite activarla, desactivarla y auditarla por gestión.

También es una pieza crítica para que los demás módulos sepan qué versión de política está activa sin mezclar configuración operativa con blobs documentales.

**Explicación amplia de cada atributo**

- id: identificador del registro de política.
- school_year_id: gestión a la que pertenece.
- policy_code: código funcional de la política.
- policy_name: nombre visible.
- policy_type: tipo de política.
- policy_version_label: etiqueta de versión.
- mongo_document_ref: referencia al documento rico en MongoDB.
- is_active: indica si la versión está activa.
- effective_from: inicio de vigencia.
- effective_to: fin de vigencia.
- policy_status: estado de la política.
- created_at: creación técnica.
- updated_at: actualización técnica.
- version: concurrencia optimista.

### 9.17 Tabla: pc_integration_channel

```sql
CREATE TABLE pc_integration_channel (
    id                              BIGSERIAL PRIMARY KEY,
    education_unit_id               BIGINT NOT NULL REFERENCES pc_education_unit(id),
    channel_code                    VARCHAR(60) NOT NULL,
    channel_name                    VARCHAR(180) NOT NULL,
    channel_type                    VARCHAR(50) NOT NULL,
    provider_name                   VARCHAR(120),
    is_enabled                      BOOLEAN NOT NULL DEFAULT FALSE,
    channel_status                  VARCHAR(30) NOT NULL,
    created_at                      TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at                      TIMESTAMP NOT NULL DEFAULT NOW(),
    version                         BIGINT NOT NULL DEFAULT 0,
    CONSTRAINT uk_pc_integration_channel UNIQUE (education_unit_id, channel_code)
);
```

**¿Para qué sirve esta tabla?**

Registra los canales de integración habilitados por el colegio: banco, pasarela, contabilidad, LMS, CMS, mensajería u otros conectores institucionales.

Su función principal es convertir las integraciones en una capacidad gobernada, versionable y auditable, en lugar de configuraciones ocultas o hardcoded.

También permite que cada integración tenga soporte documental, estado y proveedor claramente asociados dentro del gobierno maestro.

**Explicación amplia de cada atributo**

- id: identificador del canal de integración.
- education_unit_id: colegio asociado.
- channel_code: código único del canal.
- channel_name: nombre visible.
- channel_type: tipo de integración.
- provider_name: proveedor asociado.
- is_enabled: indica si está habilitado.
- channel_status: estado operativo del canal.
- created_at: creación técnica.
- updated_at: actualización técnica.
- version: concurrencia optimista.

### 9.18 Tabla: pc_school_year_rollover_policy

```sql
CREATE TABLE pc_school_year_rollover_policy (
    id                              BIGSERIAL PRIMARY KEY,
    school_year_id                  BIGINT NOT NULL REFERENCES pc_school_year(id),
    policy_code                     VARCHAR(60) NOT NULL,
    auto_promote_approved_students  BOOLEAN NOT NULL DEFAULT TRUE,
    carry_forward_family_links      BOOLEAN NOT NULL DEFAULT TRUE,
    carry_forward_financial_links   BOOLEAN NOT NULL DEFAULT TRUE,
    carry_forward_pickup_rules      BOOLEAN NOT NULL DEFAULT FALSE,
    default_target_shift_mode       VARCHAR(30) NOT NULL,
    rollover_policy_status          VARCHAR(30) NOT NULL,
    created_at                      TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at                      TIMESTAMP NOT NULL DEFAULT NOW(),
    version                         BIGINT NOT NULL DEFAULT 0,
    CONSTRAINT uk_pc_school_year_rollover_policy UNIQUE (school_year_id, policy_code)
);
```

**¿Para qué sirve esta tabla?**

Define la política base de migración entre gestiones. Es una pieza muy importante porque el colegio necesita saber qué arrastra y qué no al pasar de una gestión a otra.

Su función principal es centralizar la estrategia de rollover de la gestión, evitando reglas manuales sueltas cuando se promueve a estudiantes o se replica contexto hacia la siguiente gestión.

También delimita qué parte del contexto humano consumido desde el módulo transversal puede proyectarse a la siguiente gestión como referencia operativa.

**Explicación amplia de cada atributo**

- id: identificador de la política de rollover.
- school_year_id: gestión asociada.
- policy_code: código único de la política.
- auto_promote_approved_students: si promueve automáticamente a aprobados.
- carry_forward_family_links: si arrastra vínculos familiares operables.
- carry_forward_financial_links: si arrastra vínculos financieros.
- carry_forward_pickup_rules: si arrastra autorizaciones de retiro.
- default_target_shift_mode: regla por defecto del turno destino.
- rollover_policy_status: estado de la política.
- created_at: creación técnica.
- updated_at: actualización técnica.
- version: concurrencia optimista.

### 9.19 Tabla: pc_governance_document_binding

```sql
CREATE TABLE pc_governance_document_binding (
    id                              BIGSERIAL PRIMARY KEY,
    digital_asset_id                BIGINT NOT NULL REFERENCES pc_digital_asset(id),
    governance_context_type         VARCHAR(60) NOT NULL,
    governance_context_ref_id       VARCHAR(120) NOT NULL,
    document_role                   VARCHAR(60) NOT NULL,
    is_primary                      BOOLEAN NOT NULL DEFAULT FALSE,
    binding_status                  VARCHAR(30) NOT NULL,
    created_at                      TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at                      TIMESTAMP NOT NULL DEFAULT NOW(),
    version                         BIGINT NOT NULL DEFAULT 0
);
```

**¿Para qué sirve esta tabla?**

Esta tabla es la gran pieza documental del capítulo. Permite vincular un asset digital o documento a un contexto maestro de gobernanza, como una gestión, una sede, una política, un evento de calendario, una unidad organizacional o un canal de integración.

Su función principal es formalizar el respaldo documental de la capa maestra para que el sistema no se quede solo con configuración sin evidencia, contrato, circular, anexo o snapshot.

También permite control de primariedad, contexto y trazabilidad documental sin mezclar binarios con las tablas relacionales de negocio.

**Explicación amplia de cada atributo**

- id: identificador del vínculo documental.
- digital_asset_id: asset digital asociado.
- governance_context_type: tipo de contexto maestro asociado.
- governance_context_ref_id: identificador lógico del contexto.
- document_role: rol funcional del documento dentro del contexto.
- is_primary: indica si es el documento principal del contexto.
- binding_status: estado del vínculo.
- created_at: creación técnica.
- updated_at: actualización técnica.
- version: concurrencia optimista.

### 9.20 Tabla: pc_governance_metric_registry

```sql
CREATE TABLE pc_governance_metric_registry (
    id                              BIGSERIAL PRIMARY KEY,
    education_unit_id               BIGINT REFERENCES pc_education_unit(id),
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

Permite registrar métricas resumidas de salud de la gobernanza maestra: consistencia de gestión actual, cobertura documental, integridad de políticas, estado de integraciones y completitud del calendario.

Su función principal es convertir la gobernanza institucional en algo observable y medible, no solo configurable.

También sirve como base para dashboards administrativos y alertas automáticas sobre huecos estructurales o documentales del sistema.

**Explicación amplia de cada atributo**

- id: identificador del registro métrico.
- education_unit_id: colegio asociado si aplica.
- school_year_id: gestión asociada si aplica.
- metric_code: código único de la métrica.
- metric_name: nombre visible de la métrica.
- metric_scope: alcance de la métrica.
- metric_value: valor cuantitativo medido.
- measured_at: momento de medición.
- metric_status: estado del registro.
- created_at: creación técnica.
- updated_at: actualización técnica.
- version: concurrencia optimista.

## 10. DISEÑO FÍSICO EN MONGODB

### 10.1 Colección: governance_policy_documents

**¿Para qué sirve esta colección?**

Guarda el documento rico completo de una política institucional versionada, incluyendo reglas, anexos, narrativa de aprobación y referencias documentales.

**Estructura sugerida**

```json
{
  "_id": "POLICY-2026-EVALUATION-V1",
  "schoolYearId": 22,
  "policyCode": "EVALUATION",
  "policyType": "EVALUATION",
  "policyVersionLabel": "2026-V1",
  "sourceType": "OFFICIAL_PLUS_INSTITUTIONAL",
  "rules": {},
  "attachments": [7001, 7002],
  "audit": {},
  "status": "ACTIVE",
  "version": 2
}
```

**Explicación amplia de campos**

- _id: identificador documental único.
- schoolYearId: gestión asociada.
- policyCode: código funcional de política.
- policyType: tipo de política.
- policyVersionLabel: versión visible.
- sourceType: origen normativo o institucional.
- rules: bloque rico de reglas.
- attachments: assets digitales asociados.
- audit: metadatos de aprobación.
- status: estado documental.
- version: versión del documento.

### 10.2 Colección: school_year_snapshot_documents

**¿Para qué sirve esta colección?**

Guarda snapshots ricos de la gestión escolar, incluyendo estructura, periodización, políticas activas y documentos maestros relevantes de la gestión.

**Estructura sugerida**

```json
{
  "_id": "SY-2026-SNAPSHOT-01",
  "schoolYearId": 22,
  "educationUnitId": 10,
  "snapshotType": "STRUCTURE_AND_POLICY",
  "generatedAt": "2026-01-15T10:00:00",
  "structure": {},
  "periodization": {},
  "policies": [],
  "documents": [7101, 7102],
  "version": 1
}
```

**Explicación amplia de campos**

- _id: identificador del snapshot.
- schoolYearId: gestión asociada.
- educationUnitId: colegio asociado.
- snapshotType: tipo del snapshot.
- generatedAt: momento de generación.
- structure: estructura institucional capturada.
- periodization: periodización activa capturada.
- policies: políticas incluidas.
- documents: assets documentales relevantes.
- version: versión documental.

### 10.3 Colección: periodization_documents

**¿Para qué sirve esta colección?**

Guarda el documento rico de periodización por gestión y por nivel, incluyendo anexos, resoluciones o plantillas institucionales relacionadas.

**Estructura sugerida**

```json
{
  "_id": "PERIODIZATION-2026-V1",
  "schoolYearId": 22,
  "schemeCode": "BIMESTER_2026",
  "levels": [],
  "attachments": [7201],
  "status": "ACTIVE",
  "version": 1
}
```

**Explicación amplia de campos**

- _id: identificador documental.
- schoolYearId: gestión asociada.
- schemeCode: código del esquema.
- levels: detalle rico por nivel.
- attachments: documentos asociados.
- status: estado documental.
- version: versión documental.

### 10.4 Colección: calendar_event_documents

**¿Para qué sirve esta colección?**

Guarda documentos enriquecidos del evento de calendario, como circulares, resoluciones, comunicados o anexos.

**Estructura sugerida**

```json
{
  "_id": "CAL-EVENT-2026-0001",
  "schoolCalendarEventId": 9001,
  "eventType": "HOLIDAY",
  "narrative": {},
  "attachments": [7301],
  "status": "ACTIVE",
  "version": 1
}
```

**Explicación amplia de campos**

- _id: identificador documental del evento.
- schoolCalendarEventId: referencia relacional asociada.
- eventType: tipo de evento.
- narrative: descripción enriquecida.
- attachments: assets documentales asociados.
- status: estado documental.
- version: versión documental.

### 10.5 Colección: governance_observability_documents

**¿Para qué sirve esta colección?**

Guarda análisis enriquecidos de observabilidad de la capa maestra, incluyendo anomalías estructurales y documentales: gestión actual faltante, políticas activas sin soporte, calendario sin circular o integración sin contrato asociado.

**Estructura sugerida**

```json
{
  "_id": "GOV-OBS-2026-0001",
  "schoolYearId": 22,
  "analysisType": "MISSING_PRIMARY_DOCUMENT",
  "details": {
    "message": "La política activa de evaluación no tiene documento primario asociado"
  },
  "status": "OPEN",
  "version": 1
}
```

**Explicación amplia de campos**

- _id: identificador del análisis.
- schoolYearId: gestión asociada.
- analysisType: tipo de hallazgo.
- details: detalle rico del hallazgo.
- status: estado del análisis.
- version: versión documental.

## 11. DISEÑO DE REDIS

### 11.1 Keyspace: pc:tenant:{tenantCode}:current-school-year

**¿Para qué sirve?**

Resuelve rápidamente la gestión actual por defecto del tenant.

**Ejemplo**

```
pc:tenant:PEOPLECOLE-UNIT10:current-school-year -> 22
```

### 11.2 Keyspace: pc:education-unit:{unitId}:visible-school-years

**¿Para qué sirve?**

Lista rápidamente las gestiones visibles para consulta histórica.

**Ejemplo**

```
[20, 21, 22]
```

### 11.3 Keyspace: pc:school-year:{schoolYearId}:periodization-summary

**¿Para qué sirve?**

Resume la periodización activa de la gestión.

**Ejemplo**

```
{
  "schemeType": "BIMESTER",
  "levels": {
    "INITIAL": 4,
    "PRIMARY": 4,
    "SECONDARY": 4
  }
}
```

### 11.4 Keyspace: pc:school-year:{schoolYearId}:calendar-flags

**¿Para qué sirve?**

Resume bloqueos y banderas activas del calendario por fecha.

**Ejemplo**

```
{
  "2026-04-02": {
    "blocksAcademicActivity": true,
    "blocksAttendance": true,
    "blocksGradeCapture": false
  }
}
```

### 11.5 Keyspace: pc:policy:{schoolYearId}:{policyCode}:active

**¿Para qué sirve?**

Resuelve rápidamente qué política está activa por tipo y gestión.

**Ejemplo**

```
pc:policy:22:EVALUATION:active -> POLICY-2026-EVALUATION-V1
```

### 11.6 Keyspace: pc:governance:health-summary

**¿Para qué sirve?**

Resume la salud funcional de la capa de gobernanza maestra.

**Ejemplo**

```
{
  "currentSchoolYearResolved": true,
  "periodizationComplete": true,
  "calendarLoaded": true,
  "policyRegistryConsistent": true,
  "primaryDocumentsPresent": false
}
```

### 11.7 Keyspace: pc:governance:context:{contextType}:{contextRefId}:documents

**¿Para qué sirve?**

Lista rápidamente los assets digitales asociados a un contexto maestro.

**Ejemplo**

```
[7001, 7002, 7005]
```

## 12. Reglas de negocio críticas del capítulo

- Solo puede existir una gestión actual por defecto por unidad educativa.
- Una gestión puede estar cerrada y seguir visible para consulta o reportabilidad.
- El catálogo institucional de roles vive aquí, pero la asignación de rol a una persona vive en el módulo transversal.
- El catálogo institucional de cargos vive aquí, pero el nombramiento sobre una persona vive en el módulo transversal.
- Los niveles educativos deben gobernarse aquí como catálogo maestro y no quedar repetidos en cada módulo.
- Las políticas activas pueden requerir respaldo documental obligatorio.
- Los canales de integración críticos deben poder asociarse a soporte documental válido.
- Un evento de calendario puede requerir circular o documento asociado.
- No deben activarse configuraciones maestras cuya política o regla institucional exija soporte documental y este falte.
- Todo vínculo documental maestro debe ser trazable y auditable.
- Los documentos maestros no deben romper la separación entre metadata transaccional y binario real.
- La gobernanza institucional debe detectar ausencia o inconsistencia estructural y documental.

## 13. Integración con el Módulo Transversal

Este capítulo es consumido por el módulo transversal para:

- resolver education_unit_id;
- resolver site_id;
- resolver organizational_unit_id;
- resolver catálogos de roles institucionales;
- resolver catálogos de cargos/appointments;
- resolver el contexto temporal escolar cuando una asignación o afiliación tenga vigencia por gestión.

**Regla estratégica**

El módulo transversal **no es dueño** del catálogo de roles ni del catálogo de cargos. Es dueño de la **asignación** de esas piezas a personas. Este capítulo es dueño del catálogo.

## 14. Integración con iam-service

Este capítulo depende de IAM para controlar:

- quién puede crear o cerrar una gestión;
- quién puede activar políticas institucionales;
- quién puede modificar el calendario transversal;
- quién puede subir o cambiar documentos maestros;
- quién puede ver contratos de integración, mapas de sede o anexos sensibles;
- quién puede emitir signed URLs para documentos maestros.

## 15. Integración con ai-service

Este capítulo puede usar AI-service para:

- clasificar documentos maestros;
- detectar políticas activas sin anexo adecuado;
- comparar versiones documentales entre gestiones;
- resumir diferencias entre snapshots;
- advertir documentos duplicados o desactualizados;
- sugerir inconsistencias entre configuración estructural y soporte documental.

**Lo que la IA no debe hacer sola**

- activar una política institucional crítica sin flujo humano;
- cambiar la gestión actual por defecto sin validación explícita;
- crear o alterar catálogos estructurales sin control institucional.

## 16. Observabilidad del capítulo

### 16.1 Métricas técnicas sugeridas

- tiempo promedio de creación de gestión;
- latencia de activación de política con documento asociado;
- tiempo de resolución de bindings documentales por contexto;
- latencia de signed URL para assets maestros;
- colisiones de concurrencia optimista en políticas o gestión.

### 16.2 Métricas funcionales sugeridas

- gestiones activas sin snapshot documental completo;
- políticas activas sin documento primario;
- eventos de calendario críticos sin circular asociada;
- integraciones activas sin soporte documental;
- catálogos de rol/cargo con inconsistencias de uso;
- sedes sin mapa o documento de referencia cuando la política lo exige.

### 16.3 Alertas críticas sugeridas

- más de una gestión actual por defecto;
- ninguna gestión actual definida;
- política activa sin documento requerido;
- binding documental roto hacia asset inexistente;
- asset maestro marcado primario pero inválido;
- integración habilitada sin contrato o soporte mínimo.

## 17. Riesgos de diseño que este capítulo debe evitar

- Duplicar en otros módulos catálogos de sede, unidad organizacional, rol o cargo.
- Permitir que el módulo transversal se vuelva dueño de catálogos estructurales.
- Mantener la gobernanza maestra desconectada de sus documentos de respaldo.
- Guardar archivos maestros dentro de tablas relacionales como binarios.
- Activar políticas o integraciones críticas sin soporte documental.
- No versionar o no auditar documentos maestros.
- No controlar permisos de acceso a documentos institucionales sensibles.
- No emitir observabilidad documental de la capa maestra.
- Confundir catálogo de rol/cargo con asignación de rol/cargo a persona.
- Tratar multi-gestión como simple filtro visual y no como capacidad de gobernanza real.

## 18. Prompts sugeridos para implementación

### 18.1 Prompt general del módulo

Actúa como arquitecto backend enterprise experto en Java 21, Spring Boot, DDD, Arquitectura Hexagonal y monolito multimodelo. Implementa el Capítulo 1 recalibrado de PeopleCole como módulo de gobierno institucional, temporal, organizacional y documental maestro. Reglas obligatorias:
1. este módulo es dueño de org_group, education_unit, unit_site, organizational_unit, education_level, institutional_role_catalog, appointment_catalog, school_year, school_year_context_rule, periodization_scheme, periodization_level_rule, academic_period, period_operational_window, school_calendar_event, school_calendar_binding, policy_registry, integration_channel, school_year_rollover_policy, governance_document_binding y governance_metric_registry;
2. NO asigna roles o cargos a personas; solo gobierna sus catálogos. La asignación vive en el módulo transversal;
3. usar PostgreSQL para metadata relacional;
4. usar MongoDB para políticas ricas, snapshots de gestión, periodización documental, calendario documental y observabilidad enriquecida;
5. usar Redis para contexto rápido institucional y de gestión;
6. usar object storage para binarios reales;
7. aplicar @Version en toda entidad mutable;
8. integrar con módulo transversal, IAM, digital asset hub y AI-service;
9. incorporar observabilidad enterprise desde el diseño.

### 18.2 Prompt para entidades JPA

Genera las entidades JPA del Capítulo 1 recalibrado de PeopleCole usando Java 21 y Spring Boot. Usa @Version en toda entidad mutable. Modela pc_org_group, pc_education_unit, pc_unit_site, pc_organizational_unit, pc_education_level, pc_institutional_role_catalog, pc_appointment_catalog, pc_school_year, pc_school_year_context_rule, pc_periodization_scheme, pc_periodization_level_rule, pc_academic_period, pc_period_operational_window, pc_school_calendar_event, pc_school_calendar_binding, pc_policy_registry, pc_integration_channel, pc_school_year_rollover_policy, pc_governance_document_binding y pc_governance_metric_registry. No metas asignaciones de persona ni lógica del módulo transversal.

### 18.3 Prompt para observabilidad

Diseña la observabilidad enterprise del Capítulo 1 recalibrado de PeopleCole. Incluye métricas de gobernanza, integridad de multi-gestión, cobertura documental, salud de política activa, uso de catálogos estructurales, integraciones habilitadas y bindings documentales. Agrega logs estructurados con correlation_id y alertas críticas de más de una gestión actual, política sin respaldo, integración sin soporte y catálogo estructural inconsistente.

## 19. Cierre brutal del recalibrado

Con este recalibrado, el Capítulo 1 deja de ser un “capítulo general ambiguo” y se convierte en el verdadero **gobierno institucional, temporal, organizacional y documental maestro** de PeopleCole.

Eso significa que:

- la persona ya no nace aquí;
- la familia ya no nace aquí;
- el apoderado ya no nace aquí;
- la asignación de roles y cargos a personas ya no nace aquí;
- pero sí nacen aquí la organización, la unidad educativa, la sede, la gestión, la estructura organizacional, los niveles, los catálogos de rol y cargo, la periodización, el calendario, las políticas, las integraciones y sus documentos maestros.

Eso es brutalmente mejor, brutalmente más limpio y brutalmente más enterprise.

Ahora sí el Capítulo 1 quedó distinto del módulo transversal.
Ahora sí el módulo transversal consume a Capítulo 1 en lugar de competir con él.
Ahora sí PeopleCole tiene una base maestra institucional y otra base maestra humana, cada una dueña de lo suyo.

## 20. Siguiente paso recomendado

El siguiente movimiento correcto es recalibrar también el **frontend del Capítulo 1**, para que el frontend administrativo entienda esta misma frontera y no mezcle:

- catálogos institucionales;
- multi-gestión;
- políticas y documentos maestros;
- con asignaciones humanas que pertenecen al módulo transversal.