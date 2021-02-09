package com.example.demo.controller;

import com.example.demo.dto.PeopleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class PeopleMvcController {

    @Autowired
    @Qualifier(value = "peopleRestTemplate")
    private RestTemplate restTemplate;

    @GetMapping("/")
    public String index(Model model) {

        ResponseEntity<PeopleGetViewDto> responseEntity = restTemplate.exchange("http://localhost:8081/people",
                HttpMethod.GET, HttpEntity.EMPTY, PeopleGetViewDto.class);

//        if (HttpStatus.OK.equals(responseEntity.getStatusCode())) {
            PeopleGetViewDto peopleBody = responseEntity.getBody();
                model.addAttribute("people", peopleBody.getPeople());
//        }
        model.addAttribute("title1", "My tytle1");
        return "index";
    }


}
