package com.example.userservice.service.impl;

import com.example.userservice.dto.mapper.CardMapper;
import com.example.userservice.dto.response.CardResponse;
import com.example.userservice.entity.Card;
import com.example.userservice.entity.User;
import com.example.userservice.exception.type.BusinessException;
import com.example.userservice.repository.CardRepository;
import com.example.userservice.service.CardService;
import com.example.userservice.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final CardMapper cardMapper;
    private final UserUtils userUtils;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository, CardMapper cardMapper, UserUtils userUtils) {
        this.cardRepository = cardRepository;
        this.cardMapper = cardMapper;
        this.userUtils = userUtils;
    }

    @Override
    public CardResponse createCard(Card card) {
        User userById = userUtils.getUser();
        Optional<Card> cardFromDb = cardRepository.findAllByUserId(userById.getId())
                .stream()
                .findFirst();
        card.setStatus(cardFromDb.isEmpty());
        card.setUser(userById);
        return cardMapper.toResponseDto(cardRepository.save(card));
    }

    @Override
    public CardResponse updateCard(Card card) {
        Card getCardFromDb = cardRepository
                .findById(card.getId())
                .orElseThrow(() -> new BusinessException("Card not found", HttpStatus.NOT_FOUND));

        getCardFromDb.setId(card.getId());
        getCardFromDb.setNumber(card.getNumber());
        getCardFromDb.setOwner(card.getOwner());
        getCardFromDb.setValidityDate(card.getValidityDate());

        return cardMapper.toResponseDto(cardRepository.save(getCardFromDb));
    }

    @Override
    public List<CardResponse> getAllByUserId() {
        User userById = userUtils.getUser();
        return cardRepository.findAllByUserId(userById.getId())
                .stream()
                .sorted(Comparator.comparing(Card::getStatus).reversed())
                .map(cardMapper::toResponseDto)
                .toList();
    }

    @Override
    public void deleteById(Integer id) {
        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new BusinessException(String.format("Card with id: %d not found", id), HttpStatus.NOT_FOUND));
        if (Objects.equals(card.getStatus(), true)) {
            cardRepository.deleteById(id);
            cardRepository.findAll()
                    .stream()
                    .findAny()
                    .ifPresent(addressConsumer -> {
                        addressConsumer.setStatus(true);
                        cardRepository.save(addressConsumer);
                    });
        } else {
            cardRepository.deleteById(id);
        }
    }

    @Transactional
    @Override
    public List<CardResponse> changeActiveCard(Integer id) {
        User userById = userUtils.getUser();
        Card firstCard = cardRepository.findAllByUserId(userById.getId())
                .stream()
                .filter(card -> card.getStatus()
                        .equals(true))
                .findFirst()
                .orElseThrow(
                        () -> new BusinessException("Active card doesn't found", HttpStatus.NOT_FOUND));

        firstCard.setStatus(false);

        Card card = cardRepository.findCardByUserIdAndAndId(userById.getId(), id)
                .orElseThrow(
                        () -> new BusinessException(String.format("Card with id %s doesn't found", id), HttpStatus.NOT_FOUND));
        card.setStatus(true);
        cardRepository.save(firstCard);
        cardRepository.save(card);
        return getAllByUserId();
    }

}
