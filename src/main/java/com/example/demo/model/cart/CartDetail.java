package com.example.demo.model.cart;

import com.example.demo.model.BaseEntity;
import com.example.demo.model.goods.Product;
import com.example.demo.model.users.Seller;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * @author Infinity97
 */

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "cart_detail")
public class CartDetail extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String cartDetailId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(name = "cart_cartDetail_fk"), nullable = false)
	private Cart cart;

	@OneToOne
	@JoinColumn(name = "product_id")
	private Product product;

	@Column(name = "total_quantity")
	private long quantity;

	@OneToOne
	@JoinColumn(name = "seller_id")
	private Seller seller;
}
