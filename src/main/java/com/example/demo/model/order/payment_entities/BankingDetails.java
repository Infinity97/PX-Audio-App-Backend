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
public class BankingDetails extends BaseEntity {

	private String accountId;
	private String ifscCode;
	private String bankName;

}
