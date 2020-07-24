package com.example.demo.model;

import com.example.demo.model.goods.Company;
import com.example.demo.model.users.Seller;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Infinity97
 */
@EqualsAndHashCode(callSuper = false)
@Entity
@Data
@Table(name = "company_seller")
@IdClass(CompanySeller.IdClass.class)
public class CompanySeller extends BaseEntity {

	@Id
	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company;

	@Id
	@ManyToOne
	@JoinColumn(name = "seller_id")
	private Seller seller;

	@Data
	static class IdClass implements Serializable {
		private Company company;
		private Seller seller;
	}

}
