package com.example.demo.service.web.implementation;

import com.example.demo.apis.request.product.AddProductRequest;
import com.example.demo.apis.request.product.UpdateProductRequest;
import com.example.demo.apis.response.ApiResponse;
import com.example.demo.apis.response.ApiResponseStatus;
import com.example.demo.model.ProductSeller;
import com.example.demo.model.goods.Category;
import com.example.demo.model.goods.Company;
import com.example.demo.model.goods.Product;
import com.example.demo.model.users.Seller;
import com.example.demo.repository.ImageRepo;
import com.example.demo.repository.goods.CategoryRepo;
import com.example.demo.repository.goods.CompanyRepo;
import com.example.demo.repository.goods.ProductSellerRepo;
import com.example.demo.repository.goods.ProductsRepo;
import com.example.demo.repository.users.SellerRepo;
import com.example.demo.repository.users.UsersRepo;
import com.example.demo.service.web.IProductService;
import com.example.demo.service.web.ISellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ProductServiceImplementation implements IProductService {

    @Autowired
    private ProductsRepo productsRepo;

    @Autowired
    private CompanyRepo companyRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private SellerRepo sellerRepo;

    @Autowired
    private ImageRepo imageRepo;

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private ISellerService sellerService;

    @Autowired
    private ProductSellerRepo productSellerRepo;
    // TODO: Adding List of Products through a CSV File.
    @Override
    public ApiResponse<String> addProduct(AddProductRequest productRequest, Company companyRequest) {
        Product product = new Product();
        Company company;
        Category category;

        Seller seller =  sellerService.registerUserAsASeller(productRequest.getSellerId()).getResponseObject();
        if(seller == null)
            return new ApiResponse<>(ApiResponseStatus.USER_NOT_AVAILABLE);

        if(StringUtils.isEmpty(productRequest.getProductName()))
            return new ApiResponse<>(ApiResponseStatus.PRODUCT_NAME_NOT_ENTERED);

        if(productsRepo.findByProductName(productRequest.getProductName())!=null)
            return new ApiResponse<>(ApiResponseStatus.PRODUCT_ALREADY_EXIST);

        product.setProductName(productRequest.getProductName());

        if(!StringUtils.isEmpty(productRequest.getDetailedDescription()))
            product.setDetailedDescription(productRequest.getDetailedDescription());

        if(StringUtils.isEmpty(productRequest.getSingleLineDescription()))
            product.setSingleLineDescription(productRequest.getSingleLineDescription());

        if(productRequest.getMaximumRetailPrice()>0)
            product.setListPrice(productRequest.getMaximumRetailPrice());

        if(companyRequest!=null)
            product.setCompany(companyRequest);
        else{
        if(StringUtils.isEmpty(productRequest.getCompanyId()))
            return new ApiResponse<>(ApiResponseStatus.COMPANY_NOT_ENTERED);

        company = companyRepo.getOne(productRequest.getCompanyId());
            if (company == null)
                return new ApiResponse<>(ApiResponseStatus.COMPANY_DOEST_NOT_EXIST);
            product.setCompany(company);
        }
        if(StringUtils.isEmpty(productRequest.getCategoryId()))
            return new ApiResponse<>(ApiResponseStatus.CATEGORY_NOT_ENTERED);

        category = categoryRepo.getOne(productRequest.getCategoryId());
        if(category == null)
            return new ApiResponse<>(ApiResponseStatus.CATEGORY_DOES_NOT_EXIST);

        product.setCategory(category);
        product = productsRepo.save(product);

        ProductSeller productSeller = new ProductSeller();
        productSeller.setProduct(product);
        productSeller.setSeller(seller);
        productSellerRepo.save(productSeller);

        //TODO: Registering multiple Sellers.
        return new ApiResponse<>(ApiResponseStatus.SUCCESS,"Product has been added successfully");
    }

    //TODO : Creating this method as Async
    @Override
    public ApiResponse<String> addProductOfACompany(List<AddProductRequest> addProductRequests, String companyId){

        if(StringUtils.isEmpty(companyId) || companyRepo.getOne(companyId) == null){
            return new ApiResponse<>(ApiResponseStatus.COMPANY_DOEST_NOT_EXIST);
        }

        if(CollectionUtils.isEmpty(addProductRequests))
            return new ApiResponse<>(ApiResponseStatus.PRODUCT_NAME_NOT_ENTERED);

//        for (AddProductRequest addProductRequest : addProductRequests){
//            addProductRequest.setCompanyId(companyId);
//            if(addProduct(addProductRequest))
//        }

        return null;
    }

    @Override
    public ApiResponse<String> updateProduct(UpdateProductRequest productRequest) {

        Category category;
        Company company;

        if (StringUtils.isEmpty(productRequest.getProductId()))
            return new ApiResponse<>(ApiResponseStatus.PRODUCT_DOES_NOT_EXIST);

        Product product = productsRepo.getOne(productRequest.getProductId());

        if (!StringUtils.isEmpty(productRequest.getProductName()))
            product.setProductName(productRequest.getProductName());

        if (!StringUtils.isEmpty(productRequest.getCategoryId())) {
            category = categoryRepo.getOne(productRequest.getCategoryId());
            if (category == null)
                return new ApiResponse<>(ApiResponseStatus.CATEGORY_DOES_NOT_EXIST);
            product.setCategory(category);
        }

        if (!StringUtils.isEmpty(productRequest.getCompanyId())) {
            company = companyRepo.getOne(productRequest.getCompanyId());
            if (company == null)
                return new ApiResponse<>(ApiResponseStatus.COMPANY_DOEST_NOT_EXIST);
            product.setCompany(company);
        }

        if (!StringUtils.isEmpty(productRequest.getDetailedDescription()))
            product.setDetailedDescription(productRequest.getDetailedDescription());

        if (!StringUtils.isEmpty(productRequest.getSingleLineDescription()))
            product.setSingleLineDescription(productRequest.getSingleLineDescription());

        if (!StringUtils.isEmpty(productRequest.getMaximumRetailPrice()))
            product.setListPrice(productRequest.getMaximumRetailPrice());

//        UPLOADING TO S3 BUCKET
//        if (!CollectionUtils.isEmpty(productRequest.getImages())){
//            List<String> imageList = productRequest.getImages();
//            imageList.forEach(image -> {
//             Image imageTemp = new Image();
//            imageTemp.setImageType(ImageType.PRODUCT);
//            imageTemp.setProduct(product);
//            if(!StringUtils.isEmpty(image)){
//                imageTemp.setImageName(image);
//                imageRepo.save(imageTemp);}
//            });
//        }
        return new ApiResponse<>(ApiResponseStatus.SUCCESS,"Product Details have been succesfully updated!!!");
    }

    @Override
    public ApiResponse<String> deleteProduct(String productId) {
        if(StringUtils.isEmpty(productId))
            return new ApiResponse<>(ApiResponseStatus.PRODUCT_DOES_NOT_EXIST);

        Product product = productsRepo.getOne(productId);
        if(product == null)
            return new ApiResponse<>(ApiResponseStatus.PRODUCT_DOES_NOT_EXIST);

        productsRepo.delete(product);

        return new ApiResponse<>(ApiResponseStatus.SUCCESS,"Product Deleted Successfully");
    }

    @Override
    public ApiResponse<String> getProduct(String productId){
        if(StringUtils.isEmpty(productId))
            return new ApiResponse<>(ApiResponseStatus.PRODUCT_DOES_NOT_EXIST);

        Product product = productsRepo.getOne(productId);
        if(product == null)
            return new ApiResponse<>(ApiResponseStatus.PRODUCT_DOES_NOT_EXIST);


        return null;
    }
}
