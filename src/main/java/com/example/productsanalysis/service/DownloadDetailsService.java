package com.example.productsanalysis.service;

import com.example.productsanalysis.dto.DownloadRequestDto;
import com.example.productsanalysis.entities.DownloadDetails;
import com.example.productsanalysis.entities.Product;
import com.example.productsanalysis.entities.UserDetails;
import com.example.productsanalysis.repository.DownloadDetailsRepository;
import com.example.productsanalysis.repository.ProductRepository;
import com.example.productsanalysis.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DownloadDetailsService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Autowired
    DownloadDetailsRepository downloadDetailsRepository;

    public DownloadDetails addDownloadDetails(DownloadRequestDto downloadRequestDto) {
        Optional<Product> product = productRepository.findById(downloadRequestDto.getProductId());
        if(product.isEmpty())
            throw new RuntimeException("Invalid Product");
        Optional<UserDetails> userDetails = userDetailsRepository.findByEmail(downloadRequestDto.getEmail());
        if(userDetails.isEmpty()) {
            UserDetails newUserDetails = new UserDetails(downloadRequestDto.getUserName(),
                    downloadRequestDto.getEmail(), downloadRequestDto.getContact());
            userDetails = Optional.of(userDetailsRepository.save(newUserDetails));
        }
        DownloadDetails downloadDetails = new DownloadDetails(userDetails.get(), product.get(), LocalDateTime.now());
        return downloadDetailsRepository.save(downloadDetails);
    }

    public Integer uniqueDownloads(Integer productId) {
        return downloadDetailsRepository.fetchUniqueDownloads(productId);
    }

    public Double volumeDownloads(Integer productId) {
        List<Integer> downloadsPerUser = downloadDetailsRepository.fetchDownloadsPerUser(productId);
        return (double)downloadsPerUser.stream().reduce(0, Integer::sum)/downloadsPerUser.size();
    }
}
