package com.example.productsanalysis.service;

import com.example.productsanalysis.controller.SubscriberController;
import com.example.productsanalysis.dto.SubscribeDto;
import com.example.productsanalysis.entities.Product;
import com.example.productsanalysis.entities.Subscriber;
import com.example.productsanalysis.entities.UserDetails;
import com.example.productsanalysis.repository.ProductRepository;
import com.example.productsanalysis.repository.SubscriberRepository;
import com.example.productsanalysis.repository.UserDetailsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SubscriberServiceTest {

    @Mock
    UserDetailsRepository userDetailsRepository;

    @Mock
    ProductRepository productRepository;

    @Mock
    SubscriberRepository subscriberRepository;

    @InjectMocks
    SubscriberService subscriberService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void subscribe_whenProductAndUserArePresent() {
        SubscribeDto subscribeDto = new SubscribeDto(1, 1, "MONTHLY");
        UserDetails userDetails = new UserDetails(1,"userName",
                "email", "contact");
        Mockito.when(userDetailsRepository.findById(Mockito.any())).thenReturn(Optional.of(userDetails));
        Mockito.when(productRepository.findById(Mockito.any())).thenReturn(Optional.of(new Product()));
        Mockito.when(subscriberRepository.findByUserAndProduct(Mockito.any(), Mockito.any())).thenReturn(Optional.empty());
        Subscriber subscriber = new Subscriber(1,userDetails, new Product(), LocalDateTime.now(), LocalDateTime.now(), subscribeDto.getType());
        Mockito.when(subscriberRepository.save(Mockito.any())).thenReturn(subscriber);
        assertEquals(subscriber,subscriberService.subscribe(subscribeDto));
    }

    @Test
    void subscribe_ForYearlySubscription() {
        SubscribeDto subscribeDto = new SubscribeDto(1, 1, "YEARLY");
        UserDetails userDetails = new UserDetails(1,"userName",
                "email", "contact");
        Mockito.when(userDetailsRepository.findById(Mockito.any())).thenReturn(Optional.of(userDetails));
        Mockito.when(productRepository.findById(Mockito.any())).thenReturn(Optional.of(new Product()));
        Mockito.when(subscriberRepository.findByUserAndProduct(Mockito.any(), Mockito.any())).thenReturn(Optional.empty());
        Subscriber subscriber = new Subscriber(1,userDetails, new Product(), LocalDateTime.now(), LocalDateTime.now(), subscribeDto.getType());
        Mockito.when(subscriberRepository.save(Mockito.any())).thenReturn(subscriber);
        assertEquals(subscriber,subscriberService.subscribe(subscribeDto));
    }

    @Test
    void subscribe_whenProductIsNotPresent() {
        SubscribeDto subscribeDto = new SubscribeDto(1, 1, "MONTHLY");
        UserDetails userDetails = new UserDetails(1,"userName",
                "email", "contact");
        Mockito.when(userDetailsRepository.findById(Mockito.any())).thenReturn(Optional.of(userDetails));
        Mockito.when(productRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class,()->subscriberService.subscribe(subscribeDto));
    }

    @Test
    void subscribe_whenUserIsNotPresent() {
        SubscribeDto subscribeDto = new SubscribeDto(1, 1, "MONTHLY");
        Mockito.when(userDetailsRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        Mockito.when(productRepository.findById(Mockito.any())).thenReturn(Optional.of(new Product()));
        assertThrows(RuntimeException.class,()->subscriberService.subscribe(subscribeDto));
    }

    @Test
    void subscribe_whenAlreadySubscribed() {
        SubscribeDto subscribeDto = new SubscribeDto(1, 1, "MONTHLY");
        UserDetails userDetails = new UserDetails(1,"userName",
                "email", "contact");
        Mockito.when(userDetailsRepository.findById(Mockito.any())).thenReturn(Optional.of(userDetails));
        Mockito.when(productRepository.findById(Mockito.any())).thenReturn(Optional.of(new Product()));
        Mockito.when(subscriberRepository.findByUserAndProduct(Mockito.any(), Mockito.any())).thenReturn(Optional.of(new Subscriber()));
        assertThrows(RuntimeException.class,()->subscriberService.subscribe(subscribeDto));
    }

    @Test
    void totalSubscribers() {
        Mockito.when(subscriberService.totalSubscribers(Mockito.any())).thenReturn(2);
        assertEquals(2, subscriberService.totalSubscribers(1));
    }
}