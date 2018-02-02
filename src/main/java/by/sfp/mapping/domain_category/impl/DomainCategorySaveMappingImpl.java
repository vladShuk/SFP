package by.sfp.mapping.domain_category.impl;

import by.sfp.domain.ClassCategory;
import by.sfp.domain.DomainCategory;
import by.sfp.mapping.class_category.ClassCategorySaveMapping;
import by.sfp.mapping.domain_category.DomainCategorySaveMapping;
import by.sfp.mapping.domain_category.mapper.DomainCategorySaveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DomainCategorySaveMappingImpl implements DomainCategorySaveMapping {
    @Autowired
    private ClassCategorySaveMapping classCategorySaveMapping;

    @Override
    public DomainCategory toObject(DomainCategorySaveMapper domainCategoryMapper) {
        ClassCategory classCategory = classCategorySaveMapping.toObject(domainCategoryMapper.getCategories());
        DomainCategory domainCategory = DomainCategory.builder()
                .name(domainCategoryMapper.getName())
                .addClassCategory(classCategory)
                .build();
        return domainCategory;
    }
}
