package by.sfp.validation.domain_category;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DomainCategoryUniqueNameValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DomainCategoryUniqueNameConstraint {
    String message() default "Domain category name already exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
