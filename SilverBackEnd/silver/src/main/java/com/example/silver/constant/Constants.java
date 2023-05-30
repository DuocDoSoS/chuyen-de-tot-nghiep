package com.example.silver.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

public class Constants {
    public static final String STOCKING = "stocking";
    public static final String OUT_OF_STOCK = "out of stock";
    public static final String DISCOUNT = "discount";

    public static final String METAL = "metal";
    public static final String SILVER = "silver";
    public static final String GOLD = "gold";

    public static final String RING = "ring";
    public static final String NECKLACE = "necklace";
    public static final String EARDROP = "eardrop";

    public static final String SIZE_7 = "Size 7";
    public static final String SIZE_8 = "Size 8";
    public static final String SIZE_9 = "Size 9";
    public static final String SIZE_10 = "Size 10";

    public static final String SUCCESS = "Create success!";
    public static final String UPDATE_SUCCESS = "Update success!";
    public static final String FALSE = "Create false!";
    public static final String UPDATE_FALSE = "Update false!";

    public static final String ASCENDING = "asc";
    public static final String DECREASE = "desc";

    @Value("${product.image.link}")
    public static String linkOriginImage;

}
