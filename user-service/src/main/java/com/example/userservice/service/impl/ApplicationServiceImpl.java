package com.example.userservice.service.impl;

import com.example.userservice.dto.mapper.ApplicationMapper;
import com.example.userservice.dto.mapper.UserMapper;
import com.example.userservice.dto.request.ApplicationRequest;
import com.example.userservice.dto.response.ApplicationResponse;
import com.example.userservice.dto.response.UserResponse;
import com.example.userservice.entity.Application;
import com.example.userservice.exception.type.BusinessException;
import com.example.userservice.repository.ApplicationRepository;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.ApplicationService;
import com.example.userservice.utils.MailSender;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final ApplicationMapper applicationMapper;
    private final MailSender mailSender;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository,
                                  UserRepository userRepository,
                                  ApplicationMapper applicationMapper,
                                  MailSender mailSender,
                                  PasswordEncoder passwordEncoder,
                                  UserMapper userMapper
    ) {
        this.applicationRepository = applicationRepository;
        this.userRepository = userRepository;
        this.applicationMapper = applicationMapper;
        this.mailSender = mailSender;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @Override
    public ApplicationResponse addApplication(ApplicationRequest applicationRequest) {
        if (userRepository.findByEmail(applicationRequest.getEmail())
                .isEmpty()) {
            return applicationMapper
                    .toResponseDto(applicationRepository
                            .save(applicationMapper.toApplication(applicationRequest)));
        } else {
            throw new BusinessException(String.format("User with email %s already exist", applicationRequest.getEmail()),
                    HttpStatus.FORBIDDEN);
        }
    }

    @Override
    public List<ApplicationResponse> getAllApplications() {
        List<Application> applications = applicationRepository.findAll();
        Collections.reverse(applications);
        return applications
                .stream()
                .map(applicationMapper::toResponseDto)
                .toList();
    }

    @Override
    public void deleteById(Integer id) {
        applicationRepository.deleteById(id);
    }

    @Override
    public UserResponse updateToUser(Integer id) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Application not found", HttpStatus.NOT_FOUND));
        var password = UUID.randomUUID().toString();
        mailSender.send(application.getEmail(), password);
        var user = userRepository.save(userMapper.toUser(application, password));
        applicationRepository.deleteById(id);
        return userMapper.toResponseDto(user);
    }

}
