package com.idiots.openapi.controller;


import com.idiots.openapi.dto.UserResponseDto;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Enumeration;

@Controller
public class ViewController {

    @GetMapping("/")
    public String home()
    {
        return "views/index";
    }

    @GetMapping("/signup")
    public String signup() {
        return "views/signup";
    }

    @GetMapping("/signin")
    public String signin(HttpSession session) {
        session.setAttribute("loginUser", "Test");
        return "redirect:/";
//        return "views/signin";
    }

    @GetMapping("/signout")
    public String signout(HttpSession session) {
        session.removeAttribute("loginUser");
        return "redirect:/";
    }
}
