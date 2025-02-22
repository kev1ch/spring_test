package edu.spring_practice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

    @GetMapping("/sum")
    public int testSum(@RequestParam int a, @RequestParam int b) {
        return a + b;
    }

}
