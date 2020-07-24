package com.example.demo.utils.helper;

import com.example.demo.model.Image;
import com.example.demo.model.cart.Cart;
import com.example.demo.model.users.Users;
import com.example.demo.repository.cart.CartRepo;
import com.example.demo.utils.enums.ImageType;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;

@Component
@Slf4j
public class Helper {

    @Autowired
    private CartRepo cartRepo;

    private String serverUrl;

    public String convertToHashPassword(String plainTextPassword){
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    public boolean checkPassword(String plainTextPassword, String hashedPassword){
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }

    public long getCurrentTime(){
        Date date = new Date();
        return date.getTime();
    }

    public File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    public String generateFileName(MultipartFile multiPart, String identifier, String fileCategory) {
        StringBuilder fileName;
        fileName = new StringBuilder();

        // TODO: Do Not Add Identifier. It is a Url accessible to everyone.
        fileName.append(fileCategory.toUpperCase()).append("_").append(identifier).append("_");
        fileName.append(getCurrentTime()).append("_");
        fileName.append(Objects.requireNonNull(multiPart.getOriginalFilename()).replace(" ", "_"));
        return fileName.toString();
    }

    // TODO: Upload File Functionality
    // TODO: Upload File and Generate URL
    public boolean uploadFile(File file, String fileName, String bucketName){
        return true;
    }

    public boolean checkValidRating(double rating){
        return rating > 0 && rating <= 5;
    }

    public  boolean checkValidQuantity(long quantity, long maxQuantity){

        return quantity > 0 && quantity <= maxQuantity;
    }

    public  boolean checkValidQuantity(long quantity){
       int maxQuantity = 99;
       return quantity > 0 && quantity <= maxQuantity;
    }

    public String generateCustomReferralCode(){
        return null;
    }

    public Cart linkCartToUser(Users users){
        Cart cart = new Cart();
        cart.setUsers(users);
        return cartRepo.save(cart);
    }

    public String getMediaUrl(Image image){
        StringBuilder mediaUrl = new StringBuilder();
        if(image == null)
            return mediaUrl.toString();

        ImageType imageType = image.getImageType();
        mediaUrl.append(serverUrl).append(imageType.getPattern());
        mediaUrl.replace(mediaUrl.indexOf("#media_id#"),"#media_id#".length(),image.getImageId());
        mediaUrl.replace(mediaUrl.indexOf("#media_name#"),"#media_name#".length(),image.getImageName());

        return mediaUrl.toString();
    }
}
