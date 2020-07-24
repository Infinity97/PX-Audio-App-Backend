package com.example.demo.model.goods;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Infinity97
 */

@Entity
@Data
@Table(name = "product_attributes")

public class ProductAttributes {

	@Id
	private String productId;

	@OneToOne
	@JoinColumn
	@MapsId
	private Product product;

	@Column(name = "manufacturer_name")
	private String manufacturerName;

	@Column(name = "manufacturer_part_number")
	private String manufacturerPartNumber;

	@Column(name = "HSN_CODE")
	private String hsnCode;

	// TODO: Tax Slab



//	@Column(name = "color_options")
//	private List<String> colorOptions;
}
