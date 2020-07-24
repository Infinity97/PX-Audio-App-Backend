package com.example.demo.model.cart;

import com.example.demo.model.BaseEntity;
import com.example.demo.model.users.Users;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
/**
 * @author Infinity97
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "saved_or_favourite_products")
public class SavedOrFavouriteProduct extends BaseEntity{
	@Id
	String id;
	@MapsId
	@JoinColumn
	@OneToOne
	private Users users;

	//	@OneToMany(fetch = FetchType.LAZY)
	//	@JoinColumn(name = "product_id")
	//	Set<Product> savedForLaterProducts = new HashSet<>();

	//	@OneToMany(fetch = FetchType.LAZY)
	//	@JoinColumn(name = "favouriteProducts", referencedColumnName = "product_id")
	//	Set<Product> favouriteProducts;

	@Column(name = "name")
	private String name;
}
