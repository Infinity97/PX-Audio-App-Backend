package com.example.demo.apis.request.login;

import lombok.Getter;

@Getter
public class ForgotPasswordRequest {

    String userId;
    String mobileNumber;
    String emailId;
    String newPassword;

}
