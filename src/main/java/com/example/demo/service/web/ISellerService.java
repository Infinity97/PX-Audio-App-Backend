package com.example.demo.service.web;

import com.example.demo.apis.response.ApiResponse;
import com.example.demo.model.users.Seller;

public interface ISellerService {

	ApiResponse<Seller> registerUserAsASeller(String userId);

}
