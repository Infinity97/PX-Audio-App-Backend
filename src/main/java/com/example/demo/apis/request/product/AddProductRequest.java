package com.example.demo.apis.request.product;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class  AddProductRequest {

    @NonNull
    private String sellerId;
    private String productName;
    @NotNull
    private String categoryId;
    @NotNull
    private String companyId;
    private double maximumRetailPrice;
    private String detailedDescription;
    private String singleLineDescription;

}
