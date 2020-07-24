package com.example.demo.utils.enums;
import lombok.Getter;

@Getter
public enum LoginType {
    FACEBOOK("F", "Facebook Login"),
    GOOGLE("G","Google Login"),
    NATIVE("N", " Native Login using Mobile"),
    SKIP("S", "User Skipped Login");

    private String loginAbbreviation;
    private String loginType;

    LoginType(String loginAbbreviation, String loginType) {
        this.loginAbbreviation = loginAbbreviation;
        this.loginType = loginType;
    }
}
