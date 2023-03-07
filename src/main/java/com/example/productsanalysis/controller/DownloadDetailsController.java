package com.example.productsanalysis.controller;

import com.example.productsanalysis.dto.DownloadRequestDto;
import com.example.productsanalysis.entities.DownloadDetails;
import com.example.productsanalysis.service.DownloadDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DownloadDetailsController {

    @Autowired
    DownloadDetailsService downloadDetailsService;

    @PostMapping(path = "/downloadDetails")
    public DownloadDetails addDownloadDetails(@RequestBody DownloadRequestDto downloadRequestDto) {
        return downloadDetailsService.addDownloadDetails(downloadRequestDto);
    }

    @GetMapping(path = "/uniqueDownloads/{productId}")
    public Integer uniqueDownloads(@PathVariable Integer productId) {
        return downloadDetailsService.uniqueDownloads(productId);
    }

    @GetMapping(path = "/volumeDownloads/{productId}")
    public Double volumeDownloads(@PathVariable Integer productId) {
        return downloadDetailsService.volumeDownloads(productId);
    }
}
