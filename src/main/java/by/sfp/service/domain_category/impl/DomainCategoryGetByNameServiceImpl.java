package by.sfp.service.domain_category.impl;

import by.sfp.dao.domain_category.DomainCategoryGetByNameDao;
import by.sfp.domain.DomainCategory;
import by.sfp.service.domain_category.DomainCategoryGetByNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DomainCategoryGetByNameServiceImpl implements DomainCategoryGetByNameService {
    @Autowired
    private DomainCategoryGetByNameDao domainCategoryGetByNameDao;

    @Override
    public DomainCategory execute(String name) {
        return domainCategoryGetByNameDao.execute(name);
    }
}
