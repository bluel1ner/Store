package com.example.userservice.controller;

import com.example.userservice.dto.ProductDto;
import com.example.userservice.entity.Product;
import com.example.userservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/addProduct")
    public void addProduct(@RequestBody Product product) {
        productService.createProduct(product);
    }

    @GetMapping("/getProduct/{id}")
    public ProductDto getProductById(@PathVariable Integer id) {
        return productService.getCompanyById(id);
    }
}
