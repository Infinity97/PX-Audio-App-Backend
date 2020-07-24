package com.example.demo.service.web;

import com.example.demo.apis.request.review.SubmitReviewRequest;
import com.example.demo.apis.request.review.UpdateReviewRequest;
import com.example.demo.apis.response.ApiResponse;

/**
 * @author Infinity97
 */
public interface IReviewService {

	ApiResponse<String> submitReview(SubmitReviewRequest submitReviewRequest);
	ApiResponse<String> updateReview(UpdateReviewRequest updateReviewRequest);

}
