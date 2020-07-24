package com.example.demo.repository.cart;

import com.example.demo.model.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepo extends JpaRepository<Cart, String> {

}
