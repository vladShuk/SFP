package by.sfp.dao.domain_category.impl;

import by.sfp.dao.domain_category.DomainCategoryGetAllDao;
import by.sfp.domain.DomainCategory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DomainCategoryGetAllDaoImpl implements DomainCategoryGetAllDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<DomainCategory> execute() {
        return sessionFactory.getCurrentSession()
                .createQuery("select d from DomainCategory d", DomainCategory.class)
                .getResultList();
    }
}
