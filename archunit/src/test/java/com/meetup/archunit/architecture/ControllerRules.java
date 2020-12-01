package com.meetup.archunit.architecture;

import static com.meetup.archunit.architecture.ReasonsEnum.CONTROLLER_API_OPERATION;
import static com.meetup.archunit.architecture.ReasonsEnum.CONTROLLER_JPA_OBJECTS;
import static com.meetup.archunit.architecture.ReasonsEnum.CONTROLLER_NAMING_CONVENTIONS;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

import org.junit.jupiter.api.Test;

public class ControllerRules extends BaseRule{

  @Test
  void classesNameTest(){
    classes()
        .that()
        .resideInAnyPackage(CONTROLLER_PACKAGE)
        .should()
        .haveSimpleNameEndingWith("Controller")
        .because(CONTROLLER_NAMING_CONVENTIONS.getValue())
        .check(getPackages());
  }

  @Test
  void methodsMustBeAnnotateWithApiOperation() {
    //noMethods()
    methods()
        .that()
        .areDeclaredInClassesThat()
        .resideInAPackage(CONTROLLER_PACKAGE)
        .should()
        .beAnnotatedWith("io.swagger.annotations.ApiOperation")
        .because(CONTROLLER_API_OPERATION.getValue())
        .check(getPackages());
  }

  @Test
  void classesMustNotAccessRepositories(){
    layeredArchitecture()
        .layer("Controller").definedBy(CONTROLLER_PACKAGE)
        .layer("Service").definedBy(SERVICE_PACKAGE)
        .layer("Persistence").definedBy(REPOSITORY_PACKAGE)
        .whereLayer("Persistence").mayOnlyBeAccessedByLayers("Service")
    .check(getPackages());
  }

  @Test
  void classesMustNotUseJpaObjectReferences() {
    noClasses()
        .that()
        .resideInAPackage(CONTROLLER_PACKAGE)
        .should()
        .dependOnClassesThat()
        .resideInAPackage(ENTITY_PACKAGE)
        .because(CONTROLLER_JPA_OBJECTS.getValue())
        .check(getPackages());
  }

}
