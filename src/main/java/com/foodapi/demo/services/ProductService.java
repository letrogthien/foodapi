package com.foodapi.demo.services;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.Projection;
import org.springframework.stereotype.Service;

import com.foodapi.demo.models.Category;
import com.foodapi.demo.models.Product;
import com.foodapi.demo.models.QProduct;
import com.foodapi.demo.models.DTO.ProductDto;

import com.foodapi.demo.repositories.ProductRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ShopService shopService;

    @Autowired
    private JPAQueryFactory queryFactory;

    QProduct qProduct = QProduct.product;

    public List<Product> getAllProducts() {
        return productRepository.findAll();

    }

    public List<Product> searchProductByName(String name) {
        return productRepository.searchByNameContaining(name);

    }

    public List<Product> getProductByCategoryId(Integer id) {
        return productRepository.findByCategory_Id(id);

    }

    public Optional<Product> getProductById(Integer id) {
        return productRepository.findById(id);
    }

    public void deleteProductById(Integer id) {
        productRepository.findById(id).orElseThrow();
        productRepository.deleteById(id);
    }

    public void addProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDesciption());
        product.setImg(productDto.getImg());
        product.setPrice(productDto.getPrice());
        product.setCreateAt(new Timestamp(System.currentTimeMillis()));
        product.setUpdateAt(null);
        product.setCategory(categoryService.getCategoryById(productDto.getCategoryId()).orElseThrow());
        product.setShop(shopService.getShopById(productDto.getShopId()).orElseThrow());
        productRepository.save(product);
    }

    public List<ProductDto> convertListProductToDTO(List<Product> products) {
        return products.stream()
                .map(product -> new ProductDto(
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getImg(),
                        product.getPrice(),
                        product.getCategory().getId(),
                        product.getShop().getId()))
                .collect(Collectors.toList());
    }

    public ProductDto convertProductToDTO(Product product) {
        return new ProductDto(product.getId(),
                product.getName(),
                product.getDescription(),
                product.getImg(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getShop().getId());
    }

    public List<ProductDto> getProductWithCategoryLike(String category) {

        BooleanExpression producthascategories = qProduct.category.name.contains(category);
        return convertListProductToDTO((List<Product>) productRepository.findAll(producthascategories));
    }

    public List<Category> getCategoryByShopId(Integer shopId) {
        return queryFactory.select(qProduct.category)
                .distinct()
                .from(qProduct)
                .where(qProduct.shop.id.eq(shopId))
                .fetch();

    }

    public List<ProductDto> getProductByCategoryByShopId(Integer categoryId, Integer shopId) {
        return queryFactory
                .select(Projections.constructor(ProductDto.class, qProduct.id, qProduct.name, qProduct.description,
                        qProduct.img, qProduct.price, qProduct.category.id, qProduct.shop.id))
                .from(qProduct).where(qProduct.shop.id.eq(shopId), qProduct.category.id.eq(categoryId)).fetch();
    }

}
