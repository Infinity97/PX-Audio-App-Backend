package com.example.demo.service.mobile;

import com.example.demo.apis.request.login.ForgotPasswordRequest;
import com.example.demo.apis.request.login.RegisterNewUserRequest;
import com.example.demo.apis.request.login.UserDetailsRequest;
import com.example.demo.apis.response.ApiResponse;
import com.example.demo.apis.response.login.LoginOrSignUpResponse;

public interface ILoginService {

    ApiResponse<LoginOrSignUpResponse> registerNewUser(RegisterNewUserRequest registerNewUserRequest);
    ApiResponse<String> forgotPassword(ForgotPasswordRequest forgotPasswordRequest);
    ApiResponse<String> updateUserDetails(UserDetailsRequest userDetailsRequest) throws Exception;
    ApiResponse<LoginOrSignUpResponse> loginOrSignUp(RegisterNewUserRequest registerNewUserRequest);

}
