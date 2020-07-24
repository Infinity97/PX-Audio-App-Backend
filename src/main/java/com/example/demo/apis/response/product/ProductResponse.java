package com.example.demo.apis.response.product;

import lombok.Setter;

/**
 * @author Infinity97
 */
@Setter
public class ProductResponse {

	private String productId;
	private String productName;
	private String productSingleLineDescription;
	private String productMultiLineDescription;
	private String productImageUrl;
	private double productListPrice;
	private double productSalePrice;
	private String companyName;
	private String sellerName;
	private double numberOfUsersRated;
	private double rating;

}
