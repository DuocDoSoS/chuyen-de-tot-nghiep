package com.example.silver.repository;

import com.example.silver.entity.StatusProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusProductRepository extends JpaRepository<StatusProductEntity, Integer> {
}
