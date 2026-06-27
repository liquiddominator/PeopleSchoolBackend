# Structure Guide

This guide explains the modular hexagonal structure of the People School deployable.

## Root

- `.gitignore`: excludes build output, IDE metadata and local logs from version control.
- `.mvn/wrapper`: Maven Wrapper runtime files. They let the service build without requiring a globally installed Maven.
- `Dockerfile`: builds the service container. It first installs the local `core-plataform/core-platform` artifact, then packages this vertical.
- `docker-compose.yml`: local execution stack for the service and PostgreSQL.
- `mvnw`, `mvnw.cmd`: Unix and Windows Maven Wrapper launchers.
- `pom.xml`: Maven project definition, dependencies and build plugins for the vertical.
- `README.md`: quick service overview and local Docker execution notes.
- `STRUCTURE.md`: current tree of the service, excluding build output and `.gitkeep`.

## docs

- `docs/architecture`: architecture notes, module boundaries, dependency rules and hexagonal design decisions.
- `docs/domain`: business documentation for the four foundational chapters.
- `docs/api`: REST/internal API contracts, endpoint behavior and payload examples.
- `docs/database`: PostgreSQL, MongoDB, Redis and migration documentation.
- `docs/database/capitulo-0-1-postgresql-schema.txt`: consolidated documented PostgreSQL schema for Capitulo 0 and Capitulo 1. Capitulo 0 includes reviewed improvements already accepted; Capitulo 1 is currently transcribed faithfully from the document.
- `docs/integrations`: integration contracts with `core-plataform`, IAM, governance catalogs, document governance and digital assets.
- `docs/security`: permissions, data visibility, sensitive-profile access rules and audit expectations.
- `docs/operations`: local/dev/prod operation notes, environment variables and service health.
- `docs/testing`: unit, integration and contract testing strategy.
- `docs/decisions`: ADRs and important architectural decisions for the vertical.
- `docs/runbooks`: operational procedures for incidents, releases and maintenance.
- `docs/STRUCTURE_GUIDE.md`: this file.

## scripts

- `scripts`: home for repeatable service automation such as local test runners, Docker helpers, migration checks and release scripts.
- `scripts/README.md`: script conventions and expected usage.

## src/main

- `src/main/java`: Java production code.
- `src/main/java/com/solveria/peopleschool`: service root package.
- `src/main/java/com/solveria/peopleschool/PeopleSchoolServiceApplication.java`: Spring Boot application entry point.
- `src/main/java/com/solveria/peopleschool/institutionalpersonhouseholdregistry`: Capitulo 0 bounded context package.
- `src/main/java/com/solveria/peopleschool/governanceregistry`: Capitulo 1 institutional and temporal governance module.
- `src/main/java/com/solveria/peopleschool/schoolregulationsregistry`: Capitulo 2 regulatory governance module.
- `src/main/java/com/solveria/peopleschool/studentregistry`: Capitulo 3 student profile and lifecycle module.
- `src/main/java/com/solveria/peopleschool/shared/security`: IAM JWT validation and endpoint authorization shared by the deployable.
- `src/main/java/com/solveria/peopleschool/shared/tenancy`: tenant resolution shared by inbound adapters.
- `api`: inbound adapters for HTTP APIs.
- `api/admin`: administrative endpoints for operators and back-office workflows.
- `api/internal`: internal endpoints for consuming modules and trusted service-to-service calls.
- `application`: use cases, application services, DTOs and policies.
- `application/usecase`: explicit business operations exposed by the application layer.
- `application/service`: orchestration services that coordinate ports, policies and use cases.
- `application/dto`: application-level request/response objects.
- `application/policy`: application policies such as visibility, access and evidence association rules.
- `bootstrap`: Spring wiring owned by each module.
- `domain`: domain model, invariants and value objects.
- `domain/person`: master person aggregate.
- `domain/personlegalidentity`: legal/document identity model.
- `domain/personnameprofile`: official, preferred and variant name profiles.
- `domain/personcontactpoint`: emails, phones and other contact channels.
- `domain/personaddress`: addresses and address purposes.
- `domain/household`: household or family group aggregate.
- `domain/householdmembership`: membership between persons and households.
- `domain/guardianrelationship`: parent, guardian, tutor and representative relationships.
- `domain/emergencycontact`: emergency contact relationships.
- `domain/financialresponsiblerelationship`: financial responsibility relationships.
- `domain/pickupauthorization`: authorized pickup/withdrawal relationships.
- `domain/institutionalaffiliation`: institutional affiliation context for a person.
- `domain/institutionalroleassignment`: assignment of governance role catalogs to persons.
- `domain/schoolorganizationalappointment`: assignment of formal appointment catalogs to persons.
- `domain/personidentitylink`: IAM subject linkage.
- `domain/persondocumentreference`: document references owned by person context.
- `domain/personsensitiveprofile`: restricted sensitive profile.
- `domain/personsensitivecoverage`: restricted coverage and insurance details.
- `domain/personconflict`: possible duplicate or conflict records.
- `domain/auditsnapshot`: audit snapshots for important aggregates.
- `domain/personevidencerelation`: canonical evidence relation to digital assets.
- `domain/valueobject`: shared immutable domain values.
- `infrastructure`: outbound adapters and technical implementations.
- `infrastructure/persistence/postgres`: PostgreSQL persistence entities, repositories and mappers.
- `infrastructure/projection/mongo`: MongoDB read projections.
- `infrastructure/cache/redis`: Redis cache adapters and invalidation logic.
- `infrastructure/iam`: IAM integration adapter.
- `infrastructure/documentgovernance`: document governance adapter.
- `infrastructure/digitalasset`: digital asset adapter.
- `infrastructure/mastercatalogs`: integration with institutional governance catalogs.
- `infrastructure/events`: domain event publishing/subscription adapters.
- `infrastructure/observability`: metrics, logs and tracing adapters.

## src/main/resources

- `application.yml`: default service configuration for local non-Docker execution.
- `application-docker.yml`: Docker profile configuration used by `docker-compose.yml`.
- `db/migration`: Flyway migrations for PostgreSQL.
- `db/migration/V1__create_institutional_person_household_registry.sql`: initial relational schema for Capitulo 0.

## src/test

- `src/test/java`: Java test code.
- `src/test/java/com/solveria/peopleschool/PeopleSchoolServiceApplicationTest.java`: minimal bootstrapping sanity test.
- `src/test/java/com/solveria/peopleschool/architecture`: ArchUnit rules that prevent business-layer dependencies between modules.
- `src/test/java/com/solveria/peopleschool/institutionalpersonhouseholdregistry/unit`: unit tests for domain and application behavior.
- `src/test/java/com/solveria/peopleschool/institutionalpersonhouseholdregistry/integration`: integration tests for persistence, adapters and service wiring.
- `src/test/java/com/solveria/peopleschool/institutionalpersonhouseholdregistry/contract`: service contract tests with external consumers/providers.
- `src/test/resources/application-test.yml`: test profile configuration.
