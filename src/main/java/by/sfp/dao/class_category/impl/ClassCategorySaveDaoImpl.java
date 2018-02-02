package by.sfp.dao.class_category.impl;

import by.sfp.dao.class_category.ClassCategorySaveDao;
import by.sfp.domain.ClassCategory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClassCategorySaveDaoImpl implements ClassCategorySaveDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void execute(ClassCategory classCategory) {
        sessionFactory.getCurrentSession().saveOrUpdate(classCategory);
    }
}
