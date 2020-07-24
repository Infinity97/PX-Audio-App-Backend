package com.example.demo.apis.request.company;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class AddNewCompanyRequest {

    private String companyName;
    private String companyDetails;

    @NotNull
    private String categoryId;

}
