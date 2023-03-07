package com.example.productsanalysis.service;

import com.example.productsanalysis.dto.DownloadRequestDto;
import com.example.productsanalysis.entities.DownloadDetails;
import com.example.productsanalysis.entities.Product;
import com.example.productsanalysis.entities.UserDetails;
import com.example.productsanalysis.repository.DownloadDetailsRepository;
import com.example.productsanalysis.repository.ProductRepository;
import com.example.productsanalysis.repository.UserDetailsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DownloadDetailsServiceTest {

    @Mock
    ProductRepository productRepository;

    @Mock
    UserDetailsRepository userDetailsRepository;

    @Mock
    DownloadDetailsRepository downloadDetailsRepository;

    @InjectMocks
    DownloadDetailsService downloadDetailsService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void addDownloadDetails_whenProductIsPresent() {
        DownloadRequestDto downloadRequestDto = new DownloadRequestDto("userName", "email@gmail.com", "987654321", 1);
        Mockito.when(productRepository.findById(Mockito.any())).thenReturn(Optional.of(new Product()));
        Mockito.when(userDetailsRepository.findByEmail(Mockito.any())).thenReturn(Optional.empty());
        UserDetails newUserDetails = new UserDetails(downloadRequestDto.getUserName(),
                downloadRequestDto.getEmail(), downloadRequestDto.getContact());
        newUserDetails.setId(1);
        Mockito.when(userDetailsRepository.save(Mockito.any())).thenReturn(newUserDetails);
        DownloadDetails downloadDetails = new DownloadDetails(1, newUserDetails, new Product(), LocalDateTime.now());
        Mockito.when(downloadDetailsRepository.save(Mockito.any())).thenReturn(downloadDetails);
        assertEquals("userName",downloadDetailsService.addDownloadDetails(downloadRequestDto).getUser().getUserName());
    }

    @Test
    void addDownloadDetails_whenProductIsNotPresent() {
        DownloadRequestDto downloadRequestDto = new DownloadRequestDto("userName", "email@gmail.com", "987654321", 1);
        Mockito.when(productRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class,()->downloadDetailsService.addDownloadDetails(downloadRequestDto).getUser().getUserName());
    }

    @Test
    void uniqueDownloads() {
        Mockito.when(downloadDetailsRepository.fetchUniqueDownloads(Mockito.any())).thenReturn(2);
        assertEquals(2, downloadDetailsService.uniqueDownloads(1));
    }

    @Test
    void volumeDownloads() {
        Mockito.when(downloadDetailsRepository.fetchDownloadsPerUser(Mockito.any())).thenReturn(Arrays.asList(1, 2));
        assertEquals(1.5, downloadDetailsService.volumeDownloads(1));
    }
}