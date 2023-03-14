package com.example.userservice.service;

import com.example.userservice.dto.response.CardResponse;
import com.example.userservice.entity.Card;

import java.util.List;

public interface CardService {

    CardResponse createCard(Card card);

    CardResponse updateCard(Card card);

    List<CardResponse> getAllByUserId();

    void deleteById(Integer id);
}
