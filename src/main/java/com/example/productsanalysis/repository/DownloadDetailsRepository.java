package com.example.productsanalysis.repository;

import com.example.productsanalysis.entities.DownloadDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DownloadDetailsRepository extends JpaRepository<DownloadDetails, Integer> {
    @Query(value = "select count(distinct user_id) from download_details where product_id=:productId group by product_id", nativeQuery = true)
    Integer fetchUniqueDownloads(Integer productId);

    @Query(value = "select count(*) from download_details where product_id=:productId group by product_id, user_id", nativeQuery = true)
    List<Integer> fetchDownloadsPerUser(Integer productId);
}
