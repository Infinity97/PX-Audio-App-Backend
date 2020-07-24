package com.example.demo.apis.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponseStatusToJson {
    private int code;
    private String message;
}
