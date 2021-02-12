package com.example.demo.controller;

import com.example.demo.dto.GroupCreateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@RequestMapping("/main")
@Controller
public class MainMvcController {

    @Autowired
    @Qualifier(value = "mainRestTemplate")
    private RestTemplate restTemplate;

    @GetMapping("/")
    public String showForm(Model model) {


        return "mainMenu";
    }

}
