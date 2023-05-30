package com.example.silver.service.impl;

import com.example.silver.entity.ImageProductEntity;
import com.example.silver.repository.ImageProductRepository;
import com.example.silver.service.StorageService;
import com.example.silver.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class StorageServiceImpl implements StorageService {
    @Autowired
    ImageProductRepository imageProductRepository;
    @Override
    public String uploadImageProduct(MultipartFile file, Integer productId) throws IOException {
        ImageProductEntity image = new ImageProductEntity();
        image.setProductId(productId);
        image.setType(file.getContentType());
        image.setName(file.getOriginalFilename());
        image.setProductImg(ImageUtils.compressImage(file.getBytes()));
        image = imageProductRepository.save(image);
        if(image != null){
            return "file uploaded successfully : " + file.getOriginalFilename();
        }
        return null;
    }

    @Override
    public byte[] downloadImageProduct(Integer productId) {
        Optional<ImageProductEntity> image = imageProductRepository.findById(productId);
        byte[] images = ImageUtils.decompressImage(image.get().getProductImg());
        return images;
    }
}
