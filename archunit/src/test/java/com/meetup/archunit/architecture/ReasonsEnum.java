package com.meetup.archunit.architecture;

public enum ReasonsEnum {

  CONTROLLER_NAMING_CONVENTIONS("Naming Conventions."),
  CONTROLLER_API_OPERATION("Controller methods must be annotated with @ApiOperation."),
  CONTROLLER_JPA_OBJECTS("Controller classes cannot have JPA object references."),
  ENTITY_DEFAULT_PACKAGE("Entities must reside in entity package."),
  ENTITY_ANNOTATION("Entities must have the @Entity annotation."),
  ENTITY_BASE_CLASS("Entities must extend BaseEntity."),
  ENTITY_FETCH_TYPE("FetchType must always be Lazy.");

  private String value;

  ReasonsEnum(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
