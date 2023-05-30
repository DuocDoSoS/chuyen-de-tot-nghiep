package com.example.silver.repository;

import com.example.silver.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
    @Query("select u from ProductEntity u where u.productName = ?1")
    Optional<ProductEntity> findByProductName(String productName);

    @Query("select u from ProductEntity u where u.productId = ?1")
    Optional<ProductEntity> findByProductId(Integer productId);

    @Query("select u from ProductEntity u order by u.lastUpdate asc")
    List<ProductEntity> productAscendingByLastUpdate();

    @Query("select u from ProductEntity u order by u.lastUpdate desc")
    List<ProductEntity> productDecreaseByLastUpdate();
}
