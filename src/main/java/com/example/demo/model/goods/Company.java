package com.example.demo.model.goods;

import com.example.demo.model.CompanySeller;
import com.example.demo.model.Image;
import com.example.demo.model.users.Users;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "company_id", columnDefinition = "VARCHAR(255)", unique = true, nullable = false)
    private String companyId;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "company_details")
    private String companyDetails;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Image> companyImages = new HashSet<>();

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonManagedReference
    Set<Product> products = new HashSet<>();

    @ManyToMany(mappedBy = "companies")
    Set<Category> categories = new HashSet<>();

    @OneToOne
    @JoinColumn
    private Users admin;

    @OneToMany(mappedBy = "company")
    private Set<CompanySeller> dealers;
}
