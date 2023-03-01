package com.example.userservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "card")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String number;
    private String validityDate;
    private String owner;
    private String cvv;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


}
