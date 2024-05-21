package com.idiots.openapi.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/")
    public String home() {
        return "views/index";
    }

    @GetMapping("/signup")
    public String signup() {
        return "views/signup";
    }
}
