package com.api.parkingcontrol.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Aleatorio {

    @GetMapping("/oi")
    public String index() {
        return "Kobayashe/index.html";
    }
}
