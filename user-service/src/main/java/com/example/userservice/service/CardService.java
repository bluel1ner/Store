package com.example.userservice.service;

import com.example.userservice.dto.request.CardRequest;
import com.example.userservice.dto.response.CardResponse;
import com.example.userservice.entity.Card;

import java.util.List;

public interface CardService {

    CardResponse createCard(CardRequest card);

    CardResponse updateCard(CardRequest card);

    List<CardResponse> getAllByUserId();

    void deleteById(Integer id);

    List<CardResponse> changeActiveCard(Integer id);
}
