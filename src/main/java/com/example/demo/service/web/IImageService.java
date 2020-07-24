package com.example.demo.service.web;

import com.example.demo.apis.response.ApiResponse;
import com.example.demo.utils.enums.ImageType;
import org.springframework.web.multipart.MultipartFile;

public interface IImageService {

    ApiResponse<String> uploadImageAndReturnUrl(MultipartFile multipartFile, String identifier, String imageType) throws Exception;

}
