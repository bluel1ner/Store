package com.example.userservice.dto.response;

import com.example.userservice.entity.enums.ProductType;
import lombok.Builder;

/**
 * @author Neevels
 * @version 1.0
 * @date 5/8/2023 9:10 PM
 */
@Builder
public record AmountStatisticResponse(
        ProductType key,
        Integer value
) {

}
