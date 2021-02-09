package com.example.demo.service;
import com.example.demo.controller.PeopleGetViewDto;
import com.example.demo.dto.PeopleCreateDto;
import com.example.demo.dto.PeopleDto;
import com.example.demo.entity.People;
import java.util.List;

public interface IPeopleService {

People savePerson(PeopleCreateDto people);

//List<PeopleDto> getAllPersons();
PeopleGetViewDto getAllPersons();

People getPersonById(int id);

void deletePerson(int id);

People updatePerson(int id, People people);
}
