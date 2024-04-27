package com.foodapi.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodapi.demo.models.Shop;
import com.foodapi.demo.models.User;
import com.foodapi.demo.repositories.ShopRepository;

@Service
public class ShopService {
    @Autowired
    ShopRepository shopRepository;

    public Optional<Shop> getShopById(Integer id){
        return shopRepository.findById(id);
    }

    public Optional<Shop> getShopByUser(User user){
        return shopRepository.findByUser(user);
    }
    public Shop saveShop(Shop shop){
        return shopRepository.save(shop);
    }
    public boolean existsByUser(User user){
        return shopRepository.existsByUser(user);
    }
}
