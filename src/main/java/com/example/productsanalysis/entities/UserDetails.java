package com.example.productsanalysis.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetails {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String userName;
    private String email;
    private String contact;

    public UserDetails(String userName, String email, String contact) {
        this.userName = userName;
        this.email = email;
        this.contact = contact;
    }
}
