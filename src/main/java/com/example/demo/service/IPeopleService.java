package com.example.demo.service;

import com.example.demo.entity.People;

import java.util.List;

public interface IPeopleService {

People savePerson(People people);

List<People> getAllPersons();

People getPersonById(int id);

void deletePerson(int id);

People updatePerson(int id, People people);
}
