# Documentacion de IAM Service

Indice tecnico y operativo del servicio de identidad y acceso.

| Tema | Documento |
|---|---|
| Levantamiento | [LEVANTAMIENTO-IAM-SERVICE.md](LEVANTAMIENTO-IAM-SERVICE.md) |
| API REST | [api/REST-API.md](api/REST-API.md) |
| Tokens y autenticacion | [api/AUTH-TOKENS.md](api/AUTH-TOKENS.md) |
| Integracion frontend | [api/FRONTEND-INTEGRATION.md](api/FRONTEND-INTEGRATION.md) |
| Indice de API | [api/DOCUMENTACION-API-IAM.md](api/DOCUMENTACION-API-IAM.md) |
| Base de datos | [database/BASE-DE-DATOS-IAM.md](database/BASE-DE-DATOS-IAM.md) |
| SQL consolidado | [database/iam_postgresql_schema.txt](database/iam_postgresql_schema.txt) |
| Runbook principal | [runbooks/MASTER-RUNBOOK.md](runbooks/MASTER-RUNBOOK.md) |
| Checklist de desarrollo | [runbooks/DEV-CHECKLIST.md](runbooks/DEV-CHECKLIST.md) |
| Checklist de release | [runbooks/RELEASE-CHECKLIST.md](runbooks/RELEASE-CHECKLIST.md) |

## Responsabilidad

IAM administra usuarios, credenciales, roles, permisos, scopes de tenant y sesiones. Emite access/refresh tokens, rota refresh tokens y revoca sesiones durante logout. Los servicios consumidores validan el JWT y consultan la revocacion compartida en Redis.

## Fuente de verdad

- Contrato runtime: `/v3/api-docs`.
- Schema: `src/main/resources/db/migration`.
- Configuracion: `src/main/resources/application*.yml`.
- Guia de ejecucion: [LEVANTAMIENTO-IAM-SERVICE.md](LEVANTAMIENTO-IAM-SERVICE.md).
