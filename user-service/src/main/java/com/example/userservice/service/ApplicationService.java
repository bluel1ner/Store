package com.example.userservice.service;

import com.example.userservice.dto.request.ApplicationRequest;
import com.example.userservice.dto.response.ApplicationResponse;
import com.example.userservice.dto.response.UserResponse;

import java.util.List;

/**
 * @author Neevels
 * @version 1.0
 * @date 5/4/2023 2:22 PM
 */
public interface ApplicationService {
    ApplicationResponse addApplication(ApplicationRequest applicationRequest);

    List<ApplicationResponse> getAllApplications();

    void deleteById(Integer id);

    UserResponse updateToUser(Integer id);
}
