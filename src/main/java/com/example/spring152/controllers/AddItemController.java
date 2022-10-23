package com.example.spring152.controllers;

import com.example.spring152.models.ItemModel;
import com.example.spring152.models.enums.ItemEnum;
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
                                @RequestParam String url,
                                @RequestParam String type){
        ItemModel itemModel = new ItemModel();
        itemModel.setDisc(disc);
        itemModel.setName(name);
        itemModel.setUrl(url);
        itemModel.setPrice(price);

        RedirectView redirectView = new RedirectView("/");

        switch (type){
            case "Автомобиль":
                itemModel.setItemEnum(ItemEnum.AUTOS);
                break;
            case "Котик":
                itemModel.setItemEnum(ItemEnum.CATS);
                break;
            case "Бытовая техника":
                itemModel.setItemEnum(ItemEnum.HOMETECHNICS);
                break;
            case "Наркотики":
                itemModel.setItemEnum(ItemEnum.DRUGS);
                break;
            default:
                itemModel.setItemEnum(ItemEnum.OTHER);
                break;
        }

        itemRepo.save(itemModel);
        return redirectView;
    }
}
