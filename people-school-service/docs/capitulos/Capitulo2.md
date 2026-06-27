# PeopleCole

## Capítulo 2 Recalibrado del Backend: Normativa Académica, Convivencia, Protocolos Escolares, Resoluciones y Gobierno Regulatorio Institucional

*Versión brutalmente alineada con el Capítulo 1 de gobierno institucional, el Módulo Transversal de Personas, Familias y Apoderados, el hub documental/digital y la realidad regulatoria de colegios bolivianos*

## 1. Cómo debe leerse este capítulo

Este documento no debe leerse como una nota conceptual ni como un resumen bonito de “reglamentos del colegio”. Debe leerse como una **guía de construcción backend** para el equipo que va a desarrollar el Capítulo 2 de PeopleCole con un nivel enterprise real.

Eso significa que aquí no solo se define qué es el módulo, sino:

- cómo debe pensarse el dominio;
- qué cosas sí le pertenecen;
- qué cosas no le pertenecen;
- cómo se integra con el Capítulo 1, con el módulo transversal de personas y con el hub documental;
- qué tablas necesita;
- qué reglas de negocio debe cumplir;
- cómo debe resolver vigencia, alcance, publicación y evidencia;
- y cuándo se puede decir, con seriedad, que el capítulo está realmente terminado.

También debe quedar clarísimo algo desde el principio:

Este capítulo no es un “repositorio de PDFs del colegio”.

Si el equipo lo implementa como:

- una carpeta con reglamentos subidos;
- un CRUD de archivos con título y fecha;
- o una tabla de “documentos” con estado,

el resultado será débil, peligroso e incorrecto desde el punto de vista arquitectónico.

La norma aquí no es el PDF. La norma es el **objeto institucional de negocio**. El archivo es una evidencia documental formal asociada a una versión normativa, y esa evidencia debe integrarse de forma canónica con el módulo transversal digital y documental.

Este capítulo, además, debe construirse ya sobre una base más fuerte: la existencia del **Maestro de Personas** como fuente única de identidad humana, institucional y relacional. En normativa escolar eso importa muchísimo, porque una norma no solo necesita estado y vigencia; necesita también trazabilidad real de:

- quién la propuso;
- quién la redactó;
- quién la revisó;
- quién la aprobó;
- quién la publicó;
- quién la firmó o respaldó formalmente;
- y bajo qué cargo o appointment actuó esa persona.

Por eso, este documento deja al equipo backend una guía brutal para tomar decisiones sólidas en:

- modelado del dominio normativo escolar;
- separación entre identidad normativa y versión normativa;
- modelado de alcance por sede, nivel, grado, modalidad, gestión o caso especial;
- modelado de actores institucionales humanos de la norma;
- relación entre versión normativa y evidencia documental;
- vínculo entre normativa y parámetros/ventanas/reglas del Capítulo 1;
- resolución normativa histórica y efectiva;
- arquitectura hexagonal limpia;
- persistencia PostgreSQL, proyección Mongo y caché Redis;
- integración con IAM, Maestro de Personas y hub documental;
- observabilidad, auditoría y pruebas.

## 2. Qué es realmente este capítulo y cuál es su responsabilidad dentro de PeopleCole

El Capítulo 2 es el dominio institucional que gobierna la **normativa escolar formal** del colegio. Ese enunciado parece simple, pero para el equipo backend debe traducirse con precisión brutal.

Este capítulo es la fuente formal donde el colegio:

- declara;
- clasifica;
- redacta;
- versiona;
- revisa;
- aprueba;
- publica;
- deroga;
- consulta históricamente;
- y relaciona

los actos normativos que gobiernan la operación escolar.

Eso incluye, por ejemplo:

- reglamento interno del colegio;
- reglamento de evaluación;
- reglamento de asistencia;
- manual de convivencia escolar;
- protocolos de disciplina;
- protocolos de licencias y permisos;
- protocolos de enfermería, salud o visita médica;
- circulares académicas;
- instructivos de inscripción y reinscripción;
- lineamientos por nivel;
- instructivos de boletines y publicación de notas;
- actos normativos que modifiquen o condicionen parámetros operativos;
- normativa asociada a RUDE, SEDUCA o exigencias regulatorias externas;
- y documentos normativos que afecten políticas de grupos, subgrupos, doble bachillerato, promoción o equivalencias.

La clave brutal para el equipo es esta:

Este capítulo no existe para “guardar documentos institucionales”; existe para convertir la dimensión formal, regulatoria y jurídica del gobierno escolar en objetos institucionales digitales versionables, trazables, resolubles y relacionables con el resto del sistema.

Eso cambia completamente la forma correcta de construirlo.

Si el sistema solo almacena un reglamento como archivo, no puede responder bien preguntas como estas:

- ¿Qué versión del reglamento de evaluación estaba vigente en junio de 2026 para secundaria en la sede principal?
- ¿Qué circular autorizó una excepción de cierre de notas en determinada gestión?
- ¿Qué autoridad institucional aprobó la versión del manual de convivencia que aplicaba al caso disciplinario consultado?
- ¿Qué protocolo de enfermería estaba vigente cuando se registró determinada atención?
- ¿Qué evidencia documental respalda la publicación de una norma usada por el sistema?
- ¿Qué versión normativa respaldaba la política de consolidación de notas o entrega de boletines?

Si el backend no puede responder claramente a ese tipo de preguntas, entonces todavía no está bien construido.

## 3. Por qué este capítulo es uno de los más impactados por el Maestro de Personas

El Maestro de Personas afecta muchos capítulos, pero en el Capítulo 2 su impacto es especialmente fuerte. La razón es simple: la normativa institucional tiene una relación natural con la **legitimidad institucional de los actores humanos**.

En normativa escolar importa muchísimo:

- quién emitió la norma;
- bajo qué autoridad o nombramiento se emitió;
- quién la redactó o propuso;
- quién la revisó;
- quién la aprobó;
- quién la publicó;
- quién la firmó o respaldó documentalmente;
- y quién ejecutó técnicamente la acción dentro del sistema.

Antes del Maestro de Personas, era muy fácil caer en diseños pobres donde el capítulo guardaba cosas como:

- issuing_authority = 'Dirección Académica'
- approved_by = 'secretaria.general'
- published_by = 'admin.school'
- signed_by = 'Director de Secundaria'

Eso funciona un tiempo, pero es muy débil desde el punto de vista enterprise. Mezcla persona con cargo, cargo con unidad, texto libre con legitimidad formal y, además, destruye la capacidad de reconstruir quién actuó realmente como persona institucional y con qué appointment.

Con el Maestro de Personas, ahora ya existe una fuente institucional única para modelar correctamente:

- la persona;
- sus identidades y trazabilidad institucional;
- sus roles funcionales;
- sus nombramientos organizacionales;
- su vínculo IAM;
- y su documentación institucional relacionada.

Por eso, este capítulo ya no debe tratar a la autoridad emisora o al aprobador como texto plano. Debe poder distinguir con fuerza:

- la persona institucional;
- el tipo de autoridad emisora;
- el appointment o cargo formal bajo el cual actúa;
- la identidad técnica IAM que ejecutó la acción;
- y la evidencia documental firmada o respaldada por esa persona.

Esto no convierte a Normativa Escolar en Maestro de Personas. La regla correcta es otra:

La persona nace y se gobierna en el Maestro de Personas; el Capítulo 2 solo referencia correctamente a esa persona cuando forma parte del ciclo de vida normativo.

## 4. Qué sigue siendo verdad del capítulo y qué no debe deformarse por la recalibración

### 4.1 Lo que no cambia

El dominio principal del capítulo sigue siendo:

- normativa escolar maestra;
- versión normativa;
- scope o ámbito normativo;
- vigencia;
- workflow de revisión/aprobación/publicación/derogación;
- relación con evidencia documental;
- vínculo con parámetros y configuraciones maestras;
- auditoría y resolución histórica.

Este capítulo no se convierte en:

- un módulo de personas;
- un sistema de RRHH;
- un módulo de IAM;
- un gestor documental genérico;
- un workflow engine transversal sin semántica.

### 4.2 Lo que sí cambia

Lo que sí cambia es la forma correcta de modelar la participación humana institucional sobre la norma.

A partir de ahora, este capítulo debe poder representar con precisión:

- persona emisora o autoridad emisora asociada;
- autoridad institucional de respaldo;
- appointment formal bajo el cual se emite o respalda;
- revisor institucional;
- aprobador institucional;
- publicador institucional;
- firmante institucional de evidencia;
- actor técnico IAM asociado al workflow.

### 4.3 Regla maestra para el equipo

El Maestro de Personas no cambia el centro del dominio normativo; cambia la forma correcta de modelar sus actores humanos, emisores y autoridades.

Si el equipo entiende esa frase, no va a romper el bounded context.

## 5. Propósito real del capítulo dentro de PeopleCole

El propósito real del Capítulo 2 es construir la **fuente normativa oficial de la operación escolar** del colegio.

Eso significa que este capítulo debe permitir:

- registrar la identidad institucional de una norma escolar;
- crear múltiples versiones sin destruir historial;
- modelar contenido, resumen, referencia legal, fecha de emisión y vigencia;
- definir el ámbito de aplicación de cada versión;
- someter una versión a revisión, aprobación, publicación o derogación;
- consultar qué versión normativa era válida en una fecha y contexto determinados;
- vincular una versión normativa con parámetros o reglas ejecutables del sistema;
- asociar evidencia documental canónica;
- reconstruir la capa humana institucional del ciclo de vida normativo.

El colegio no necesita aquí una “biblioteca de reglamentos”. Necesita un dominio que pueda responder con respaldo fuerte:

- qué norma estaba vigente;
- qué autoridad la emitió o respaldó;
- quién la aprobó y publicó dentro del sistema;
- qué evidencia digital la soporta;
- qué parámetros operativos se apoyan en ella.

## 6. Frontera real del capítulo con otros dominios

### 6.1 Qué sí es este capítulo

Es el dominio de gobierno de la normativa institucional escolar.

### 6.2 Qué no es este capítulo

No es:

- una carpeta digital de reglamentos;
- un repositorio de PDFs;
- un módulo de personas;
- un módulo de RRHH;
- un módulo de IAM;
- un gestor documental general;
- un motor de parámetros;
- un motor de evaluación;
- un motor de matrícula;
- ni un workflow engine transversal sin semántica.

### 6.3 Frontera con el Maestro de Personas

El Maestro de Personas es dueño de:

- pc_person;
- identidades legales;
- roles institucionales asignados a personas;
- appointments;
- vínculos IAM;
- documentación humana/institucional relacionada.

El Capítulo 2 solo debe guardar referencias limpias a personas cuando estas participen en la emisión, aprobación, publicación, firma o respaldo de una versión normativa.

### 6.4 Frontera con el hub digital/documental

El capítulo no guarda binarios, rutas físicas ni metadatos documentales profundos como si fuera repositorio. Los archivos viven en el hub documental. Aquí solo vive la relación canónica entre la versión normativa y la evidencia documental.

### 6.5 Frontera con el Capítulo 1

El Capítulo 1 gobierna reglas operativas resolubles, gestiones, políticas maestras, ventanas y calendario. El Capítulo 2 gobierna la fuente formal que puede originar, justificar, modificar o derogar esas reglas. No deben mezclarse, pero sí deben vincularse formalmente.

### 6.6 Frontera con el Capítulo 3

Capítulo 3 gobierna el estudiante escolar. Este capítulo puede explicar qué norma aplicaba al contexto del estudiante, pero no gobierna student profiles.

### 6.7 Frontera con IAM

IAM provee autenticación, autorización y sujeto técnico. Este capítulo no debe tratar a IAM como sustituto de la persona humana.

## 7. Qué sí hace y qué no hace este capítulo, explicado para programadores

### 7.1 Qué sí hace

- crea normativa institucional escolar;
- clasifica y describe normas;
- versiona sin destruir historial;
- define autoridad emisora y referencias normativas;
- define vigencia y ámbito de aplicación;
- mantiene flujo de revisión, aprobación, publicación y derogación;
- consulta normativa vigente por fecha y contexto;
- vincula normas con parámetros del Capítulo 1;
- asocia evidencia documental canónica;
- audita el ciclo de vida normativo;
- modela actores humanos institucionales del flujo.

### 7.2 Qué no hace

- no ejecuta matrícula;
- no calcula notas;
- no resuelve parámetros del Capítulo 1 como si fuera su motor;
- no sustituye al hub documental;
- no aloja archivos físicos;
- no duplica el dominio de personas;
- no representa autoridades como textos sueltos cuando el Maestro de Personas ya existe.

### 7.3 Error típico a evitar

Un error clásico sería modelar la norma como:

- school_regulation
- school_regulation_attachment
- school_regulation_history

Y listo.

Ese diseño trataría a la normativa como archivo con algo de metadata, no como dominio institucional con identidad, versiones, vigencia, autoridad y trazabilidad.

Otro error igual de grave sería guardar:

- issuing_authority = 'Dirección General'
- approved_by = 'juan.perez'

sin diferenciar persona, appointment, autoridad y actor técnico.

## 8. Actores reales del capítulo y cómo deben modelarse

### 8.1 Actores humanos institucionales

- dirección general;
- dirección académica;
- coordinación de inicial;
- coordinación de primaria;
- coordinación de secundaria;
- secretaría académica;
- administración escolar;
- comité de convivencia;
- orientación o psicopedagogía;
- enfermería;
- responsable legal o jurídico si existe;
- rectoría o consejo directivo del colegio;
- auditoría interna;
- calidad institucional.

### 8.2 Actores tecnológicos

- iam-service;
- core-platform;
- hub documental / digital-asset;
- proyecciones Mongo;
- caché Redis;
- procesos internos batch o simulación.

### 8.3 Cómo debe modelarse un actor humano ahora

El capítulo debe soportar, según el caso:

- person_id institucional;
- iam_subject_id técnico;
- role_code funcional visible;
- appointment_id si la acción se apoya en un cargo formal;
- issuing_authority_type_code como clasificación institucional del origen de la norma;
- y eventualmente unidad o sede asociada.

No todos los eventos necesitarán todas las capas. Pero el modelo debe soportarlas.

## 9. Objetivo técnico real del equipo backend

El equipo backend debe entregar un módulo que cumpla simultáneamente con estos resultados:

### 9.1 Dominio limpio

- sin lógica de negocio en controllers;
- sin JPA annotations en entidades de dominio;
- sin mezclar persistencia con invariantes;
- sin tratar la normativa como archivo.

### 9.2 Casos de uso explícitos

Cada operación relevante debe existir como caso de uso claro y bien nombrado.

### 9.3 Persistencia pensada para historia y resolución histórica

La base de datos debe permitir:

- identidad estable de la norma;
- múltiples versiones;
- vigencia temporal;
- publicación controlada;
- contradicción detectable;
- relación documental canónica;
- relación con parámetros.

### 9.4 Integración limpia con Maestro de Personas

Debe poder validar y usar referencias humanas institucionales sin copiar el dominio de personas.

### 9.5 Integración limpia con documental y activos digitales

La evidencia debe asociarse canónicamente y no como archivo local.

### 9.6 Observabilidad real

Cada creación, revisión, aprobación, publicación, conflicto o resolución histórica debe poder seguirse con correlationId, actor técnico y actor institucional.

## 10. Capacidades transversales obligatorias del capítulo

### 10.1 Multiidioma

Los nombres, resúmenes y descripciones funcionales de la normativa deben estar preparados al menos para Español y Portugués.

### 10.2 Un solo colegio, múltiples sedes

No se modelará multi-colegio artificial dentro de este módulo. El colegio es una unidad educativa dentro del tenant. Pero sí debe haber soporte multisede real.

### 10.3 Multinivel escolar

La normativa puede variar por inicial, primaria y secundaria.

### 10.4 Multimodalidad

La normativa puede variar por presencial, virtual o mixta si el colegio lo necesita.

### 10.5 Gestión digital integrada

Toda versión normativa debe poder vincular evidencia documental formal a través del hub digital.

### 10.6 Identidad institucional humana integrada

La trazabilidad de emisores, aprobadores, publicadores y firmantes debe formar parte nativa del ciclo de vida de la versión normativa.

## 11. Ubicación dentro del monolito multimodelo hexagonal

La estructura debe reflejar responsabilidades reales.

```text
peoplecole-school
  └── school-governance
       └── school-regulations
            ├── domain
            │   ├── schoolregulation
            │   ├── schoolregulationversion
            │   ├── regulationscope
            │   ├── normativereference
            │   ├── regulationparameterlink
            │   ├── publicationlog
            │   ├── auditsnapshot
            │   ├── evidencerelation
            │   ├── regulationactorreference
            │   ├── conflict
            │   └── policy
            ├── application
            │   ├── usecase
            │   ├── service
            │   ├── dto
            │   ├── command
            │   ├── query
            │   └── mapper
            ├── infrastructure
            │   ├── persistence
            │   │   └── postgres
            │   ├── projection
            │   │   └── mongo
            │   ├── cache
            │   │   └── redis
            │   ├── iam
            │   ├── digitalasset
            │   ├── personmaster
            │   ├── events
            │   └── observability
            ├── api
            │   ├── admin
            │   └── internal
            └── bootstrap
```

**Qué debe vivir en domain**

Normativa, versión, scope, referencias normativas, conflictos, relaciones, invariantes y políticas.

**Qué debe vivir en application**

Casos de uso, orquestación, DTOs, validaciones de aplicación y coordinación de puertos.

**Qué debe vivir en infrastructure**

Persistencia, adapters IAM, Person Master, digital-asset, Redis, Mongo, observabilidad y eventos.

**Error grave a evitar**

No meter la lógica de “normativa vigente por contexto y fecha” en queries opacas imposibles de explicar o mantener. Parte del filtrado puede apoyarse en SQL; la decisión final debe seguir siendo controlable desde aplicación/dominio.

## 12. Lenguaje ubicuo oficial del capítulo

El equipo debe compartir exactamente el mismo vocabulario.

**Términos obligatorios**

- School Regulation
- Regulation Definition
- School Regulation Version
- Regulation Scope
- Effective Date Range
- Normative Reference
- Issuing Authority
- Approval Status
- Publication Status
- Regulation Conflict
- Regulation Version Evidence
- Evidence Relation Type
- Regulation ↔ Parameter Link
- Institutional Issuing Actor
- Technical IAM Actor
- Supporting Appointment

**Por qué importa tanto**

Si un developer habla de “documento”, otro de “reglamento”, otro de “file”, otro de “norma” y otro de “attachment”, el diseño se degrada. El lenguaje ubicuo evita que el módulo se deslice hacia un simple repositorio documental.

## 13. Casos de uso del capítulo explicados para el equipo

- CreateSchoolRegulation
- UpdateSchoolRegulationMetadata
- AssignNormativeOwnerToSchoolRegulation
- CreateRegulationVersion
- EditDraftRegulationVersion
- DefineRegulationScope
- DefineEffectivePeriod
- SetVersionInstitutionalResponsibility
- SubmitRegulationVersionForApproval
- ApproveRegulationVersion
- RejectRegulationVersion
- PublishRegulationVersion
- DeprecateRegulationVersion
- AssociateRegulationVersionEvidence
- ReplacePrimaryRegulationVersionEvidence
- LinkRegulationToParameter
- UnlinkRegulationFromParameter
- ResolveEffectiveRegulation
- ExplainRegulationInstitutionalLifecycle
- SimulateRegulationImpact

## 14. Diseño de dominio recomendado, explicado como guía de implementación

### 14.1 Aggregate Root: SchoolRegulation

Es la identidad estable de la normativa escolar.

**Responsabilidad real**

- proteger unicidad del código;
- mantener clasificación general;
- gobernar colección de versiones;
- permitir cambios válidos a nivel maestro;
- anclar dueño funcional si la política lo requiere.

### 14.2 Entidad: SchoolRegulationVersion

Es la unidad operativa central del capítulo.

**Responsabilidad real**

- representar la versión concreta de la norma;
- sostener resumen y/o contenido estructurado;
- representar vigencia;
- representar estados de aprobación y publicación;
- sostener trazabilidad humana institucional;
- servir como objeto resoluble históricamente.

### 14.3 Value Object: RegulationScope

Modela el contexto de aplicación. No debe ser un map arbitrario.

### 14.4 Value Object: EffectiveDateRange

Encapsula lógica temporal básica de vigencia.

### 14.5 Value Object: NormativeReference

Encapsula la referencia formal: número de resolución, acta, circular, expediente, fecha de emisión, autoridad o libro fuente.

### 14.6 Entidad: RegulationParameterLink

Representa la relación semántica entre una versión normativa y un parámetro del Capítulo 1.

### 14.7 Entidad: SchoolRegulationPublicationLog

Representa la bitácora del flujo de vida de la versión.

### 14.8 Entidad: SchoolRegulationAuditSnapshot

Congela estados completos del agregado o subagregado.

### 14.9 Entidad: SchoolRegulationVersionEvidenceRelation

Modela la relación canónica entre versión y evidencia documental.

### 14.10 Entidad conceptual: SchoolRegulationActorReference

No necesariamente tiene que existir siempre como tabla aislada en la primera implementación, pero sí como concepto de diseño. Debe permitir representar:

- persona institucional;
- rol visible;
- appointment relevante;
- capa técnica IAM asociada al workflow.

### 14.11 Entidad: SchoolRegulationConflict

Formaliza contradicciones o superposiciones normativas detectadas.

## 15. Reglas de negocio brutales que este módulo debe cumplir

### 15.1 Reglas de identidad de la norma

- El código de la normativa debe ser único.
- La identidad maestra de la norma no debe destruirse con cada cambio.
- La norma maestra debe poder existir sin versión publicada.

### 15.2 Reglas de versión

- Toda versión debe pertenecer a una única norma maestra.
- El número de versión debe ser único dentro de la norma.
- Una nueva versión nunca debe destruir a la anterior.
- Una versión no publicada no debe resolverse como vigente.
- Una versión derogada no debe entrar a resolución futura.

### 15.3 Reglas de vigencia

- effective_from es obligatorio.
- effective_to puede ser nulo.
- No debe permitirse una vigencia inválida donde effective_to < effective_from.
- El sistema debe poder detectar solapamientos peligrosos entre versiones publicadas del mismo tipo normativo y mismo contexto cuando generen contradicción funcional.

### 15.4 Reglas de scope

- Una norma puede ser general para todo el colegio si así se define explícitamente.
- Una norma puede ser específica por sede, nivel, grado, modalidad, gestión, track curricular o condición especial.
- La resolución debe favorecer mayor especificidad compatible.
- Sede debe tratarse como raíz territorial principal.
- Nivel y modalidad deben influir en la aplicabilidad.

### 15.5 Reglas de workflow

- Toda versión debe nacer en borrador.
- Solo un borrador debe poder editarse libremente.
- Una versión sometida no debe comportarse como borrador abierto.
- Una normativa crítica no debe publicarse sin aprobación formal.
- Toda aprobación debe dejar trazabilidad institucional.
- Toda publicación debe dejar trazabilidad técnica e institucional.
- Rechazo y derogación deben dejar razón o notas.

### 15.6 Reglas de trazabilidad humana

- Debe poder registrarse persona responsable del cambio.
- Debe poder registrarse autoridad emisora o autoridad de respaldo.
- Debe poder registrarse appointment formal asociado a emisión o aprobación cuando corresponda.
- Debe poder registrarse aprobador institucional.
- Debe poder registrarse publicador institucional.
- Debe poder registrarse actor técnico IAM del workflow.

### 15.7 Reglas de evidencia documental

- Toda evidencia asociada debe apuntar a un activo digital válido.
- Deben existir tipos claros de relación de evidencia.
- Debe poder marcarse una evidencia principal.
- Debe poder existir más de una evidencia por versión.
- Debe poder asociarse firmante institucional si el negocio lo requiere.
- El capítulo no debe almacenar binarios locales.

### 15.8 Reglas de vínculo con parámetros

- El vínculo normativa ↔ parámetro debe ser explícito y trazable.
- El vínculo debe poder distinguir si la norma respalda, origina, modifica, reemplaza o deroga el parámetro.
- El vínculo no debe romper historia cuando cambia la norma o el parámetro.

### 15.9 Reglas de conflicto

- Si dos versiones publicadas compiten en el mismo contexto y generan contradicción funcional, debe detectarse conflicto.
- El conflicto debe registrarse como objeto de negocio.
- Debe poder asignarse responsable institucional de revisión.
- Debe poder dejarse constancia de autoridad consultada si el caso escala.

### 15.10 Reglas de resolución histórica y efectiva

- Solo deben considerarse versiones publicadas.
- Solo deben considerarse versiones vigentes en la fecha consultada.
- El scope debe ser compatible con el contexto.
- Debe privilegiarse mayor especificidad.
- Si persiste contradicción, debe registrarse conflicto o explicación de fallback.
- La respuesta debe ser explicable, no solo devolver un documento.

### 15.11 Reglas de seguridad y auditoría

- Toda operación crítica debe dejar correlationId.
- Toda transición de workflow debe dejar timestamp.
- Toda operación crítica debe registrar actor técnico y, si existe, actor institucional.
- La visibilidad de datos humanos debe respetar permisos.

## 16. DISEÑO FÍSICO PRINCIPAL EN POSTGRESQL

Este capítulo consume pc_unit_site, pc_school_year, pc_education_level, pc_digital_asset y referencias del módulo transversal, pero no redefine su dominio maestro.

### 16.1 Tabla pc_school_regulation

```sql
CREATE TABLE pc_school_regulation (
    id                              BIGSERIAL PRIMARY KEY,
    education_unit_id               BIGINT NOT NULL REFERENCES pc_education_unit(id),
    regulation_code                 VARCHAR(100) NOT NULL,
    title_i18n                      JSONB NOT NULL,
    description_i18n                JSONB,
    regulation_type_code            VARCHAR(50) NOT NULL,
    issuing_authority_type_code     VARCHAR(50),
    criticality_level               VARCHAR(50) NOT NULL,
    regulation_status               VARCHAR(50) NOT NULL,
    normative_owner_person_id       BIGINT REFERENCES pc_person(id),
    owning_organizational_unit_id   BIGINT REFERENCES pc_organizational_unit(id),
    created_at                      TIMESTAMP NOT NULL DEFAULT NOW(),
    created_by_user_id              VARCHAR(120) NOT NULL,
    created_by_person_id            BIGINT REFERENCES pc_person(id),
    updated_at                      TIMESTAMP,
    updated_by_user_id              VARCHAR(120),
    updated_by_person_id            BIGINT REFERENCES pc_person(id),
    version                         BIGINT NOT NULL DEFAULT 0,
    CONSTRAINT uq_pc_school_regulation_code UNIQUE (education_unit_id, regulation_code)
);
```

**¿Para qué sirve esta tabla?**

Representa la identidad estable de la norma escolar. Aquí no vive todavía una vigencia concreta ni una versión publicada específica; aquí vive el concepto persistente de la norma como objeto institucional.

**Qué problema resuelve**

Permite separar la norma como identidad maestra de sus versiones históricas. Sin esta tabla, cada cambio normativo destruiría historia o obligaría a tratar archivos como si fueran la norma misma.

**Qué debe entender el equipo**

Si alguien intenta guardar el documento vigente o el estado completo de publicación aquí, está mezclando identidad con historia. La historia vive en pc_school_regulation_version.

**Significado de cada campo**

- id: identificador técnico interno de la norma.
- education_unit_id: colegio asociado.
- regulation_code: código único y estable de la normativa.
- title_i18n: título multiidioma de la norma.
- description_i18n: descripción funcional multiidioma del alcance general.
- regulation_type_code: tipo funcional de la norma.
- issuing_authority_type_code: tipo general de autoridad emisora.
- criticality_level: nivel de criticidad institucional.
- regulation_status: estado del objeto normativo maestro.
- normative_owner_person_id: persona institucional responsable de la norma maestra.
- owning_organizational_unit_id: unidad organizacional dueña de la gobernanza.
- created_at: huella temporal de creación.
- created_by_user_id: actor técnico creador.
- created_by_person_id: actor humano institucional creador si aplica.
- updated_at: huella temporal de actualización.
- updated_by_user_id: actor técnico actualizador.
- updated_by_person_id: actor humano institucional actualizador.
- version: concurrencia optimista.

### 16.2 Tabla pc_school_regulation_version

```sql
CREATE TABLE pc_school_regulation_version (
    id                                  BIGSERIAL PRIMARY KEY,
    school_regulation_id                BIGINT NOT NULL REFERENCES pc_school_regulation(id),
    version_number                      INTEGER NOT NULL,
    summary_i18n                        JSONB,
    body_content_i18n                   JSONB,
    approval_status                     VARCHAR(50) NOT NULL,
    publication_status                  VARCHAR(50) NOT NULL,
    effective_from                      TIMESTAMP NOT NULL,
    effective_to                        TIMESTAMP,
    change_reason                       TEXT NOT NULL,
    issuing_date                        TIMESTAMP,
    legal_reference                     VARCHAR(255),
    source_record_reference             VARCHAR(255),
    change_owner_person_id              BIGINT REFERENCES pc_person(id),
    issuing_person_id                   BIGINT REFERENCES pc_person(id),
    issuing_appointment_id              BIGINT,
    supporting_authority_person_id      BIGINT REFERENCES pc_person(id),
    supporting_appointment_id           BIGINT,
    submitted_at                        TIMESTAMP,
    submitted_by_user_id                VARCHAR(120),
    submitted_by_person_id              BIGINT REFERENCES pc_person(id),
    approved_at                         TIMESTAMP,
    approved_by_user_id                 VARCHAR(120),
    approved_by_person_id               BIGINT REFERENCES pc_person(id),
    approved_by_appointment_id          BIGINT,
    published_at                        TIMESTAMP,
    published_by_user_id                VARCHAR(120),
    published_by_person_id              BIGINT REFERENCES pc_person(id),
    published_by_appointment_id         BIGINT,
    deprecated_at                       TIMESTAMP,
    deprecated_by_user_id               VARCHAR(120),
    deprecated_by_person_id             BIGINT REFERENCES pc_person(id),
    created_at                          TIMESTAMP NOT NULL DEFAULT NOW(),
    created_by_user_id                  VARCHAR(120) NOT NULL,
    created_by_person_id                BIGINT REFERENCES pc_person(id),
    updated_at                          TIMESTAMP,
    updated_by_user_id                  VARCHAR(120),
    updated_by_person_id                BIGINT REFERENCES pc_person(id),
    version                             BIGINT NOT NULL DEFAULT 0,
    CONSTRAINT uq_pc_school_regulation_version_number UNIQUE (school_regulation_id, version_number)
);
```

**¿Para qué sirve esta tabla?**

Esta es la tabla más importante del capítulo. Convierte la identidad abstracta de la norma en una unidad operativa consultable, con contenido, vigencia, workflow y trazabilidad.

**Qué problema resuelve**

Permite preservar historia normativa completa y responder qué versión estaba vigente, quién la aprobó, quién la publicó y qué referencia legal tenía.

**Qué debe entender el equipo**

Si la tabla maestra es “la norma como identidad”, esta tabla es “la norma como versión operativa e histórica”. Aquí viven los estados y la legitimidad temporal de la norma.

**Significado de cada campo**

- id: identificador interno de la versión normativa.
- school_regulation_id: referencia a la norma maestra.
- version_number: número secuencial de versión.
- summary_i18n: resumen multiidioma de la versión.
- body_content_i18n: contenido estructurado de la versión.
- approval_status: estado de aprobación.
- publication_status: estado de publicación.
- effective_from: inicio de vigencia.
- effective_to: fin de vigencia si aplica.
- change_reason: motivo formal del cambio.
- issuing_date: fecha formal de emisión del acto.
- legal_reference: número o referencia normativa.
- source_record_reference: referencia a expediente, acta o registro fuente.
- change_owner_person_id: persona responsable de la preparación funcional.
- issuing_person_id: persona institucional emisora.
- issuing_appointment_id: appointment bajo el cual se emite.
- supporting_authority_person_id: autoridad de respaldo.
- supporting_appointment_id: appointment asociado al respaldo.
- submitted_at: huella del sometimiento.
- submitted_by_user_id: actor técnico de submit.
- submitted_by_person_id: actor institucional de submit.
- approved_at: huella temporal de aprobación.
- approved_by_user_id: actor técnico aprobador.
- approved_by_person_id: actor institucional aprobador.
- approved_by_appointment_id: appointment usado al aprobar.
- published_at: huella temporal de publicación.
- published_by_user_id: actor técnico publicador.
- published_by_person_id: actor institucional publicador.
- published_by_appointment_id: appointment usado al publicar.
- deprecated_at: huella temporal de derogación.
- deprecated_by_user_id: actor técnico que depreca.
- deprecated_by_person_id: actor institucional que depreca.
- created_at: creación del borrador.
- created_by_user_id: actor técnico creador.
- created_by_person_id: actor institucional creador.
- updated_at: actualización técnica.
- updated_by_user_id: actor técnico actualizador.
- updated_by_person_id: actor institucional actualizador.
- version: concurrencia optimista.

### 16.3 Tabla pc_school_regulation_scope

```sql
CREATE TABLE pc_school_regulation_scope (
    id                              BIGSERIAL PRIMARY KEY,
    school_regulation_version_id    BIGINT NOT NULL REFERENCES pc_school_regulation_version(id),
    site_id                         BIGINT REFERENCES pc_unit_site(id),
    education_level_id              BIGINT REFERENCES pc_education_level(id),
    academic_grade_id               BIGINT,
    modality_code                   VARCHAR(50),
    school_year_id                  BIGINT REFERENCES pc_school_year(id),
    period_type_code                VARCHAR(50),
    special_track_code              VARCHAR(50),
    special_condition_code          VARCHAR(50),
    declared_priority               INTEGER NOT NULL DEFAULT 0,
    created_at                      TIMESTAMP NOT NULL DEFAULT NOW(),
    created_by_user_id              VARCHAR(120) NOT NULL,
    updated_at                      TIMESTAMP,
    updated_by_user_id              VARCHAR(120),
    version                         BIGINT NOT NULL DEFAULT 0
);
```

**¿Para qué sirve esta tabla?**

Define el ámbito de aplicación de una versión normativa. Es el corazón de la resolución histórica y contextual del módulo.

**Qué problema resuelve**

Permite expresar que una norma puede ser general para todo el colegio o específica por sede, nivel, grado, modalidad, gestión, periodo o condición especial.

**Significado de cada campo**

- school_regulation_version_id: versión normativa a la que pertenece el scope.
- site_id: sede a la que aplica.
- education_level_id: nivel educativo específico.
- academic_grade_id: grado académico concreto si aplica.
- modality_code: modalidad.
- school_year_id: gestión específica si la norma es temporal.
- period_type_code: tipo de periodo al que se refiere.
- special_track_code: track especial curricular si aplica.
- special_condition_code: condición especial o excepción.
- declared_priority: prioridad explícita adicional del scope.
- created_at: creación técnica.
- created_by_user_id: actor técnico creador.
- updated_at: actualización técnica.
- updated_by_user_id: actor técnico actualizador.
- version: concurrencia optimista.

### 16.4 Tabla pc_school_regulation_version_evidence_relation

```sql
CREATE TABLE pc_school_regulation_version_evidence_relation (
    id                                  BIGSERIAL PRIMARY KEY,
    school_regulation_version_id        BIGINT NOT NULL REFERENCES pc_school_regulation_version(id),
    digital_asset_id                    BIGINT NOT NULL REFERENCES pc_digital_asset(id),
    evidence_relation_type              VARCHAR(50) NOT NULL,
    signer_person_id                    BIGINT REFERENCES pc_person(id),
    signer_appointment_id               BIGINT,
    supporting_authority_person_id      BIGINT REFERENCES pc_person(id),
    supporting_appointment_id           BIGINT,
    is_primary                          BOOLEAN NOT NULL DEFAULT FALSE,
    relation_status                     VARCHAR(50) NOT NULL,
    effective_from                      TIMESTAMP,
    effective_to                        TIMESTAMP,
    notes                               TEXT,
    created_at                          TIMESTAMP NOT NULL DEFAULT NOW(),
    created_by_user_id                  VARCHAR(120) NOT NULL,
    created_by_person_id                BIGINT REFERENCES pc_person(id),
    updated_at                          TIMESTAMP,
    updated_by_user_id                  VARCHAR(120),
    updated_by_person_id                BIGINT REFERENCES pc_person(id),
    version                             BIGINT NOT NULL DEFAULT 0
);
```

**¿Para qué sirve esta tabla?**

Representa la asociación canónica entre una versión normativa y una evidencia documental gestionada por el hub digital.

**Qué problema resuelve**

Separa la verdad normativa de la verdad del archivo, pero mantiene vínculo formal entre ambas. Evita meter storage_url o binarios dentro del capítulo.

**Significado de cada campo**

- school_regulation_version_id: versión normativa respaldada.
- digital_asset_id: activo digital referenciado.
- evidence_relation_type: tipo de relación de evidencia.
- signer_person_id: persona institucional firmante si aplica.
- signer_appointment_id: appointment asociado a esa firma.
- supporting_authority_person_id: autoridad que respalda documentalmente la evidencia.
- supporting_appointment_id: appointment de esa autoridad.
- is_primary: indica si la evidencia es la principal.
- relation_status: estado del vínculo documental.
- effective_from: inicio de vigencia del vínculo si aplica.
- effective_to: fin de vigencia del vínculo si aplica.
- notes: notas explicativas.
- created_at: creación técnica.
- created_by_user_id: actor técnico creador.
- created_by_person_id: actor institucional creador.
- updated_at: actualización técnica.
- updated_by_user_id: actor técnico actualizador.
- updated_by_person_id: actor institucional actualizador.
- version: concurrencia optimista.

### 16.5 Tabla pc_school_regulation_parameter_link

```sql
CREATE TABLE pc_school_regulation_parameter_link (
    id                              BIGSERIAL PRIMARY KEY,
    school_regulation_version_id    BIGINT NOT NULL REFERENCES pc_school_regulation_version(id),
    parameter_ref_type              VARCHAR(50) NOT NULL,
    parameter_ref_id                VARCHAR(120) NOT NULL,
    relationship_type               VARCHAR(50) NOT NULL,
    relationship_status             VARCHAR(50) NOT NULL,
    notes                           TEXT,
    created_at                      TIMESTAMP NOT NULL DEFAULT NOW(),
    created_by_user_id              VARCHAR(120) NOT NULL,
    created_by_person_id            BIGINT REFERENCES pc_person(id),
    updated_at                      TIMESTAMP,
    updated_by_user_id              VARCHAR(120),
    updated_by_person_id            BIGINT REFERENCES pc_person(id),
    version                         BIGINT NOT NULL DEFAULT 0
);
```

**¿Para qué sirve esta tabla?**

Registra la relación entre una versión normativa y un parámetro/configuración/regla del Capítulo 1.

**Qué problema resuelve**

Permite demostrar qué acto normativo respalda, origina, modifica, reemplaza o deroga una regla operativa del sistema.

**Significado de cada campo**

- school_regulation_version_id: versión normativa vinculada.
- parameter_ref_type: tipo del parámetro referenciado.
- parameter_ref_id: identificador lógico del parámetro.
- relationship_type: tipo de relación.
- relationship_status: estado del vínculo.
- notes: notas explicativas.
- created_at: creación técnica.
- created_by_user_id: actor técnico creador.
- created_by_person_id: actor institucional creador.
- updated_at: actualización técnica.
- updated_by_user_id: actor técnico actualizador.
- updated_by_person_id: actor institucional actualizador.
- version: concurrencia optimista.

### 16.6 Tabla pc_school_regulation_publication_log

```sql
CREATE TABLE pc_school_regulation_publication_log (
    id                              BIGSERIAL PRIMARY KEY,
    school_regulation_version_id    BIGINT NOT NULL REFERENCES pc_school_regulation_version(id),
    action_type                     VARCHAR(50) NOT NULL,
    action_by_user_id               VARCHAR(120) NOT NULL,
    action_by_person_id             BIGINT REFERENCES pc_person(id),
    action_by_appointment_id        BIGINT,
    action_by_iam_subject_id        VARCHAR(150),
    action_at                       TIMESTAMP NOT NULL,
    comments                        TEXT,
    version                         BIGINT NOT NULL DEFAULT 0
);
```

**¿Para qué sirve esta tabla?**

Es la bitácora cronológica del flujo institucional de la versión normativa.

**Qué problema resuelve**

Permite reconstruir submit, review, approve, reject, publish y deprecate sin depender solo del estado actual.

**Significado de cada campo**

- school_regulation_version_id: versión afectada.
- action_type: tipo de evento del workflow.
- action_by_user_id: actor técnico original.
- action_by_person_id: persona institucional asociada.
- action_by_appointment_id: appointment bajo el cual actuó.
- action_by_iam_subject_id: sujeto IAM asociado.
- action_at: fecha y hora exacta del evento.
- comments: comentarios del evento.
- version: concurrencia optimista.

### 16.7 Tabla pc_school_regulation_audit_snapshot

```sql
CREATE TABLE pc_school_regulation_audit_snapshot (
    id                              BIGSERIAL PRIMARY KEY,
    aggregate_id                    BIGINT NOT NULL,
    aggregate_type                  VARCHAR(100) NOT NULL,
    snapshot_json                   JSONB NOT NULL,
    captured_at                     TIMESTAMP NOT NULL,
    captured_by_user_id             VARCHAR(120),
    captured_by_person_id           BIGINT REFERENCES pc_person(id),
    captured_by_iam_subject_id      VARCHAR(150),
    version                         BIGINT NOT NULL DEFAULT 0
);
```

**¿Para qué sirve esta tabla?**

Guarda fotografías completas del estado de la norma o de una versión en momentos relevantes.

**Qué problema resuelve**

Facilita reconstrucción histórica precisa sin depender únicamente de eventos parciales.

**Significado de cada campo**

- aggregate_id: ID del agregado capturado.
- aggregate_type: tipo del agregado o subagregado capturado.
- snapshot_json: estado serializado relevante.
- captured_at: momento de captura.
- captured_by_user_id: actor técnico que la detonó.
- captured_by_person_id: persona institucional asociada.
- captured_by_iam_subject_id: IAM subject técnico asociado.
- version: concurrencia optimista.

### 16.8 Tabla pc_school_regulation_conflict

```sql
CREATE TABLE pc_school_regulation_conflict (
    id                                  BIGSERIAL PRIMARY KEY,
    regulation_code                     VARCHAR(100) NOT NULL,
    left_regulation_version_id          BIGINT NOT NULL REFERENCES pc_school_regulation_version(id),
    right_regulation_version_id         BIGINT NOT NULL REFERENCES pc_school_regulation_version(id),
    conflict_type                       VARCHAR(100) NOT NULL,
    conflict_status                     VARCHAR(50) NOT NULL,
    detection_reason                    TEXT,
    review_owner_person_id              BIGINT REFERENCES pc_person(id),
    consulted_authority_person_id       BIGINT REFERENCES pc_person(id),
    detected_at                         TIMESTAMP NOT NULL,
    detected_by_user_id                 VARCHAR(120),
    resolved_at                         TIMESTAMP,
    resolved_by_user_id                 VARCHAR(120),
    resolved_by_person_id               BIGINT REFERENCES pc_person(id),
    resolution_notes                    TEXT,
    version                             BIGINT NOT NULL DEFAULT 0
);
```

**¿Para qué sirve esta tabla?**

Registra contradicciones o superposiciones normativas detectadas entre versiones o normas.

**Qué problema resuelve**

Vuelve visible y gobernable la contradicción normativa antes de que se manifieste como comportamiento ambiguo en otros módulos.

**Significado de cada campo**

- regulation_code: código funcional de la norma afectada.
- left_regulation_version_id: primera versión involucrada.
- right_regulation_version_id: segunda versión involucrada.
- conflict_type: naturaleza del conflicto.
- conflict_status: estado actual del conflicto.
- detection_reason: explicación o criterio de detección.
- review_owner_person_id: persona responsable de revisar el conflicto.
- consulted_authority_person_id: autoridad consultada si el caso escala.
- detected_at: huella temporal de detección.
- detected_by_user_id: actor técnico que detectó.
- resolved_at: huella temporal de resolución.
- resolved_by_user_id: actor técnico que resolvió.
- resolved_by_person_id: actor institucional que resolvió.
- resolution_notes: notas de cierre.
- version: concurrencia optimista.

## 17. Índices, performance y resolución

El equipo no debe postergar performance hasta el final.

**Índices recomendados**

- índice único por regulation_code en pc_school_regulation junto a education_unit_id;
- índice compuesto por school_regulation_id, version_number;
- índices por publication_status, effective_from, effective_to en pc_school_regulation_version;
- índices por site_id, education_level_id, modality_code, school_year_id y period_type_code en pc_school_regulation_scope;
- índices por school_regulation_version_id en evidencias, logs y vínculos;
- índice por regulation_code y conflict_status en conflictos.

**Recomendación brutal**

No intentes resolver todo con una sola query gigantesca si luego nadie puede explicarla o mantenerla. Combina buena selección de candidatos con lógica de decisión encapsulada y testeable.

## 18. Integración canónica con el Maestro de Personas

### 18.1 Principio base

El capítulo no copia personas. Las referencia.

### 18.2 Qué debe hacer el adapter personmaster

- validar existencia de personId;
- traer resumen seguro de persona;
- traer resumen de appointment si se necesita;
- distinguir actor institucional de iamSubject;
- rechazar referencias inválidas;
- no exponer datos sensibles irrelevantes.

### 18.3 DTOs mínimos recomendados

PersonSummaryDTO

- personId
- displayName
- preferredName
- primaryLegalIdentityMasked
- avatarAssetId
- activeRoleCodes[]
- activeAppointmentSummary
- siteSummary
- iamLinkedFlag

AppointmentSummaryDTO

- appointmentId
- appointmentCode
- appointmentLabel
- siteLabel
- organizationalUnitLabel
- startedAt
- endedAt

### 18.4 Qué NO debe hacer

- no leer tablas internas del Maestro de Personas desde repositories de este capítulo;
- no arrastrar el agregado Person completo para mostrar un nombre;
- no meter lógica de personas dentro del dominio normativo.

## 19. Integración con IAM

**Qué debe hacer**

- autorizar acceso por permisos;
- identificar sujeto técnico;
- dejar iamSubjectId disponible para trazabilidad.

**Qué no debe hacer**

- no reemplazar la noción de persona emisora o aprobadora;
- no convertirse en dueño del actor humano visible.

**Regla importante**

Siempre que sea posible, el sistema debe poder registrar ambas capas:

- actor técnico IAM;
- actor institucional del Maestro de Personas.

## 20. Integración con el hub documental / digital-asset

**Qué debe hacer el capítulo**

- asociar activos digitales ya gobernados externamente;
- clasificar el tipo de relación de evidencia;
- marcar evidencia principal;
- permitir firmante y autoridad si aplica;
- mantener vigencia o notas de la relación.

**Qué no debe hacer**

- no almacenar binarios;
- no modelar storage_url como verdad principal;
- no duplicar metadatos documentales profundos que ya viven en el hub documental.

## 21. Integración con otros módulos de la suite

### 21.1 Integración con el Capítulo 1

La relación con parámetros del Capítulo 1 es estratégica. El capítulo debe poder respaldar, originar, modificar o derogar parámetros ejecutables.

### 21.2 Integración con calendario y ventanas

Una norma puede respaldar políticas de ventanas, cierres, excepciones o actos académicos temporales. No reemplaza al calendario, pero puede legitimarlo o condicionarlo.

### 21.3 Integración con evaluación

Evaluación puede necesitar saber qué reglamento o resolución estaba vigente para cierto proceso o política de evaluación.

### 21.4 Integración con asistencia

Asistencia puede requerir saber qué protocolo o reglamento aplicaba a licencias, ausencias justificadas o mínimos de presencia.

### 21.5 Integración con historial escolar

El historial puede requerir reconstruir qué normativa respaldaba una operación en una fecha histórica.

### 21.6 Integración con convivencia y bienestar

La trazabilidad de manuales de convivencia, protocolos y evidencias es extremadamente valiosa para investigación interna, comités y defensa institucional.

### 21.7 Integración con Maestro de Personas

Toda la capa humana institucional del capítulo debe colgar del Maestro de Personas.

## 22. Proyección MongoDB recalibrada

Mongo no es fuente de verdad. Debe funcionar como proyección de lectura enriquecida.

**Qué debe contener**

- código normativo;
- versión;
- título visible;
- resumen;
- tipo normativo;
- scope resumido;
- vigencia;
- estado de aprobación/publicación;
- parámetros vinculados;
- resumen de evidencia;
- resumen institucional de emisión/aprobación/publicación.

**Por qué sirve**

Reduce llamadas cruzadas desde frontend y facilita buscador normativo, paneles y comparaciones.

**Colecciones sugeridas**

- school_regulation_documents
- school_regulation_resolution_documents
- school_regulation_conflict_documents
- school_regulation_observability_documents

## 23. Redis y estrategia de caché

Redis debe acelerar la resolución normativa efectiva, no reemplazar PostgreSQL.

**Clave sugerida**

```
school-regulation:{regulationCode}:{scopeHash}:{resolutionDate}
```

**Qué puede guardar**

- versión resuelta;
- scope resuelto;
- explicación corta;
- evidencias resumidas;
- personIds relevantes si el caso de uso lo justifica.

**Invalidaciones mínimas**

- publicación;
- derogación;
- cambio de scope;
- cambio importante de evidencia;
- conflicto relevante.

## 24. Resolver efectivo de normativa explicado como implementación seria

El resolvedor debe ser un caso de uso formal, no una utilidad improvisada.

**Algoritmo base**

- validar existencia del código normativo;
- tomar solo versiones publicadas;
- filtrar por vigencia efectiva;
- filtrar por compatibilidad de scope;
- calcular especificidad;
- detectar contradicción o superposición;
- aplicar reglas de selección;
- devolver versión, scope, evidencia y explicación.

**Qué cambia por Maestro de Personas**

No cambia el algoritmo central. Cambia la riqueza de la respuesta. Ahora la explicación puede incluir:

- persona emisora;
- appointment emisor;
- persona aprobadora;
- persona publicadora;
- firmante de evidencia relevante.

**Error crítico a evitar**

No repartir la lógica de resolución normativa entre frontend, queries sueltas y otros módulos. Debe haber una única verdad de resolución histórica y efectiva.

## 25. Seguridad y permisos

**Permisos base**

- school.regulations.read
- school.regulations.write
- school.regulations.version.create
- school.regulations.version.submit
- school.regulations.version.approve
- school.regulations.version.publish
- school.regulations.evidence.associate
- school.regulations.evidence.replace
- school.regulations.audit.read
- school.regulations.link.parameters
- school.regulations.simulate

**Permisos adicionales recomendados**

- school.regulations.actors.read
- school.regulations.issuing-authority.assign
- school.regulations.signer.assign
- school.regulations.human-trace.read

**Regla importante**

La visibilidad de datos humanos institucionales debe ser graduable. No todos los perfiles deben ver el mismo nivel de detalle.

## 26. Eventos de dominio sugeridos

- SchoolRegulationCreated
- SchoolRegulationVersionDrafted
- SchoolRegulationVersionSubmitted
- SchoolRegulationVersionApproved
- SchoolRegulationVersionRejected
- SchoolRegulationVersionPublished
- SchoolRegulationVersionDeprecated
- SchoolRegulationVersionEvidenceAssociated
- SchoolRegulationVersionEvidenceReplaced
- SchoolRegulationLinkedToParameter
- SchoolRegulationConflictDetected
- SchoolRegulationImpactSimulationRequested
- SchoolRegulationInstitutionalTraceEnriched

## 27. Observabilidad, auditoría y trazabilidad

Toda operación crítica debe dejar:

- correlationId;
- actor técnico;
- actor institucional si existe;
- appointment si existe;
- regulationCode;
- versionNumber;
- acción;
- resultado;
- timestamp;
- motivo del cambio;
- referencia normativa;
- evidencia documental impactada.

**Métricas recomendadas**

- normas creadas;
- versiones creadas;
- submits realizados;
- aprobaciones;
- publicaciones;
- conflictos detectados;
- latencia del resolver;
- cache hit/miss;
- evidencias asociadas;
- enriquecimiento humano exitoso/fallido;
- fallos de locking optimista.

**Logs estructurados mínimos**

- school_regulation_created
- school_regulation_version_drafted
- school_regulation_version_submitted
- school_regulation_version_approved
- school_regulation_version_published
- school_regulation_conflict_detected
- school_regulation_resolution_executed
- school_regulation_evidence_associated
- school_regulation_human_trace_enriched
- permission_denied

## 28. Estrategia de pruebas backend, ampliada para el equipo

### 28.1 Unit tests

Deben probar:

- unicidad del código normativo;
- creación de versión válida;
- validación de vigencia;
- transiciones válidas del workflow;
- publicación inválida bloqueada;
- asignación de autoridad emisora;
- asociación de appointment emisor;
- asociación de evidence signer;
- vínculo normativa ↔ parámetro;
- resolución simple y contextual;
- detección de conflicto;
- coexistencia de actor técnico y humano.

### 28.2 Integration tests

Deben probar:

- PostgreSQL real;
- queries de resolución;
- Redis cache;
- Mongo projection;
- IAM authorization;
- Person Master adapter;
- integración digital/documental;
- optimistic locking;
- flujo completo create → submit → approve → publish.

### 28.3 API tests

Deben probar:

- endpoints admin;
- endpoints internos de resolución;
- payloads enriquecidos con actores institucionales;
- errores de autorización;
- mensajes de error claros;
- serialización consistente.

### 28.4 Escenarios reales mínimos para PeopleCole

- norma general para todo el colegio;
- norma específica por sede;
- norma específica para secundaria y modalidad virtual o mixta;
- conflicto entre dos versiones superpuestas;
- aprobación por persona con appointment de director o coordinador;
- publicación por persona distinta enlazada a IAM;
- evidencia con firmante institucional;
- vínculo normativo con parámetro de asistencia, evaluación o publicación de boletines.

## 29. Prompts técnicos para el equipo

#### Prompt 1. Reescritura completa del módulo

Act as a principal enterprise Java architect. Create a production-ready backend module called school-regulations for the PeopleCole school suite using Spring Boot 3, Java 21, hexagonal architecture, and clean DDD boundaries. This is a single-school institutional module, not a multi-school SaaS domain by itself. It must support multiple sites, multiple school levels, multiple modalities, historical regulation resolution, approval and publication flows, Redis cache for effective regulation resolution, MongoDB read projections, IAM integration, canonical documentary evidence relations through a transversal digital-asset module, and strong integration with a transversal institutional-person-registry module so that issuing authorities, approvers, publishers, supporting authorities, institutional signers and workflow actors are modeled as real institutional persons instead of plain usernames. Do not place business logic in controllers and do not put JPA annotations in domain entities.

#### Prompt 2. Dominio con actores institucionales

Create domain entities and value objects for a backend module called school-regulations, recalibrated with a transversal institutional-person-registry: SchoolRegulation, SchoolRegulationVersion, RegulationScope, EffectiveDateRange, NormativeReference, RegulationParameterLink, SchoolRegulationPublicationLog, SchoolRegulationAuditSnapshot, SchoolRegulationVersionEvidenceRelation, SchoolRegulationActorReference, and SchoolRegulationConflict. Add invariants for code uniqueness, approval/publication lifecycle, historical preservation, scope validity, effective date validity, conflict detection, regulation-to-parameter traceability, allowed evidence relation types, issuing authority assignment, supporting authority assignment, institutional signer assignment, and human-vs-technical actor traceability.

#### Prompt 3. Persistencia recalibrada

Create the PostgreSQL persistence layer for the school-regulations module of PeopleCole, recalibrated with person master integration. Implement JPA entities, repositories, and mappers for pc_school_regulation, pc_school_regulation_version, pc_school_regulation_scope, pc_school_regulation_version_evidence_relation, pc_school_regulation_parameter_link, pc_school_regulation_publication_log, pc_school_regulation_audit_snapshot, and pc_school_regulation_conflict. Include person-related references such as normative_owner_person_id, issuing_person_id, issuing_appointment_id, supporting_authority_person_id, supporting_appointment_id, approved_by_person_id, published_by_person_id, signer references in evidence relations, and appointment references in workflow logs. Use optimistic locking, JSONB mappings, indexes for effective resolution, and clean naming conventions aligned with a school multisite model.

#### Prompt 4. Resolver enriquecido

Implement an EffectiveSchoolRegulationResolver use case for PeopleCole. The resolver must filter by regulation code, consider only published versions, validate effective dates, match regulation scope including school site, school level, grade, school year and modality, calculate specificity, detect contradiction or overlap, and return both the selected version and an explanation of why that version was selected. Additionally, enrich the response with institutional traceability data such as issuingPerson, issuingAppointment, approvedByPerson, publishedByPerson, signerSummary, and relevant documentary evidence metadata when available. Prepare the resolver for Redis caching and batch resolution.

#### Prompt 5. Integración con Person Master

Create an adapter for integrating the school-regulations module with a transversal institutional-person-registry module. The adapter must validate person existence, retrieve safe person summaries, retrieve appointment summaries, differentiate institutional person identity from IAM technical subject identity, and never duplicate person domain data locally. Support use cases such as assigning issuing authorities, supporting authorities, workflow actors, institutional approvers, institutional publishers, and documentary signers.

## 30. Definition of Done real del capítulo

Este capítulo solo puede considerarse terminado cuando exista:

- dominio normativo escolar claro;
- identidad estable de la norma;
- historia íntegra de versiones;
- workflow formal de aprobación/publicación;
- resolución normativa efectiva e histórica por contexto y fecha;
- detección de conflictos normativos;
- relación documental canónica con el hub digital;
- relación formal con parámetros del Capítulo 1;
- integración IAM;
- integración limpia con Maestro de Personas;
- observabilidad real;
- pruebas automatizadas relevantes;
- documentación técnica suficiente;
- capacidad de explicar no solo qué norma fue seleccionada, sino también qué personas institucionales estuvieron detrás de esa versión.

Si el sistema solo guarda reglamentos y archivos, no está terminado. Si resuelve una versión, pero no puede reconstruir autoridad emisora, aprobador, publicador y firmante institucional, tampoco está terminado.

## 31. Cierre estratégico brutal para el equipo backend

El Capítulo 2 no debe construirse como una carpeta institucional bonita. Debe construirse como una de las bases formales del gobierno escolar de PeopleCole.

Aquí se decide si PeopleCole tendrá memoria normativa real o solo archivos colgados. Aquí se decide si la suite podrá explicar qué acto normativo estaba vigente, por qué aplicaba y quién estuvo detrás de su emisión, aprobación y publicación. Y ahora, con el Maestro de Personas, también se decide si la legitimidad institucional de la norma quedará modelada de forma seria o degradada a textos ambiguos.

Este capítulo queda así en el nivel correcto: no como resumen, sino como guía brutal para los encargados de desarrollar el backend. Les deja claro qué deben construir, por qué deben construirlo así, qué tablas necesitan, qué significa cada campo, qué reglas de negocio deben cumplir, cómo se integra con el resto del ecosistema y qué errores no deben cometer.