package com.example.spring152.controllers;

import com.example.spring152.models.ItemModel;
import com.example.spring152.repos.ItemRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/detailItem")
public class DetailItemController {

    final ItemRepo itemRepo;

    public DetailItemController(ItemRepo itemRepo) {
        this.itemRepo = itemRepo;
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable long id,
                         Model model){
        ItemModel itemModel = itemRepo.findById(id);
        model.addAttribute("item", itemModel);
        return "detail";
    }
}
