package br.com.primeiroProjeto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PrimeiroController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }
}
