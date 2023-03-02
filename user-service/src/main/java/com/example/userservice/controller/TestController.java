package com.example.userservice.controller;

import com.example.userservice.entity.Address;
import com.example.userservice.entity.User;
import com.example.userservice.repository.AddressRepository;
import com.example.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController()
@RequestMapping("/test")
public class TestController {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    @Autowired
    public TestController(UserRepository userRepository, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }


    @GetMapping("/getInfo")
    public User getInfo() {
        User byId = userRepository.findById(1);
        return null;
    }

    @PostMapping("/createUser")
    public String createUser(@RequestBody User user) {
        userRepository.save(user);
        return "Ok";
    }

    @PostMapping("/addAddress")
    public String addAddress(@RequestBody Address address) {
        User byId = userRepository.findById(1l).orElse(null);
        address.setUser(byId);
        addressRepository.save(address);
        return "Ok";
    }


}
