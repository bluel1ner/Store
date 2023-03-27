package com.example.userservice.controller;

import com.example.userservice.dto.response.AddressResponse;
import com.example.userservice.entity.Address;
import com.example.userservice.repository.AddressRepository;
import com.example.userservice.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Neevels
 * @version 1.0
 * @date 3/8/2023 12:49 PM
 */
@RestController()
@RequestMapping("/addresses")
public class AddressController {

    private final AddressRepository addressRepository;
    private final AddressService addressService;

    public AddressController(AddressRepository addressRepository, AddressService addressService) {
        this.addressRepository = addressRepository;
        this.addressService = addressService;
    }

    @PostMapping
    public ResponseEntity<AddressResponse> createAddress(@RequestBody Address address) {
        return ResponseEntity.ok(addressService.addAddress(address));
    }

    @PutMapping
    public ResponseEntity<AddressResponse> editAddress(@RequestBody Address address) {
        addressService.editAddress(address);
        return ResponseEntity.status(200).body(addressService.editAddress(address));
    }

    @GetMapping
    public ResponseEntity<List<AddressResponse>> getAllAddresses() {
        return ResponseEntity.ok(addressService.getAllAddresses());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteAddress(@PathVariable Integer id) {
        addressService.deleteById(id);
    }


}
