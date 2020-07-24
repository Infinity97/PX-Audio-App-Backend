package com.example.demo.apis.request.category;

import lombok.Data;

@Data
public class AddCategoryRequest {
    private String categoryName;
    private String categoryImageUrl;
    private String parentCategoryId;
}
