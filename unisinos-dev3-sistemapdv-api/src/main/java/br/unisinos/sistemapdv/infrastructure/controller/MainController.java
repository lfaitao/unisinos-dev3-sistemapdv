package br.unisinos.sistemapdv.infrastructure.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Faitão on 25/03/2017.
 */
@RestController
public class MainController {

    @RequestMapping("/")
    @ResponseBody
    private String home() {
        return "Hello World!";
    }

}
