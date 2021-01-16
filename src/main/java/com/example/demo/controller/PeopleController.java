package com.example.demo.controller;

import com.example.demo.entity.People;
import com.example.demo.service.IPeopleService;
import com.example.demo.validator.PeopleValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(value = "/people")
@RestController

public class PeopleController {

    @Autowired
    private IPeopleService iPeopleService;

    @GetMapping
    public List<People> getAllPersons() {
        return iPeopleService.getAllPersons();
    }

    @GetMapping("/{id}")
    public People getPersonById(@PathVariable int id) {
        return iPeopleService.getPersonById(id);
    }

    @PostMapping
    public People createPerson(@RequestBody @Valid People people) {
        return iPeopleService.savePerson(people);
    }

    @PutMapping("/{id}")
    public People updatePerson(@PathVariable int id, @RequestBody @Valid People people) {
        return iPeopleService.updatePerson(id, people);
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
