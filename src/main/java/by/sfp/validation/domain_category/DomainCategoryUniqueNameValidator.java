package by.sfp.validation.domain_category;

import by.sfp.service.domain_category.DomainCategoryGetByNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class DomainCategoryUniqueNameValidator implements ConstraintValidator<DomainCategoryUniqueNameConstraint, String> {
    @Autowired
    private DomainCategoryGetByNameService domainCategoryGetByName;

    @Override
    public boolean isValid(String domainCategoryName, ConstraintValidatorContext context) {
        return domainCategoryGetByName.execute(domainCategoryName) == null;
    }
}
