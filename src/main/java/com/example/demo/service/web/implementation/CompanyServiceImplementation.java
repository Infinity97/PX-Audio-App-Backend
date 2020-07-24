package com.example.demo.service.web.implementation;

import com.example.demo.apis.request.company.AddNewCompanyRequest;
import com.example.demo.apis.request.company.UpdateCompanyRequest;
import com.example.demo.apis.response.ApiResponse;
import com.example.demo.apis.response.ApiResponseStatus;
import com.example.demo.apis.response.company.CompanyResponse;
import com.example.demo.model.goods.Category;
import com.example.demo.model.goods.Company;
import com.example.demo.repository.goods.CategoryRepo;
import com.example.demo.repository.goods.CompanyRepo;
import com.example.demo.service.web.ICompanyService;
import com.example.demo.service.web.IProductService;
import com.example.demo.utils.helper.ObjectToResponseConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class CompanyServiceImplementation implements ICompanyService {

	@Autowired
	private CompanyRepo companyRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private IProductService productService;

	@Autowired
	private ObjectToResponseConverter objectToResponseConverter;

	@Override
	public ApiResponse<String> addCompany(AddNewCompanyRequest addNewCompanyRequest) {

		//TODO: Before Adding a Company we need to ask for Trademark Certificate.
		Company company;
		if (StringUtils.isEmpty(addNewCompanyRequest.getCompanyName()))
			return new ApiResponse<>(ApiResponseStatus.COMPANY_NOT_ENTERED);

		company = companyRepo.findByCompanyName(addNewCompanyRequest.getCompanyName());
		if (company != null)
			return new ApiResponse<>(ApiResponseStatus.COMPANY_ALREADY_EXIST);

		company = new Company();
		company.setCompanyName(addNewCompanyRequest.getCompanyName());

		if (!StringUtils.isEmpty(addNewCompanyRequest.getCompanyDetails()))
			company.setCompanyDetails(addNewCompanyRequest.getCompanyDetails());

		if(StringUtils.isEmpty(addNewCompanyRequest.getCategoryId()))
			return new ApiResponse<>(ApiResponseStatus.CATEGORY_NOT_ENTERED);

		Category category = categoryRepo.getOne(addNewCompanyRequest.getCategoryId());
		if(category == null)
			return new ApiResponse<>(ApiResponseStatus.CATEGORY_DOES_NOT_EXIST);

		Set<Category> categories = new HashSet<>();
		categories.add(category);
		company.setCategories(categories);

		companyRepo.save(company);
		return new ApiResponse<>(ApiResponseStatus.SUCCESS, "Company Added Successfully");
	}

	@Override
	public ApiResponse<String> updateCompany(UpdateCompanyRequest updateCompanyRequest) {

		Company company;
		if (StringUtils.isEmpty(updateCompanyRequest.getCompanyId()))
			return new ApiResponse<>(ApiResponseStatus.COMPANY_NOT_ENTERED);

		company = companyRepo.getOne(updateCompanyRequest.getCompanyId());
		if (company == null)
			return new ApiResponse<>(ApiResponseStatus.COMPANY_DOEST_NOT_EXIST);

		if (!StringUtils.isEmpty(updateCompanyRequest.getCompanyName())) {
			Company companyTemp = companyRepo.findByCompanyName(updateCompanyRequest.getCompanyName());
			if (companyTemp == null) {
				company.setCompanyName(updateCompanyRequest.getCompanyName());
			}
			//TODO: Not Allowing same name for multiple Companies irrespective of the categories they serve.
			else return new ApiResponse<>(ApiResponseStatus.COMPANY_ALREADY_EXIST);
		}

		Set<Category> categories = company.getCategories();

		if(!StringUtils.isEmpty(updateCompanyRequest.getCategoryId())){
			Category category = categoryRepo.getOne(updateCompanyRequest.getCategoryId());
			if(category!=null){
				categories.add(category);
				company.setCategories(categories);
			}
		}

		companyRepo.save(company);
		return new ApiResponse<>(ApiResponseStatus.SUCCESS,"Company Details has been updated successfully");
	}

	@Override
	public ApiResponse<CompanyResponse> getCompany(String companyId, String companyName) {

		Company company;
		if (StringUtils.isEmpty(companyId)) {
			if (StringUtils.isEmpty(companyName))
				return new ApiResponse<>(ApiResponseStatus.COMPANY_NOT_ENTERED);
			else {
				company = companyRepo.findByCompanyName(companyName);
				if (company == null) {
					log.error("No Company Exists with the following company Name {}", companyName);
					return new ApiResponse<>(ApiResponseStatus.COMPANY_DOEST_NOT_EXIST);
				} else {
					return new ApiResponse<>(ApiResponseStatus.SUCCESS,
							objectToResponseConverter.convertToCompanyResponseFromCompany(company));
				}
			}
		}
		company = companyRepo.getOne(companyId);
		if (company == null)
			return new ApiResponse<>(ApiResponseStatus.COMPANY_DOEST_NOT_EXIST);

		return new ApiResponse<>(ApiResponseStatus.SUCCESS,
				objectToResponseConverter.convertToCompanyResponseFromCompany(company));
	}

	@Override
	public ApiResponse<List<CompanyResponse>> getListOfCompaniesByCategory(String categoryId, String categoryName) {

		Category category;
		if (StringUtils.isEmpty(categoryId)) {
			if (StringUtils.isEmpty(categoryName))
				return new ApiResponse<>(ApiResponseStatus.CATEGORY_NOT_ENTERED);
			else {
				category = categoryRepo.findByCategoryName(categoryName);
				if(category == null)
					return new ApiResponse<>(ApiResponseStatus.CATEGORY_DOES_NOT_EXIST);
				return new ApiResponse<>(ApiResponseStatus.SUCCESS, objectToResponseConverter
						.convertToListOfCompanyResponsesFromListOfCompany(category.getCompanies()));
			}
		}
		category = categoryRepo.getOne(categoryId);
		if(category == null)
			return new ApiResponse<>(ApiResponseStatus.CATEGORY_DOES_NOT_EXIST);
		return new ApiResponse<>(ApiResponseStatus.SUCCESS,
				objectToResponseConverter.convertToListOfCompanyResponsesFromListOfCompany(category.getCompanies()));
	}
}
