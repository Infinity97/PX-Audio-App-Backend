package com.example.demo.apis.request.login;
import lombok.Getter;

@Getter
public class RegisterNewUserRequest {

    private String userId;
    private String name;
    private String emailId;
    private String mobileNumber;
    private String password;
    private String userType;
    private String imageUrl;
    private String loginType;
}
