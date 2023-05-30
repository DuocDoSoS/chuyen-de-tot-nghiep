package com.example.silver.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PRODUCT_DETAIL")
public class ProductDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "product_id")
    private Integer productId;
    @Column(name = "product_sales")
    private Integer productSales = 0;
    @Column(name = "product_quantity")
    private Integer productQuantity = 0;
    @Column(name = "product_price")
    private Long productPrice = 0L;
    @Column(name = "product_size")
    private String productSize;
}
