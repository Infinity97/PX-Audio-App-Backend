package com.example.demo.repository.goods;

import com.example.demo.model.goods.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepo extends JpaRepository<Product,String> {
    Product findByProductName(String productName);
}
