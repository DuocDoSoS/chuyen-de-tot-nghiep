package com.example.silver.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER_CART")
public class UserCartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "product_id")
    private Integer productId;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "product_quantity")
    private Integer productQuantity;
    @Column(name = "product_size")
    private String productSize;
    @Column(name = "product_price")
    private Long productPrice;
    @Column(name = "total_price")
    private Long totalPrice;
    @Column(name = "status")
    private String status;
}
