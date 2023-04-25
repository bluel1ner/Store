package com.example.userservice.service.impl;

import com.example.userservice.dto.mapper.AddressMapper;
import com.example.userservice.dto.response.AddressResponse;
import com.example.userservice.entity.Address;
import com.example.userservice.entity.User;
import com.example.userservice.exception.type.BusinessException;
import com.example.userservice.exception.type.address.AddressNotFoundException;
import com.example.userservice.repository.AddressRepository;
import com.example.userservice.service.AddressService;
import com.example.userservice.utils.UserUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressMapper addressMapper;
    private final AddressRepository addressRepository;
    private final UserUtils userUtils;

    public AddressServiceImpl(AddressMapper addressMapper, AddressRepository addressRepository, UserUtils userUtils) {
        this.addressMapper = addressMapper;
        this.addressRepository = addressRepository;
        this.userUtils = userUtils;
    }

    @Override
    public AddressResponse addAddress(Address address) {
        User userById = userUtils.getUser();
        Optional<Address> addressFromDb = addressRepository.findAllByUserId(userById.getId())
                .stream()
                .findFirst();
        address.setStatus(addressFromDb.isEmpty());
        address.setUser(userById);
        Address savedEntity = addressRepository.save(address);
        return addressMapper.toResponseDto(savedEntity);
    }

    @Override
    public List<AddressResponse> getAllAddresses() {
        User userById = userUtils.getUser();
        return addressRepository
                .findAllByUserId(userById.getId())
                .stream()
                .sorted(Comparator.comparing(Address::getStatus).reversed())
                .map(addressMapper::toResponseDto)
                .toList();
    }

    @Override
    public AddressResponse editAddress(Address address) {
        Address addressEntity = addressRepository
                .findById(address.getId())
                .orElseThrow(() -> new AddressNotFoundException(String.format("Address with id: %d not found", address.getId())));
        addressEntity.setApartment(address.getApartment());
        addressEntity.setCity(address.getCity());
        addressEntity.setCountry(address.getCountry());
        addressEntity.setHouse(address.getHouse());
        addressEntity.setState(address.getState());
        addressEntity.setApartment(address.getApartment());
        Address save = addressRepository.save(addressEntity);
        return addressMapper.toResponseDto(save);
    }

    @Override
    public void deleteById(Integer id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new AddressNotFoundException(String.format("Address with id: %d not found", id)));
        if (Objects.equals(address.getStatus(), true)) {
            addressRepository.deleteById(id);
            addressRepository.findAll()
                    .stream()
                    .findAny()
                    .ifPresent(addressConsumer -> {
                        addressConsumer.setStatus(true);
                        addressRepository.save(addressConsumer);
                    });
        } else {
            addressRepository.deleteById(id);
        }
    }

    @Transactional
    @Override
    public List<AddressResponse> changeActiveAddress(Integer id) {
        User userById = userUtils.getUser();
        Address first = addressRepository.findAllByUserId(userById.getId())
                .stream()
                .filter(address -> Objects.equals(address.getStatus(), true))
                .findFirst()
                .orElseThrow(
                        () -> new BusinessException("Active address doesn't found", HttpStatus.NOT_FOUND));
        first.setStatus(false);

        Address address = addressRepository.findAddressByUserIdAndAndId(userById.getId(), id)
                .orElseThrow(
                        () -> new BusinessException(String.format("Address with id %s doesn't found", id), HttpStatus.NOT_FOUND));
        address.setStatus(true);
        addressRepository.save(first);
        addressRepository.save(address);
        return getAllAddresses();
    }

}
