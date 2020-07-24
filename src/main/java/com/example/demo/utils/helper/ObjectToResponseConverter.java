package com.example.demo.utils.helper;

import com.example.demo.apis.response.cart.CartDetailResponse;
import com.example.demo.apis.response.cart.DisplayCartResponse;
import com.example.demo.apis.response.company.CompanyResponse;
import com.example.demo.apis.response.product.ProductResponse;
import com.example.demo.model.cart.Cart;
import com.example.demo.model.cart.CartDetail;
import com.example.demo.model.goods.Company;
import com.example.demo.model.goods.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Infinity97
 */
@Component
public class ObjectToResponseConverter {

	/**
	 * Company Service Implementation
	 */

	@Autowired
	private Helper helper;

	public CompanyResponse convertToCompanyResponseFromCompany(Company company) {
		return new CompanyResponse(company.getCompanyId(), company.getCompanyName());
	}

	public List<CompanyResponse> convertToListOfCompanyResponsesFromListOfCompany(Set<Company> companySet){

		List<CompanyResponse> companyResponseList = new ArrayList<>();

		companySet.forEach(company -> {
			CompanyResponse companyResponse = new CompanyResponse();
			companyResponse.setCompanyId(company.getCompanyId());
			companyResponse.setCompanyName(company.getCompanyName());
			companyResponseList.add(companyResponse);
		});

		return companyResponseList;
	}

	/**
	 * Company Controller
	 */

	public DisplayCartResponse convertCartToDisplayCartResponse(Cart cart){
		DisplayCartResponse displayCartResponse = new DisplayCartResponse();

		Set<CartDetail> cartDetails = cart.getCartDetails();
		if(CollectionUtils.isEmpty(cartDetails)) {
			displayCartResponse.setCartDetailResponses(new ArrayList<>());
			return displayCartResponse ;
		}

		List<CartDetailResponse> cartDetailResponses = new ArrayList<>();
		cartDetails.forEach(cartDetail -> {
			CartDetailResponse cartDetailResponse = convertCartDetailToCartDetailResponse(cartDetail);
			cartDetailResponses.add(cartDetailResponse);
		});

		displayCartResponse.setCartDetailResponses(cartDetailResponses);
		return displayCartResponse;
	}

	public CartDetailResponse convertCartDetailToCartDetailResponse(CartDetail cartDetail){

		CartDetailResponse cartDetailResponse = new CartDetailResponse();
		cartDetailResponse.setCartDetailId(cartDetail.getCartDetailId());
		cartDetailResponse.setQuantity(cartDetail.getQuantity());
		cartDetailResponse.setProductResponse(convertProductToProductResponse(cartDetail.getProduct()));
		return cartDetailResponse;

	}

	public ProductResponse convertProductToProductResponse(Product product){
		ProductResponse productResponse = new ProductResponse();

		productResponse.setCompanyName(product.getCompany().getCompanyName());
		productResponse.setNumberOfUsersRated(product.getNumberOfUsersRated());
		productResponse.setProductId(product.getProductId());
		//TODO: Identify Image Url.
		productResponse.setProductImageUrl(helper.getMediaUrl(product.getImage().get(0)));
		productResponse.setProductListPrice(product.getListPrice());
		productResponse.setProductSalePrice(product.getSalesPrice());
		productResponse.setProductMultiLineDescription(product.getDetailedDescription());
		productResponse.setProductSingleLineDescription(product.getSingleLineDescription());
		productResponse.setProductName(product.getProductName());
		productResponse.setRating(product.getRating());
		//TODO: How to set the name of the seller.
		//productResponse.setSellerName();
		return productResponse;
	}

}
