package com.cooper.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class OverviewController {

    @RequestMapping("/characterList")
    public String getCharacterList() {
        return "Hello";
    }
}

