package com.example.demo.utils.enums;

import lombok.Getter;

@Getter
public enum ImageType {

    USER("USER", "/user/#media_id#_#media_name#"),
    PRODUCT("PRODUCT", "/product/#media_id#_#media_name#"),
    COMPANY("COMPANY", "/company/#media_id#_#media_name#"),
    CATEGORY("CATEGORY", "/category/#media_id#_#media_name#"),
    REVIEW("REVIEW","/review/#media_id#_#media_name#");

    private String name;
    private String pattern;

    ImageType(String name, String pattern){
        this.name = name;
        this.pattern = pattern;
    }
}
