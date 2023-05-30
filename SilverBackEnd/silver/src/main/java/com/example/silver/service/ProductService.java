package com.example.silver.service;

import com.example.silver.entity.ProductDetailEntity;
import com.example.silver.entity.ProductEntity;
import com.example.silver.model.ProductResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
//    managerProduct
    String createOrUpdateProduct(ProductEntity product) throws IOException;

    String createProductImage(MultipartFile file, Integer productId) throws IOException;

    String createOrUpdateProductDetail(ProductDetailEntity productDetailEntity);
    String addProductCart(Integer productId, Integer quantity, String productSize);
//    filter Product
    List<ProductResponse> filterProduct(String category, String sortPrice, String sortLastUpdate, String material);
}
