package com.example.demo.repository.goods;

import com.example.demo.model.goods.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepo extends JpaRepository<Company, String> {

    Company findByCompanyName(String companyName);


}
