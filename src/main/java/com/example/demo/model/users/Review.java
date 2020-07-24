package com.example.demo.model.users;

import com.example.demo.model.BaseEntity;
import com.example.demo.model.goods.Product;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author Infinity97
 */

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "reviews")
public class Review extends BaseEntity {

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "review_id", columnDefinition = "VARCHAR(255)", unique = true, nullable = false)
	private String reviewId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id",
			referencedColumnName = "user_id",
			foreignKey = @ForeignKey(name = "review_user_id_fk"),
			nullable = false)
	private Users users;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id",
			referencedColumnName = "product_id",
			foreignKey = @ForeignKey(name = "review_product_id_fk"),
			nullable = false)
	private Product product;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@Column(name = "rating")
	private double rating;

}
