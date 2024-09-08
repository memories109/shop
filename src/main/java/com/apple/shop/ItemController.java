package com.apple.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepositiory;

    @GetMapping("/list")
    String list(Model model){

        List<Item> result = itemRepositiory.findAll();
        var a = new Item();
        System.out.println(a.toString());

        model.addAttribute("items",result);
        return "list.html";
    }
    @GetMapping("/write")
    String write(){
        return "write.html";
    }

    @PostMapping("/add")
    String addPost(String title, Integer price){
//        Map<String, Objects> map = new HashMap<>();

        Item item = new Item();
        item.setTitle(title);
        item.setPrice(price);
        itemRepositiory.save(item);
        return "redirect:/list";
    }
    @GetMapping("/detail/{id}")
    String detail(@PathVariable Integer id, Model model){
        Optional<Item> result = itemRepositiory.findById(id);
        if(result.isPresent()){
            model.addAttribute("data", result.get());
        }else{

        }
        return "detail.html";
    }

}
