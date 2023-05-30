package com.example.silver.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
    private Integer productId;
    private String productName;
    private String category;
    private Long productPrice;
    private String description;
    private String linkImage;
    private PriceModel priceList;
    private QuantityModel quantityList;
}
