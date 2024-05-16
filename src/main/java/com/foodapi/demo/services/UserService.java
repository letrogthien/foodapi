package com.foodapi.demo.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.foodapi.demo.models.Role;
import com.foodapi.demo.models.User;
import com.foodapi.demo.repositories.RoleRepository;
import com.foodapi.demo.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;



    public Optional<User> getUserByUserName(String name){
        return userRepository.findByUsername(name);
    }
    public Optional<User> getUserById(Integer id){
        return userRepository.findById(id);
    }
    public Optional<User> getUserByUserNameOrEmail(String userNameOrEmail){
        return userRepository.findByUsernameOrEmail(userNameOrEmail,userNameOrEmail);
    }
    public boolean exitUserName(String userName){
        return userRepository.existsByUsername(userName);
    }
    public boolean exitEmail(String email){
        return userRepository.existsByEmail(email);
    }
    public User saveUser(User user){
        return userRepository.save(user);
    }
    
    public void defaultRole(User user){
        Set<Role> roles= new HashSet<>();
        Role role = roleRepository.findByName("USER").orElseThrow(()->new RuntimeException("role not fount"));
        roles.add(role);
        user.setRoles(roles);
    }
    public Integer getIdByUserName(String userName){
        User user = getUserByUserNameOrEmail(userName).orElseThrow();
        return user.getId();

    }

   

}
