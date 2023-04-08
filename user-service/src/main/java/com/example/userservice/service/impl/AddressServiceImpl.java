package com.example.userservice.service.impl;

import com.example.userservice.dto.response.AddressResponse;
import com.example.userservice.entity.Address;
import com.example.userservice.entity.User;
import com.example.userservice.entity.enums.Status;
import com.example.userservice.exception.type.BusinessException;
import com.example.userservice.exception.type.address.AddressNotFoundException;
import com.example.userservice.repository.AddressRepository;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public AddressServiceImpl(AddressRepository addressRepository, UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    @Override
    public AddressResponse addAddress(Address address) {
        User userById = getUserById();
        Optional<Address> addressFromDb = addressRepository.findAllByUserId(userById.getId())
                .stream()
                .findFirst();
        if (addressFromDb.isPresent()) {
            address.setStatus(Status.NOT_ACTIVE);
        } else {
            address.setStatus(Status.ACTIVE);
        }
        address.setUser(userById);
        Address savedEntity = addressRepository.save(address);
        return AddressResponse.builder()
                .id(savedEntity.getId())
                .street(savedEntity.getStreet())
                .country(savedEntity.getCountry())
                .house(savedEntity.getHouse())
                .city(savedEntity.getCity())
                .apartment(savedEntity.getApartment())
                .state(savedEntity.getState())
                .status(address.getStatus())

                .build();
    }

    @Override
    public List<AddressResponse> getAllAddresses() {
        User userById = getUserById();
        return addressRepository
                .findAllByUserId(userById.getId())
                .stream()
                .map(address ->
                        AddressResponse.builder()
                                .id(address.getId())
                                .street(address.getStreet())
                                .country(address.getCountry())
                                .house(address.getHouse())
                                .city(address.getCity())
                                .apartment(address.getApartment())
                                .state(address.getState())
                                .status(address.getStatus())
                                .build()
                ).toList();
    }

    @Override
    public AddressResponse editAddress(Address address) {
        Address getAddressFromDb = addressRepository
                .findById(address.getId())
                .orElseThrow(() -> new AddressNotFoundException(String.format("Address with id: %d not found", address.getId())));
        getAddressFromDb.setApartment(address.getApartment());
        getAddressFromDb.setCity(address.getCity());
        getAddressFromDb.setCountry(address.getCountry());
        getAddressFromDb.setHouse(address.getHouse());
        getAddressFromDb.setState(address.getState());
        getAddressFromDb.setApartment(address.getApartment());
        Address save = addressRepository.save(getAddressFromDb);

        return AddressResponse.builder()
                .country(save.getCountry())
                .house(save.getHouse())
                .city(save.getCity())
                .street(save.getStreet())
                .state(save.getState())
                .apartment(save.getApartment())
                .status(address.getStatus())
                .build();
    }

    @Override
    public void deleteById(Integer id) {
        addressRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void changeActiveAddress(Integer id) {
        User userById = getUserById();
        Address first = addressRepository.findAllByUserId(userById.getId())
                .stream()
                .filter(address -> address.getStatus().equals(Status.ACTIVE))
                .findFirst()
                .orElseThrow(
                        () -> new BusinessException("Active address doesn't found", HttpStatus.NOT_FOUND));
        first.setStatus(Status.NOT_ACTIVE);

        Address address = addressRepository.findAddressByUserIdAndAndId(userById.getId(), id)
                .orElseThrow(
                        () -> new BusinessException(String.format("Address with id %s doesn't found", id), HttpStatus.NOT_FOUND));
        address.setStatus(Status.ACTIVE);

        addressRepository.save(first);
        addressRepository.save(address);

    }


    private User getUserById() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
