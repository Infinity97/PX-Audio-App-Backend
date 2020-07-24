package com.example.demo.model.order;

import com.example.demo.model.BaseEntity;
import com.example.demo.model.users.Seller;
import com.example.demo.model.users.Users;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * @author Infinity97
 */

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "payment")
public class Payment extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "payment_id")
	private String paymentId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id",
			referencedColumnName = "order_id",
			foreignKey = @ForeignKey(name = "payment_order_id_fk"),
			nullable = false)
	private Order order;
	/**
	 * PaymentType Enum
	 */
	@Column(name = "payment_type")
	private String paymentType;
	/**
	 * PaymentStatus Enum
	 */
	@Column(name = "payment_status")
	private String paymentStatus;

	@Column(name = "payment_name")
	private String paymentName;

	@Column(name = "payment")
	private double paymentAmount;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "payment_by", nullable = false)
	private Users paymentBy;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "payment_to", nullable = false)
	private Seller paymentTo;

}
