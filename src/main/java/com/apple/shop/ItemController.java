package com.apple.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepositiory;

//    @Autowired
    private final ItemService itemService;

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
        itemService.saveItem(title, price);
        return "redirect:/list";
    }

    @GetMapping("/edit/{id}")
    String edit(@PathVariable Integer id, Model model){
        Optional<Item> result = itemRepositiory.findById(id);
        if(result.isPresent()){
            model.addAttribute("item",result.get());
            return "edit.html";
        }else{
            return  "redirect:/list";
        }
    }
    @PostMapping("/edit")
    String editSave(@RequestParam String title , Integer price , Integer id){
        itemService.updateItem(title, price, id);
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

    @PostMapping("/test1")
    String test1(@RequestBody Map<String, Object> body){
        System.out.println(body);
        return "redirect:/list";
    }

    @PostMapping("/delete/{id}")
    String delete(@PathVariable Integer id){
        Optional<Item> result = itemRepositiory.findById(id);
        itemRepositiory.delete(result.get());
        return "redirect:/list";
    }

    @DeleteMapping("/item")
    ResponseEntity<String> deleteItem(@RequestParam Integer id){
        itemRepositiory.deleteById(id);
        return ResponseEntity.status(200).body("삭제완료");
    }



}
