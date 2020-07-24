package com.example.demo.repository.users;

import com.example.demo.model.users.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepo extends JpaRepository<Review, String> {

}
