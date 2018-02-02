package by.sfp.service.class_category.impl;

import by.sfp.dao.class_category.ClassCategoryGetByNameDao;
import by.sfp.domain.ClassCategory;
import by.sfp.service.class_category.ClassCategoryGetByNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClassCategoryGetByNameServiceImpl implements ClassCategoryGetByNameService {
    @Autowired
    private ClassCategoryGetByNameDao classCategoryGetByNameDao;

    @Override
    public ClassCategory execute(String name) {
        return classCategoryGetByNameDao.execute(name);
    }
}
