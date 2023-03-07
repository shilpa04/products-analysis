package com.example.productsanalysis.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscribeDto {
    private Integer userId;
    private Integer productId;
    private String type; //MONTHLY, YEARLY
}
