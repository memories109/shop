package com.apple.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
//    @GetMapping("/detail/{id}")
//    ResponseEntity<String> detail(@PathVariable Integer id, Model model){
//        try {
//            Optional<Item> result = itemRepositiory.findById(id);
//            if(result.isPresent()){
//                model.addAttribute("data", result.get());
//                return "detail.html";
//            }else{
//                return "redirect:/list"
//            }
//
//            ResponseEntity.status(200).body("Success");
//        } catch (Exception e){
//            System.out.println(e.getMessage());
////            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("error");
//        }
//    }

    @GetMapping("/detail/{id}")
    String detail(@PathVariable Integer id, Model model){
            Optional<Item> result = itemRepositiory.findById(id);
            if(result.isPresent()){
                model.addAttribute("data", result.get());
                return "detail.html";
            }else{
                return "redirect:/list";
            }
    }



}
