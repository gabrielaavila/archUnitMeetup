package com.meetup.archunit.architecture;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;

import com.meetup.archunit.entity.BaseEntity;
import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import java.util.List;
import org.junit.jupiter.api.Test;

;

public class ServiceRules extends BaseRule{

  @Test
  void test(){
    methods()
        .that()
        .areDeclaredInClassesThat()
        .resideInAPackage(SERVICE_PACKAGE)
        .and()
        .arePublic()
        .should(notReturnMembersOf(BaseEntity.class))
        .because("RULE")
    .check(getPackages());
  }


  static ArchCondition<JavaMethod> notReturnMembersOf(Class<?> type) {
    return new ArchCondition<JavaMethod>("not return type " + type.getName()) {
      @Override
      public void check(JavaMethod method, ConditionEvents events) {
        boolean violation = isNotList(method) && typeMatches(method);
        String message = method.getFullName() + " returns " + method.getRawReturnType().getName() + " and is a type of " + type.getName();
        events.add(new SimpleConditionEvent(method, violation, message));
      }

      public boolean isNotList(JavaMethod method){
        return !method.getRawReturnType().getSimpleName().equals(List.class.getSimpleName());
      }

      public boolean typeMatches(JavaMethod method){
        return method.getRawReturnType().getSuperClass().isPresent() && !method.getRawReturnType()
            .getSuperClass().get().getSimpleName().equals(type.getSimpleName());
      }
    };
  }


}
