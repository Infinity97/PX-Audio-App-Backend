package com.example.demo.apis.request.cart;

import lombok.Getter;

/**
 * @author Infinity97
 */
@Getter
public class RemoveFromCartRequest{

	String userId;
	String cartDetailId;
	String productId;
}
