package com.example.productservice.utils;


import com.example.productservice.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

/**
 * @author Neevels
 * @version 1.0
 * @date 4/11/2023 12:18 AM
 */
@Component
public class UserModelListener extends AbstractMongoEventListener<Product> {

    private SequenceGeneratorService sequenceGenerator;

    @Autowired
    public UserModelListener(SequenceGeneratorService sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Product> event) {
        if (event.getSource().getId() < 1) {
            event.getSource().setId(sequenceGenerator.generateSequence(Product.SEQUENCE_PRODUCT));
        }
    }


}