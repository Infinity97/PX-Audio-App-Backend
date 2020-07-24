package com.example.demo.apis.response.login;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Setter;

@Setter
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class LoginOrSignUpResponse {

    private String userId;
    private String name;
    private String imageUrl;
    private String mobileNumber;

}
