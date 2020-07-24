package com.example.demo.apis.request.login;

import com.example.demo.apis.request.AddressRequest;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class UserDetailsRequest {

    @NonNull
    private String userId;

    private String userName;
    private String imageUrl;
    private AddressRequest addressRequest;
}
