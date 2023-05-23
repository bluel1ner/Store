package com.example.userservice.dto.mapper;

import com.example.userservice.dto.response.CardResponse;
import com.example.userservice.entity.Card;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class CardMapper {
    public CardResponse toResponseDto(Card card) {
        return CardResponse.builder()
                .id(card.getId())
                .validityDate(card.getValidityDate())
                .number(card.getNumber())
                .owner(card.getOwner())
                .status(card.getStatus())
                .build();
    }
}
