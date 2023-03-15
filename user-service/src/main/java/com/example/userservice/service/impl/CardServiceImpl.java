package com.example.userservice.service.impl;

import com.example.userservice.dto.response.CardResponse;
import com.example.userservice.entity.Card;
import com.example.userservice.entity.User;
import com.example.userservice.exception.type.user.UserNotFoundException;
import com.example.userservice.repository.CardRepository;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.CardService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

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
        card.setUser(userById);
        Card savedUser = cardRepository.save(card);
        return CardResponse.builder()
                .id(savedUser.getId())
                .validityDate(savedUser.getValidityDate())
                .owner(savedUser.getOwner())
                .number(savedUser.getNumber())
                .build();
    }

    @Override
    public CardResponse updateCard(Card card) {
        Card getCardFromDb = cardRepository
                .findById(card.getId())
                //FIXME: change type of exception
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
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
                .build();
    }

    @Override
    public List<CardResponse> getAllByUserId() {
        User userById = getUserById();
        return cardRepository.findAllByUserId(userById)
                .stream()
                .map(card -> CardResponse.builder()
                        .id(card.getId())
                        .owner(card.getOwner())
                        .number(card.getNumber())
                        .validityDate(card.getValidityDate())
                        .build())
                .toList();
    }

    @Override
    public void deleteById(Integer id) {
        cardRepository.deleteById(id);
    }


    private User getUserById() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(String.format("User with email: %s not found", email)));
    }
}
