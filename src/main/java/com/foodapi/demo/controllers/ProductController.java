package com.foodapi.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodapi.demo.models.Product;
import com.foodapi.demo.models.Shop;
import com.foodapi.demo.models.User;
import com.foodapi.demo.models.DTO.ProductDto;
import com.foodapi.demo.services.AuthenticationService;
import com.foodapi.demo.services.CategoryService;
import com.foodapi.demo.services.FlashSaleService;
import com.foodapi.demo.services.OrderItemService;
import com.foodapi.demo.services.ProductService;
import com.foodapi.demo.services.ShopService;
import com.foodapi.demo.services.UploadService;
import com.foodapi.demo.services.UserService;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    UserService userService;

    @Autowired
    ShopService shopService;

    @Autowired
    UploadService uploadService;

    @Autowired
    FlashSaleService flashSaleService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    CategoryService categoryService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllProduct() {
        return new ResponseEntity<>(productService.convertListProductToDTO(productService.getAllProducts()),
                HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<?> getProductById(@RequestParam(required = false) Integer id) {
        Product product = productService.getProductById(id).orElseThrow();
        ProductDto productDto = productService.convertProductToDTO(product);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @GetMapping("/category")
    public ResponseEntity<?> getListProductByCategoryId(@RequestParam(required = false) int categoryId) {
        List<Product> products = productService.getProductByCategoryId(categoryId);
        List<ProductDto> productDto = productService.convertListProductToDTO(products);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> adeleteProductById(@RequestParam(required = false) Integer id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByUserNameOrEmail(authentication.getName()).orElseThrow();
        Shop shop = shopService.getShopByUser(user).orElseThrow();
        Product product = productService.getProductById(id).orElseThrow();
        if (product.getShop().getId() != shop.getId()) {
            return new ResponseEntity<>("ban deo phai chu", HttpStatus.BAD_REQUEST);
        }
        productService.deleteProductById(id);
        return new ResponseEntity<>("delete success", HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestBody ProductDto productDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByUserNameOrEmail(authentication.getName()).orElseThrow();
        Shop shop = shopService.getShopByUser(user).orElseThrow();
        Product product = productService.getProductById(productDto.getId()).orElseThrow();
        if (product.getShop().getId() != shop.getId()) {
            return new ResponseEntity<>("not you", HttpStatus.UNAUTHORIZED);
        }
        Product product1 = productService.updateProduct(productDto.getName(), productDto.getPrice(), categoryService.getCategoryById(productDto.getCategoryId()).orElseThrow(),
        productDto.getDescription(),shopService.getShopById(productDto.getShopId()).orElseThrow(),productDto.getId());
        
        return new ResponseEntity<>("ok",HttpStatus.OK);
    }
    

    @GetMapping("/search")
    public ResponseEntity<?> searchProductByName(@RequestParam(required = false) String name) {

        return new ResponseEntity<>(productService.convertListProductToDTO(productService.searchProductByName(name)),
                HttpStatus.OK);
    }

    @GetMapping("/searchprice")
    public ResponseEntity<?> seaerchByNameAndPrice(@RequestParam String name, @RequestParam BigDecimal firstprice, @RequestParam BigDecimal endprice) {
        return new ResponseEntity<>(productService.getByNameAndPricebetwen(name, firstprice, endprice), HttpStatus.OK);
    }
    

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestParam String name, @RequestParam String desc,
            @RequestParam BigDecimal price, @RequestParam Integer categoryId,
            @RequestParam("file") MultipartFile file) {
        try {
            User user = authenticationService.authenticationUser();
            Shop shop = shopService.getShopByUser(user).orElseThrow();
            ProductDto productDto = new ProductDto();
            productDto.setCategoryId(categoryId);
            productDto.setDescription(desc);
            productDto.setName(name);
            productDto.setPrice(price);
            productDto.setShopId(shop.getId());
            if (file != null) {
                String ok = uploadService.uploadImageService(file);
                productDto.setImg(ok);
            }
            productService.addProduct(productDto);
            return new ResponseEntity<>("add success", HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }



    @GetMapping("/bestsale")
    public ResponseEntity<?> getAllBestSale() {
        return new ResponseEntity<>(productService.convertListProductToDTO(orderItemService.getListProductId()),
                HttpStatus.OK);
    }

    @GetMapping("/flashsale")
    public ResponseEntity<?> getAllFlashSale() {
        return new ResponseEntity<>(flashSaleService.getAllProductSale(), HttpStatus.OK);
    }

    @GetMapping("/flashsale/shop")
    public ResponseEntity<?> getProductSaleOfShop(@RequestParam Integer shopId) {

        return new ResponseEntity<>(flashSaleService.getAllProductSaleOfShop(shopId), HttpStatus.OK);
    }
    @GetMapping("/category/shop")
    public ResponseEntity<?> getMethodName(@RequestParam Integer shopId, @RequestParam Integer categoryId) {
        return new ResponseEntity<>(productService.getProductByCategoryByShopId(categoryId, shopId), HttpStatus.OK);
    }
    @GetMapping("/shop")
    public ResponseEntity<?> getProductOfShopEntity(@RequestParam Integer shopId) {
        return new ResponseEntity<>(productService.getProductByShopId(shopId), HttpStatus.OK);
    }
    
    

}
