# Compilacion e instalacion de Core Platform

## Naturaleza del proyecto

`core-plataform` no es un servicio HTTP y no ocupa URL ni puerto. Es un proyecto Maven que publica localmente la libreria `com.solveria:core-platform:1.0.0-SNAPSHOT`, consumida por IAM y People School.

## Requisitos

- Java 21.
- Maven Wrapper incluido.
- Repositorio Maven local escribible (`%USERPROFILE%\.m2`).

## Compilar, probar e instalar

Desde `PeopleSchoolBackend/core-plataform`:

```powershell
.\mvnw.cmd clean install
```

El comando:

1. compila el modulo `core-platform`;
2. ejecuta sus pruebas y verificaciones de formato configuradas;
3. instala el POM padre y el JAR en el repositorio Maven local.

Para una instalacion rapida sin pruebas, reservada para builds de contenedor o diagnostico:

```powershell
.\mvnw.cmd -DskipTests -Dspotless.check.skip=true install
```

## Verificar el artefacto

```powershell
Get-ChildItem "$env:USERPROFILE\.m2\repository\com\solveria\core-platform\1.0.0-SNAPSHOT"
```

Debe existir un archivo similar a `core-platform-1.0.0-SNAPSHOT.jar`.

## Uso por los servicios

Después de instalar la libreria:

```powershell
cd ..\iam-service
.\mvnw.cmd test

cd ..\people-school-service
.\mvnw.cmd test
```

Los Dockerfiles de IAM y People School copian `core-plataform` desde la raiz del backend y lo instalan dentro de la etapa de build, por lo que no dependen del repositorio Maven local del host.

## Limpieza

```powershell
.\mvnw.cmd clean
```

La limpieza elimina `target`; no desinstala el artefacto ya copiado en `%USERPROFILE%\.m2`.
