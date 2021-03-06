//package com.example.demo.controller;
//
//import com.example.demo.dto.PeopleDto;
//import com.example.demo.dto.PeopleGetViewDto;
//import com.example.demo.entity.People;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.client.RestTemplate;
//
//@RequestMapping("/view")
//@Controller()
//public class SomePeopleMvcController {
//
//    @Autowired
//    @Qualifier(value = "peopleRestTemplate")
//    private RestTemplate restTemplate;
//
//    @GetMapping("/{id}")
//    public String person(@PathVariable int id, Model model) {
//        String link = "http://localhost:8081/people/";
//        String fullLink = link + id;
//
//        ResponseEntity<People> responceEntity = restTemplate.exchange(fullLink, HttpMethod.GET, HttpEntity.EMPTY,
//                People.class);
//        model.addAttribute("some_people", responceEntity.getBody());
////        if (HttpStatus.OK.equals())
//        return "person";
//    }
//
//    @GetMapping("/")
//    public String index(Model model) {
//
//        ResponseEntity<PeopleGetViewDto> responseEntity = restTemplate.exchange("http://localhost:8081/people",
//                HttpMethod.GET, HttpEntity.EMPTY, PeopleGetViewDto.class);
//
//        if (HttpStatus.OK.equals(responseEntity.getStatusCode())) {
//            PeopleGetViewDto peopleBody = responseEntity.getBody();
//            model.addAttribute("people", peopleBody.getPeople());
//        }
//        model.addAttribute("title1", "My tytle1");
//        return "index";
//    }
//
//}
