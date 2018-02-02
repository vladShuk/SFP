package by.sfp.service.domain_category;

import by.sfp.config.RunnableTestConfiguration;
import by.sfp.domain.DomainCategory;
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
public class DomainCategoryGetAllServiceTest {
    @Autowired
    private DomainCategoryGetAllService domainCategoryGetAll;

    @Autowired
    private DomainCategorySaveService domainCategorySave;

    @Test
    public void getDomainCategories() {
        DomainCategory tourism = DomainCategory.builder().name("Туризм").build();
        domainCategorySave.execute(tourism);

        DomainCategory auto = DomainCategory.builder().name("Авто").build();
        domainCategorySave.execute(auto);

        List<DomainCategory> domainCategories = domainCategoryGetAll.execute();
        assertNotNull(domainCategories);
        assertEquals(2, domainCategories.size());
        assertTrue(domainCategories.contains(auto) && domainCategories.contains(tourism));
    }
}
