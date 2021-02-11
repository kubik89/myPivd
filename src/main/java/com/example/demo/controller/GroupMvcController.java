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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/")
    public String groups(Model model) {
        ResponseEntity<GroupGetViewDto> responseEntity = restTemplate.exchange(mainUrl, HttpMethod.GET,
                HttpEntity.EMPTY, GroupGetViewDto.class);
        model.addAttribute("list", responseEntity.getBody().getList());

        model.addAttribute("newGroup", GroupCreateDto.class);

        return "allGroups";
    }

//    @PostMapping("/create")
//    public String createGroup(GroupCreateDto group) {
//        HttpEntity<GroupCreateDto> httpEntity = new HttpEntity<>(group, HttpHeaders.EMPTY);
//
//        restTemplate.exchange("http://localhost:8081/groups", HttpMethod.POST, httpEntity, GroupCreateDto.class);
//
//        return "createGroup";
//    }


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
