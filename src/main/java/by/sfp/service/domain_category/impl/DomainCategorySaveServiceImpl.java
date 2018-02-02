package by.sfp.service.domain_category.impl;

import by.sfp.dao.domain_category.DomainCategorySaveDao;
import by.sfp.domain.DomainCategory;
import by.sfp.service.domain_category.DomainCategorySaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DomainCategorySaveServiceImpl implements DomainCategorySaveService {
    @Autowired
    private DomainCategorySaveDao domainCategorySaveDao;

    @Override
    public void execute(DomainCategory domainCategory) {
        domainCategorySaveDao.execute(domainCategory);
    }
}
