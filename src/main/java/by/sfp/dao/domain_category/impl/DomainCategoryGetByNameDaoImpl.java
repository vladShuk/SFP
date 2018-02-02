package by.sfp.dao.domain_category.impl;

import by.sfp.dao.domain_category.DomainCategoryGetByNameDao;
import by.sfp.domain.DomainCategory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DomainCategoryGetByNameDaoImpl implements DomainCategoryGetByNameDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public DomainCategory execute(String name) {
        return sessionFactory.getCurrentSession()
                .createQuery("select d from DomainCategory d where d.name=:name", DomainCategory.class)
                .setParameter("name", name)
                .stream().findFirst().orElse(null);
    }
}
