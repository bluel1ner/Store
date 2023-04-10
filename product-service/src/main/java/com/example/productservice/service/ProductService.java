package com.example.productservice.service;

import com.example.productservice.entity.Product;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Neevels
 * @version 1.0
 * @date 4/10/2023 4:26 PM
 */
public interface ProductService {


    Product addProduct();

}
