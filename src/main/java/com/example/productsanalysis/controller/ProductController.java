package com.example.productsanalysis.controller;

import com.example.productsanalysis.dto.ProductDto;
import com.example.productsanalysis.entities.Product;
import com.example.productsanalysis.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping(path = "/products")
    public Product addProduct(@RequestBody ProductDto productDto) {
        return productService.addProduct(productDto);
    }
}
