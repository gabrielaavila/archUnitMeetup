package com.meetup.archunit.architecture;

import static com.meetup.archunit.architecture.ReasonsEnum.ENTITY_ANNOTATION;
import static com.meetup.archunit.architecture.ReasonsEnum.ENTITY_BASE_CLASS;
import static com.meetup.archunit.architecture.ReasonsEnum.ENTITY_DEFAULT_PACKAGE;
import static com.meetup.archunit.architecture.ReasonsEnum.ENTITY_FETCH_TYPE;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.fields;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaAnnotation;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class EntityRules extends BaseRule{

  private static final DescribedPredicate<JavaAnnotation> FETCH_EAGER_ANNOTATION =
      new DescribedPredicate<JavaAnnotation>("FetchType.EAGER")  {
        @Override
        public boolean apply(JavaAnnotation input) {
          List<String> annotationTypes = Arrays
              .asList("ManyToOne", "ManyToMany", "OneToMany", "OneToOne");
          return annotationTypes.contains(input.getRawType().getSimpleName()) &&
              input.get("fetch").isPresent() &&
              input.get("fetch").get().toString().equalsIgnoreCase("FetchType.EAGER");
        }
      };

  @Test
  void classesShouldResideInPackage() {
    classes()
        .that()
        .areAnnotatedWith("javax.persistence.Entity")
        .should()
        .resideInAPackage(ENTITY_PACKAGE)
        .because(ENTITY_DEFAULT_PACKAGE.getValue())
        .check(getPackages());
  }

  @Test
  void classesMustHaveEntityAnnotation() {
    classes()
        .that()
        .resideInAPackage(ENTITY_PACKAGE)
        .should()
        .beAnnotatedWith("javax.persistence.Entity")
        .because(ENTITY_ANNOTATION.getValue())
        .check(getPackages());
  }

  @Test
  void classesMustExtendBaseEntityObject() {
    classes()
        .that()
        .resideInAPackage(ENTITY_PACKAGE)
        .should()
        .beAssignableTo("com.meetup.archunit.entity.BaseEntity")
        .because(ENTITY_BASE_CLASS.getValue())
        .check(getPackages());
  }

  @Test
  void propertyFetchTypeMustNotBeEager() {
    fields()
        .that()
        .areAnnotatedWith("javax.persistence.OneToMany")
        .or()
        .areAnnotatedWith("javax.persistence.OneToOne")
        .or()
        .areAnnotatedWith("javax.persistence.ManyToMany")
        .or()
        .areAnnotatedWith("javax.persistence.ManyToOne")
        .should()
        .notBeAnnotatedWith(FETCH_EAGER_ANNOTATION)
        .because(ENTITY_FETCH_TYPE.getValue())
        .check(getPackages());
  }

}
