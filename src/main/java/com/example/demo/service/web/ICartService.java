package com.example.demo.service.web;

import com.example.demo.apis.request.cart.AddToCartRequest;
import com.example.demo.apis.request.cart.RemoveFromCartRequest;
import com.example.demo.apis.response.ApiResponse;
import com.example.demo.apis.response.cart.CartDetailResponse;
import com.example.demo.apis.response.cart.DisplayCartResponse;

/**
 * @author Infinity97
 */
public interface ICartService {
	//TODO : Remove All
	ApiResponse<String> addToCart(AddToCartRequest addToCartRequest);
	ApiResponse<String> removeFromCart(RemoveFromCartRequest removeFromCartRequest);
	ApiResponse<DisplayCartResponse> displayCart(String userId);
	ApiResponse<CartDetailResponse> updateCartDetail(String userId, String cartDetailId, long updatedQuantity);
}
