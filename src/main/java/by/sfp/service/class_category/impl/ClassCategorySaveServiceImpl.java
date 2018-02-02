package by.sfp.service.class_category.impl;

import by.sfp.dao.class_category.ClassCategorySaveDao;
import by.sfp.domain.ClassCategory;
import by.sfp.service.class_category.ClassCategorySaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClassCategorySaveServiceImpl implements ClassCategorySaveService {
    @Autowired
    private ClassCategorySaveDao classCategorySaveDao;

    @Override
    public void execute(ClassCategory classCategory) {
        classCategorySaveDao.execute(classCategory);
    }
}
