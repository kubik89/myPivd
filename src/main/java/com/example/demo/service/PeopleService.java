package com.example.demo.service;

import com.example.demo.dao.PeopleRepository;
import com.example.demo.entity.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeopleService implements IPeopleService {

    PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public People savePerson(People people) {
        return peopleRepository.saveAndFlush(people);
    }

    @Override
    public List<People> getAllPersons() {
        return peopleRepository.findAll();
    }

    @Override
    public People getPersonById(int id) {
        return peopleRepository.getOne(id);
    }

    @Override
    public void deletePerson(int id) {
        peopleRepository.deleteById(id);
    }

    @Override
    public People updatePerson(int id, People people) {
        if (peopleRepository.existsById(id)) {
            people.setId(id);
            return peopleRepository.saveAndFlush(people);
        } else {
            throw new IllegalArgumentException("Person not found");
        }
    }
};
