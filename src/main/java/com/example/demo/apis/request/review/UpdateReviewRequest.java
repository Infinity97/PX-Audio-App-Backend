package com.example.demo.apis.request.review;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Infinity97
 */

@Data
public class UpdateReviewRequest {

	private String reviewId;
	private String reviewDescription;
	private String reviewTitle;
	private MultipartFile reviewImage;
	private double rating;

}
