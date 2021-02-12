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
import org.springframework.web.bind.annotation.*;
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
        model.addAttribute("groupID", new GroupCreateDto());
        return "form";
    }

    // return "redirect:/form/" - це на пусту форму
// return "redirect:/group/" - це на всіх в групі
    @PostMapping("/post")
    public String form(GroupCreateDto group) {
        HttpEntity<GroupCreateDto> httpEntity = new HttpEntity<>(group, HttpHeaders.EMPTY);

        restTemplate.exchange("http://localhost:8081/groups", HttpMethod.POST, httpEntity, GroupCreateDto.class);

        return "redirect:/form/";
//        return "redirect:/group/";
    }

    @PostMapping("/update")
    public String updateGroup(GroupCreateDto group) {

        HttpEntity<GroupCreateDto> httpEntity = new HttpEntity<>(group, HttpHeaders.EMPTY);
        String url = "http://localhost:8081/groups/" + group.getNumber();

        restTemplate.exchange(url, HttpMethod.PUT, httpEntity, GroupCreateDto.class);
        return "redirect:/form/";
    }

    @PostMapping("/delete")
    public String delForm(GroupCreateDto groupID) {

//        HttpEntity<GroupCreateDto> httpEntity = new HttpEntity<>(groupID, HttpHeaders.EMPTY);
        String fullLinkToGroup = "http://localhost:8081/groups/" + groupID.getNumber();

        System.out.println(fullLinkToGroup);
        System.out.println(groupID);

        restTemplate.exchange(fullLinkToGroup, HttpMethod.DELETE, HttpEntity.EMPTY, GroupCreateDto.class);

        System.out.println(fullLinkToGroup);
        return "redirect:/form/";
    }

    @PostMapping("/delete1")
    public String delForm1(int number) {

//        HttpEntity<GroupCreateDto> httpEntity = new HttpEntity<>(groupID, HttpHeaders.EMPTY);
        String fullLinkToGroup = "http://localhost:8081/groups/" + number;

        System.out.println(fullLinkToGroup);
        System.out.println(number);

        restTemplate.exchange(fullLinkToGroup, HttpMethod.DELETE, HttpEntity.EMPTY, Integer.class);

        System.out.println(fullLinkToGroup);
        return "redirect:/group/";
    }
}
