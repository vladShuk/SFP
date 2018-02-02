package by.sfp.dao.class_category.impl;

import by.sfp.dao.class_category.ClassCategoryGetByNameDao;
import by.sfp.domain.ClassCategory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClassCategoryGetByNameDaoImpl implements ClassCategoryGetByNameDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public ClassCategory execute(String name) {
        return sessionFactory.getCurrentSession()
                .createQuery("select d from ClassCategory d where d.name=:name", ClassCategory.class)
                .setParameter("name", name)
                .stream().findFirst().orElse(null);
    }
}
