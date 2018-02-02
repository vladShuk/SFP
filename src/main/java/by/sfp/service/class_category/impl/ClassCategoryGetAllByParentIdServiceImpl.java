package by.sfp.service.class_category.impl;

import by.sfp.dao.class_category.ClassCategoryGetAllByParentIdDao;
import by.sfp.domain.ClassCategory;
import by.sfp.service.class_category.ClassCategoryGetAllByParentIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClassCategoryGetAllByParentIdServiceImpl implements ClassCategoryGetAllByParentIdService {
    @Autowired
    private ClassCategoryGetAllByParentIdDao classCategoryGetAllByParentIdDao;

    @Override
    public List<ClassCategory> execute(Long domainCategoryId) {
        return classCategoryGetAllByParentIdDao.execute(domainCategoryId);
    }
}
