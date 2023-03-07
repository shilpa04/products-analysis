package com.example.productsanalysis.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subscriber {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private UserDetails user;
    @ManyToOne
    private Product product;
    private LocalDateTime dateOfSubscription;
    private LocalDateTime validTill;

    private String type;

    public Subscriber(Optional<UserDetails> userDetails, Optional<Product> product, LocalDateTime now, LocalDateTime validTill, String type) {
        user = userDetails.get();
        this.product = product.get();
        dateOfSubscription = now;
        this.validTill = validTill;
        this.type = type;
    }
}
