package com.example.demo.apis.request.company;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class UpdateCompanyRequest {

    @NonNull
    private String companyId;
    private String companyName;
    private String companyDetails;
    private ListOfProducts products;
    private String categoryId;

}
