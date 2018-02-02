package by.sfp.mapping.domain_category.mapper;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DomainCategoryGetMapper {
    private Long id;
    private String name;

    public static DomainCategoryGetMapperBuilder builder() {
        return new DomainCategoryGetMapperBuilder();
    }

    public static class DomainCategoryGetMapperBuilder {
        private DomainCategoryGetMapper mapper = new DomainCategoryGetMapper();

        public DomainCategoryGetMapperBuilder id(Long id) {
            mapper.id = id;
            return this;
        }

        public DomainCategoryGetMapperBuilder name(String name) {
            mapper.name = name;
            return this;
        }

        public DomainCategoryGetMapper build() {
            return mapper;
        }
    }
}
