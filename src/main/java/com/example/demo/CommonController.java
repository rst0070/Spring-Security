package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class CommonController {
    
    @GetMapping("home")
    public String homePage(){
        return "home";
    }

    @GetMapping("student")
    public String studentPage(){
        return "student";
    }
}
