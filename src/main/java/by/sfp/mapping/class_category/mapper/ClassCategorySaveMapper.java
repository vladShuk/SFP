package by.sfp.mapping.class_category.mapper;

import by.sfp.validation.class_category.ClassCategoryUniqueNameConstraint;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@GroupSequence({ClassCategorySaveMapper.class, ClassCategorySaveMapper.SizeGroup.class, ClassCategorySaveMapper.ClassCategoryUniqueNameGroup.class})
public class ClassCategorySaveMapper {
    @NotEmpty(message = "\'Name\' for class category must not be empty")
    @Size(groups = SizeGroup.class, max = 30, message = "\'Name\' for class category must has length less or equal 30")
    @ClassCategoryUniqueNameConstraint(groups = ClassCategoryUniqueNameGroup.class)
    private String name;

    @JsonIgnore
    private Long domainCategoryId;

    public interface SizeGroup {}

    public interface ClassCategoryUniqueNameGroup {}

    public static ClassCategorySaveMapperBuilder builder() {
        return new ClassCategorySaveMapperBuilder();
    }

    public static ClassCategorySaveMapperBuilder builder(ClassCategorySaveMapper mapper) {
        return new ClassCategorySaveMapperBuilder(mapper);
    }

    public static class ClassCategorySaveMapperBuilder {
        private ClassCategorySaveMapper mapper;

        public ClassCategorySaveMapperBuilder() {
            mapper = new ClassCategorySaveMapper();
        }

        public ClassCategorySaveMapperBuilder(ClassCategorySaveMapper mapper) {
            this.mapper = mapper;
        }

        public ClassCategorySaveMapperBuilder name(String name) {
            mapper.name = name;
            return this;
        }

        public ClassCategorySaveMapperBuilder domainCategoryId(Long domainCategoryId) {
            mapper.domainCategoryId = domainCategoryId;
            return this;
        }

        public ClassCategorySaveMapper build() {
            return mapper;
        }
    }
}
