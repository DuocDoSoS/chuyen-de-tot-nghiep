package com.example.silver.repository;

import com.example.silver.entity.UserCartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCartRepository extends JpaRepository<UserCartEntity, Integer> {
}
