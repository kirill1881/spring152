package com.example.spring152.controllers;

import com.example.spring152.repos.ItemRepo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/item")
public class ItemController {
    final ItemRepo itemRepo;

    public ItemController(ItemRepo itemRepo) {
        this.itemRepo = itemRepo;
    }

    @GetMapping("/delete/{id}")
    public RedirectView deleteItem(@PathVariable long id){
        itemRepo.deleteById(id);
        return new RedirectView("/admin/edit");
    }
    @GetMapping("/edit/{id}")
    public String editItem(@PathVariable long id){

        return "edit";
    }
}
