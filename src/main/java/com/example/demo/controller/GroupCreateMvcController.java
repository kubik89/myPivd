package com.example.demo.controller;

import com.example.demo.dto.GroupCreateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

//@Controller
//public class GroupCreateMvcController {
//
//    @Autowired
//    @Qualifier(value = "groupCreateRestTemplate")
//    private RestTemplate restTemplate;
//
//    @PostMapping("/creategroup")
//    public String createGroup(GroupCreateDto group, Model model) {
//        HttpEntity<GroupCreateDto> httpEntity = new HttpEntity<>(group, HttpHeaders.EMPTY);
//
//        restTemplate.exchange("http://localhost:8081/groups",
//                HttpMethod.POST, httpEntity, GroupCreateDto.class);
//        model.addAttribute("newGroup", new GroupCreateDto());
//
//        model.addAttribute("test", "test");
//
//        return "createGroup";
//    }

//}
