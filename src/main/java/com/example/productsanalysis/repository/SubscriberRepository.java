package com.example.productsanalysis.repository;

import com.example.productsanalysis.entities.Product;
import com.example.productsanalysis.entities.Subscriber;
import com.example.productsanalysis.entities.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, Integer> {

    Optional<Subscriber> findByUserAndProduct(UserDetails userDetails, Product product);

    @Query(value = "select count(distinct user_id) from subscriber where product_id = :productId and valid_till >= now() group by product_id", nativeQuery = true)
    Integer fetchTotalSubscribers(Integer productId);
}
