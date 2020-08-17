package com.example.demo.apis.request.order.orderDetail;

import lombok.Getter;

/**
 * @author Infinity97
 */
@Getter
public class CreateOrderDetailRequest {

	private String buyerId;
	private String sellerId;
	private String productId;
	private double quantity;
	private double shippingCost;

}
