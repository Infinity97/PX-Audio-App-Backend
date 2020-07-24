package com.example.demo.service.web;

import com.example.demo.apis.request.category.AddCategoryRequest;
import com.example.demo.apis.request.category.UpdateCategoryRequest;
import com.example.demo.apis.response.ApiResponse;

public interface ICategoryService {
    ApiResponse<String> addCategory(AddCategoryRequest addCategoryRequest);

    ApiResponse<String> updateCategory(UpdateCategoryRequest updateCategoryRequest);
}
