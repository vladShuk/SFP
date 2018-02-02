package by.sfp.mapping.class_category.impl;

import by.sfp.domain.ClassCategory;
import by.sfp.mapping.class_category.ClassCategorySaveMapping;
import by.sfp.mapping.class_category.mapper.ClassCategorySaveMapper;
import by.sfp.service.domain_category.DomainCategoryGetByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassCategorySaveMappingImpl implements ClassCategorySaveMapping {
    @Autowired
    private DomainCategoryGetByIdService domainCategoryGetById;
    
    @Override
    public ClassCategory toObject(ClassCategorySaveMapper classCategoryMapper) {
        if(classCategoryMapper.getDomainCategoryId() == null) {
            return ClassCategory.builder()
                    .name(classCategoryMapper.getName())
                    .build();
        } else {
            return ClassCategory.builder()
                    .name(classCategoryMapper.getName())
                    .domainCategory(domainCategoryGetById.execute(classCategoryMapper.getDomainCategoryId()))
                    .build();
        }
    }
}
