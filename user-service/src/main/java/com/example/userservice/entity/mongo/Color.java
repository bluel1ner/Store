package com.example.userservice.entity.mongo;

import lombok.*;

import java.util.List;

/**
 * @author Neevels
 * @version 1.0
 * @date 4/10/2023 1:38 PM
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Color {
    private String name;
    private String value;
    private List<String> photos;
}
