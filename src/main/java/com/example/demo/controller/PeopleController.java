package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.People;
import com.example.demo.service.IPeopleService;
import com.example.demo.validator.PeopleValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RequestMapping(value = "/people")
@RestController

public class PeopleController {

    @Autowired
    private IPeopleService iPeopleService;

    private static Logger logger = LoggerFactory.getLogger(GroupsController.class);

    @GetMapping
    public PeopleGetViewDto getAllPersons() {
        return iPeopleService.getAllPersons();
    }

    @GetMapping("/getEldersOrHelpers")
    public PeopleEldersAndHelpers getEldersOrHelpers() {
        return iPeopleService.getEldersOrHelpers();
    }

    @GetMapping("/{id}")
    public PeopleViewCurrentUserDto getPersonById(@PathVariable int id) {
        return iPeopleService.getPersonById(id);
    }

    @GetMapping("/getEldAndHelp")
    public List<PeopleViewCurrentUserDto> getEldOrHelp(){
        return iPeopleService.getEldOrHelp();
    }

    @GetMapping("/getMeetServiceTypes")
    public MeetTypesListDto getMeetTypes() {
        return iPeopleService.getAllMeetTypes();
    }

    @GetMapping("/getAllServicesInS")
    public ServiceTypesDto getAllServiceTypes() {
        return iPeopleService.getAllServiceTypes();
    }

    @GetMapping("/getAllHopes")
    public HopeTypesDto getAllHopes() {
        return iPeopleService.getAllHopes();
    }

//    @PostMapping
//    public People createPerson(@RequestBody @Valid PeopleCreateDto people) {
//        logger.info("New person created: {}", people.getLname());
//        return iPeopleService.savePerson(people);
//    }

    @PostMapping
    public People createPerson(@RequestBody @Valid PeopleCreateDto people) {
        logger.info("New person created: {}", people.getLname());
        return iPeopleService.savePerson(people);
    }

    @PutMapping
    public People updatePerson(@RequestBody @Valid PeopleViewCurrentUserDto people) {
        return iPeopleService.updatePerson(people);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable int id) {
        iPeopleService.deletePerson(id);
    }

    @InitBinder
    public void myInitBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(new PeopleValidator());
    }

}
