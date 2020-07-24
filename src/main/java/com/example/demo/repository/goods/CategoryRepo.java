package com.example.demo.repository.goods;

import com.example.demo.model.goods.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, String> {

    Category findByCategoryName(String categoryName);

}
