package com.example.silver.api;

import com.example.silver.entity.ProductDetailEntity;
import com.example.silver.entity.ProductEntity;
import com.example.silver.model.ProductResponse;
import com.example.silver.model.response.PageModel;
import com.example.silver.model.response.ResponseModel;
import com.example.silver.model.response.ResultModel;
import com.example.silver.util.ResponseUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/product")
public class ProductController extends BaseService{

    @GetMapping("findProduct")
    public ResponseEntity<Object> findProduct(
            @RequestParam(name = "page") Integer page,
            @RequestParam(name = "size") Integer size,
            @RequestParam(name = "category",required = false) String category,
            @RequestParam(name = "material",required = false) String material,
            @RequestParam(name = "sortPrice",required = false) String sortPrice,
            @RequestParam(name = "sortLastUpdate",required = false) String sortLastUpdate
    ){
        try{
            List<ProductResponse> productResponseList = productService.filterProduct(category,sortPrice,sortLastUpdate,material);
            Pageable paging;
            if(page == null || size == null){
                paging = PageRequest.of(0,productResponseList.size());
            }else {
                paging = PageRequest.of(page-1,size);
            }
            final int start = (int)paging.getOffset();
            final int end = Math.min((start + paging.getPageSize()), productResponseList.size());
            Page<ProductResponse> pageTimeEntryMongo= new PageImpl<>(productResponseList.subList(start,end),paging,productResponseList.size());
            ResponseModel response = new ResponseModel();
            response.setStatus(true);
            response.setResult(new ResultModel<>(new PageModel(pageTimeEntryMongo.getSize(),page,pageTimeEntryMongo.getTotalElements(),pageTimeEntryMongo.getTotalPages()),pageTimeEntryMongo.getContent()));
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(ResponseUtils.responseFail(e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/image/{productId}")
    public ResponseEntity<Object> downloadImage(@PathVariable(name = "productId") Integer productId){
        byte[] imageData=storageService.downloadImageProduct(productId);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }

    @PostMapping("/saveOrUpdate")
    public ResponseEntity<Object> saveOrUpdateProduct(@RequestBody ProductEntity product){
        try{
            ResponseModel responseModel = new ResponseModel();
            responseModel.setStatus(true);
            return new ResponseEntity<>(productService.createOrUpdateProduct(product),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/createProductImage")
    public ResponseEntity<Object> createProductImage(@RequestParam("image")MultipartFile file,@RequestParam("productId") Integer productId){
        try{
            ResponseModel responseModel = new ResponseModel();
            responseModel.setStatus(true);
            return new ResponseEntity<>(productService.createProductImage(file,productId),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/saveOrUpdateProductDetail")
    public ResponseEntity<Object> saveOrUpdateProductDetail(@RequestBody ProductDetailEntity product){
        try{
            ResponseModel responseModel = new ResponseModel();
            responseModel.setStatus(true);
            return new ResponseEntity<>(productService.createOrUpdateProductDetail(product),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("addCartProduct")
    public ResponseEntity<Object> addCartProduct(
            @RequestParam("productId") Integer productId,
            @RequestParam("quantity") Integer quantity,
            @RequestParam("productSize") String productSize
    ){
        try{
            ResponseModel responseModel = new ResponseModel();
            responseModel.setStatus(true);
            return new ResponseEntity<>(productService.addProductCart(productId,quantity,productSize),HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
