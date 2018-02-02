package by.sfp.service.domain_category.impl;

import by.sfp.dao.domain_category.DomainCategoryGetAllDao;
import by.sfp.domain.DomainCategory;
import by.sfp.service.domain_category.DomainCategoryGetAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DomainCategoryGetAllServiceImpl implements DomainCategoryGetAllService {
    @Autowired
    private DomainCategoryGetAllDao domainCategoryGetAllDao;

    @Override
    public List<DomainCategory> execute() {
        return domainCategoryGetAllDao.execute();
    }
}
