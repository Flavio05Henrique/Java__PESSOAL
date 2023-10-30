package com.teste.teste;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Teste {
    @RequestMapping("/1")
    public String test() {
        return "index.html";
    }

    @RequestMapping("/2")
    public String test2() {
        return "teste.html";
    }

}
