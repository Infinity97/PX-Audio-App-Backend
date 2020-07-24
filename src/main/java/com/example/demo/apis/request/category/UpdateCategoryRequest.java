package com.example.demo.apis.request.category;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class UpdateCategoryRequest {
    @NonNull
    private String categoryId;
    private String imageUrl;
    private String categoryName;
    private String parentCategoryId;
}
