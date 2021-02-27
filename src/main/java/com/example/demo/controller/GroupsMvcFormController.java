package com.example.demo.controller;

import com.example.demo.dto.*;
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

    String mainUrl = "http://localhost:8081/groups/";

    @GetMapping("/")
    public String showForm(Model model) {

        ResponseEntity<GroupGetViewDto> responseEntity = restTemplate.exchange(mainUrl, HttpMethod.GET,
                HttpEntity.EMPTY, GroupGetViewDto.class);
        model.addAttribute("allGroups", responseEntity.getBody().getList());

        ResponseEntity<PeopleListJustNameDto> exchange = restTemplate.exchange("http://localhost:8081/people/getEldersOrHelpers",
                HttpMethod.GET, HttpEntity.EMPTY, PeopleListJustNameDto.class);
        model.addAttribute("eldersAndHelpers", exchange.getBody().getEldersOrHelpers());

        ResponseEntity<Integer> nextGroupNumber = restTemplate.exchange("http://localhost:8081/groups/getNextGroupNumber", HttpMethod.GET, HttpEntity.EMPTY,
                Integer.class);
        model.addAttribute("nextNumber", nextGroupNumber.getBody());

        model.addAttribute("hello", "Hello my form");
        model.addAttribute("group", new GroupCreateDto());
        model.addAttribute("groupID", new GroupCreateDto());
        model.addAttribute("groupID1", "16");
        model.addAttribute("person", new PeopleDto());
        return "form";
    }

    // return "redirect:/form/" - це на пусту форму
// return "redirect:/group/" - це на всіх в групі
    @PostMapping("/post")
    public String form(GroupCreateDto group) {
        HttpEntity<GroupCreateDto> httpEntity = new HttpEntity<>(group, HttpHeaders.EMPTY);
        restTemplate.exchange("http://localhost:8081/groups", HttpMethod.POST, httpEntity, GroupCreateDto.class);

        System.out.println(group);

        return "redirect:/form/";
    }

    @PostMapping("/update")
    public String updateGroup(GroupCreateDto group) {

        System.out.println(group);

        HttpEntity<GroupCreateDto> httpEntity = new HttpEntity<>(group, HttpHeaders.EMPTY);

        String url = "http://localhost:8081/groups/";
        restTemplate.exchange(url, HttpMethod.PUT, httpEntity, GroupCreateDto.class);
        return "redirect:/form/";
    }

    @PostMapping("/delete")
    public String delForm(GroupCreateDto groupID) {

        String fullLinkToGroup = "http://localhost:8081/groups/" + groupID.getNumber();

        System.out.println(fullLinkToGroup);
        System.out.println(groupID);

        restTemplate.exchange(fullLinkToGroup, HttpMethod.DELETE, HttpEntity.EMPTY, GroupCreateDto.class);

        System.out.println(fullLinkToGroup);
        return "redirect:/form/";
    }

//    @PostMapping("/delete1")
//    public String delForm1(Integer groupID1) {
//
//        String fullLinkToGroup = "http://localhost:8081/groups/";
//
//        System.out.println(fullLinkToGroup);
//        System.out.println(groupID1);
//
//        restTemplate.exchange(fullLinkToGroup, HttpMethod.DELETE, HttpEntity.EMPTY, GroupCreateDto.class);
//
//        System.out.println(fullLinkToGroup);
//        return "redirect:/form/";
//    }

}
