package com.example.demo.model.users;

import com.example.demo.model.BaseEntity;
import com.example.demo.model.goods.Product;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * @author Infinity97
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "customer_questions")
public class CustomerQuestions extends BaseEntity {

	@Id
	private String customerQuestionsId;

	@Column(name = "questionTitle")
	private String questionTitle;

	@Column(name = "questionDescription")
	private String questionDescription;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "questioner", nullable = false)
	private Users questioner;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id",nullable = false)
	private Product product;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "answerer", nullable = false)
	private Users answerer;

	@Column(name = "answer")
	private String answer;

}
