package com.example.demo.model.goods;

import com.example.demo.model.BaseEntity;
import com.example.demo.model.Image;
import com.example.demo.model.ProductSeller;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "product")
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "product_id", columnDefinition = "VARCHAR(255)", unique = true, nullable = false)
    private String productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "list_price")
    private double listPrice;

    @Column(name = "sales_price")
    private double salesPrice;

    @Column(name = "detailed_description")
    private String detailedDescription;

    @Column(name = "single_line_description")
    private String singleLineDescription;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company_id", nullable = false)
    @JsonBackReference
    private Company company;

    // This should be many to many relationship
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    @JsonBackReference
    private Category category;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Image> image;

    @Column(name = "rating")
    private double rating;

    @Column(name = "number_of_users_rated")
    private double numberOfUsersRated;

    /**
     * Enum of type ProductStatus
     */
    @Column(name = "product_status")
    private String productStatus;

    @Column(name = "quantity")
    private long quantity;

    /**
     * Enum of Unit Type
     */
    @Column(name = "unitType")
    private String unitType;

    @OneToMany(mappedBy = "product")
    Set<ProductSeller> productSellers = new HashSet<>();

}
