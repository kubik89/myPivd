package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@RequestMapping("/view")
@Controller()
public class PeopleMvcController {

    @Autowired
    @Qualifier(value = "peopleRestTemplate")
    private RestTemplate restTemplate;

    String linkPeople = "http://localhost:8081/people/";

    @GetMapping("/{id}")
    public String person(@PathVariable int id, Model model) {
        String fullLink = linkPeople + id;

        ResponseEntity<PeopleViewCurrentUserDto> responceEntity = restTemplate.exchange(fullLink, HttpMethod.GET, HttpEntity.EMPTY,
                PeopleViewCurrentUserDto.class);
        model.addAttribute("some_people", responceEntity.getBody());
        return "person";
    }

    @GetMapping("/")
    public String index(Model model) {

        ResponseEntity<PeopleGetViewDto> responseEntity = restTemplate.exchange(linkPeople, HttpMethod.GET,
                HttpEntity.EMPTY, PeopleGetViewDto.class);

        if (HttpStatus.OK.equals(responseEntity.getStatusCode())) {
            PeopleGetViewDto peopleBody = responseEntity.getBody();
            model.addAttribute("people", peopleBody.getPeople());
        }
        model.addAttribute("title1", "My tytle1");
        return "index";
    }

    @GetMapping("/create_person")
    public String create(Model model) {

        model.addAttribute("person", new PeopleCreateDto());

        return "formCreatePerson";
    }

    @PostMapping("/create_person")
    public String createPersonForm(PeopleCreateDto person) {

        HttpEntity<PeopleCreateDto> httpEntity = new HttpEntity<>(person, HttpHeaders.EMPTY);

        restTemplate.exchange("http://localhost:8081/people", HttpMethod.POST, httpEntity, PeopleCreateDto.class);

        return "redirect:/view/";
    }

}
