package by.sfp.service.class_category.impl;

import by.sfp.dao.class_category.ClassCategoryGetAllByIdsDao;
import by.sfp.domain.ClassCategory;
import by.sfp.service.class_category.ClassCategoryGetAllByIdsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class ClassCategoryGetAllByIdsServiceImpl implements ClassCategoryGetAllByIdsService {
    @Autowired
    private ClassCategoryGetAllByIdsDao classCategoryGetAllByIdsDao;

    @Override
    public Set<ClassCategory> execute(List<Long> ids) {
        return classCategoryGetAllByIdsDao.execute(ids);
    }
}
