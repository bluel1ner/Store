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
@RequestMapping("/card")
public class CardController {
    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("/create")
    public ResponseEntity<CardResponse> createCard(@RequestBody Card card) {
        return ResponseEntity.ok(cardService.createCard(card));
    }


    @PutMapping("/update")
    public ResponseEntity<CardResponse> updateCard(@RequestBody Card card) {
        return ResponseEntity.ok(cardService.updateCard(card));
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<CardResponse>> getAllByUserId() {
        return ResponseEntity.ok(cardService.getAllByUserId());

    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    public void deleteCard(@PathVariable Integer id) {
        cardService.deleteById(id);
    }
}
