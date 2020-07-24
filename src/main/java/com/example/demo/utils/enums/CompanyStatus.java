package com.example.demo.utils.enums;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public enum CompanyStatus {

    PAID_CLIENT(0,"Paid Company"),
    CLIENT_IN_PROGRESS(1,"Company In Progress of Confirmation"),
    UNPAID_CLIENT(2, "Company is not contacted yet");

    private int hierarchy;
    private String text;

    CompanyStatus(int hierarchy, String text){
        this.hierarchy = hierarchy;
        this.text = text ;
    }

}
