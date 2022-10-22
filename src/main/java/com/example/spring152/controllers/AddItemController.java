package com.example.spring152.controllers;

import com.example.spring152.models.ItemModel;
import com.example.spring152.repos.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/addItem")
public class AddItemController {

    final ItemRepo itemRepo;
    private String name;

    public AddItemController(ItemRepo itemRepo) {
        this.itemRepo = itemRepo;
    }

    @GetMapping
    public String getAddItemPage(){
        return "addItem";
    }

    @PostMapping
    public RedirectView setData(@RequestParam String name,
                                @RequestParam String price,
                                @RequestParam String disc,
                                @RequestParam String url){
        ItemModel itemModel = new ItemModel();
        itemModel.setDisc(disc);
        itemModel.setName(name);
        itemModel.setUrl(url);
        itemModel.setPrice(price);

        itemRepo.save(itemModel);
        RedirectView redirectView = new RedirectView("/");

        return redirectView;
    }
}
