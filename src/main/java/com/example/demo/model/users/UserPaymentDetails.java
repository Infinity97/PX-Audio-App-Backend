package com.example.demo.model.users;

import com.example.demo.model.BaseEntity;
import com.example.demo.model.order.payment_entities.BankingDetails;
import com.example.demo.model.order.payment_entities.CardDetails;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

/**
 * @author Infinity97
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "user_payment_details")
public class UserPaymentDetails extends BaseEntity {

	@Id
	private String userId;
	@MapsId
	@OneToOne
	@JoinColumn
	private Users users;

	@Type(type = "jsonb")
	@Column(columnDefinition = "jsonb")
	@Basic(fetch = FetchType.LAZY)
	private List<CardDetails> cardDetails;

	@Type(type = "jsonb")
	@Column(columnDefinition = "jsonb")
	@Basic(fetch = FetchType.LAZY)
	private List<BankingDetails> bankingDetails;

}
