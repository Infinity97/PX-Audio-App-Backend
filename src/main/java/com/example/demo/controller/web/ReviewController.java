package com.example.demo.controller.web;

import com.example.demo.apis.request.review.SubmitReviewRequest;
import com.example.demo.apis.request.review.UpdateReviewRequest;
import com.example.demo.apis.response.ApiResponse;
import com.example.demo.apis.response.ApiResponseStatus;
import com.example.demo.service.web.IReviewService;
import com.example.demo.utils.constants.ApiPathConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Infinity97
 */

// Non Admin
@Slf4j
@RestController
@RequestMapping(value = ApiPathConstants.REVIEW_CONTROLLER)
public class ReviewController {

	@Autowired
	private IReviewService reviewService;

	@PostMapping(value = "/submitReview")
	public ResponseEntity<ApiResponse<String>> submitReview(@RequestBody SubmitReviewRequest submitReviewRequest)
	{
		ApiResponse<String> apiResponse;
		try{
			apiResponse = reviewService.submitReview(submitReviewRequest);
		}catch (Exception e){
			apiResponse = new ApiResponse<>(ApiResponseStatus.FAILURE,e);
		}
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	@PostMapping(value = "/updateReview")
	public ResponseEntity<ApiResponse<String>> updateReview(@RequestBody UpdateReviewRequest updateReviewRequest)
	{
		ApiResponse<String> apiResponse;
		try{
			apiResponse = reviewService.updateReview(updateReviewRequest);
		}catch (Exception e){
			apiResponse = new ApiResponse<>(ApiResponseStatus.FAILURE,e);
		}
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}


}
