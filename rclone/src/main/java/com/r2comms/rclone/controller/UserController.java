package com.r2comms.rclone.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequestMapping({"/user"})
@RequiredArgsConstructor
public class UserController {

    @GetMapping({"list"})
    public String list(Model model) {
        return "index";
    }
   
}
