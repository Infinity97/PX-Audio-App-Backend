package com.example.demo.service.web.implementation;

import com.example.demo.apis.request.login.RegisterNewUserRequest;
import com.example.demo.apis.response.ApiResponse;
import com.example.demo.apis.response.ApiResponseStatus;
import com.example.demo.model.users.Users;
import com.example.demo.repository.users.UsersRepo;
import com.example.demo.service.web.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Slf4j
@Service
public class UserServiceImplementation implements IUserService {


    @Autowired
    private UsersRepo usersRepo;

    @Override
    public ApiResponse<List<Users>> addUsers(RegisterNewUserRequest userRequest) {

        ApiResponse<List<Users>> apiResponse = new ApiResponse<>();
        Users users = new Users();
        users.setEmailId(userRequest.getEmailId());
        users.setName(userRequest.getName());
        usersRepo.save(users);
        log.info("Added an Element");
        apiResponse.setResponseObject(usersRepo.findAll());
        apiResponse.setApiResponseStatus(ApiResponseStatus.SUCCESS);
        return apiResponse;
    }
}
