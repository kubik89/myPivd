package com.example.demo.service;
import com.example.demo.dto.PeopleGetViewDto;
import com.example.demo.dto.PeopleCreateDto;
import com.example.demo.dto.PeopleViewCurrentUserDto;
import com.example.demo.entity.People;

public interface IPeopleService {

People savePerson(PeopleCreateDto people);

//List<PeopleDto> getAllPersons();
PeopleGetViewDto getAllPersons();

//People getPersonById(int id);
PeopleViewCurrentUserDto getPersonById(int id);

void deletePerson(int id);

People updatePerson(int id, People people);
}
