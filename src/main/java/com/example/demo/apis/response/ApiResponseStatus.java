package com.example.demo.apis.response;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ApiResponseStatus {
    // All the Success Messages to be displayed here with Code 1000
    SUCCESS(1000, "Success"),

    // All the Failure Messages to be displayed here with Code other than 1000
    FAILURE(1001,"Failure"),
    INCOMPLETE_OR_INCORRECT_REQUEST(1001,"FATAL ERROR: Some information in request is missing"),

    // Login related Errors
    USER_NOT_AVAILABLE(1002, "User is Not Available"),
    USER_ALREADY_REGISTERED(1002, "User Already Registered"),
    MOBILE_NUMBER_NOT_ENTERED(1002, "Mobile Number Not Entered"),
    INVALID_PASSWORD(1002,"Invalid Password"),
    INCORRECT_PASSWORD(1002,"Incorrect Password"),
    LOGIN_TYPE_NOT_MENTIONED(1002,"Login Type is not mentioned"),
	EMAIL_NOT_PRESENT(1002, "Email Id Is Not Present"),

	//Seller Related Errors
    SELLER_NOT_AVAILABLE(1002,"Seller is not available"),

    //Product Detail Related Errors
    PRODUCT_NAME_NOT_ENTERED(1003, "Product name is not entered"),
    PRODUCT_DOES_NOT_EXIST(1003, "Product does not exist"),
    PRODUCT_ALREADY_EXIST(1003, "Product already exist"),
    COMPANY_NOT_ENTERED(1003, "Company name is not entered"),
    COMPANY_DOEST_NOT_EXIST(1003, "Company does not exist"),
    COMPANY_ALREADY_EXIST(1003,"Company already exist"),
    CATEGORY_DOES_NOT_EXIST(1003, "Category Does Not Exist"),
    PARENT_CATEGORY_DOES_NOT_EXIST(1003, "Parent Category Does Not Exist"),
    CATEGORY_NOT_ENTERED(1003, "Category is not entered"),
    CATEGORY_ALREADY_EXIST(1003, "Category Already exists"),

    //Review Related Errors
    REVIEW_DOES_NOT_EXIST(1004,"Review Does Not Exist"),
    NOT_VALID_RATING(1004, " Not a Valid Rating"),


    //Cart related Errors
    CART_DOES_NOT_EXIST(1005,"Cart does not exist"),
    CART_DETAIL_NOT_PRESENT_IN_CART(1005,"Cart detail is not associated to the same cart"),
    CART_DETAIL_DOES_NOT_EXIST(1005,"Cart detail does not exist"),
    CART_IS_EMPTY(1005,"Cart is empty"),
    NOT_A_VALID_QUANTITY(1005,"Please enter a valid quantity");

	private int code;
    private String message;

    ApiResponseStatus(int code, String message){
        this.code =code;
        this.message = message;
    }

    @JsonValue
    protected ApiResponseStatusToJson toJson() {
        ApiResponseStatusToJson apiResponseStatusToJson = new ApiResponseStatusToJson();
        apiResponseStatusToJson.setCode(this.code);
        apiResponseStatusToJson.setMessage(this.message);
        return apiResponseStatusToJson;
    }
}
