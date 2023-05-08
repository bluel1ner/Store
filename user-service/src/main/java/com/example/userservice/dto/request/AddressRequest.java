package com.example.userservice.dto.request;

import lombok.*;

/**
 * @author Neevels
 * @version 1.0
 * @date 5/6/2023 3:14 PM
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddressRequest {
    private Integer id;
    private String country;
    private String state;
    private String city;
    private String street;
    private String house;
    private String apartment;

}
