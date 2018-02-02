package by.sfp.mapping.class_category.impl;

import by.sfp.domain.ClassCategory;
import by.sfp.mapping.class_category.ClassCategoryGetMapping;
import by.sfp.mapping.class_category.mapper.ClassCategoryGetMapper;
import org.springframework.stereotype.Service;

@Service
public class ClassCategoryGetMappingImpl implements ClassCategoryGetMapping {
    @Override
    public ClassCategoryGetMapper toObject(ClassCategory classCategory) {
        return ClassCategoryGetMapper.builder()
                .id(classCategory.getId())
                .name(classCategory.getName())
                .build();
    }
}
