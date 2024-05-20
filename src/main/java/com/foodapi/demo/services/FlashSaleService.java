package com.foodapi.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.Projection;
import org.springframework.stereotype.Service;

import com.foodapi.demo.models.FlashSale;
import com.foodapi.demo.models.Product;
import com.foodapi.demo.models.QFlashSale;
import com.foodapi.demo.models.DTO.FlashSaleDto;
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

    public List<FlashSaleDto> getAllProductSale() {
        return jpaQueryFactory
                .select(Projections.constructor(FlashSaleDto.class, qFlashSale.id, qFlashSale.product.name,
                qFlashSale.product.description,
                qFlashSale.product.img,
                qFlashSale.product.price,
                qFlashSale.price,
                qFlashSale.product.category.id,
                qFlashSale.product.shop.id))
                .from(qFlashSale)
                .fetch();
    }

    public List<FlashSaleDto> getAllProductSaleOfShop(Integer shopId) {
        return jpaQueryFactory
                .select(Projections.constructor(FlashSaleDto.class, qFlashSale.id, qFlashSale.product.name,
                        qFlashSale.product.description,
                        qFlashSale.product.img,
                        qFlashSale.product.price,
                        qFlashSale.price,
                        qFlashSale.product.category.id,
                        qFlashSale.product.shop.id))
                .from(qFlashSale)
                .where(qFlashSale.product.shop.id.eq(shopId) )
                .fetch();
    }

}
