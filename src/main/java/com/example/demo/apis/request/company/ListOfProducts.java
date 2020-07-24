package com.example.demo.apis.request.company;

import com.example.demo.apis.request.product.AddProductRequest;
import lombok.Getter;

import java.util.List;

@Getter
public class ListOfProducts {

    List<AddProductRequest> productRequests;


}
