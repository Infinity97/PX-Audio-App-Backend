package com.example.demo.utils.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Slf4j
public class CustomLoggerFactory {

    public static void error(String urlPath, String message, Object request, Exception e){

    }

    public static void error(String urlPath, String message, Object request, List<Object> objectList, Exception e){

        StringBuilder loggingInfo = new StringBuilder();
        loggingInfo.append("API: ").append(urlPath);
        loggingInfo.append("\t REQUEST: ").append(request);
        loggingInfo.append("\t MESSAGE: ").append(message);


        if(!CollectionUtils.isEmpty(objectList))
            log.error(loggingInfo.toString(),e);
        else
            log.error(loggingInfo.toString(),e);
    }

    public static void info(){}

}
