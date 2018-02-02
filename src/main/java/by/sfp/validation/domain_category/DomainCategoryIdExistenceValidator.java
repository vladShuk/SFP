package by.sfp.validation.domain_category;

import by.sfp.service.domain_category.DomainCategoryGetByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class DomainCategoryIdExistenceValidator implements ConstraintValidator<DomainCategoryIdExistenceConstraint, Long> {
    @Autowired
    private DomainCategoryGetByIdService domainCategoryGetById;

    @Override
    public boolean isValid(Long domainCategoryId, ConstraintValidatorContext context) {
        return domainCategoryGetById.execute(domainCategoryId) != null;
    }
}
