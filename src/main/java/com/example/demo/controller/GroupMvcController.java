package com.example.demo.controller;

import com.example.demo.dto.PeopleInGroups;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@RequestMapping("/group")
@Controller()
public class GroupMvcController {

    @Autowired
    @Qualifier(value = "groupRestTemplate")
    private RestTemplate restTemplate;

    String url = "http://localhost:8081/groups/members/";

    @GetMapping("/members/{id}")
    public String groups(@PathVariable int id, Model model) {
        String fullLink = url + id;
        ResponseEntity<PeopleInGroups> responseEntity = restTemplate.exchange(fullLink, HttpMethod.GET,
                HttpEntity.EMPTY, PeopleInGroups.class);

        model.addAttribute("people", responseEntity.getBody());
        return "groups";
    }
}
