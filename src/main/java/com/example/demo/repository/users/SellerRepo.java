package com.example.demo.repository.users;

import com.example.demo.model.users.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepo extends JpaRepository<Seller, String> {
}
