package com.example.demo.apis.response.cart;

import lombok.Setter;

import java.util.List;

/**
 * @author Infinity97
 */
@Setter
public class DisplayCartResponse {
	private List<CartDetailResponse> cartDetailResponses;
}
