# People School Service

Documentacion completa: [docs/DOCUMENTACION-PEOPLE-SCHOOL.md](docs/DOCUMENTACION-PEOPLE-SCHOOL.md).

Guia de levantamiento: [docs/operations/LEVANTAMIENTO-PEOPLE-SCHOOL-SERVICE.md](docs/operations/LEVANTAMIENTO-PEOPLE-SCHOOL-SERVICE.md).

Modular hexagonal monolith for the four foundational PeopleCole chapters.

The deployable contains four isolated business modules:

- `institutionalpersonhouseholdregistry`: Capitulo 0, person and household master data;
- `governanceregistry`: Capitulo 1, institutional and temporal governance;
- `schoolregulationsregistry`: Capitulo 2, school regulations and regulatory governance;
- `studentregistry`: Capitulo 3, student profile and school lifecycle.

Each module owns its domain, application use cases, inbound API and outbound adapters. Business
layers cannot depend directly on another module; cross-module access is implemented through ports
and infrastructure adapters. Shared code is limited to technical concerns such as security, tenancy
and observability.

Java root package:

```text
com.solveria.peopleschool
```

## Local Docker Execution

From this directory:

```powershell
docker compose up --build
```

The compose stack starts:

- `people-school-service` on host port `8082`;
- PostgreSQL on host port `5434`;
The Docker build context is the workspace root because the service depends on the local `core-plataform/core-platform` artifact.
