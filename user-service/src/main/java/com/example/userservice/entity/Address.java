package com.example.userservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "address")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String country;
    private String state;
    private String city;
    private String street;
    private String house;
    private String apartment;


    @JsonIgnore
    @OneToOne()
    @JoinColumn(name = "user_id")
    private User user;

}
