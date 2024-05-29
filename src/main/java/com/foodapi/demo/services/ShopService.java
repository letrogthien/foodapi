package com.foodapi.demo.services;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.foodapi.demo.models.Shop;
import com.foodapi.demo.models.User;
import com.foodapi.demo.models.DTO.ShopDto;
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

    public Optional<Shop> getShopByUserId(Integer userId){
        return shopRepository.findByUser_Id(userId);
    }
    public Shop saveShop(Shop shop){
        return shopRepository.save(shop);
    }
    public boolean existsByUser(User user){
        return shopRepository.existsByUser(user);
    }
    public List<Shop> getAllShop(){ 
        return shopRepository.findAll();
    }


    public List<ShopDto> convertListShopToDTO(List<Shop> shops) {
        return shops.stream()
                .map(shop -> new ShopDto(
                        shop.getId(),
                        shop.getName(),
                        shop.getRegisDate(),
                        shop.getUser().getId()))
                .collect(Collectors.toList());
    }

    public ShopDto convertShopToDTO(Shop shop) {
        return new ShopDto(shop.getId(),
                            shop.getName(),
                            shop.getRegisDate(),
                            shop.getUser().getId());
    }
}
