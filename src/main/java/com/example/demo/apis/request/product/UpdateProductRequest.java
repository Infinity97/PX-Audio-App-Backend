package com.example.demo.apis.request.product;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;

@Getter
public class UpdateProductRequest {

    @NonNull
    private String productId;
    private String productName;
    private String categoryId;
    private String companyId;
    private double maximumRetailPrice;
    private String detailedDescription;
    private String singleLineDescription;
    List<String> images;
}
