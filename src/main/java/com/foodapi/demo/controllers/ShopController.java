package com.foodapi.demo.controllers;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import com.foodapi.demo.models.Role;
import com.foodapi.demo.models.Shop;
import com.foodapi.demo.models.User;
import com.foodapi.demo.models.DTO.ShopDto;
import com.foodapi.demo.repositories.RoleRepository;
import com.foodapi.demo.services.FeedBackService;
import com.foodapi.demo.services.ShopService;
import com.foodapi.demo.services.UserService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/shop")
public class ShopController {
    @Autowired 
    ShopService shopService;
    @Autowired
    UserService userService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    FeedBackService feedBackService;
    
    @PostMapping("/register/{shopName}")
    public ResponseEntity<?> registerShop(@PathVariable String shopName) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByUserNameOrEmail(authentication.getName()).orElseThrow();
        if(shopService.existsByUser(user)){
            String string = "shop of "+ user.getUsername() + " allready";
            return new ResponseEntity<>(string,HttpStatus.NOT_ACCEPTABLE);
        }
        Shop shop= new Shop();
        shop.setName(shopName);
        shop.setRegisDate(new Timestamp(System.currentTimeMillis()));
        shop.setUser(user);
        shopService.saveShop(shop);
        Set<Role> roles = user.getRoles();
        Role role= roleRepository.findByName("SHOPKEEPER").orElseThrow();
        roles.add(role);
        user.setRoles(roles);
        User newUser = userService.saveUser(user);
        
        
        return new ResponseEntity<>(shop,HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<?> getShopById(@RequestParam Integer id) {
        return new ResponseEntity<>(shopService.getShopById(id), HttpStatus.OK);
    }
    

    @GetMapping("/all")
    public ResponseEntity<?> getAllShop() {
        List<ShopDto> shopDtos = shopService.convertListShopToDTO(shopService.getAllShop());
        return new ResponseEntity<>(shopDtos, HttpStatus.OK);
    }

    @GetMapping("/rating/avarage")
    public ResponseEntity<?> ratingAvarage(@RequestParam Integer shopId) {
        return new ResponseEntity<>(feedBackService.averageRating(shopId), HttpStatus.OK);
    }
    

    
    
    
}
