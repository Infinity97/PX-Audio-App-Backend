package com.example.demo.controller.web;

import com.example.demo.apis.request.cart.AddToCartRequest;
import com.example.demo.apis.request.cart.RemoveFromCartRequest;
import com.example.demo.apis.response.ApiResponse;
import com.example.demo.apis.response.ApiResponseStatus;
import com.example.demo.apis.response.cart.DisplayCartResponse;
import com.example.demo.service.web.ICartService;
import com.example.demo.utils.constants.ApiPathConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Infinity97
 */

// TODO : Add Cart
// TODO: Delete Cart
	@RestController
	@RequestMapping(value = ApiPathConstants.CART_CONTROLLER)
public class CartController {

	@Autowired
	private ICartService cartService;

	@PostMapping(value = ApiPathConstants.ADD_TO_CART)
	public ResponseEntity<ApiResponse<String>> addToCart(@RequestBody AddToCartRequest addToCartRequest){
		ApiResponse<String> apiResponse;
		try{
			apiResponse = cartService.addToCart(addToCartRequest);
		}catch (Exception e){
			apiResponse = new ApiResponse<>(ApiResponseStatus.FAILURE, e);
		}
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	@PostMapping(value = ApiPathConstants.REMOVE_FROM_CART)
	public ResponseEntity<ApiResponse<String>> removeFromCart(@RequestBody RemoveFromCartRequest removeFromCartRequest){
		ApiResponse<String> apiResponse;
		try{
			apiResponse = cartService.removeFromCart(removeFromCartRequest);
		}catch (Exception e){
			apiResponse = new ApiResponse<>(ApiResponseStatus.FAILURE, e);
		}
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	@GetMapping(value = ApiPathConstants.DISPLAY_CART)
	public ResponseEntity<ApiResponse<DisplayCartResponse>> registerNewUser(@RequestParam(name = "userId") String userId){
		ApiResponse<DisplayCartResponse> apiResponse;
		try{
			apiResponse = cartService.displayCart(userId);
		}catch (Exception e){
			apiResponse = new ApiResponse<>(ApiResponseStatus.FAILURE, e);
		}
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

}
