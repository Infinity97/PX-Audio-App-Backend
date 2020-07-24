package com.example.demo.controller.web;

import com.example.demo.apis.request.category.AddCategoryRequest;
import com.example.demo.apis.request.category.UpdateCategoryRequest;
import com.example.demo.apis.response.ApiResponse;
import com.example.demo.apis.response.ApiResponseStatus;
import com.example.demo.service.web.ICategoryService;
import com.example.demo.utils.constants.ApiPathConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = ApiPathConstants.ADMIN + ApiPathConstants.CATEGORY_CONTROLLER)
public class CategoryController{


    @Autowired
    private ICategoryService categoryService;

    @PostMapping(value = "/add")
    public ResponseEntity<ApiResponse<String>> addCategory(@RequestBody AddCategoryRequest addCategoryRequest)
    {
        ApiResponse<String> apiResponse;
        try{
            apiResponse = categoryService.addCategory(addCategoryRequest);
        }catch (Exception e){
            apiResponse = new ApiResponse<>(ApiResponseStatus.FAILURE,e);
        }
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<ApiResponse<String>> updateCategory(@RequestBody UpdateCategoryRequest updateCategoryRequest)
    {
        ApiResponse<String> apiResponse;
        try{
            apiResponse = categoryService.updateCategory(updateCategoryRequest);
        }catch (Exception e){
            apiResponse = new ApiResponse<>(ApiResponseStatus.FAILURE,e);
        }
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
