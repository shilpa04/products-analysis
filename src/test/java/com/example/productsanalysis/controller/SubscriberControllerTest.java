package com.example.productsanalysis.controller;

import com.example.productsanalysis.dto.SubscribeDto;
import com.example.productsanalysis.entities.Subscriber;
import com.example.productsanalysis.service.SubscriberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SubscriberControllerTest {

    @Mock
    SubscriberService subscriberService;

    @InjectMocks
    SubscriberController subscriberController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void subscribe() {
        Mockito.when(subscriberService.subscribe(Mockito.any())).thenReturn(new Subscriber());
        assertEquals(new Subscriber(),subscriberController.subscribe(new SubscribeDto()));
    }

    @Test
    void totalSubscribers() {
        Mockito.when(subscriberService.totalSubscribers(Mockito.any())).thenReturn(2);
        assertEquals(2, subscriberController.totalSubscribers(1));
    }
}