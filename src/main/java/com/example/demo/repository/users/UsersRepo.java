package com.example.demo.repository.users;

import com.example.demo.model.users.Users;
import com.example.demo.utils.enums.LoginType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepo extends JpaRepository<Users, String> {

    Users findByMobileNumber(String mobileNumber);
    Users findByEmailId(String emailId);
    Users findByEmailIdAndLoginType(String emailId, LoginType loginType);

}
