package com.example.demo.service.web;

import com.example.demo.apis.request.AddressRequest;
import com.example.demo.model.users.Address;
import com.example.demo.model.users.Users;

public interface IAddressService {
    Address saveAddressToDB(AddressRequest address, Users users, String userId) throws Exception;

}
