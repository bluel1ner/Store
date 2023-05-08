package com.example.userservice.service.impl;

import com.example.userservice.dto.mapper.AddressMapper;
import com.example.userservice.dto.request.AddressRequest;
import com.example.userservice.dto.response.AddressResponse;
import com.example.userservice.entity.Address;
import com.example.userservice.entity.User;
import com.example.userservice.exception.type.BusinessException;
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
    public AddressResponse addAddress(AddressRequest addressRequest) {
        User user = userUtils.getUser();
        Optional<Address> addressFromDb = addressRepository
                .findAllByUserId(user.getId())
                .stream()
                .findFirst();
        Address address = addressMapper.updateAddress(addressRequest, null, addressFromDb.isEmpty(), user);
        return addressMapper.toResponseDto(addressRepository.save(address));
    }

    @Override
    public List<AddressResponse> getAllAddresses() {
        User userById = userUtils.getUser();
        return addressRepository
                .findAllByUserId(userById.getId())
                .stream()
                .sorted(Comparator.comparing(Address::getStatus)
                        .reversed())
                .map(addressMapper::toResponseDto)
                .toList();
    }

    @Override
    public AddressResponse editAddress(AddressRequest address) {
        Address addressEntity = addressRepository
                .findById(address.getId())
                .orElseThrow(() -> new BusinessException(String.format("Address with id %d not found", address.getId()),
                        HttpStatus.NOT_FOUND)
                );
        Address updatedAddress = addressMapper.updateAddress(address, addressEntity.getId(), addressEntity.getStatus(), addressEntity.getUser());
        return addressMapper.toResponseDto(addressRepository.save(updatedAddress));
    }

    @Override
    public void deleteById(Integer id) {
        addressRepository.findById(id)
                .ifPresentOrElse(address -> {
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
                        },
                        () -> {
                            throw new BusinessException(String.format("Address with id %d not found", id),
                                    HttpStatus.NOT_FOUND);
                        });
    }

    @Transactional
    @Override
    public List<AddressResponse> changeActiveAddress(Integer id) {
        User user = userUtils.getUser();
        addressRepository.findAllByUserIdAndStatusEquals(user.getId(), true)
                .stream()
                .findFirst()
                .ifPresentOrElse((address) -> {
                            address.setStatus(false);
                            addressRepository.findAddressByUserIdAndAndId(user.getId(), id)
                                    .ifPresentOrElse((addressFromDb) -> {
                                        addressFromDb.setStatus(true);
                                        addressRepository.save(address);
                                        addressRepository.save(addressFromDb);
                                    }, () -> {
                                        throw new BusinessException(String.format("Address with id %s doesn't found", id), HttpStatus.NOT_FOUND);
                                    });
                        }, () -> {
                            throw new BusinessException("Active address doesn't found", HttpStatus.NOT_FOUND);
                        }
                );

        return getAllAddresses();
    }

}
