package com.example.demo.model.order;

import com.example.demo.model.BaseEntity;
import com.example.demo.model.goods.Product;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author Infinity97
 */

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "order_detail")
public class OrderDetail extends BaseEntity {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "order_detail_id", columnDefinition = "VARCHAR(255)", unique = true, nullable = false)
	private String orderDetailId;

	@Column(name = "quantity")
	private double quantity;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id")
	@JsonManagedReference
	private Product product;

	@Column(name = "shipping_cost")
	private double shippingCost;

	@Column(name = "weight")
	private double weight;

	@Column(name = "volume")
	private double volume;

	@Column(name = "total_list_price")
	private double totalListPrice;

	@Column(name = "total_selling_price")
	private double totalSellingPrice;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id", nullable = false)
	private Order order;

	@Column(name = "buyer_referral_earning")
	private double buyerReferralEarning;

	@Column(name = "seller_referral_earning")
	private double sellerReferralEarning;

}
