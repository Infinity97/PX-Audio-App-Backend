package com.example.demo.controller.web;

import com.example.demo.apis.request.AddressRequest;
import com.example.demo.apis.response.ApiResponse;
import com.example.demo.apis.response.ApiResponseStatus;
import com.example.demo.model.users.Address;
import com.example.demo.repository.users.AddressRepo;
import com.example.demo.repository.ImageRepo;
import com.example.demo.repository.users.UsersRepo;
import com.example.demo.service.web.IAddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/address")
public class AddressController {

    @Autowired
    private IAddressService addressService;

    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private ImageRepo imageRepo;

    @Autowired
    private UsersRepo usersRepo;

    @PostMapping(value = "/add")
    public ResponseEntity<ApiResponse<Address>> addAddress(@RequestBody AddressRequest addressRequest, @RequestParam(value = "userId", required = false) String userId) {

        ApiResponse<Address> apiResponse = new ApiResponse<>();
        try {
            Address address = addressService.saveAddressToDB(addressRequest, null, userId);
            log.info("INFO: The Address is {}", address);
            apiResponse.setResponseObject(address);
            apiResponse.setApiResponseStatus(ApiResponseStatus.SUCCESS);
        } catch (Exception e) {
            apiResponse = new ApiResponse<>(HttpStatus.OK, e);
        }
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    ResponseEntity<ApiResponse<List<Address>>> getAll(@RequestParam String userId) {

        return new ResponseEntity<>(new ApiResponse<List<Address>>(ApiResponseStatus.SUCCESS, addressRepo.findAll()), HttpStatus.OK);
    }

}
