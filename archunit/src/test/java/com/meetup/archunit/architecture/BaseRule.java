package com.meetup.archunit.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.BeforeAll;

public abstract class BaseRule {

  private static final String BASE_PROJECT = "com.meetup.archunit";
  private static final ImportOption IMPORT_IGNORE_TESTS = location -> !location.contains("/test/");
  private static JavaClasses packages;

  protected static final String ENTITY_PACKAGE = "..entity";
  protected static final String REPOSITORY_PACKAGE = "..repository..";
  protected static final String REQUEST_PACKAGE = "..domain.request..";
  protected static final String RESPONSE_PACKAGE = "..domain.response..";
  protected static final String DTO_PACKAGE = "..domain.dto..";
  protected static final String CONTROLLER_PACKAGE = "..controller..";
  protected static final String SERVICE_PACKAGE = "..service..";

  protected JavaClasses getPackages(){
    return packages;
  }

  @BeforeAll
  public static void init(){
    packages = new ClassFileImporter()
        .withImportOption(IMPORT_IGNORE_TESTS)
        .importPackages(BASE_PROJECT);
  }

}
