package com.example.productsanalysis.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DownloadDetails {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private UserDetails user;
    @ManyToOne
    private Product product;
    private LocalDateTime downloadingDate;

    public DownloadDetails(UserDetails user, Product product, LocalDateTime now) {
        this.user = user;
        this.product = product;
        downloadingDate = now;
    }
}
