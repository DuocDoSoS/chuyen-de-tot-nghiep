package com.example.silver.api;

import com.example.silver.service.ProductService;
import com.example.silver.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseService {
    @Autowired
    protected StorageService storageService;
    @Autowired
    protected ProductService productService;

}
