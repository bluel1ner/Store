package com.example.userservice.service.impl;

import com.example.userservice.dto.response.CardResponse;
import com.example.userservice.entity.Card;
import com.example.userservice.entity.User;
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

    public CardServiceImpl(CardRepository cardRepository, UserRepository userRepository) {
        this.cardRepository = cardRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CardResponse createCard(Card card) {
        User userById = getUserById();
        Optional<Card> cardFromDb = cardRepository.findAllByUserId(userById.getId())
                .stream()
                .findFirst();
        //TODO: solve problem using fuctional interface
//        cardFromDb.ifPresentOrElse(() -> card.setStatus(Status.NOT_ACTIVE), () -> card.setStatus(Status.NOT_ACTIVE));
        if (cardFromDb.isEmpty()) {
            card.setStatus(true);
        } else {
            card.setStatus(false);
        }
        card.setUser(userById);
        Card savedCard = cardRepository.save(card);
        return CardResponse.builder()
                .id(savedCard.getId())
                .validityDate(savedCard.getValidityDate())
                .owner(savedCard.getOwner())
                .number(savedCard.getNumber())
                .status(savedCard.getStatus())
                .build();
    }

    @Override
    public CardResponse updateCard(Card card) {
        Card getCardFromDb = cardRepository
                .findById(card.getId())
                .orElseThrow(() -> new BusinessException("User not found", HttpStatus.NOT_FOUND));

        getCardFromDb.setId(card.getId());
        getCardFromDb.setNumber(card.getNumber());
        getCardFromDb.setOwner(card.getOwner());
        getCardFromDb.setValidityDate(card.getValidityDate());
        Card savedCard = cardRepository.save(getCardFromDb);

        return CardResponse.builder()
                .number(savedCard.getNumber())
                .owner(savedCard.getOwner())
                .validityDate(savedCard.getValidityDate())
                .id(savedCard.getId())
                .status(savedCard.getStatus())
                .build();
    }

    @Override
    public List<CardResponse> getAllByUserId() {
        User userById = getUserById();
        return cardRepository.findAllByUserId(userById.getId())
                .stream()
                .map(card -> CardResponse.builder()
                        .id(card.getId())
                        .owner(card.getOwner())
                        .number(card.getNumber())
                        .validityDate(card.getValidityDate())
                        .status(card.getStatus())
                        .build())
                .toList();
    }

    @Override
    public void deleteById(Integer id) {
        cardRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void changeActiveCard(Integer id) {
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
    }


    private User getUserById() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(String.format("User with email: %s not found", email)));
    }
}
