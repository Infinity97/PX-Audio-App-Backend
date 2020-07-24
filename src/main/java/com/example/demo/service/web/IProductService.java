package com.example.demo.service.web;

import com.example.demo.apis.request.product.AddProductRequest;
import com.example.demo.apis.request.product.UpdateProductRequest;
import com.example.demo.apis.response.ApiResponse;
import com.example.demo.model.goods.Company;

import java.util.List;

public interface IProductService {

    ApiResponse<String> addProduct(AddProductRequest productRequest, Company company);

    ApiResponse<String> addProductOfACompany(List<AddProductRequest> addProductRequests, String companyId);

    ApiResponse<String> updateProduct(UpdateProductRequest productRequest);

    ApiResponse<String> deleteProduct(String productId);

    ApiResponse<String> getProduct(String productId);
}
