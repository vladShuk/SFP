package by.sfp.mapping.class_category.impl;

import by.sfp.domain.ClassCategory;
import by.sfp.mapping.class_category.ClassCategoryGetMapping;
import by.sfp.mapping.class_category.ClassCategoryGetMappingList;
import by.sfp.mapping.class_category.mapper.ClassCategoryGetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassCategoryGetMappingListImpl implements ClassCategoryGetMappingList {
    @Autowired
    private ClassCategoryGetMapping classCategoryGetMapping;

    @Override
    public List<ClassCategoryGetMapper> toObject(List<ClassCategory> classCategories) {
        return classCategories.stream()
                .map(classCategory -> classCategoryGetMapping.toObject(classCategory))
                .collect(Collectors.toList());
    }
}
