package com.example.demo.controller;

import com.example.demo.dto.GroupCreateDto;
import com.example.demo.dto.GroupGetViewDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RequestMapping("/form")
@Controller()
public class GroupsMvcFormController {

    @Autowired
    @Qualifier(value = "groupRestTemplate")
    private RestTemplate restTemplate;

    @GetMapping("/")
    public String showForm(Model model) {

        model.addAttribute("hello", "Hello my form");
// group - після натискання на кнопку в формі html, запускається Post запит по урлі і в Get прокидається обєкт group
// із Post. Такий принцип прокидання лише через Get
        model.addAttribute("group", new GroupCreateDto());
        return "form";
    }

// return "redirect:/form/" - це на пусту форму
// return "redirect:/group/" - це на всіх в групі
    @PostMapping("/post")
    public String form(GroupCreateDto group, Model model) {
        HttpEntity<GroupCreateDto> httpEntity = new HttpEntity<>(group, HttpHeaders.EMPTY);

        restTemplate.exchange("http://localhost:8081/groups", HttpMethod.POST, httpEntity, GroupCreateDto.class);

        model.addAttribute("form","Form1");
        return "redirect:/form/";
//        return "redirect:/group/";
    }
}
