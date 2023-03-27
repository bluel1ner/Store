package com.example.userservice.controller;

import com.example.userservice.dto.response.CardResponse;
import com.example.userservice.entity.Card;
import com.example.userservice.repository.CardRepository;
import com.example.userservice.service.CardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController()
@RequestMapping("/cards")
public class CardController {
    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping
    public ResponseEntity<CardResponse> createCard(@RequestBody Card card) {
        return ResponseEntity.ok(cardService.createCard(card));
    }


    @PutMapping
    public ResponseEntity<CardResponse> updateCard(@RequestBody Card card) {
        return ResponseEntity.ok(cardService.updateCard(card));
    }


    @GetMapping
    public ResponseEntity<List<CardResponse>> getAllByUserId() {
        return ResponseEntity.ok(cardService.getAllByUserId());

    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteCard(@PathVariable Integer id) {
        cardService.deleteById(id);
    }
}
