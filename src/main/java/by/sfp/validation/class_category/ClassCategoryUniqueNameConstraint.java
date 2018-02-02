package by.sfp.validation.class_category;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ClassCategoryUniqueNameValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ClassCategoryUniqueNameConstraint {
    String message() default "Class category name already exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
