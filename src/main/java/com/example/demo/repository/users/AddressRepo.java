package com.example.demo.repository.users;

import com.example.demo.model.users.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<Address, String> {
}
