package com.example.userservice.dto.mapper;

import com.example.userservice.dto.response.AddressResponse;
import com.example.userservice.dto.response.CardResponse;
import com.example.userservice.entity.Address;
import com.example.userservice.entity.Card;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Neevels
 * @version 1.0
 * @date 4/9/2023 11:56 AM
 */
@Component
@NoArgsConstructor
public class CardMapper {
    public CardResponse toResponseDto(Card card) {
        return CardResponse.builder()
                .id(card.getId())
                .validityDate(card.getValidityDate())
                .number(card.getOwner())
                .owner(card.getOwner())
                .status(card.getStatus())
                .build();
    }
}
