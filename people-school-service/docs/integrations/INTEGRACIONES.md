# Integraciones

| Sistema | Direccion | Proposito | Estado actual |
|---|---|---|---|
| IAM | IAM emite, este servicio consume | Access JWT, roles, scopes, tenant y revocacion | Activo |
| PostgreSQL | Salida | Persistencia transaccional de los cuatro modulos | Activo |
| Redis | Salida | Comprobacion de access tokens revocados | Activo/configurable |
| `core-platform` | Dependencia Maven | Base entity, excepciones y utilidades comunes | Activo |
| Modulo gobierno | Adaptador interno | Validar referencias desde normativa | Activo |
| MongoDB | Dependencia disponible | Proyecciones de lectura futuras | No activo; autoconfiguracion excluida |
| AI service | Servicio vecino | Capacidades de IA | Sin llamada implementada en este servicio |

## Contrato con IAM

IAM y `people-school-service` deben compartir issuer, algoritmo/secreto y convenio de revocacion Redis. El consumidor acepta solamente access tokens con `jti`, `sub`, `tenant_id`, roles y scopes pertinentes. Refresh y logout nunca se delegan a este backend funcional.

## Integracion interna entre modulos

Una llamada entre modulos no se hace importando un caso de uso ajeno desde la capa de aplicacion. El consumidor declara un puerto y un adaptador de infraestructura lo conecta al proveedor. El ejemplo actual es:

```text
schoolregulationsregistry.application.port.out.EducationUnitReferencePort
    <- schoolregulationsregistry.infrastructure.integration.governance.GovernanceEducationUnitAdapter
    -> governanceregistry persistence
```

Este patron permite sustituir la llamada interna por HTTP/eventos si algun modulo se separa en el futuro.

## Identificadores externos

- `iamSubjectId`: referencia opaca al subject de IAM.
- `assetId`: referencia opaca a un activo/documento; el binario no se almacena en este servicio.
- Las integraciones deben preservar `tenant_id` y no confiar en IDs sin validar pertenencia al tenant.
