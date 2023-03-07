package com.example.productsanalysis.service;

import com.example.productsanalysis.controller.ProductController;
import com.example.productsanalysis.dto.ProductDto;
import com.example.productsanalysis.entities.Product;
import com.example.productsanalysis.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void addProduct() {
        ProductDto productDto = new ProductDto("name", "type");
        ModelMapper modelMapper = new ModelMapper();
        Product product = modelMapper.map(productDto, Product.class);
        Mockito.when(productRepository.save(Mockito.any())).thenReturn(product);
        assertEquals("name",productService.addProduct(productDto).getName());
    }
}