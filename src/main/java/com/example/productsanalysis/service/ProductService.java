package com.example.productsanalysis.service;

import com.example.productsanalysis.dto.ProductDto;
import com.example.productsanalysis.entities.Product;
import com.example.productsanalysis.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Product addProduct(ProductDto productDto) {
        ModelMapper modelMapper = new ModelMapper();
        Product product = modelMapper.map(productDto, Product.class);
        return productRepository.save(product);
    }
}
