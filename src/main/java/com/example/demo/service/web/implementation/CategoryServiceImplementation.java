package com.example.demo.service.web.implementation;

import com.example.demo.apis.request.category.AddCategoryRequest;
import com.example.demo.apis.request.category.UpdateCategoryRequest;
import com.example.demo.apis.response.ApiResponse;
import com.example.demo.apis.response.ApiResponseStatus;
import com.example.demo.model.Image;
import com.example.demo.model.goods.Category;
import com.example.demo.repository.ImageRepo;
import com.example.demo.repository.goods.CategoryRepo;
import com.example.demo.service.web.ICategoryService;
import com.example.demo.utils.enums.ImageType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class CategoryServiceImplementation implements ICategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ImageRepo imageRepo;

    @Override
    public ApiResponse<String> addCategory(AddCategoryRequest addCategoryRequest) {

        Category category;
        if(StringUtils.isEmpty(addCategoryRequest.getCategoryName()))
            return new ApiResponse<>(ApiResponseStatus.CATEGORY_NOT_ENTERED);
        else{
            category = categoryRepo.findByCategoryName(addCategoryRequest.getCategoryName());
            if(category != null)
                return new ApiResponse<>(ApiResponseStatus.CATEGORY_ALREADY_EXIST);
            category = new Category();
            category.setCategoryName(addCategoryRequest.getCategoryName());
        }

        if(!StringUtils.isEmpty(addCategoryRequest.getCategoryImageUrl())){
            Image image =  imageRepo.findByImageNameAndImageType(addCategoryRequest.getCategoryName(), ImageType.CATEGORY);
            if(image!=null) {
                Set<Image> imageList = new HashSet<>();
                imageList.add(image);
                category.setCategoryImages(imageList);
            }
        }

        if(!StringUtils.isEmpty(addCategoryRequest.getParentCategoryId())){
            Category parentCategory = categoryRepo.getOne(addCategoryRequest.getParentCategoryId());
            if(parentCategory == null){
                return new ApiResponse<>(ApiResponseStatus.PARENT_CATEGORY_DOES_NOT_EXIST);
            }
            category.setParentCategory(parentCategory);
        }

        categoryRepo.save(category);
        return new ApiResponse<>(ApiResponseStatus.SUCCESS,"Category Added Successfully");
    }

    @Override
    public ApiResponse<String> updateCategory(UpdateCategoryRequest updateCategoryRequest) {

        if(StringUtils.isEmpty(updateCategoryRequest.getCategoryId()))
            return new ApiResponse<>(ApiResponseStatus.CATEGORY_NOT_ENTERED);

        Category category = categoryRepo.getOne(updateCategoryRequest.getCategoryId());
        if(category == null)
            return new ApiResponse<>(ApiResponseStatus.CATEGORY_DOES_NOT_EXIST);

        if(!StringUtils.isEmpty(updateCategoryRequest.getCategoryName())){
            Category categoryTemp = categoryRepo.findByCategoryName(updateCategoryRequest.getCategoryName());
            if(categoryTemp != null)
                return new ApiResponse<>(ApiResponseStatus.CATEGORY_ALREADY_EXIST,"Category Name Already Exists");
            category.setCategoryName(updateCategoryRequest.getCategoryName());
        }

        if(!StringUtils.isEmpty(updateCategoryRequest.getImageUrl())){
            Image image = imageRepo.findByImageNameAndImageType(updateCategoryRequest.getImageUrl(),ImageType.CATEGORY);
            if(image != null){
                Set<Image> imagesList = category.getCategoryImages();
                if(CollectionUtils.isEmpty(imagesList))
                    imagesList = new HashSet<>();
                imagesList.add(image);
                category.setCategoryImages(imagesList);
            }
        }

        if(!StringUtils.isEmpty(updateCategoryRequest.getParentCategoryId())){
            Category categoryTemp = categoryRepo.getOne(updateCategoryRequest.getParentCategoryId());
            if(categoryTemp == null)
                return new ApiResponse<>(ApiResponseStatus.CATEGORY_DOES_NOT_EXIST);
            category.setParentCategory(categoryTemp);
        }

        categoryRepo.save(category);

        return new ApiResponse<>(ApiResponseStatus.SUCCESS,"Category has been updated Successfully");
    }
}
