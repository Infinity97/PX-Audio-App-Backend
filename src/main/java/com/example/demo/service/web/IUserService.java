package com.example.demo.service.web;

import com.example.demo.apis.request.login.RegisterNewUserRequest;
import com.example.demo.apis.response.ApiResponse;
import com.example.demo.model.users.Users;

import java.util.List;

public interface IUserService {

    ApiResponse<List<Users>> addUsers(RegisterNewUserRequest userRequest);

}
