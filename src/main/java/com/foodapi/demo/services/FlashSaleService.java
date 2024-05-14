package com.foodapi.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.Projection;
import org.springframework.stereotype.Service;

import com.foodapi.demo.models.FlashSale;
import com.foodapi.demo.models.Product;
import com.foodapi.demo.models.QFlashSale;
import com.foodapi.demo.models.DTO.ProductDto;
import com.foodapi.demo.repositories.FlashSaleRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Service
public class FlashSaleService {
    @Autowired
    private FlashSaleRepository flashSaleRepository;
    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    QFlashSale qFlashSale = QFlashSale.flashSale;

    public List<ProductDto> getAllProductSale() {
        return jpaQueryFactory
                .select(Projections.constructor(ProductDto.class, qFlashSale.product.id, qFlashSale.product.name,
                        qFlashSale.product.description,
                        qFlashSale.product.img,
                        qFlashSale.product.price,
                        qFlashSale.product.category.id,
                        qFlashSale.product.shop.id))
                .from(qFlashSale)
                .fetch();
    }

}
