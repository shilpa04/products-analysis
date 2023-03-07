package com.example.productsanalysis.service;

import com.example.productsanalysis.dto.SubscribeDto;
import com.example.productsanalysis.entities.Product;
import com.example.productsanalysis.entities.Subscriber;
import com.example.productsanalysis.entities.UserDetails;
import com.example.productsanalysis.repository.ProductRepository;
import com.example.productsanalysis.repository.SubscriberRepository;
import com.example.productsanalysis.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SubscriberService {

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SubscriberRepository subscriberRepository;
    public Subscriber subscribe(SubscribeDto subscribeDto) {
        Optional<UserDetails> userDetails = userDetailsRepository.findById(subscribeDto.getUserId());
        Optional<Product> product = productRepository.findById(subscribeDto.getProductId());
        if(userDetails.isEmpty() || product.isEmpty())
            throw new RuntimeException("Invalid user or product");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime validTill;
        if("MONTHLY".equalsIgnoreCase(subscribeDto.getType()))
            validTill = now.plusMonths(1);
        else
            validTill = now.plusYears(1);
        Optional<Subscriber> existingSubscriber = subscriberRepository.findByUserAndProduct(userDetails.get(), product.get());
        if(existingSubscriber.isPresent() && (existingSubscriber.get().getValidTill().isAfter(validTill)
                                        || existingSubscriber.get().getValidTill().isEqual(validTill)))
            throw new RuntimeException("Already Subscribed");
        Subscriber subscriber = new Subscriber(userDetails, product, now, validTill, subscribeDto.getType());
        return subscriberRepository.save(subscriber);
    }

    public Integer totalSubscribers(Integer productId) {
        return subscriberRepository.fetchTotalSubscribers(productId);
    }
}
