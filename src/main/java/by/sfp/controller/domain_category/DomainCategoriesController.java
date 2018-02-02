package by.sfp.controller.domain_category;

import by.sfp.domain.ClassCategory;
import by.sfp.domain.DomainCategory;
import by.sfp.mapping.class_category.ClassCategorySaveMapping;
import by.sfp.mapping.class_category.mapper.ClassCategorySaveMapper;
import by.sfp.mapping.domain_category.DomainCategoryGetMappingList;
import by.sfp.mapping.domain_category.DomainCategorySaveMapping;
import by.sfp.mapping.domain_category.mapper.DomainCategorySaveMapper;
import by.sfp.service.class_category.ClassCategoryGetAllByParentIdService;
import by.sfp.service.class_category.ClassCategorySaveService;
import by.sfp.service.domain_category.DomainCategoryGetAllService;
import by.sfp.service.domain_category.DomainCategorySaveService;

import by.sfp.validation.domain_category.DomainCategoryIdExistenceConstraint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/domainCategories", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Validated
public class DomainCategoriesController {
    @Autowired
    private ClassCategorySaveService classCategorySave;

    @Autowired
    private DomainCategoryGetAllService domainCategoryGetAll;

    @Autowired
    private DomainCategorySaveService domainCategorySave;

    @Autowired
    private ClassCategorySaveMapping classCategorySaveMapping;

    @Autowired
    private DomainCategoryGetMappingList domainCategoryGetMappingList;

    @Autowired
    private DomainCategorySaveMapping domainCategorySaveMapping;

    @Autowired
    private ClassCategoryGetAllByParentIdService classCategoryGetAllByParentId;

    @GetMapping(consumes = MediaType.ALL_VALUE)
    public ResponseEntity getDomainCategories() {
        List<DomainCategory> domainCategories = domainCategoryGetAll.execute();
        return new ResponseEntity<>(domainCategoryGetMappingList.toObject(domainCategories), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveDomainCategoryWithClassCategory(@Valid @RequestBody DomainCategorySaveMapper domainCategorySaveMapper) {
        DomainCategory domainCategory = domainCategorySaveMapping.toObject(domainCategorySaveMapper);
        domainCategorySave.execute(domainCategory);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/{id}/classCategories")
    public ResponseEntity saveClassCategory(@DomainCategoryIdExistenceConstraint @PathVariable("id") Long domainCategoryId, @Valid @RequestBody ClassCategorySaveMapper classCategorySaveMapper) {
        classCategorySaveMapper = ClassCategorySaveMapper.builder(classCategorySaveMapper)
                .domainCategoryId(domainCategoryId)
                .build();
        ClassCategory classCategory = classCategorySaveMapping.toObject(classCategorySaveMapper);
        classCategorySave.execute(classCategory);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}/classCategories", consumes = MediaType.ALL_VALUE)
    public ResponseEntity getAllClassCategoriesByParentId(@PathVariable("id") Long domainCategoryId) {
        return new ResponseEntity(classCategoryGetAllByParentId.execute(domainCategoryId), HttpStatus.OK);
    }
}
