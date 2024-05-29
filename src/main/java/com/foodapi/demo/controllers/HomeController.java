package com.foodapi.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * HomeController
 */
@Controller
public class HomeController {
    @GetMapping("/")
    public String getMethodName() {
        return "index";
    }
    
    
}