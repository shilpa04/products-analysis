package com.example.productsanalysis.controller;

import com.example.productsanalysis.dto.SubscribeDto;
import com.example.productsanalysis.entities.Subscriber;
import com.example.productsanalysis.service.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SubscriberController {

    @Autowired
    SubscriberService subscriberService;

    @PostMapping(path = "/subscribe")
    public Subscriber subscribe(@RequestBody SubscribeDto subscribeDto) {
        return subscriberService.subscribe(subscribeDto);
    }

    @GetMapping(path = "/subscribers/{productId}")
    public Integer totalSubscribers(@PathVariable Integer productId) {
        return subscriberService.totalSubscribers(productId);
    }
}
