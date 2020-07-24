package com.example.demo.repository.cart;

import com.example.demo.model.cart.Cart;
import com.example.demo.model.cart.CartDetail;
import com.example.demo.model.goods.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartDetailRepo extends JpaRepository<CartDetail, String> {

	CartDetail findByCartAndProduct(Cart cart, Product product);

}
