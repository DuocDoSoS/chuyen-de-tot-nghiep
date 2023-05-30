package com.example.silver.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StorageService {
    String uploadImageProduct(MultipartFile file, Integer productId) throws IOException;
    byte[] downloadImageProduct(Integer productId);
}
