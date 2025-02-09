package edu.spring_test.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

    @GetMapping("/sum")
    public int testSum(int a, int b) {
        return a + b;
    }

}
