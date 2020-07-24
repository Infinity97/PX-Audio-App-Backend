package com.example.demo.repository;

import com.example.demo.model.Image;
import com.example.demo.utils.enums.ImageType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepo extends JpaRepository<Image, String> {

    Image findByImageNameAndImageType(String imageName, ImageType imageType);

}
