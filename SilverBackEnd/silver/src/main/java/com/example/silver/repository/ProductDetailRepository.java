package com.example.silver.repository;

import com.example.silver.entity.ProductDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetailEntity, Integer> {

    @Query(value = "select u from ProductDetailEntity u where u.productId = ?1 and u.productSize = ?2")
    Optional<ProductDetailEntity> findByProductIdAndProductSize(Integer productId, String size);

    @Query(value = "select u from ProductDetailEntity u where u.productId = ?1")
    List<ProductDetailEntity> findAllByProductId(Integer productId);
}
