package by.sfp.dao.domain_category.impl;

import by.sfp.dao.domain_category.DomainCategorySaveDao;
import by.sfp.domain.DomainCategory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DomainCategorySaveDaoImpl implements DomainCategorySaveDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void execute(DomainCategory domainCategory) {
        sessionFactory.getCurrentSession().saveOrUpdate(domainCategory);
    }
}
