package by.sfp.dao.domain_category.impl;

import by.sfp.dao.domain_category.DomainCategoryGetByIdDao;
import by.sfp.domain.DomainCategory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DomainCategoryGetByIdDaoImpl implements DomainCategoryGetByIdDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public DomainCategory execute(Long id) {
        return sessionFactory.getCurrentSession()
                .createQuery("select d from DomainCategory d where d.id=:id", DomainCategory.class)
                .setParameter("id", id)
                .stream().findFirst().orElse(null);
    }
}
