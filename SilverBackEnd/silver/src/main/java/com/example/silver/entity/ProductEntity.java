package com.example.silver.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PRODUCT")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "product_category")
    private String productCategory;
    @Column(name = "material")
    private String productMaterial;
    @Column(name = "total_quantity")
    private Integer totalQuantity = 0;
    @Lob
    @Column(name = "product_description", columnDefinition = "TEXT")
    private String productDescription;
    @Column(name = "total_sales")
    private Integer totalSales = 0;
    @Column(name = "product_price")
    private Long productPrice = 0L;
    @Column(name = "product_status")
    private String productStatus;
    @Column(name = "created")
    private Date created;
    @Column(name = "last_update")
    private Date lastUpdate;
}
