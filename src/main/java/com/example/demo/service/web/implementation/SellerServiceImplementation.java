package com.example.demo.service.web.implementation;

import com.example.demo.apis.response.ApiResponse;
import com.example.demo.apis.response.ApiResponseStatus;
import com.example.demo.model.users.Seller;
import com.example.demo.repository.users.SellerRepo;
import com.example.demo.repository.users.UsersRepo;
import com.example.demo.service.web.ISellerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author Infinity97
 */
@Slf4j
@Service
public class SellerServiceImplementation implements ISellerService {

	@Autowired
	private SellerRepo sellerRepo;

	@Autowired
	private UsersRepo usersRepo;

	@Override
	public ApiResponse<Seller> registerUserAsASeller(String userId) {

		if(StringUtils.isEmpty(userId))
			return new ApiResponse<>(ApiResponseStatus.USER_NOT_AVAILABLE);

		Seller seller = sellerRepo.getOne(userId);
		if(seller == null){
			if(usersRepo.getOne(userId)==null){
				return new ApiResponse<>(ApiResponseStatus.USER_NOT_AVAILABLE);
			}
			else{
				seller = new Seller();
				seller.setSellerId(userId);
				seller = sellerRepo.save(seller);
			}
		}
		return new ApiResponse<>(ApiResponseStatus.SUCCESS,seller);
	}
}
