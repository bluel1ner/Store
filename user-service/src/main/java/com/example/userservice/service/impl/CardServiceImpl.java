package com.example.userservice.service.impl;

import com.example.userservice.dto.mapper.CardMapper;
import com.example.userservice.dto.response.CardResponse;
import com.example.userservice.entity.Card;
import com.example.userservice.entity.User;
import com.example.userservice.entity.enums.Status;
import com.example.userservice.exception.type.BusinessException;
import com.example.userservice.exception.type.user.UserNotFoundException;
import com.example.userservice.repository.CardRepository;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.CardService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final UserRepository userRepository;
    private final CardMapper cardMapper;

    public CardServiceImpl(CardRepository cardRepository, UserRepository userRepository, CardMapper cardMapper) {
        this.cardRepository = cardRepository;
        this.userRepository = userRepository;
        this.cardMapper = cardMapper;
    }

    @Override
    public CardResponse createCard(Card card) {
        User userById = getUserById();
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
        User userById = getUserById();
        return cardRepository.findAllByUserId(userById.getId())
                .stream()
                .map(cardMapper::toResponseDto)
                .toList();
    }

    @Override
    public void deleteById(Integer id) {
        cardRepository.deleteById(id);
    }

    @Transactional
    @Override
    public List<CardResponse> changeActiveCard(Integer id) {
        User userById = getUserById();
        Card firstCard = cardRepository.findAllByUserId(userById.getId())
                .stream()
                .filter(card -> card.getStatus().equals(true))
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


    private User getUserById() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(String.format("User with email: %s not found", email)));
    }
}
