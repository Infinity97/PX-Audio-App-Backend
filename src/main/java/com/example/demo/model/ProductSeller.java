package com.example.demo.model;

import com.example.demo.model.goods.Product;
import com.example.demo.model.users.Seller;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Infinity97
 */

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "product_seller")
@IdClass(ProductSeller.IdClass.class)
public class ProductSeller extends BaseEntity{

	@Id
	@ManyToOne
	@JoinColumn(name = "product")
	private Product product;

	@Id
	@ManyToOne
	@JoinColumn(name = "seller")
	private Seller seller;

	@Column(name = "stock")
	private double stock;

	@Column(name = "list_price")
	private double listPrice;

	@Column(name = "sale_price")
	private double salePrice;

	@Data
	static class IdClass implements Serializable {
		private Product product;
		private Seller seller;
	}
}
