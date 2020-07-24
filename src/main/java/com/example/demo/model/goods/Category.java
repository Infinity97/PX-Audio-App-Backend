package com.example.demo.model.goods;
import com.example.demo.model.BaseEntity;
import com.example.demo.model.Image;
import com.example.demo.model.goods.category_attributes.CategoryAttributes;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "category")
@Data
public class Category extends BaseEntity {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "category_id", columnDefinition = "VARCHAR(255)", unique = true, nullable = false)
    private String categoryId;

    @Column(name = "category_name")
    private String categoryName;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Image> categoryImages = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_category")
    @JsonBackReference
    private Category parentCategory;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Product> products = new HashSet<>();

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    @Basic(fetch = FetchType.LAZY)
    private Set<CategoryAttributes> categoryAttributes = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "category_company",
            joinColumns = {@JoinColumn(name = "fk_category")},
            inverseJoinColumns = {@JoinColumn(name = "fk_company")})
    Set<Company> companies = new HashSet<>();

}
