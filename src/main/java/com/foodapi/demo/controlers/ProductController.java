package com.foodapi.demo.controlers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodapi.demo.models.Product;
import com.foodapi.demo.models.DTO.ProductDto;
import com.foodapi.demo.services.ProductService;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;
    @GetMapping("/all")
    public ResponseEntity<?> getAllProduct() {
        return new ResponseEntity<>(productService.convertListProductToDTO(productService.getAllProducts()), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Integer id) {
        Product product = productService.getProductById(id).orElseThrow();
        ProductDto productDto = productService.convertProductToDTO(product);
        return new ResponseEntity<>(productDto,HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<?> getListProductByCategoryId(@PathVariable Integer id) {
        List<Product> products = productService.getProductByCategoryId(id);
        List<ProductDto> productDto = productService.convertListProductToDTO(products);
        return new ResponseEntity<>(productDto,HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable Integer id) {
        productService.deleteProductById(id);
        return new ResponseEntity<>("delete success",HttpStatus.OK);
    }
    
    @GetMapping("/search/{name}")
    public ResponseEntity<?> searchProductByName(@PathVariable String name) {
        
        return new ResponseEntity<>(productService.convertListProductToDTO(productService.searchProductByName(name)), HttpStatus.OK);
    }
    
    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody ProductDto productDto) {
        productService.addProduct(productDto);
        return new ResponseEntity<>("add success",HttpStatus.OK);
    }
    

    

    
}
