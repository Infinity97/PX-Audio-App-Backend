package com.example.demo.controller.mobile;

import com.example.demo.apis.request.login.ForgotPasswordRequest;
import com.example.demo.apis.request.login.RegisterNewUserRequest;
import com.example.demo.apis.request.login.UserDetailsRequest;
import com.example.demo.apis.response.ApiResponse;
import com.example.demo.apis.response.ApiResponseStatus;
import com.example.demo.apis.response.login.LoginOrSignUpResponse;
import com.example.demo.service.mobile.ILoginService;
import com.example.demo.utils.constants.ApiPathConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * All Mobile user Creation here
 */
@Slf4j
@RestController
@RequestMapping(value = ApiPathConstants.MOBILE + ApiPathConstants.LOGIN_CONTROLLER)
public class LoginController {

    @Autowired
    private ILoginService loginService;

    @PostMapping(value = ApiPathConstants.LOGIN_OR_SIGN_UP)
    public ResponseEntity<ApiResponse<LoginOrSignUpResponse>> loginOrSignUp(@RequestBody RegisterNewUserRequest userRequest){
        ApiResponse<LoginOrSignUpResponse> apiResponse;
        try{
            apiResponse = loginService.loginOrSignUp(userRequest);
        }catch (Exception e){
            log.error("ERROR: Exception occurred at LOGIN_OR_SIGN_UP:- ", e);
            apiResponse = new ApiResponse<>(ApiResponseStatus.FAILURE,e);
        }
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @PostMapping(value = ApiPathConstants.REGISTER_NEW_USER)
    public ResponseEntity<ApiResponse<LoginOrSignUpResponse>> registerNewUser(@RequestBody RegisterNewUserRequest userRequest){
        ApiResponse<LoginOrSignUpResponse> apiResponse;
        try{
            apiResponse = loginService.registerNewUser(userRequest);
        }catch (Exception e){
            apiResponse = new ApiResponse<>(ApiResponseStatus.FAILURE, e);
        }
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping(value = ApiPathConstants.FORGOT_PASSWORD)
    public ResponseEntity<ApiResponse<String>> forgotPassword(@RequestBody ForgotPasswordRequest forgotPasswordRequest){
        ApiResponse<String> apiResponse = new ApiResponse<>();
        try {
            apiResponse = loginService.forgotPassword(forgotPasswordRequest);
        }
        catch (Exception e){
            apiResponse.setApiResponseStatus(ApiResponseStatus.FAILURE);
            apiResponse.setResponseObject(e.toString());
        }
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping(value = ApiPathConstants.UPDATE_USER_DETAILS)
    public ResponseEntity<ApiResponse<String>> updateUserDetails(@RequestBody UserDetailsRequest userDetailsRequest){

        ApiResponse<String> apiResponse = new ApiResponse<>();
        try{
            apiResponse = loginService.updateUserDetails(userDetailsRequest);
        }catch (Exception e){
            apiResponse.setResponseObject(e.toString());
            apiResponse.setApiResponseStatus(ApiResponseStatus.FAILURE);
        }
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
}
