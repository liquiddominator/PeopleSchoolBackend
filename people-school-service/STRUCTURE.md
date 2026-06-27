# People School Service Structure

`people-school-service` is a modular hexagonal monolith. Build output and individual Java files are omitted below.

```text
people-school-service
|-- docs
|-- scripts
|-- src
|   |-- main
|   |   |-- java/com/solveria/peopleschool
|   |   |   |-- PeopleSchoolServiceApplication.java
|   |   |   |-- institutionalpersonhouseholdregistry  # Capitulo 0
|   |   |   |   |-- api
|   |   |   |   |-- application
|   |   |   |   |-- bootstrap
|   |   |   |   |-- domain
|   |   |   |   `-- infrastructure
|   |   |   |-- governanceregistry                    # Capitulo 1
|   |   |   |   |-- api
|   |   |   |   |-- application
|   |   |   |   |-- bootstrap
|   |   |   |   |-- domain
|   |   |   |   `-- infrastructure
|   |   |   |-- schoolregulationsregistry             # Capitulo 2
|   |   |   |   |-- api
|   |   |   |   |-- application
|   |   |   |   |-- bootstrap
|   |   |   |   |-- domain
|   |   |   |   `-- infrastructure
|   |   |   |-- studentregistry                       # Capitulo 3
|   |   |   |   |-- api
|   |   |   |   |-- application
|   |   |   |   |-- bootstrap
|   |   |   |   |-- domain
|   |   |   |   `-- infrastructure
|   |   |   `-- shared
|   |   |       |-- security
|   |   |       `-- tenancy
|   |   `-- resources
|   |       |-- application.yml
|   |       |-- application-docker.yml
|   |       `-- db/migration
|   `-- test
|       |-- java/com/solveria/peopleschool
|       |   `-- architecture
|       `-- resources/application-test.yml
|-- Dockerfile
|-- docker-compose.yml
|-- pom.xml
`-- README.md
```
