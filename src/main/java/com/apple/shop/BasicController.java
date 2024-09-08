package com.apple.shop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BasicController {
    @GetMapping("/")
    String hello(){
       return "index.html";
    }

    @GetMapping("/about")
    @ResponseBody
    String about(){
        return "about 페이지 입니다.";
    }

    @GetMapping("/mypage")
    @ResponseBody
    String mypage(){
        return "mypage 페이지 입니다.";
    }
}
