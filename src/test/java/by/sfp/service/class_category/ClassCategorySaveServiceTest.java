package by.sfp.service.class_category;

import by.sfp.config.RunnableTestConfiguration;
import by.sfp.domain.ClassCategory;
import by.sfp.domain.DomainCategory;
import by.sfp.service.domain_category.DomainCategorySaveService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunnableTestConfiguration
@RunWith(SpringRunner.class)
public class ClassCategorySaveServiceTest {
    @Autowired
    private ClassCategorySaveService classCategorySave;

    @Autowired
    private ClassCategoryGetAllByParentIdService classCategoryGetAllByParentId;

    @Autowired
    private DomainCategorySaveService domainCategorySave;

    @Test
    public void saveDomainCategory(){
        DomainCategory food = DomainCategory.builder().name("Еда").build();
        domainCategorySave.execute(food);
        ClassCategory cafe = ClassCategory.builder().name("Кафе").domainCategory(food).build();
        classCategorySave.execute(cafe);

        List<ClassCategory> categories = classCategoryGetAllByParentId.execute(food.getId());
        assertNotNull(categories);
        assertEquals(1, categories.size());
        assertTrue(categories.contains(cafe));
    }
}
