package com.example.silver.service.impl;

import com.example.silver.common.Commons;
import com.example.silver.constant.Constants;
import com.example.silver.entity.ProductDetailEntity;
import com.example.silver.entity.ProductEntity;
import com.example.silver.model.PriceModel;
import com.example.silver.model.ProductResponse;
import com.example.silver.model.QuantityModel;
import com.example.silver.repository.*;
import com.example.silver.service.ProductService;
import com.example.silver.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductDetailRepository productDetailRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    MaterialRepository materialRepository;
    @Autowired
    StatusProductRepository statusProductRepository;
    @Autowired
    StorageService storageService;

    @Override
    public String createProductImage(MultipartFile file,Integer productId) throws IOException {
        storageService.uploadImageProduct(file, productId);
        return Constants.SUCCESS;
    }

    @Override
    public String createOrUpdateProduct(ProductEntity product) throws IOException {
        product.setLastUpdate(new Date());
        if(product.getProductId() != null){
            Optional<ProductEntity> productEntityById = productRepository.findByProductId(product.getProductId());
            if(productEntityById.isPresent()){
                productEntityById.get().setProductCategory(product.getProductCategory());
                productEntityById.get().setProductName(product.getProductName());
                productEntityById.get().setProductMaterial(product.getProductMaterial());
                productEntityById.get().setProductStatus(product.getProductStatus());
                productEntityById.get().setLastUpdate(product.getLastUpdate());
                return Constants.UPDATE_SUCCESS;
            }
        }
//        Optional<ProductEntity> productEntity = productRepository.findByProductName(product.getProductName());
//        if(productEntity.isPresent()){
//            productEntity.get().setProductName(product.getProductName());
//            productEntity.get().setProductCategory(product.getProductCategory());
//            productEntity.get().setProductMaterial(product.getProductMaterial());
//            productEntity.get().setProductStatus(product.getProductStatus());
//            productEntity.get().setLastUpdate(product.getLastUpdate());
//            return Constants.UPDATE_SUCCESS;
//        }
        product.setCreated(new Date());
        ProductEntity productSave = productRepository.save(product);
        return Constants.SUCCESS;
    }

    @Override
    public String createOrUpdateProductDetail(ProductDetailEntity productDetailEntity) {
        Optional<ProductDetailEntity> productDetail = productDetailRepository.findByProductIdAndProductSize(productDetailEntity.getProductId(),productDetailEntity.getProductSize());
        if(productDetail.isPresent()){
            productDetail.get().setProductPrice(productDetailEntity.getProductPrice());
            productDetail.get().setProductQuantity(productDetailEntity.getProductQuantity());
            productDetail.get().setProductSales(productDetailEntity.getProductSales());
            productDetailRepository.save(productDetail.get());
            ProductEntity productEntity = updateQuantityAndSale(productDetailEntity.getProductId());
            productRepository.save(productEntity);
            return Constants.UPDATE_SUCCESS;
        }
        productDetailRepository.save(productDetailEntity);
        ProductEntity productEntity = updateQuantityAndSale(productDetailEntity.getProductId());
        productRepository.save(productEntity);
        return Constants.SUCCESS;
    }

    public ProductEntity updateQuantityAndSale(Integer productId){
        ProductEntity productEntity = productRepository.findByProductId(productId).get();
        List<ProductDetailEntity> productDetailEntityList = productDetailRepository.findAllByProductId(productEntity.getProductId());
        Integer totalQuanty = 0;
        Integer totalSale = 0;
        for (ProductDetailEntity detailEntity : productDetailEntityList) {
            totalSale += detailEntity.getProductSales();
            totalQuanty += detailEntity.getProductQuantity();
        }
        productEntity.setTotalQuantity(totalQuanty);
        productEntity.setTotalSales(totalSale);
        productEntity.setLastUpdate(new Date());
        return productEntity;

    }

    @Override
    public String addProductCart(Integer productId, Integer quantity, String productSize) {
        ProductDetailEntity productDetail = productDetailRepository.findByProductIdAndProductSize(productId,productSize).get();
        productDetail.setProductSales(productDetail.getProductSales() + quantity);
        productDetailRepository.save(productDetail);
        ProductEntity productEntity = updateQuantityAndSale(productId);
        productRepository.save(productEntity);
        return Constants.UPDATE_SUCCESS;
    }

    @Override
    public List<ProductResponse> filterProduct(String category, String sortPrice, String sortLastUpdate, String material) {
        List<ProductEntity> productEntities = filterProductEntity(category, sortPrice, sortLastUpdate, material);
        List<ProductResponse> productResponses = new ArrayList<>();
        for (ProductEntity productEntity : productEntities) {
            List<ProductDetailEntity> productDetailEntityList = productDetailRepository.findAllByProductId(productEntity.getProductId());
            productResponses.add(ProductResponse.builder()
                            .productId(productEntity.getProductId())
                            .productName(productEntity.getProductName())
                            .category(productEntity.getProductCategory())
                            .productPrice(productEntity.getProductPrice())
                            .description(productEntity.getProductDescription())
                            .linkImage("http://localhost:8088/api/product/image/"+productEntity.getProductId())
                            .priceList(setPriceModel(productDetailEntityList))
                            .quantityList(setQuantityModel(productDetailEntityList))
                    .build());
        }
        return productResponses;
    }

    private PriceModel setPriceModel(List<ProductDetailEntity> productDetailEntityList){
        PriceModel priceModel = new PriceModel();
        productDetailEntityList.forEach(element -> {
            if (element.getProductSize().equals(Constants.SIZE_7)){
                priceModel.setPriceSeven(element.getProductPrice());
            }else if (element.getProductSize().equals(Constants.SIZE_8)){
                priceModel.setPriceEight(element.getProductPrice());
            }else if (element.getProductSize().equals(Constants.SIZE_9)){
                priceModel.setPriceNight(element.getProductPrice());
            }else if (element.getProductSize().equals(Constants.SIZE_10)){
                priceModel.setPriceTen(element.getProductPrice());
            }
        });
        return priceModel;
    }
    private QuantityModel setQuantityModel(List<ProductDetailEntity> productDetailEntityList){
        QuantityModel quantityModel = new QuantityModel();
        productDetailEntityList.forEach(element -> {
            if (element.getProductSize().equals(Constants.SIZE_7)){
                quantityModel.setQtySeven(element.getProductQuantity() - element.getProductSales());
            }else if (element.getProductSize().equals(Constants.SIZE_8)){
                quantityModel.setQtyEight(element.getProductQuantity() - element.getProductSales());
            }else if (element.getProductSize().equals(Constants.SIZE_9)){
                quantityModel.setQtyNight(element.getProductQuantity() - element.getProductSales());
            }else if (element.getProductSize().equals(Constants.SIZE_10)){
                quantityModel.setQtyTen(element.getProductQuantity() - element.getProductSales());
            }
        });
        return quantityModel;
    }


    private List<ProductEntity> filterProductEntity(String category, String sortPrice, String sortLastUpdate, String material) {
        List<ProductEntity> productEntities = productRepository.findAll();
        if(!Commons.isNullOrEmpty(sortLastUpdate) && sortLastUpdate.equals(Constants.ASCENDING)){
            productEntities = productRepository.productAscendingByLastUpdate();
        }
        if(!Commons.isNullOrEmpty(sortLastUpdate) && sortLastUpdate.equals(Constants.DECREASE)){
            productEntities = productRepository.productDecreaseByLastUpdate();
        }
        if(!Commons.isNullOrEmpty(sortPrice) && sortPrice.equals(Constants.ASCENDING)){
            productEntities.sort((Comparator.comparingLong(ProductEntity::getProductPrice)));
        }
        if(!Commons.isNullOrEmpty(sortPrice) && sortPrice.equals(Constants.DECREASE)){
            productEntities.sort((Comparator.comparingLong(ProductEntity::getProductPrice)).reversed());
        }
        if(!Commons.isNullOrEmpty(category)){
            productEntities = productEntities.stream().filter(element -> element.getProductCategory().equals(category)).toList();
        }
        if(!Commons.isNullOrEmpty(material)){
            productEntities = productEntities.stream().filter(element -> element.getProductMaterial().equals(material)).toList();
        }
        return productEntities;
    }
}
