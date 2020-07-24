package com.example.demo.service.web;

import com.example.demo.apis.request.company.AddNewCompanyRequest;
import com.example.demo.apis.request.company.UpdateCompanyRequest;
import com.example.demo.apis.response.ApiResponse;
import com.example.demo.apis.response.company.CompanyResponse;

import java.util.List;

public interface ICompanyService {

    ApiResponse<String> addCompany(AddNewCompanyRequest addNewCompanyRequest);

    ApiResponse<String> updateCompany(UpdateCompanyRequest updateCompanyRequest);

    ApiResponse<CompanyResponse> getCompany(String companyId, String companyName);

    ApiResponse<List<CompanyResponse>> getListOfCompaniesByCategory(String categoryId, String categoryName);

}
