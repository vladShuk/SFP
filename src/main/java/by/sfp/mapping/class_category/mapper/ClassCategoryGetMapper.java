package by.sfp.mapping.class_category.mapper;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClassCategoryGetMapper {
    private Long id;
    private String name;

    public static ClassCategoryGetMapperBuilder builder() {
        return new ClassCategoryGetMapperBuilder();
    }

    public static class ClassCategoryGetMapperBuilder {
        private ClassCategoryGetMapper mapper = new ClassCategoryGetMapper();

        public ClassCategoryGetMapperBuilder id(Long id) {
            mapper.id = id;
            return this;
        }

        public ClassCategoryGetMapperBuilder name(String name) {
            mapper.name = name;
            return this;
        }

        public ClassCategoryGetMapper build() {
            return mapper;
        }
    }
}
