package by.sfp.service.class_category;


import by.sfp.config.RunnableTestConfiguration;
import by.sfp.domain.ClassCategory;
import by.sfp.domain.DomainCategory;
import by.sfp.service.domain_category.DomainCategorySaveService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunnableTestConfiguration
@RunWith(SpringRunner.class)
public class ClassCategoryGetAllByIdsServiceTest {
    @Autowired
    private ClassCategoryGetAllByIdsService classCategoryGetAllByIds;

    @Autowired
    private DomainCategorySaveService domainCategorySave;

    @Test
    public void getClassCategoriesByIds() {
        ClassCategory karaoke = ClassCategory.builder().name("Караоке").build();
        DomainCategory entertainment = DomainCategory.builder()
                .name("Развлечения")
                .addClassCategory(karaoke)
                .build();
        domainCategorySave.execute(entertainment);
        ClassCategory football = ClassCategory.builder().name("Футбол").build();
        DomainCategory sport = DomainCategory.builder()
                .name("Спорт")
                .addClassCategory(football)
                .build();
        domainCategorySave.execute(sport);

        List<Long> ids = Arrays.asList(karaoke.getId(), football.getId());

        Set<ClassCategory> classCategories = classCategoryGetAllByIds.execute(ids);
        assertNotNull(classCategories);
        assertEquals(2, classCategories.size());
        assertTrue(classCategories.contains(karaoke) && classCategories.contains(football));
    }
}
