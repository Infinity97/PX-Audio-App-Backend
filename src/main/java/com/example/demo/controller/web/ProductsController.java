package com.example.demo.controller.web;

import com.example.demo.apis.request.product.AddProductRequest;
import com.example.demo.apis.request.product.UpdateProductRequest;
import com.example.demo.apis.response.ApiResponse;
import com.example.demo.apis.response.ApiResponseStatus;
import com.example.demo.repository.goods.ProductsRepo;
import com.example.demo.service.web.IProductService;
import com.example.demo.utils.constants.ApiPathConstants;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = ApiPathConstants.ADMIN + ApiPathConstants.PRODUCT_CONTROLLER)
public class ProductsController {

    @Autowired
    private ProductsRepo productRepo;

    @Autowired
    private IProductService productService;
    // TODO : Upload An Image

    Logger logger =LoggerFactory.getLogger(ProductsController.class);

    @PostMapping(value = ApiPathConstants.ADD_PRODUCT)
    public ResponseEntity<ApiResponse<String>> add(@RequestBody AddProductRequest product)
    {
        ApiResponse<String> apiResponse;
        try{
            apiResponse = productService.addProduct(product,null);
        }catch (Exception e){
            apiResponse = new ApiResponse<>(ApiResponseStatus.FAILURE,e);
        }
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @PostMapping(value = ApiPathConstants.UPDATE_PRODUCT)
    public  ResponseEntity<ApiResponse<String>> update(@RequestBody UpdateProductRequest productRequest)
    {
        ApiResponse<String> apiResponse;
        try {
            apiResponse = productService.updateProduct(productRequest);
        }catch (Exception e){
            apiResponse = new ApiResponse<>(ApiResponseStatus.FAILURE,e);
        }
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

//    @DeleteMapping(value = ApiPathConstants.DELETE_PRODUCT)
//    public  ResponseEntity<ApiResponse<String>> delete(@RequestParam(value = "productId", required = true) String productId)
//    {
//        ApiResponse<String> apiResponse;
//        try {
//            apiResponse = productService.deleteProduct(productId);
//        }catch (Exception e){
//
//            apiResponse = new ApiResponse<>(ApiResponseStatus.FAILURE,e);
//        }
//        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
//    }

    @GetMapping(value = ApiPathConstants.GET_PRODUCT)
    public ResponseEntity<ApiResponse<String>> getProductDetails(@RequestParam(value = "productId") String productId){

        ApiResponse<String> apiResponse;
        try {
            apiResponse = productService.deleteProduct(productId);
        }catch (Exception e){
            apiResponse = new ApiResponse<>(ApiResponseStatus.FAILURE,e);
        }
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
