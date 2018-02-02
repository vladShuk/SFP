package by.sfp.service.domain_category;

import by.sfp.config.RunnableTestConfiguration;
import by.sfp.domain.DomainCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunnableTestConfiguration
@RunWith(SpringRunner.class)
public class DomainCategoryGetByIdServiceTest {
    @Autowired
    private DomainCategorySaveService domainCategorySave;

    @Autowired
    private DomainCategoryGetByIdService domainCategoryGetById;

    @Test
    public void getDomainCategoryById() {
        DomainCategory tourism = DomainCategory.builder().name("Туризм").build();
        domainCategorySave.execute(tourism);

        DomainCategory savedDomainCategory = domainCategoryGetById.execute(tourism.getId());
        assertNotNull(savedDomainCategory);
        assertEquals(tourism, savedDomainCategory);
    }
}
