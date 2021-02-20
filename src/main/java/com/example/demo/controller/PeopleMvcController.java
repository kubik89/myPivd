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
    String allGenders = "http://localhost:8081/sex/getGenders";
    String allGroupsURL = "http://localhost:8081/groups/";
    String allMeetServices = "http://localhost:8081/people/getMeetServiceTypes";

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

        ResponseEntity<SexDtoList> responseEntity1 = restTemplate.exchange(allGenders, HttpMethod.GET, HttpEntity.EMPTY, SexDtoList.class);
        model.addAttribute("genderList", responseEntity1.getBody().getSexDtoList());

        ResponseEntity<GroupGetViewDto> responseEntity = restTemplate.exchange(allGroupsURL, HttpMethod.GET,
                HttpEntity.EMPTY, GroupGetViewDto.class);
        model.addAttribute("list", responseEntity.getBody().getList());

        ResponseEntity<MeetTypesListDto> responseEntity2 = restTemplate.exchange(allMeetServices, HttpMethod.GET,
                HttpEntity.EMPTY, MeetTypesListDto.class);
        model.addAttribute("typesDtoList", responseEntity2.getBody().getTypesDtoList());

        model.addAttribute("person", new PeopleCreateDto());

        return "formCreatePerson";
    }

    @PostMapping("/create_person")
    public String createPersonForm(PeopleCreateDto person) {

        HttpEntity<PeopleCreateDto> httpEntity = new HttpEntity<>(person, HttpHeaders.EMPTY);

        System.out.println(person);
        System.out.println(person.getGroupNumb());
        System.out.println(person.getBirthday());

        restTemplate.exchange("http://localhost:8081/people", HttpMethod.POST, httpEntity, People.class);

//        return "redirect:/view/";
        return "redirect:/view/create_person";
    }

}
