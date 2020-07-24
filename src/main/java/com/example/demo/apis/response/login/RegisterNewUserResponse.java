package com.example.demo.apis.response.login;
import lombok.Data;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Setter
public class RegisterNewUserResponse {

    private String userName;
    private String emailId;
    private String userImageUrl;

}
