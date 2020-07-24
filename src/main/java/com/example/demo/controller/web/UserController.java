package com.example.demo.controller.web;

import com.example.demo.apis.request.login.RegisterNewUserRequest;
import com.example.demo.apis.response.ApiResponse;
import com.example.demo.model.users.Users;
import com.example.demo.repository.users.UsersRepo;
import com.example.demo.service.web.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/admin/users")
public class UserController {

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private IUserService userService;

    @GetMapping(value = "/all")
    List<Users> getAll(){
        return usersRepo.findAll();
    }

    @PostMapping(value = "/add")
    public ResponseEntity<ApiResponse<List<Users>>> add(@RequestBody RegisterNewUserRequest userRequest) {
         ApiResponse<List<Users>> apiResponse = userService.addUsers(userRequest);
         return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
