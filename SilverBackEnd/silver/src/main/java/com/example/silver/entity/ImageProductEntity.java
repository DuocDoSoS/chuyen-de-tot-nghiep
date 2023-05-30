package com.example.silver.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "IMAGE_PRODUCT")
public class ImageProductEntity {
    @Id
    @Column(name = "product_id")
    private Integer productId;
    @Column(name = "image_name")
    private String name;
    @Column(name = "image_type")
    private String type;
    @Lob
    @Column(name = "product_img", length = 1000)
    private byte[] productImg;
}
