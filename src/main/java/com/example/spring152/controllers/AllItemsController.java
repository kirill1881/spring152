package com.example.spring152.controllers;

import com.example.spring152.models.ItemModel;
import com.example.spring152.models.enums.ItemEnum;
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
    @GetMapping("/cars")
    public String getCars(Model model){
        List<ItemModel> list = itemRepo.findAll();
        list = list.stream().filter(i ->
                i.getItemEnum().equals(ItemEnum.AUTOS)).collect(Collectors.toList());
        model.addAttribute("items", list);
        return "allItems";
    }
    @GetMapping("/cats")
    public String getCats(Model model){
        List<ItemModel> list = itemRepo.findAll();
        list = list.stream().filter(i ->
                i.getItemEnum().equals(ItemEnum.CATS)).collect(Collectors.toList());
        model.addAttribute("items", list);
        return "allItems";
    }
    @GetMapping("/drugs")
    public String getDrugs(Model model){
        List<ItemModel> list = itemRepo.findAll();
        list = list.stream().filter(i ->
                i.getItemEnum().equals(ItemEnum.DRUGS)).collect(Collectors.toList());
        model.addAttribute("items", list);
        return "allItems";
    }
    @GetMapping("/hometechniks")
    public String getHome(Model model){
        List<ItemModel> list = itemRepo.findAll();
        list = list.stream().filter(i ->
                i.getItemEnum().equals(ItemEnum.HOMETECHNICS)).collect(Collectors.toList());
        model.addAttribute("items", list);
        return "allItems";
    }
    @GetMapping("/other")
    public String getOther(Model model){
        List<ItemModel> list = itemRepo.findAll();
        list = list.stream().filter(i ->
                i.getItemEnum().equals(ItemEnum.OTHER)).collect(Collectors.toList());
        model.addAttribute("items", list);
        return "allItems";
    }
}
