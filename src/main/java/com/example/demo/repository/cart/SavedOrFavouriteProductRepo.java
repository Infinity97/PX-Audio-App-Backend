package com.example.demo.repository.cart;

import com.example.demo.model.cart.SavedOrFavouriteProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavedOrFavouriteProductRepo extends JpaRepository<SavedOrFavouriteProduct, String> {
}
