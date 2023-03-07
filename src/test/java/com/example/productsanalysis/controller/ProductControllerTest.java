package com.example.productsanalysis.controller;

import com.example.productsanalysis.dto.DownloadRequestDto;
import com.example.productsanalysis.dto.ProductDto;
import com.example.productsanalysis.entities.DownloadDetails;
import com.example.productsanalysis.entities.Product;
import com.example.productsanalysis.service.DownloadDetailsService;
import com.example.productsanalysis.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductControllerTest {

    @Mock
    ProductService productService;

    @InjectMocks
    ProductController productController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void addProduct() {
        Mockito.when(productService.addProduct(Mockito.any())).thenReturn(new Product());
        assertEquals(new Product(),productController.addProduct(new ProductDto()));
    }
}