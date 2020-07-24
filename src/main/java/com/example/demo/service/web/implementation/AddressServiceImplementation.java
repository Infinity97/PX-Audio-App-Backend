package com.example.demo.service.web.implementation;

import com.example.demo.apis.request.AddressRequest;
import com.example.demo.model.users.Address;
import com.example.demo.model.users.Users;
import com.example.demo.repository.users.AddressRepo;
import com.example.demo.repository.users.UsersRepo;
import com.example.demo.service.web.IAddressService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Slf4j
public class AddressServiceImplementation implements IAddressService {

    @Autowired
    AddressRepo addressRepo;

    @Autowired
    UsersRepo usersRepo;

    @Override
    public Address saveAddressToDB(@NonNull AddressRequest address, Users users, String userId) throws Exception {

        Address addressObject = new Address();
        if (users == null) {
            if(StringUtils.isEmpty(userId))
                throw new Exception("User Id is Empty");
            users = usersRepo.getOne(userId);
        }

        addressObject.setUsers(users);

        if (StringUtils.isEmpty(address.getAddress())) {
            throw new Exception("Address is Empty");
        }
        addressObject.setAddress(address.getAddress());

        if (!StringUtils.isEmpty(address.getLatitude())) {
            if (StringUtils.isEmpty(address.getLongitude())) {
                throw new Exception("Longitude is Empty");
            }
            addressObject.setLatitude(address.getLatitude());
            addressObject.setLongitude(address.getLongitude());
        } else if (!StringUtils.isEmpty(address.getLongitude())) {
            throw new Exception("Latitude is Empty");
        }
        log.info("Something has happened here !!!");
        Address addressSavedToDB;
        try{
            addressSavedToDB = addressRepo.save(addressObject);
            log.info("INFO: The Address value availaible is {} ",addressSavedToDB);
        }
        catch (Exception e){
            log.info("INFO: Caught Exception while saving to the repo");
            throw new Exception( " The Exception thrown while saving", e);
        };
        return addressSavedToDB;
    }
}
