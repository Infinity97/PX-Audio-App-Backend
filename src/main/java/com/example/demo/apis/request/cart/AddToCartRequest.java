package com.example.demo.apis.request.cart;

import lombok.Getter;
import lombok.NonNull;

/**
 * @author Infinity97
 */

@Getter
public class AddToCartRequest {

	@NonNull
	String userId;
	String productId;
	long quantity;
	String sellerId;

}
