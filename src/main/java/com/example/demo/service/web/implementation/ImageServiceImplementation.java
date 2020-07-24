package com.example.demo.service.web.implementation;

import com.example.demo.apis.response.ApiResponse;
import com.example.demo.apis.response.ApiResponseStatus;
import com.example.demo.model.*;
import com.example.demo.model.goods.Category;
import com.example.demo.model.goods.Company;
import com.example.demo.model.goods.Product;
import com.example.demo.model.users.Users;
import com.example.demo.repository.*;
import com.example.demo.repository.goods.CategoryRepo;
import com.example.demo.repository.goods.CompanyRepo;
import com.example.demo.repository.goods.ProductsRepo;
import com.example.demo.repository.users.UsersRepo;
import com.example.demo.service.web.IImageService;
import com.example.demo.utils.enums.ImageType;
import com.example.demo.utils.helper.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ImageServiceImplementation implements IImageService {

	@Autowired
	private UsersRepo usersRepo;

	@Autowired
	private ProductsRepo productsRepo;

	@Autowired
	private CompanyRepo companyRepo;

	@Autowired
	private ImageRepo imageRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private Helper helper;

	//TODO: Ability to delete an Image.

	@Override
	public ApiResponse<String> uploadImageAndReturnUrl(MultipartFile multipartFile, String identifier, String imageType)
			throws Exception {
//        return new ApiResponse<>(ApiResponseStatus.SUCCESS,"Something");
		ApiResponse<String> apiResponse = new ApiResponse<>();

		if (StringUtils.isEmpty(imageType)) throw new Exception("Image Type is Not Entered");

		if (StringUtils.isEmpty(identifier)) throw new Exception("Image Identifier is Not Entered");

		ImageType imageTypeEnum = ImageType.valueOf(imageType.toUpperCase());

		switch (imageTypeEnum) {
			case CATEGORY:
				apiResponse = uploadCategoryImageAndReturnUrl(multipartFile, identifier);
				break;
			case COMPANY:
				apiResponse = uploadCompanyImageAndReturnUrl(multipartFile, identifier);
				break;
			case PRODUCT:
				apiResponse = uploadProductImageAndReturnUrl(multipartFile, identifier);
				break;
			case USER:
				apiResponse = uploadUserImageAndReturnUrl(multipartFile, identifier);
				break;
			default:
				apiResponse.setApiResponseStatus(ApiResponseStatus.FAILURE);
				apiResponse.setResponseObject("ImageEnum is Other than the one saved");
				break;
		}

		return apiResponse;
	}

	private ApiResponse<String> uploadUserImageAndReturnUrl(MultipartFile multipartFile, String identifier)
			throws Exception {

		Users users = usersRepo.getOne(identifier);
		if (users == null) {
			return new ApiResponse<>(ApiResponseStatus.USER_NOT_AVAILABLE);
		}

		File file = helper.convertMultiPartToFile(multipartFile);
		String imageName = helper.generateFileName(multipartFile, identifier, "USER");

		// TODO: UPLOAD FILE TO S3 BUCKET
		helper.uploadFile(file, imageName, "");

		Image image = new Image();
		image.setImageType(ImageType.USER);
		image.setUsers(users);
		image.setImageName(imageName);
		Image savedImage = imageRepo.save(image);


		users.setImage(savedImage);
		usersRepo.save(users);

		return new ApiResponse<>(ApiResponseStatus.SUCCESS, "Image has been uploaded Successfully");
	}

	private ApiResponse<String> uploadProductImageAndReturnUrl(MultipartFile multipartFile, String identifier)
			throws Exception {

		Product product = productsRepo.getOne(identifier);
		if (product == null) {
			return new ApiResponse<>(ApiResponseStatus.PRODUCT_DOES_NOT_EXIST);
		}

		File file = helper.convertMultiPartToFile(multipartFile);
		String imageName = helper.generateFileName(multipartFile, identifier, "PRODUCT");

		// TODO: UPLOAD FILE TO S3 BUCKET
		helper.uploadFile(file, imageName, "");

		Image image = new Image();
		image.setImageType(ImageType.PRODUCT);
		image.setProduct(product);
		image.setImageName(imageName);
		Image savedImage = imageRepo.save(image);

		List<Image> imageSet = product.getImage();
		if (CollectionUtils.isEmpty(imageSet)) imageSet = new ArrayList<>();
		imageSet.add(savedImage);
		product.setImage(imageSet);
		productsRepo.save(product);

		return new ApiResponse<>(ApiResponseStatus.SUCCESS, "The Image is uploaded successfully");
	}

	private ApiResponse<String> uploadCompanyImageAndReturnUrl(MultipartFile multipartFile, String identifier)
			throws Exception {

		Company company = companyRepo.getOne(identifier);
		if (company == null) {
			return new ApiResponse<>(ApiResponseStatus.COMPANY_DOEST_NOT_EXIST);
		}

		File file = helper.convertMultiPartToFile(multipartFile);
		String imageName = helper.generateFileName(multipartFile, identifier, "COMPANY");

		// TODO: UPLOAD FILE TO S3 BUCKET
		helper.uploadFile(file, imageName, "");

		Image image = new Image();
		image.setImageType(ImageType.COMPANY);
		image.setCompany(company);
		image.setImageName(imageName);
		Image savedImage = imageRepo.save(image);

		Set<Image> imageSet = company.getCompanyImages();
		if (CollectionUtils.isEmpty(imageSet)) imageSet = new HashSet<>();
		imageSet.add(savedImage);
		company.setCompanyImages(imageSet);
		companyRepo.save(company);

		return new ApiResponse<>(ApiResponseStatus.SUCCESS, "The Image is uploaded successfully");

	}

	private ApiResponse<String> uploadCategoryImageAndReturnUrl(MultipartFile multipartFile, String identifier)
			throws Exception {

		Category category = categoryRepo.getOne(identifier);
		if (category == null) {
			return new ApiResponse<>(ApiResponseStatus.CATEGORY_DOES_NOT_EXIST);
		}

		File file = helper.convertMultiPartToFile(multipartFile);
		String imageName = helper.generateFileName(multipartFile, identifier, "CATEGORY");

		// TODO: UPLOAD FILE TO S3 BUCKET
		helper.uploadFile(file, imageName, "");

		Image image = new Image();
		image.setImageType(ImageType.COMPANY);
		image.setCategory(category);
		image.setImageName(imageName);
		Image savedImage = imageRepo.save(image);

		Set<Image> imageSet = category.getCategoryImages();
		if (CollectionUtils.isEmpty(imageSet)) imageSet = new HashSet<>();
		imageSet.add(savedImage);
		category.setCategoryImages(imageSet);
		categoryRepo.save(category);

		return new ApiResponse<>(ApiResponseStatus.SUCCESS, "The Image is uploaded successfully");
	}
}
