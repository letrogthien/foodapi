package com.foodapi.demo.services;


import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.foodapi.demo.models.User;

@Service
public class CustomUserDetail implements UserDetailsService 
{
    private UserService userService;
    private CustomUserDetail(UserService userService){
        this.userService=userService;
    }

    @Override
    public UserDetails loadUserByUsername(String userNameOrEmail) throws UsernameNotFoundException {
        User user = userService.getUserByUserNameOrEmail(userNameOrEmail).orElseThrow(
            ()->new UsernameNotFoundException(userNameOrEmail)
        );
        Set<GrantedAuthority> authorities= user.getRoles()
                                                .stream()
                                                .map((role)->new SimpleGrantedAuthority(role.getName()))
                                                .collect(Collectors.toSet());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),authorities);
        
    }
    
}
