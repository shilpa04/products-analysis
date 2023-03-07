package com.example.productsanalysis.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DownloadRequestDto {
    private String userName;
    private String email;
    private String contact;
    private Integer productId;
}
