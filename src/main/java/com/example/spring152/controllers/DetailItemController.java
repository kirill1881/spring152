package com.example.spring152.controllers;

import com.example.spring152.models.ItemModel;
import com.example.spring152.models.RequestModel;
import com.example.spring152.repos.ItemRepo;
import com.example.spring152.repos.RequestRepo;
import com.example.spring152.services.FirebaseImageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/detailItem")
public class DetailItemController {

    final ItemRepo itemRepo;
    final RequestRepo requestRepo;
    final FirebaseImageService firebaseImageService;

    public DetailItemController(ItemRepo itemRepo, RequestRepo requestRepo1, FirebaseImageService firebaseImageService) {
        this.itemRepo = itemRepo;
        this.requestRepo = requestRepo1;
        this.firebaseImageService = firebaseImageService;
    }

    @PostMapping("/post/{id}")
    public RedirectView ok(@PathVariable long id,
                           @RequestParam String name,
                           @RequestParam String contact){
        RequestModel requestModel = new RequestModel();
        requestModel.setItemId(id);
        requestModel.setName(name);
        requestModel.setContact(contact);

        requestRepo.save(requestModel);
        return new RedirectView("/allItems");
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable long id,
                         Model model){
        ItemModel itemModel = itemRepo.findById(id);
        itemModel.setUrl(firebaseImageService.getImgUrl(itemModel.getUrl()));
        model.addAttribute("item", itemModel);
        return "detail";
    }
}
