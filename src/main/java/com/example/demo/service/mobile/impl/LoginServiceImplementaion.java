package com.example.demo.service.mobile.impl;

import com.example.demo.apis.request.login.ForgotPasswordRequest;
import com.example.demo.apis.request.login.RegisterNewUserRequest;
import com.example.demo.apis.request.login.UserDetailsRequest;
import com.example.demo.apis.response.ApiResponse;
import com.example.demo.apis.response.ApiResponseStatus;
import com.example.demo.apis.response.login.LoginOrSignUpResponse;
import com.example.demo.model.users.Address;
import com.example.demo.model.users.Users;
import com.example.demo.repository.cart.CartRepo;
import com.example.demo.repository.users.UsersRepo;
import com.example.demo.service.mobile.ILoginService;
import com.example.demo.service.web.IAddressService;
import com.example.demo.utils.enums.LoginType;
import com.example.demo.utils.enums.UserType;
import com.example.demo.utils.helper.Helper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;


@Slf4j
@Service
public class LoginServiceImplementaion implements ILoginService {

	@Autowired
	private UsersRepo usersRepo;

	@Autowired
	private IAddressService addressService;

	@Autowired
	private Helper helper;

	@Autowired
	private CartRepo cartRepo;

//	@Autowired
//	private CompanyRepo companyRepo;
//
//	@Autowired
//	private CategoryRepo categoryRepo;
//
//	@Autowired
//	private ProductsRepo productsRepo;
//
//	@Autowired
//	private SavedOrFavouriteProductRepo savedOrFavouriteProductRepo;

	private UserType userType;

	// SignUp
	@Override
	public ApiResponse<LoginOrSignUpResponse> registerNewUser(RegisterNewUserRequest registerNewUserRequest) {

		String mobileNumber = registerNewUserRequest.getMobileNumber();
		if (StringUtils.isEmpty(mobileNumber)) {
			return new ApiResponse<>(ApiResponseStatus.MOBILE_NUMBER_NOT_ENTERED);
		}

		Users users = usersRepo.findByMobileNumber(mobileNumber);

		if (users != null) {
			return new ApiResponse<>(ApiResponseStatus.USER_ALREADY_REGISTERED);
		}

		users = new Users();
		users.setMobileNumber(mobileNumber);

		String password = registerNewUserRequest.getPassword();
		if (!checkIsPasswordValid(password)) return new ApiResponse<>(ApiResponseStatus.INVALID_PASSWORD);

		users.setPassword(helper.convertToHashPassword(password));

		if (!StringUtils.isEmpty(registerNewUserRequest.getEmailId())) {
			users.setEmailId(registerNewUserRequest.getEmailId());
		}

		if (!StringUtils.isEmpty(registerNewUserRequest.getName())) {
			users.setName(registerNewUserRequest.getName());
		}

		if (!StringUtils.isEmpty(registerNewUserRequest.getUserId()))
			users.setUserId(registerNewUserRequest.getUserId());

//        if (!StringUtils.isEmpty(registerNewUserRequest.getImageUrl()))
//            users.setImageUrl(registerNewUserRequest.getImageUrl());

		if (!StringUtils.isEmpty(registerNewUserRequest.getLoginType()))
			users.setLoginType(LoginType.valueOf(registerNewUserRequest.getLoginType().toUpperCase()).name());
		else users.setLoginType(LoginType.NATIVE.name());

		if (!StringUtils.isEmpty(registerNewUserRequest.getUserType())){
			UserType userType = UserType.valueOf(registerNewUserRequest.getUserType().toUpperCase());
			users.setUserType(userType.name());

		}

		users = usersRepo.save(users);

//		Category category = new Category();
//		category.setCategoryName("sdsdf");
//		category = categoryRepo.save(category);
//
//		Company company = new Company();
//		company.setCompanyName("sfsdf");
//		company.setCompanyDetails("sdfsdf");
//		company = companyRepo.save(company);
//
//		Product product = new Product();
//		product.setDetailedDescription("sdfsdf");
//		product.setCompany(company);
//		product.setCategory(category);
//		product = productsRepo.save(product);
//
//		SavedOrFavouriteProduct savedOrFavouriteProduct = new SavedOrFavouriteProduct();
//		savedOrFavouriteProduct.setUsers(users);
//		savedOrFavouriteProduct.setName("sdfsdfsdf");
//
//		savedOrFavouriteProductRepo.save(savedOrFavouriteProduct);
//		System.out.println(savedOrFavouriteProduct);

		helper.linkCartToUser(users);
		return new ApiResponse<>(ApiResponseStatus.SUCCESS, convertUserRequestToResponse(users));
	}

	private boolean checkIsPasswordValid(String password) {

		if (StringUtils.isEmpty(password))
			return false;
		if (password.length() <= 5)
			return false;
		return true;
	}

	@Override
	public ApiResponse<String> forgotPassword(ForgotPasswordRequest forgotPasswordRequest) {
		Users users;
		if (!StringUtils.isEmpty(forgotPasswordRequest.getMobileNumber())) {
			users = usersRepo.findByMobileNumber(forgotPasswordRequest.getMobileNumber());
			if (users == null) {
				return new ApiResponse<>(ApiResponseStatus.USER_NOT_AVAILABLE);
			}
		} else {
			return new ApiResponse<>(ApiResponseStatus.MOBILE_NUMBER_NOT_ENTERED);
		}

		if (!StringUtils.isEmpty(forgotPasswordRequest.getNewPassword())) {
			return new ApiResponse<>(ApiResponseStatus.INVALID_PASSWORD);
		}

		users.setPassword(helper.convertToHashPassword(forgotPasswordRequest.getNewPassword()));
		usersRepo.save(users);
		return new ApiResponse<>(ApiResponseStatus.SUCCESS);

	}

	@Override
	public ApiResponse<String> updateUserDetails(UserDetailsRequest userDetailsRequest) throws Exception {

		if (StringUtils.isEmpty(userDetailsRequest.getUserId()))
			return new ApiResponse<>(ApiResponseStatus.USER_NOT_AVAILABLE);
		Users users = usersRepo.getOne(userDetailsRequest.getUserId());
		if (users == null) return new ApiResponse<>(ApiResponseStatus.USER_NOT_AVAILABLE);

		if (!StringUtils.isEmpty(userDetailsRequest.getUserName())) users.setName(userDetailsRequest.getUserName());

		log.info("Now entering into putting address related informations.");

		Address address = new Address();
		if (userDetailsRequest.getAddressRequest() != null) {
			address = addressService.saveAddressToDB(userDetailsRequest.getAddressRequest(), users, "");
		}

		if (CollectionUtils.isEmpty(users.getAddress())) {
			users.setAddress(new HashSet<>(users.getAddress()));
		} else {
			Set<Address> addressSet;
			addressSet = users.getAddress();
			addressSet.add(address);
			users.setAddress(addressSet);
		}

		usersRepo.save(users);
		return new ApiResponse<>(ApiResponseStatus.SUCCESS, "");
	}

	@Override
	public ApiResponse<LoginOrSignUpResponse> loginOrSignUp(RegisterNewUserRequest registerNewUserRequest) {

		LoginType loginType;
		ApiResponse<LoginOrSignUpResponse> apiResponse;
		if (StringUtils.isEmpty(registerNewUserRequest.getLoginType())) loginType = LoginType.SKIP;
		else {
			loginType = LoginType.valueOf(registerNewUserRequest.getLoginType().toUpperCase());
		}
		switch (loginType) {
			case GOOGLE:
				apiResponse = loginOrSignUpThroughThirdParty(registerNewUserRequest, LoginType.GOOGLE);
				break;
			case FACEBOOK:
				apiResponse = loginOrSignUpThroughThirdParty(registerNewUserRequest, LoginType.FACEBOOK);
				break;
			case NATIVE:
				apiResponse = loginOrSignUpThroughNative(registerNewUserRequest);
				break;
			default:
				apiResponse = loginOrSignUpThroughSkip(registerNewUserRequest);
				break;
		}
		return apiResponse;
	}

	private ApiResponse<LoginOrSignUpResponse> loginOrSignUpThroughSkip(RegisterNewUserRequest registerNewUserRequest) {
		// TODO: Login Through Skip. Assign a userId and a dummy username once they login then change the name to the
		//  one neeeded.
		return null;
	}

	private ApiResponse<LoginOrSignUpResponse> loginOrSignUpThroughNative(RegisterNewUserRequest userRequest) {
		if (StringUtils.isEmpty(userRequest.getMobileNumber()))
			return new ApiResponse<>(ApiResponseStatus.MOBILE_NUMBER_NOT_ENTERED);
		Users users = usersRepo.findByMobileNumber(userRequest.getMobileNumber());
		if (users == null) return registerNewUser(userRequest);

		if (StringUtils.isEmpty(userRequest.getPassword()))
			return new ApiResponse<>(ApiResponseStatus.INVALID_PASSWORD);

		if (!helper.checkPassword(userRequest.getPassword(), users.getPassword()))
			return new ApiResponse<>(ApiResponseStatus.INCORRECT_PASSWORD);

		return new ApiResponse<>(ApiResponseStatus.SUCCESS);
	}

	private ApiResponse<LoginOrSignUpResponse> loginOrSignUpThroughThirdParty(RegisterNewUserRequest userRequest,
	                                                                          LoginType loginType) {
		if(StringUtils.isEmpty(userRequest.getEmailId()))
			return new ApiResponse<>(ApiResponseStatus.EMAIL_NOT_PRESENT);
		Users users = usersRepo.findByEmailId(userRequest.getEmailId());

		if (users == null) {
			return new ApiResponse<>(ApiResponseStatus.USER_NOT_AVAILABLE);
		}

		if (StringUtils.isEmpty(users.getMobileNumber())) {
			return new ApiResponse<>(ApiResponseStatus.MOBILE_NUMBER_NOT_ENTERED);
		}

		if (StringUtils.isEmpty(users.getLoginType()))
			return new ApiResponse<>(ApiResponseStatus.LOGIN_TYPE_NOT_MENTIONED);

		if (users.getLoginType().equalsIgnoreCase(loginType.name())) {
			users.setLoginType(loginType.name());
			users = usersRepo.save(users);
		}
		return new ApiResponse<>(ApiResponseStatus.SUCCESS, convertUserRequestToResponse(users));
	}

	private LoginOrSignUpResponse convertUserRequestToResponse(Users users) {

		LoginOrSignUpResponse loginOrSignUpResponse = new LoginOrSignUpResponse();
		loginOrSignUpResponse.setUserId(users.getUserId());
		//loginOrSignUpResponse.setImageUrl(users.getImageUrl());
		loginOrSignUpResponse.setMobileNumber(users.getMobileNumber());
		loginOrSignUpResponse.setName(users.getName());

		return loginOrSignUpResponse;
	}
}
