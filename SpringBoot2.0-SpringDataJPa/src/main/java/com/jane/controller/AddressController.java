package com.jane.controller;

import com.jane.dao.AddressRepository;
import com.jane.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Janus on 2018/9/10.
 */
@RestController
public class AddressController {

    @Autowired
    AddressRepository addressRepository;

    @GetMapping("/test17")
    public Address test17(){
        Address address = addressRepository.method();
        return address;
    }
}
