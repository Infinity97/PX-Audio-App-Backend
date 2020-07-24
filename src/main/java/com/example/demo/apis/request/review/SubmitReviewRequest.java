package com.example.demo.apis.request.review;

import lombok.Getter;
import lombok.NonNull;

/**
 * @author Infinity97
 */
@Getter
public class SubmitReviewRequest {

	private String reviewTitle;
	private String reviewDescription;
	private String productId;
	private String userId;

	@NonNull
	private double rating;

}
