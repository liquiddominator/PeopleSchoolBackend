# IAM API Documentation

Esta carpeta contiene la documentacion Markdown de la API REST de `iam-service`.

## Documentos

- [REST-API.md](./REST-API.md): referencia completa de endpoints, headers, payloads, responses y errores.
- [AUTH-TOKENS.md](./AUTH-TOKENS.md): flujo de login, access token, refresh token, rotacion y logout.
- [FRONTEND-INTEGRATION.md](./FRONTEND-INTEGRATION.md): reglas para clientes frontend y servicios secundarios que consumen JWT de IAM.

## Documentacion runtime

Cuando el servicio esta levantado:

- OpenAPI JSON: `GET /v3/api-docs`
- Swagger UI: `GET /swagger-ui.html`
