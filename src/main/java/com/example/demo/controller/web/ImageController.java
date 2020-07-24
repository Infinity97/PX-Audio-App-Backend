package com.example.demo.controller.web;

import com.example.demo.apis.response.ApiResponse;
import com.example.demo.apis.response.ApiResponseStatus;
import com.example.demo.service.web.IImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

// All Image Uploads through this computer and nowhere else.
@Slf4j
@RestController
@RequestMapping(value = "/image")
public class ImageController {

    @Autowired
    private IImageService imageService;

    @PostMapping(value = "/uploadImage")
    public ResponseEntity<ApiResponse<String>> uploadImage(@RequestPart MultipartFile multipartFile, @RequestParam(value = "identifier") String identifier, @RequestParam(value = "imageType") String imageType) {
        {
            ApiResponse<String> apiResponse;
            try{
                apiResponse = imageService.uploadImageAndReturnUrl(multipartFile,identifier,imageType);
            }catch (Exception e){
                log.error("An Exception occured while Uploading an Image for identifier {} and Image Type {}", identifier, imageType);
                apiResponse = new ApiResponse<>(ApiResponseStatus.FAILURE,e);
            }
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }
    }
}
