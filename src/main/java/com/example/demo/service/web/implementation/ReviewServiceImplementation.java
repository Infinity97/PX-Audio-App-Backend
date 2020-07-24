package com.example.demo.service.web.implementation;

import com.example.demo.apis.request.review.SubmitReviewRequest;
import com.example.demo.apis.request.review.UpdateReviewRequest;
import com.example.demo.apis.response.ApiResponse;
import com.example.demo.apis.response.ApiResponseStatus;
import com.example.demo.model.goods.Product;
import com.example.demo.model.users.Review;
import com.example.demo.model.users.Users;
import com.example.demo.repository.goods.ProductsRepo;
import com.example.demo.repository.users.ReviewRepo;
import com.example.demo.repository.users.UsersRepo;
import com.example.demo.service.web.IReviewService;
import com.example.demo.utils.helper.Helper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author Infinity97
 */

@Slf4j
@Service
public class ReviewServiceImplementation implements IReviewService {

	@Autowired
	private ReviewRepo reviewRepo;

	@Autowired
	private UsersRepo usersRepo;

	@Autowired
	private ProductsRepo productsRepo;

	@Autowired
	private Helper helper;

	@Override
	public ApiResponse<String> submitReview(SubmitReviewRequest submitReviewRequest) {

		if(!helper.checkValidRating(submitReviewRequest.getRating()))
			return new ApiResponse<>(ApiResponseStatus.NOT_VALID_RATING);

		if(StringUtils.isEmpty(submitReviewRequest.getUserId()))
			return new ApiResponse<>(ApiResponseStatus.USER_NOT_AVAILABLE);

		Users users = usersRepo.getOne(submitReviewRequest.getUserId());
		if(users == null)
			return new ApiResponse<>(ApiResponseStatus.USER_NOT_AVAILABLE);

		if(StringUtils.isEmpty(submitReviewRequest.getProductId()))
			return new ApiResponse<>(ApiResponseStatus.PRODUCT_DOES_NOT_EXIST);

		Product product = productsRepo.getOne(submitReviewRequest.getProductId());
		if(product == null)
			return new ApiResponse<>(ApiResponseStatus.PRODUCT_DOES_NOT_EXIST);

		Review review = new Review();
		review.setUsers(users);
		review.setProduct(product);
		review.setRating(5);

		if(!StringUtils.isEmpty(submitReviewRequest.getReviewTitle()))
			review.setTitle(submitReviewRequest.getReviewTitle());

		if(!StringUtils.isEmpty(submitReviewRequest.getReviewDescription()))
			review.setDescription(submitReviewRequest.getReviewDescription());

		reviewRepo.save(review);

		return new ApiResponse<>(ApiResponseStatus.SUCCESS,"ReviewUploadedSuccessfully");

	}

	@Override
	public ApiResponse<String> updateReview(UpdateReviewRequest updateReviewRequest) {

		if(StringUtils.isEmpty(updateReviewRequest.getReviewId())){
			log.error("ERROR: Review Id is Empty");
			return new ApiResponse<>(ApiResponseStatus.REVIEW_DOES_NOT_EXIST);
			}

		Review review = reviewRepo.getOne(updateReviewRequest.getReviewId());
		if(review == null)
			return new ApiResponse<>(ApiResponseStatus.REVIEW_DOES_NOT_EXIST);

		if(!StringUtils.isEmpty(updateReviewRequest.getReviewDescription()))
			review.setDescription(updateReviewRequest.getReviewDescription());

		if(!StringUtils.isEmpty(updateReviewRequest.getReviewTitle()))
			review.setTitle(updateReviewRequest.getReviewTitle());

		if(helper.checkValidRating(updateReviewRequest.getRating()))
			review.setRating(updateReviewRequest.getRating());

		//TODO: Upload the MultiPart File.
		return new ApiResponse<>(ApiResponseStatus.SUCCESS,"Review has been successfully updated");
	}
}
