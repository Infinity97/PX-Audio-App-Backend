package com.example.demo.utils.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AmazonS3Services {

//    private AmazonS3 amazonS3;
//
//    @Autowired
//    private PropertyReader propertyReader;
//
//    @PostConstruct
//    void init(){
//        this.amazonS3 = dynamoDbConfig.amazonS3Config();
//    }
//
//    public String uploadFileToS3bucketAndGetURL(String fileName, File file, String bucketName) {
//        try {
//            amazonS3.putObject(new PutObjectRequest(bucketName, fileName, file)
//                    .withCannedAcl(CannedAccessControlList.PublicRead));
//            return String.valueOf(amazonS3.getUrl(bucketName, fileName));
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.error("An Error Occurred in uploading the Image of the following File Name {}", fileName);
//            return "";
//        }
//    }

}
