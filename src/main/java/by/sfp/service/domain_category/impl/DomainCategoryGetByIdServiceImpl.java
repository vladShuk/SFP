package by.sfp.service.domain_category.impl;

import by.sfp.dao.domain_category.DomainCategoryGetByIdDao;
import by.sfp.domain.DomainCategory;
import by.sfp.service.domain_category.DomainCategoryGetByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DomainCategoryGetByIdServiceImpl implements DomainCategoryGetByIdService {
    @Autowired
    private DomainCategoryGetByIdDao domainCategoryGetByIdDao;

    @Override
    public DomainCategory execute(Long id) {
        return domainCategoryGetByIdDao.execute(id);
    }
}
