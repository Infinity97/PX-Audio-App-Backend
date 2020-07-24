package com.example.demo.model.order;

import com.example.demo.model.BaseEntity;
import com.example.demo.model.users.Address;
import com.example.demo.model.users.Seller;
import com.example.demo.model.users.Users;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "order")
public class Order extends BaseEntity {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "order_id", columnDefinition = "VARCHAR(255)", unique = true, nullable = false)
	private String orderId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "buyer_id", referencedColumnName = "user_id",
			foreignKey = @ForeignKey(name = "buyer_order_fk"), nullable = false)
	private Users buyer;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "seller_id",
			foreignKey = @ForeignKey(name = "seller_order_fk"), nullable = false)
	@JsonBackReference
	private Seller seller;

	/**
	 * ENUM Payment Status
	 */
	// Paid or Partially Paid or Not Paid
	@Column(name = "payment_status")
	private String paymentStatus;

	/**
	 * ENUM Delivery Status
	 */
	@Column(name = "delivery_status")
	private String deliveryStatus;

	/**
	 * ENUM Order Status
	 */
	@Column(name = "order_status")
	private String orderStatus;

	@Column(name = "total_list_price")
	private double totalListPrice;

	@Column(name = "total_selling_price")
	private double totalSellingPrice;

	@Column(name = "total_amount_pending")
	private double pendingAmount;

	@Column(name = "checkout_comment")
	String checkoutComment;

	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "address_id", nullable = false)
	private Address deliveryAddress;

	// If any modifications to the order placed
	@Column(name = "is_changed")
	private boolean isChanged;

	@OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<OrderDetail> orderDetails;
}
