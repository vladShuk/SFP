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

@RunnableTestConfiguration
@RunWith(SpringRunner.class)
public class ClassCategoryGetAllServiceTest {
    @Autowired
    private DomainCategorySaveService domainCategorySave;

    @Autowired
    private ClassCategoryGetAllByParentIdService classCategoryGetAllByParentId;

    @Test
    public void getClassCategories() {
        DomainCategory entertainment = DomainCategory.builder()
                .name("Развлечения")
                .addClassCategory(ClassCategory.builder().name("Кино").build())
                .addClassCategory(ClassCategory.builder().name("Рестораны").build())
                .addClassCategory(ClassCategory.builder().name("Музеи").build())
                .build();
        domainCategorySave.execute(entertainment);

        List<ClassCategory> classCategories = classCategoryGetAllByParentId.execute(entertainment.getId());
        assertNotNull(classCategories);
        assertEquals(entertainment.getClassCategories().size(), classCategories.size());
    }
}
