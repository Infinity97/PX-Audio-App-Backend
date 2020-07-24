package com.example.demo.apis.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

@Slf4j
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timeStamp;
    private String message;
    private String debugMessage;
    private T responseObject;
    private ApiResponseStatus apiResponseStatus;

    public ApiResponse() {
        timeStamp = LocalDateTime.now();
    }

    public ApiResponse(HttpStatus status) {
        this();
        this.status = status;
    }

    public ApiResponse(HttpStatus status, Throwable exception) {
        this();
        this.status = status;
        this.message = "UnExpected Error";
        this.debugMessage = exception.getLocalizedMessage();
    }

    public ApiResponse(HttpStatus status, String message, Throwable exception) {
        this();
        this.status = status;
        this.message = message;
        this.debugMessage = exception.getLocalizedMessage();
    }

    public ApiResponse(ApiResponseStatus apiResponseStatus, T responseObject) {
        this();
        this.apiResponseStatus = apiResponseStatus;
        this.responseObject = responseObject;
    }

    public ApiResponse(ApiResponseStatus apiResponseStatus, Throwable exception){
        this();
        this.apiResponseStatus = apiResponseStatus;
        this.debugMessage = exception.getLocalizedMessage();
        log.error("Error occured:- ", exception);
    }

    public ApiResponse(ApiResponseStatus apiResponseStatus) {
        this();
        this.apiResponseStatus = apiResponseStatus;
        log.error("ERROR: {} ", apiResponseStatus);
    }
}
