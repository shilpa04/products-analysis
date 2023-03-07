package com.example.productsanalysis.controller;

import com.example.productsanalysis.dto.DownloadRequestDto;
import com.example.productsanalysis.entities.DownloadDetails;
import com.example.productsanalysis.service.DownloadDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class DownloadDetailsControllerTest {

    @Mock
    DownloadDetailsService downloadDetailsService;

    @InjectMocks
    DownloadDetailsController downloadDetailsController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void addDownloadDetails() {
        Mockito.when(downloadDetailsService.addDownloadDetails(Mockito.any())).thenReturn(new DownloadDetails());
        assertEquals(new DownloadDetails(),downloadDetailsController.addDownloadDetails(new DownloadRequestDto()));
    }

    @Test
    void uniqueDownloads() {
        Mockito.when(downloadDetailsService.uniqueDownloads(Mockito.any())).thenReturn(2);
        assertEquals(2, downloadDetailsController.uniqueDownloads(1));
    }

    @Test
    void volumeDownloads() {
        Mockito.when(downloadDetailsService.volumeDownloads(Mockito.any())).thenReturn(2.0);
        assertEquals(2.0, downloadDetailsController.volumeDownloads(1));
    }
}