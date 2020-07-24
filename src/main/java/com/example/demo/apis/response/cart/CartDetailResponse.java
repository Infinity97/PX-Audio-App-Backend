package com.example.demo.apis.response.cart;

import com.example.demo.apis.response.product.ProductResponse;
import lombok.Setter;

/**
 * @author Infinity97
 */
@Setter
public class CartDetailResponse {

	private String cartDetailId;
	private ProductResponse productResponse;
	private long quantity;

}
