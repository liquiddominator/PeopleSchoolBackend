# PeopleSchool Backend

PeopleSchool backend monorepo.

## Projects

- `core-plataform`: shared technical platform and reusable backend components.
- `iam-service`: authentication, JWT lifecycle, authorization and tenant permissions.
- `people-school-service`: modular hexagonal monolith for person registry, institutional governance, school regulations and student registry.
- `ai-service`: AI capabilities and integrations.

## Local Ports

- IAM Service: `8080`
- AI Service: `8081`
- People School Service: `8082`

Each deployable keeps its own Maven wrapper, documentation and Docker configuration. Run commands from the corresponding project directory.
