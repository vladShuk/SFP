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
public class DomainCategorySaveServiceTest {
    @Autowired
    private DomainCategoryGetAllService domainCategoryGetAll;

    @Autowired
    private DomainCategorySaveService domainCategorySave;

    @Test
    public void saveDomainCategory(){
        DomainCategory entertainment = DomainCategory.builder().name("Развлечения").build();
        domainCategorySave.execute(entertainment);

        List<DomainCategory> categories = domainCategoryGetAll.execute();
        assertNotNull(categories);
        assertEquals(1, categories.size());
        assertTrue(categories.contains(entertainment));
    }
}
