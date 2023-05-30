package com.example.silver.repository;

import com.example.silver.entity.CategoryEntity;
import com.example.silver.entity.ProductDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {

}
