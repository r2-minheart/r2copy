package com.r2comms.r2copy.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Log4j2
@Controller
@RequiredArgsConstructor
public class HomeController {

    @GetMapping({"/"})
    public String backend(Model model) {
        return "/index";
    }
    
    
    @GetMapping({"/login"})
    public String login(Model model) {
        return "/login";
    }
   
   
}
