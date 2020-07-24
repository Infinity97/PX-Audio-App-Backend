package com.example.demo.service.web.implementation;

import com.example.demo.apis.request.cart.AddToCartRequest;
import com.example.demo.apis.request.cart.RemoveFromCartRequest;
import com.example.demo.apis.response.ApiResponse;
import com.example.demo.apis.response.ApiResponseStatus;
import com.example.demo.apis.response.cart.CartDetailResponse;
import com.example.demo.apis.response.cart.DisplayCartResponse;
import com.example.demo.model.cart.Cart;
import com.example.demo.model.cart.CartDetail;
import com.example.demo.model.goods.Product;
import com.example.demo.model.users.Seller;
import com.example.demo.model.users.Users;
import com.example.demo.repository.cart.CartDetailRepo;
import com.example.demo.repository.cart.CartRepo;
import com.example.demo.repository.goods.ProductsRepo;
import com.example.demo.repository.users.SellerRepo;
import com.example.demo.repository.users.UsersRepo;
import com.example.demo.service.web.ICartService;
import com.example.demo.utils.helper.Helper;
import com.example.demo.utils.helper.ObjectToResponseConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author Infinity97
 */
@Slf4j
@Service
public class CartServiceImplementation implements ICartService{

	@Autowired
	private CartRepo cartRepo;

	@Autowired
	private CartDetailRepo cartDetailRepo;

	@Autowired
	private UsersRepo usersRepo;

	@Autowired
	private ProductsRepo productsRepo;

	@Autowired
	private SellerRepo sellerRepo;

	@Autowired
	private Helper helper;

	@Autowired
	private ObjectToResponseConverter objectToResponseConverter;

	@Override
	public ApiResponse<String> addToCart(AddToCartRequest addToCartRequest) {

		if(StringUtils.isEmpty(addToCartRequest.getUserId()))
			return new ApiResponse<>(ApiResponseStatus.USER_NOT_AVAILABLE);

		Cart cart = cartRepo.getOne(addToCartRequest.getUserId());

		if(cart == null){
			if(usersRepo.getOne(addToCartRequest.getUserId())!=null){
				cart = new Cart();
				cart.setUserId(addToCartRequest.getUserId());
			}else
				return new ApiResponse<>(ApiResponseStatus.USER_NOT_AVAILABLE);
		}

		if(StringUtils.isEmpty(addToCartRequest.getProductId()))
			return new ApiResponse<>(ApiResponseStatus.PRODUCT_NAME_NOT_ENTERED);

		Product product = productsRepo.getOne(addToCartRequest.getProductId());
		if(product == null)
			return new ApiResponse<>(ApiResponseStatus.PRODUCT_DOES_NOT_EXIST);

		Seller seller = new Seller();
		if(!StringUtils.isEmpty(addToCartRequest.getSellerId())){
			seller = sellerRepo.getOne(addToCartRequest.getSellerId());
			if(seller == null)
				return new ApiResponse<>(ApiResponseStatus.SELLER_NOT_AVAILABLE);
		}

		addCartDetailToCart(product,addToCartRequest.getQuantity(),cart,seller);

		return new ApiResponse<>(ApiResponseStatus.SUCCESS, "Added to cart Successfully");
	}

	private CartDetail addCartDetailToCart(Product product, long quantity, Cart cart, Seller seller){

		CartDetail cartDetail = new CartDetail();
		cartDetail.setProduct(product);
		cartDetail.setQuantity(quantity);
		cartDetail.setCart(cart);
		cartDetail.setSeller(seller);

		return cartDetailRepo.save(cartDetail);
	}

	@Override
	public ApiResponse<String> removeFromCart(RemoveFromCartRequest removeFromCartRequest) {

		CartDetail cartDetail;
		if (StringUtils.isEmpty(removeFromCartRequest.getUserId()))
			return new ApiResponse<>(ApiResponseStatus.USER_NOT_AVAILABLE);

		Cart cart = cartRepo.getOne(removeFromCartRequest.getUserId());
		if (cart == null) {
			if (usersRepo.getOne(removeFromCartRequest.getUserId()) == null)
				return new ApiResponse<>(ApiResponseStatus.USER_NOT_AVAILABLE);
			else {
				//cart = helper.linkCartToUser(removeFromCartRequest.getUserId());
				return new ApiResponse<>(ApiResponseStatus.CART_DETAIL_DOES_NOT_EXIST);
			}
		}
		if (StringUtils.isEmpty(removeFromCartRequest.getCartDetailId())){
			if (StringUtils.isEmpty(removeFromCartRequest.getProductId()))
				return new ApiResponse<>(ApiResponseStatus.INCOMPLETE_OR_INCORRECT_REQUEST);
			else {
				cartDetail = findCartDetailByCartAndProduct(cart, removeFromCartRequest.getProductId());
				if (cartDetail == null) {
					return new ApiResponse<>(ApiResponseStatus.CART_DETAIL_DOES_NOT_EXIST);
				}
			}
		}else {
			cartDetail = cartDetailRepo.getOne(removeFromCartRequest.getCartDetailId());
		}
		if(cart.getCartDetails().remove(cartDetail)){
			 cartRepo.save(cart);
			 cartDetailRepo.delete(cartDetail);
		}else{
			return new ApiResponse<>(ApiResponseStatus.CART_DETAIL_NOT_PRESENT_IN_CART);
		}
		//To return display Cart
		return new ApiResponse<>(ApiResponseStatus.SUCCESS);
	}

	private CartDetail findCartDetailByCartAndProduct(Cart cart, String productId){

		if(StringUtils.isEmpty(productId))
			return null;

		Product product = productsRepo.getOne(productId);
		if(product == null) {
			log.error("Product does not exist {}",productId);
			return null;
		}

		return cartDetailRepo.findByCartAndProduct(cart,product);
	}

	@Override
	public ApiResponse<DisplayCartResponse> displayCart(String userId) {

		if(StringUtils.isEmpty(userId))
			return new ApiResponse<>(ApiResponseStatus.USER_NOT_AVAILABLE);

		Cart cart = cartRepo.getOne(userId);
		if(cart == null){
			Users users = usersRepo.getOne(userId);
			if(users == null){
				return new ApiResponse<>(ApiResponseStatus.USER_NOT_AVAILABLE);
			}else{
//				cart = helper.linkCartToUser(userId);
			}
		}

		return new ApiResponse<>(ApiResponseStatus.SUCCESS,
				objectToResponseConverter.convertCartToDisplayCartResponse(cart));
	}

	@Override
	public ApiResponse<CartDetailResponse> updateCartDetail(String userId, String cartDetailId, long quantity) {

		if(StringUtils.isEmpty(userId))
			return new ApiResponse<>(ApiResponseStatus.USER_NOT_AVAILABLE);

		Cart cart = cartRepo.getOne(userId);
		if(cart == null){
			Users users = usersRepo.getOne(userId);
			if(users == null)
				return new ApiResponse<>(ApiResponseStatus.USER_NOT_AVAILABLE);
			else {
//				cart = helper.linkCartToUser(userId);
			}
		}

		if(StringUtils.isEmpty(cartDetailId))
			return new ApiResponse<>(ApiResponseStatus.CART_DETAIL_DOES_NOT_EXIST);

		CartDetail cartDetail = cartDetailRepo.getOne(cartDetailId);
		if(cartDetail == null){
			return new ApiResponse<>(ApiResponseStatus.CART_DETAIL_DOES_NOT_EXIST);
		}

		if(!cart.getCartDetails().contains(cartDetail)){
			return new ApiResponse<>(ApiResponseStatus.CART_DETAIL_NOT_PRESENT_IN_CART);
		}

		if(!helper.checkValidQuantity(quantity)){
			return new ApiResponse<>(ApiResponseStatus.NOT_A_VALID_QUANTITY);
		}

		cartDetail.setQuantity(quantity);
		cartDetail = cartDetailRepo.save(cartDetail);

		return new ApiResponse<>(ApiResponseStatus.SUCCESS,
				objectToResponseConverter.convertCartDetailToCartDetailResponse(cartDetail));

	}
}
