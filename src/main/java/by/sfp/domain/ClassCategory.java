package by.sfp.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CLASS_CATEGORY")
@Data
@EqualsAndHashCode(exclude = {"domainCategory", "serviceProviders"})
@ToString(exclude = {"domainCategory", "serviceProviders"})
@NoArgsConstructor
public class ClassCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLASS_CATEGORY_ID_SEQ")
    @SequenceGenerator(name = "CLASS_CATEGORY_ID_SEQ", sequenceName = "CLASS_CATEGORY_ID_SEQ", allocationSize = 0)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "DOMAIN_ID")
    @JsonManagedReference
    private DomainCategory domainCategory;

    @ManyToMany
    @Cascade(CascadeType.SAVE_UPDATE)
    @JoinTable(name = "SERVICE_PROVIDER_CLASS_CATEGORY",
            joinColumns = {@JoinColumn(name = "CLASS_ID", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "PROVIDER_ID", nullable = false)})
    @JsonBackReference
    private Set<ServiceProvider> serviceProviders = new HashSet<>();

    public static ClassCategoryBuilder builder(){
        return new ClassCategoryBuilder();
    }

    public static class ClassCategoryBuilder {
        private ClassCategory classCategory = new ClassCategory();

        public ClassCategoryBuilder name(String name){
            classCategory.name = name;
            return this;
        }

        public ClassCategoryBuilder domainCategory(DomainCategory domainCategory){
            classCategory.domainCategory = domainCategory;
            return this;
        }

        public ClassCategoryBuilder addServiceProvider(ServiceProvider serviceProvider){
            serviceProvider.getClassCategories().add(classCategory);
            classCategory.serviceProviders.add(serviceProvider);
            return this;
        }

        public ClassCategory build(){
            return classCategory;
        }
    }
}
