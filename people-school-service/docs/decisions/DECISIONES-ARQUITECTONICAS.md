# Decisiones arquitectonicas vigentes

## ADR-001: un monolito modular para los cuatro capitulos

**Estado:** aceptada.

Los capitulos 0 a 3 se despliegan juntos como `people-school-service`, con limites por paquete y propiedad de modelo. Esto simplifica transacciones y operacion inicial sin renunciar a una separacion que permita extraer modulos posteriormente.

## ADR-002: arquitectura hexagonal dentro de cada modulo

**Estado:** aceptada.

API y persistencia son adaptadores. Las reglas viven en dominio/aplicacion y las necesidades externas se expresan como puertos de salida. ArchUnit verifica las dependencias mas importantes.

## ADR-003: PostgreSQL como fuente de verdad

**Estado:** aceptada.

El modelo transaccional reside en tablas `pc_*` y evoluciona exclusivamente con Flyway. MongoDB no participa actualmente en ejecucion aunque existan dependencia y paquetes reservados.

## ADR-004: IAM como autoridad de identidad

**Estado:** aceptada.

El servicio valida localmente JWT de IAM y aplica scopes/roles. No duplica usuarios, contrasenas, refresh tokens o sesiones. `PersonIdentityLink` conserva solo la vinculacion de dominio.

## ADR-005: aislamiento logico por tenant

**Estado:** aceptada.

El tenant se obtiene del token. Solo el administrador global puede hacer override con `X-Tenant-Id`. Todas las consultas de negocio deben incluir tenant para evitar referencias cruzadas.

## ADR-006: colaboracion modular mediante puertos

**Estado:** aceptada.

Cuando normativa necesita comprobar una unidad educativa, declara su propia interfaz y la infraestructura la implementa contra gobierno. No se permite una dependencia directa entre capas de negocio de modulos distintos.
