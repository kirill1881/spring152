package com.example.spring152.controllers;

import com.example.spring152.models.ItemModel;
import com.example.spring152.repos.ItemRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/allItems")
public class AllItemsController {

    final ItemRepo itemRepo;

    public AllItemsController(ItemRepo itemRepo) {
        this.itemRepo = itemRepo;
    }

    @GetMapping
    public String getPage(Model model){
        List<ItemModel> list = itemRepo.findAll();
        model.addAttribute("items", list);
        list.get(0).getUrl();
        return "allItems";
    }
    @PostMapping
    public String setFilter(@RequestParam int max,
                            @RequestParam int min,
                            Model model){
        List<ItemModel> list = itemRepo.findAll();
        list = list.stream().filter(i -> Integer.parseInt(i.getPrice())>=min&&
                Integer.parseInt(i.getPrice())<=max).collect(Collectors.toList());
        model.addAttribute("items", list);
        return "allItems";
    }
}
