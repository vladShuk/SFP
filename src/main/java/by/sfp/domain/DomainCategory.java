package by.sfp.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "DOMAIN_CATEGORY")
@Data
@NoArgsConstructor
public class DomainCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DOMAIN_CATEGORY_ID_SEQ")
    @SequenceGenerator(name = "DOMAIN_CATEGORY_ID_SEQ", sequenceName = "DOMAIN_CATEGORY_ID_SEQ", allocationSize = 0)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "domainCategory", cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<ClassCategory> classCategories = new HashSet<>();

    public static DomainCategoryBuilder builder(){
        return new DomainCategoryBuilder();
    }

    public static class DomainCategoryBuilder {
        private DomainCategory domainCategory = new DomainCategory();

        public DomainCategoryBuilder name(String name){
            domainCategory.name = name;
            return this;
        }

        public DomainCategoryBuilder addClassCategory(ClassCategory classCategory){
            classCategory.setDomainCategory(domainCategory);
            domainCategory.classCategories.add(classCategory);
            return this;
        }

        public DomainCategory build(){
            return domainCategory;
        }
    }
}
