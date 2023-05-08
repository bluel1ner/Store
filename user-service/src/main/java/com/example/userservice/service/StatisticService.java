package com.example.userservice.service;

import com.example.userservice.dto.response.ProfitByProductTypeRequest;

import java.util.List;

/**
 * @author Neevels
 * @version 1.0
 * @date 5/8/2023 9:09 PM
 */
public interface StatisticService {
    List<ProfitByProductTypeRequest> getProfitByProductType();
}
