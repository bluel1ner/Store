package com.example.productservice.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Neevels
 * @version 1.0
 * @date 4/11/2023 12:07 AM
 */
@Document(collection = "database_sequences")
@Getter
@Setter
public class DatabaseSequence {
    @Id
    private String id;
    private long seq;
}
