package com.example.demo.model.order.payment_entities;

import com.example.demo.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Infinity97
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardDetails extends BaseEntity {

	private String cardNumber;
	private String cvv;
	private String name;
	private int expiryMonth;
	private int expiryYear;
	private String cardType;

}
