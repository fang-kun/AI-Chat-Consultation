package cn.aicnn.chatssespringboot.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ThymeleafController {

    @RequestMapping("/index")
    public String hello3(){
        return "chat";
    }


}
