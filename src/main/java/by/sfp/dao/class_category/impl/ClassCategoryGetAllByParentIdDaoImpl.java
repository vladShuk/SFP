package by.sfp.dao.class_category.impl;

import by.sfp.dao.class_category.ClassCategoryGetAllByParentIdDao;
import by.sfp.domain.ClassCategory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClassCategoryGetAllByParentIdDaoImpl implements ClassCategoryGetAllByParentIdDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<ClassCategory> execute(Long domainCategoryId) {
        return sessionFactory.getCurrentSession()
                .createQuery("select c from ClassCategory c where c.domainCategory.id=:id", ClassCategory.class)
                .setParameter("id", domainCategoryId)
                .getResultList();
    }
}
