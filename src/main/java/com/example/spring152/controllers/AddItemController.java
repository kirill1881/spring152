package com.example.spring152.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/addItem")
public class AddItemController {
    @GetMapping
    public String getAddItemPage(){
        return "addItem";
    }
}
