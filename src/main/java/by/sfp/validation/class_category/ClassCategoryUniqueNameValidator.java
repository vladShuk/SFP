package by.sfp.validation.class_category;

import by.sfp.service.class_category.ClassCategoryGetByNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class ClassCategoryUniqueNameValidator implements ConstraintValidator<ClassCategoryUniqueNameConstraint, String> {
    @Autowired
    private ClassCategoryGetByNameService classCategoryGetByName;

    @Override
    public boolean isValid(String classCategoryName, ConstraintValidatorContext context) {
        return classCategoryGetByName.execute(classCategoryName) == null;
    }
}
