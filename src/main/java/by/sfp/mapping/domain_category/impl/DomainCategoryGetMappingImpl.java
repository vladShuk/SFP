package by.sfp.mapping.domain_category.impl;

import by.sfp.domain.DomainCategory;
import by.sfp.mapping.domain_category.DomainCategoryGetMapping;
import by.sfp.mapping.domain_category.mapper.DomainCategoryGetMapper;
import org.springframework.stereotype.Service;

@Service
public class DomainCategoryGetMappingImpl implements DomainCategoryGetMapping {
    @Override
    public DomainCategoryGetMapper toObject(DomainCategory domainCategory) {
        return DomainCategoryGetMapper.builder()
                .id(domainCategory.getId())
                .name(domainCategory.getName())
                .build();
    }
}
