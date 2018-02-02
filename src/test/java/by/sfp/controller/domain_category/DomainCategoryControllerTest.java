package by.sfp.controller.domain_category;

import by.sfp.config.RunnableWebTestConfiguration;
import by.sfp.controller.context.ControllerTestContext;
import by.sfp.domain.ClassCategory;
import by.sfp.domain.DomainCategory;
import by.sfp.mapping.class_category.mapper.ClassCategorySaveMapper;
import by.sfp.mapping.domain_category.mapper.DomainCategorySaveMapper;
import by.sfp.service.domain_category.DomainCategorySaveService;
import by.sfp.util.JsonConverter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@RunnableWebTestConfiguration
public class DomainCategoryControllerTest extends ControllerTestContext {
    @Autowired
    private DomainCategorySaveService domainCategorySave;

    @Autowired
    private JsonConverter converter;

    @Test
    public void getDomainCategories() throws Exception {
        DomainCategory domainCategory = DomainCategory.builder()
                .name("Туризм")
                .build();
        domainCategorySave.execute(domainCategory);

        getMockMvc().perform(get("/domainCategories")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andDo(getDocumentHandler()
                        .document(
                                responseFields(
                                        fieldWithPath("[].id").description(""),
                                        fieldWithPath("[].name").description("")
                )));
    }

    @Test
    public void saveDomainCategoryWithClassCategory() throws Exception {
        ClassCategorySaveMapper classCategoryMapper = ClassCategorySaveMapper.builder()
                .name("Турция")
                .build();

        DomainCategorySaveMapper domainCategoryMapper = DomainCategorySaveMapper.builder()
                .name("Кино")
                .categories(classCategoryMapper)
                .build();

        getMockMvc().perform(post("/domainCategories")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(converter.toJson(domainCategoryMapper)))
                .andExpect(status().isCreated())
                .andDo(getDocumentHandler()
                        .document(
                                requestFields(
                                        fieldWithPath("name").description(""),
                                        fieldWithPath("categories.name").description("")
                )));
    }

    @Test
    public void saveDomainCategoryWithClassCategoryWithEmptyNames() throws Exception {
        ClassCategorySaveMapper classCategoryMapper = ClassCategorySaveMapper.builder()
                .name("")
                .build();

        DomainCategorySaveMapper domainCategoryMapper = DomainCategorySaveMapper.builder()
                .name("")
                .categories(classCategoryMapper)
                .build();

        getMockMvc().perform(post("/domainCategories")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(converter.toJson(domainCategoryMapper)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fields.name",
                        containsInAnyOrder("\'Name\' for domain category must not be empty")))
                .andExpect(jsonPath("$.fields.['categories.name']",
                        containsInAnyOrder("\'Name\' for class category must not be empty")
                ));
    }

    @Test
    public void saveDomainCategoryWithClassCategoryWithIncorrectNames() throws Exception {
        ClassCategorySaveMapper classCategoryMapper = ClassCategorySaveMapper.builder()
                .name("ТурцияТурцияТурцияТурцияТурцияТурция")
                .build();

        DomainCategorySaveMapper domainCategoryMapper = DomainCategorySaveMapper.builder()
                .name("ТуризмТуризмТуризмТуризмТуризмТуризм")
                .categories(classCategoryMapper)
                .build();

        getMockMvc().perform(post("/domainCategories")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(converter.toJson(domainCategoryMapper)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fields.name",
                        containsInAnyOrder("\'Name\' for domain category must has length less or equal 30")))
                .andExpect(jsonPath("$.fields.['categories.name']",
                        containsInAnyOrder("\'Name\' for class category must has length less or equal 30")
                ));
    }

    @Test
    public void saveDomainCategoryWithClassCategoryWithExistingNames() throws Exception {
        ClassCategory classCategory = ClassCategory.builder()
                .name("Бассейн")
                .build();

        DomainCategory domainCategory = DomainCategory.builder()
                .name("Спорт")
                .addClassCategory(classCategory)
                .build();
        domainCategorySave.execute(domainCategory);

        ClassCategorySaveMapper classCategoryMapperWithExistingName = ClassCategorySaveMapper.builder()
                .name("Бассейн")
                .build();

        DomainCategorySaveMapper domainCategoryMapperWithExistingName = DomainCategorySaveMapper.builder()
                .name("Спорт")
                .categories(classCategoryMapperWithExistingName)
                .build();

        getMockMvc().perform(post("/domainCategories")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(converter.toJson(domainCategoryMapperWithExistingName)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fields.name",
                        containsInAnyOrder("Domain category name already exists")))
                .andExpect(jsonPath("$.fields.['categories.name']",
                        containsInAnyOrder("Class category name already exists")
                ));
    }

    @Test
    public void saveClassCategory() throws Exception {
        DomainCategory domainCategory = DomainCategory.builder()
                .name("Туризм")
                .build();
        domainCategorySave.execute(domainCategory);

        Long domainCategoryId = domainCategory.getId();

        ClassCategorySaveMapper classCategoryMapper = ClassCategorySaveMapper.builder()
                .name("Турция")
                .build();

        getMockMvc().perform(post("/domainCategories/{id}/classCategories", domainCategoryId)
                .accept(MediaType.APPLICATION_JSON_UTF8).contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(converter.toJson(classCategoryMapper)))
                .andExpect(status().isCreated())
                .andDo(getDocumentHandler()
                        .document(
                                pathParameters(
                                        parameterWithName("id").description("Domain category id")
                                ),
                                requestFields(
                                        fieldWithPath("name").description("")
                )));
    }

    @Test
    public void saveClassCategoryWithEmptyName() throws Exception {
        DomainCategory domainCategory = DomainCategory.builder()
                .name("Туризм")
                .build();
        domainCategorySave.execute(domainCategory);

        Long domainCategoryId = domainCategory.getId();

        ClassCategorySaveMapper classCategoryMapper = ClassCategorySaveMapper.builder()
                .name("")
                .build();

        getMockMvc().perform(post("/domainCategories/{id}/classCategories", domainCategoryId)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(converter.toJson(classCategoryMapper)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fields.name",
                        containsInAnyOrder("\'Name\' for class category must not be empty")
                ));
    }

    @Test
    public void saveClassCategoryWithIncorrectName() throws Exception {
        DomainCategory domainCategory = DomainCategory.builder()
                .name("Туризм")
                .build();
        domainCategorySave.execute(domainCategory);

        Long domainCategoryId = domainCategory.getId();

        ClassCategorySaveMapper classCategoryMapper = ClassCategorySaveMapper.builder()
                .name("ТурцияТурцияТурцияТурцияТурцияТурция")
                .build();

        getMockMvc().perform(post("/domainCategories/{id}/classCategories", domainCategoryId)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(converter.toJson(classCategoryMapper)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fields.name",
                        containsInAnyOrder("\'Name\' for class category must has length less or equal 30")
                ));
    }

    @Test
    public void saveClassCategoryWithExistingName() throws Exception {
        ClassCategory classCategory = ClassCategory.builder()
                .name("Тайланд")
                .build();

        DomainCategory domainCategory = DomainCategory.builder()
                .name("Туризм")
                .addClassCategory(classCategory)
                .build();
        domainCategorySave.execute(domainCategory);

        Long domainCategoryId = domainCategory.getId();

        ClassCategorySaveMapper classCategoryMapper = ClassCategorySaveMapper.builder()
                .name("Тайланд")
                .build();

        getMockMvc().perform(post("/domainCategories/{id}/classCategories", domainCategoryId)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(converter.toJson(classCategoryMapper)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fields.name",
                        containsInAnyOrder("Class category name already exists")
                ));
    }

    @Test
    public void saveClassCategoryWithIncorrectDomainCategoryId() throws Exception {
        ClassCategorySaveMapper classCategoryMapper = ClassCategorySaveMapper.builder()
                .name("Мягкие игрушки")
                .build();

        Long incorrectDomainCategoryId = -1L;

        getMockMvc().perform(post("/domainCategories/{incorrectDomainCategoryId}/classCategories", incorrectDomainCategoryId)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(converter.toJson(classCategoryMapper)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.common",
                        containsInAnyOrder("Domain category's id doesn't exist")
                ));
    }

    @Test
    public void saveClassCategoryWithAlphabeticDomainCategoryId() throws Exception {
        ClassCategorySaveMapper classCategoryMapper = ClassCategorySaveMapper.builder()
                .name("Мягкие игрушки")
                .build();

        String stringDomainCategoryId = "a";

        getMockMvc().perform(post("/domainCategories/{stringDomainCategoryId}/classCategories", stringDomainCategoryId)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(converter.toJson(classCategoryMapper)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.common",
                        containsInAnyOrder("Incorrect input value \'" + stringDomainCategoryId + "\' for parameter \'id\'")
                ));
    }

    @Test
    public void getAllClassCategoriesByParentId() throws Exception {
        DomainCategory domainCategory = DomainCategory.builder()
                .name("Entertainment")
                .addClassCategory(ClassCategory.builder().name("Clubs").build())
                .addClassCategory(ClassCategory.builder().name("Pubs").build())
                .addClassCategory(ClassCategory.builder().name("Cinema").build())
                .build();
        domainCategorySave.execute(domainCategory);

        getMockMvc().perform(get("/domainCategories/{id}/classCategories", domainCategory.getId())
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andDo(getDocumentHandler().document(
                        pathParameters(
                                parameterWithName("id").description("Domain categories id")
                        ),
                        responseFields(
                                fieldWithPath("[].id").description(""),
                                fieldWithPath("[].name").description(""),
                                fieldWithPath("[]domainCategory.id").description(""),
                                fieldWithPath("[]domainCategory.name").description("")
                        )
                ));

    }
}
