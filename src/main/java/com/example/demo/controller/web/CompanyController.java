package com.example.demo.controller.web;

import com.example.demo.apis.request.company.AddNewCompanyRequest;
import com.example.demo.apis.request.company.UpdateCompanyRequest;
import com.example.demo.apis.response.ApiResponse;
import com.example.demo.apis.response.ApiResponseStatus;
import com.example.demo.apis.response.company.CompanyResponse;
import com.example.demo.service.web.ICompanyService;
import com.example.demo.utils.constants.ApiPathConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = ApiPathConstants.ADMIN + ApiPathConstants.COMPANY_CONTROLLER)
public class CompanyController {

	@Autowired
	private ICompanyService companyService;

	@PostMapping(value = ApiPathConstants.ADD_COMPANY)
	public ResponseEntity<ApiResponse<String>> addCompany(@RequestBody AddNewCompanyRequest addNewCompanyRequest){
		ApiResponse<String> apiResponse;
		try {
			apiResponse = companyService.addCompany(addNewCompanyRequest);
		}catch (Exception e){
			apiResponse = new ApiResponse<>(ApiResponseStatus.FAILURE,e);
		}
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	@PostMapping(value = ApiPathConstants.UPDATE_COMPANY)
	public ResponseEntity<ApiResponse<String>> updateCompany(@RequestBody UpdateCompanyRequest updateCompanyRequest){
		ApiResponse<String> apiResponse;
		try {
			apiResponse = companyService.updateCompany(updateCompanyRequest);
		}catch (Exception e){
			apiResponse = new ApiResponse<>(ApiResponseStatus.FAILURE,e);
		}
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	@GetMapping(value = ApiPathConstants.GET_COMPANY_DETAILS)
	public ResponseEntity<ApiResponse<CompanyResponse>> getCompanyDetails(@RequestParam(value = "companyId") String companyId,
	                                                             @RequestParam(value = "companyName") String companyName){
		ApiResponse<CompanyResponse> apiResponse;
		try {
			apiResponse = companyService.getCompany(companyId,companyName);
		}catch (Exception e){
			apiResponse = new ApiResponse<>(ApiResponseStatus.FAILURE,e);
		}
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	@GetMapping(value = ApiPathConstants.GET_COMPANIES_BY_CATEGORY)
	public ResponseEntity<ApiResponse<List<CompanyResponse>>> getListOfCompaniesByCategory(@RequestParam(value =
			"categoryId") String categoryId, @RequestParam(value = "categoryName") String categoryName){
		ApiResponse<List<CompanyResponse>> apiResponse;
		try {
			apiResponse = companyService.getListOfCompaniesByCategory(categoryId,categoryName);
		}catch (Exception e){
			apiResponse = new ApiResponse<>(ApiResponseStatus.FAILURE,e);
		}
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	//TODO: Get List of Dealers of a Company's Products.
	//TODO: Get List of Sellers of a Company's Products.
}
