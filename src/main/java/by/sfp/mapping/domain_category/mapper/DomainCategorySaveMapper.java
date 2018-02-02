package by.sfp.mapping.domain_category.mapper;

import by.sfp.mapping.class_category.mapper.ClassCategorySaveMapper;
import by.sfp.validation.domain_category.DomainCategoryUniqueNameConstraint;
import lombok.Getter;
import lombok.Setter;

import javax.validation.GroupSequence;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@GroupSequence({DomainCategorySaveMapper.class, DomainCategorySaveMapper.SizeGroup.class, DomainCategorySaveMapper.DomainCategoryUniqueNameGroup.class})
public class DomainCategorySaveMapper {
    @NotEmpty(message = "\'Name\' for domain category must not be empty")
    @Size(groups = SizeGroup.class, max = 30, message = "\'Name\' for domain category must has length less or equal 30")
    @DomainCategoryUniqueNameConstraint(groups = DomainCategoryUniqueNameGroup.class)
    private String name;

    @Valid
    private ClassCategorySaveMapper categories;

    public interface SizeGroup {}

    public interface DomainCategoryUniqueNameGroup {}

    public static DomainCategorySaveMapperBuilder builder() {
        return new DomainCategorySaveMapperBuilder();
    }

    public static class DomainCategorySaveMapperBuilder {
        private DomainCategorySaveMapper mapper = new DomainCategorySaveMapper();

        public DomainCategorySaveMapperBuilder name(String name) {
            mapper.name = name;
            return this;
        }

        public DomainCategorySaveMapperBuilder categories(ClassCategorySaveMapper classCategories) {
            mapper.categories = classCategories;
            return this;
        }

        public DomainCategorySaveMapper build() {
            return mapper;
        }
    }
}
