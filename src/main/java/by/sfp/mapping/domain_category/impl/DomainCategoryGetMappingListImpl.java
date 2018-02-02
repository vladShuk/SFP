package by.sfp.mapping.domain_category.impl;

import by.sfp.domain.DomainCategory;
import by.sfp.mapping.domain_category.DomainCategoryGetMapping;
import by.sfp.mapping.domain_category.DomainCategoryGetMappingList;
import by.sfp.mapping.domain_category.mapper.DomainCategoryGetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DomainCategoryGetMappingListImpl implements DomainCategoryGetMappingList {
    @Autowired
    private DomainCategoryGetMapping domainCategoryGetMapping;

    @Override
    public List<DomainCategoryGetMapper> toObject(List<DomainCategory> domainCategories) {
        return domainCategories.stream()
                .map(domainCategory -> domainCategoryGetMapping.toObject(domainCategory))
                .collect(Collectors.toList());
    }
}
