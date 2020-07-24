package com.example.demo.model;
import com.example.demo.model.goods.Category;
import com.example.demo.model.goods.Company;
import com.example.demo.model.goods.Product;
import com.example.demo.model.users.Users;
import com.example.demo.utils.enums.ImageType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

// Media Type
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "image")
public class Image extends BaseEntity{

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "image_id", columnDefinition = "VARCHAR(255)", unique = true, nullable = false)
    private String imageId;

    @Column(name = "image_name")
    private String imageName;

    @Column(name = "image_type")
    private ImageType imageType;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    @JsonBackReference
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonBackReference
    private Category category;

    @Column(name = "is_default")
    private boolean isDefault;

    @Column(name = "quantityInStock")
    private long quantityInStock;

}
