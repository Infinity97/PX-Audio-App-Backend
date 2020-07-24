package com.example.demo.model.users;

import com.example.demo.model.BaseEntity;
import com.example.demo.model.CompanySeller;
import com.example.demo.model.ProductSeller;
import com.example.demo.model.order.Order;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Infinity97
 */
// Dealers and Distributors who supply particular items.
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "seller")
public class Seller extends BaseEntity {

	@Id
	private String sellerId;
	@OneToOne
	@MapsId
	@JoinColumn
	private Users users;

	@OneToMany(mappedBy = "seller", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<Order> sellingOrders;

	// Those orders bought through the company.
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "buying_orders")
	private Set<Order> buyingOrders;

	//TODO: Add all the orders that he has bought for
	@Column(name = "payment_received")
	private double paymentReceived;

	@Column(name = "payment_made")
	private double paymentMade;

	@OneToMany(mappedBy = "seller")
	private Set<ProductSeller> productSellers = new HashSet<>();

	@OneToMany(mappedBy = "seller")
	private Set<CompanySeller> companySellers = new HashSet<>();

}
