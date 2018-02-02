package by.sfp.dao.class_category.impl;

import by.sfp.dao.class_category.ClassCategoryGetAllByIdsDao;
import by.sfp.domain.ClassCategory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class ClassCategoryGetAllByIdsDaoImpl implements ClassCategoryGetAllByIdsDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Set<ClassCategory> execute(List<Long> ids) {
        List<ClassCategory> classCategoryList = sessionFactory.getCurrentSession()
                .createQuery("select c from ClassCategory c where c.id in :ids", ClassCategory.class)
                .setParameter("ids", ids)
                .getResultList();
        return new HashSet<>(classCategoryList);
    }
}
