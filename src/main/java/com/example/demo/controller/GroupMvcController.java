package com.example.demo.controller;

import com.example.demo.dto.*;
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

    String mainUrl = "http://localhost:8081/groups/";
    String url = "http://localhost:8081/groups/members/";
    String getResp = "http://localhost:8081/groups/resp/";

    @GetMapping("/members/{id}")
    public String groups(@PathVariable int id, Model model) {
        String fullLink = url + id;
        ResponseEntity<GroupMembersDto> responseEntity = restTemplate.exchange(fullLink, HttpMethod.GET,
                HttpEntity.EMPTY, GroupMembersDto.class);
        model.addAttribute("list", responseEntity.getBody().getList());

        String fullLinkForResp = getResp + id;
        ResponseEntity<PeopleJustNameDto> responseEntityRespId = restTemplate.exchange(fullLinkForResp, HttpMethod.GET,
                HttpEntity.EMPTY, PeopleJustNameDto.class);
        model.addAttribute("responsible", responseEntityRespId.getBody());

        String fullLinkForCountMembers = mainUrl + "count/" + id;
        ResponseEntity<GroupMembersCountDto> responseEntityCountInGroup = restTemplate.exchange(fullLinkForCountMembers,
                HttpMethod.GET, HttpEntity.EMPTY, GroupMembersCountDto.class);
        model.addAttribute("count", responseEntityCountInGroup.getBody());

        model.addAttribute("groupNumb", id);

        return "groups";
    }
}
