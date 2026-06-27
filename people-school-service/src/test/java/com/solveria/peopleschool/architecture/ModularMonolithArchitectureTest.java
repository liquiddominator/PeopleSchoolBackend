package com.solveria.peopleschool.architecture;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

@AnalyzeClasses(
        packages = "com.solveria.peopleschool",
        importOptions = ImportOption.DoNotIncludeTests.class)
class ModularMonolithArchitectureTest {

    @ArchTest
    static final ArchRule personBusinessLayersAreIsolated =
            noClasses()
                    .that()
                    .resideInAnyPackage(
                            "..institutionalpersonhouseholdregistry.domain..",
                            "..institutionalpersonhouseholdregistry.application..")
                    .should()
                    .dependOnClassesThat()
                    .resideInAnyPackage(
                            "..governanceregistry..",
                            "..schoolregulationsregistry..",
                            "..studentregistry..");

    @ArchTest
    static final ArchRule governanceBusinessLayersAreIsolated =
            noClasses()
                    .that()
                    .resideInAnyPackage(
                            "..governanceregistry.domain..", "..governanceregistry.application..")
                    .should()
                    .dependOnClassesThat()
                    .resideInAnyPackage(
                            "..institutionalpersonhouseholdregistry..",
                            "..schoolregulationsregistry..",
                            "..studentregistry..");

    @ArchTest
    static final ArchRule regulationsBusinessLayersAreIsolated =
            noClasses()
                    .that()
                    .resideInAnyPackage(
                            "..schoolregulationsregistry.domain..",
                            "..schoolregulationsregistry.application..")
                    .should()
                    .dependOnClassesThat()
                    .resideInAnyPackage(
                            "..institutionalpersonhouseholdregistry..",
                            "..governanceregistry..",
                            "..studentregistry..");

    @ArchTest
    static final ArchRule studentBusinessLayersAreIsolated =
            noClasses()
                    .that()
                    .resideInAnyPackage(
                            "..studentregistry.domain..", "..studentregistry.application..")
                    .should()
                    .dependOnClassesThat()
                    .resideInAnyPackage(
                            "..institutionalpersonhouseholdregistry..",
                            "..governanceregistry..",
                            "..schoolregulationsregistry..");

    @ArchTest
    static final ArchRule sharedKernelDoesNotDependOnBusinessModules =
            noClasses()
                    .that()
                    .resideInAnyPackage("..shared..")
                    .should()
                    .dependOnClassesThat()
                    .resideInAnyPackage(
                            "..institutionalpersonhouseholdregistry..",
                            "..governanceregistry..",
                            "..schoolregulationsregistry..",
                            "..studentregistry..");
}
