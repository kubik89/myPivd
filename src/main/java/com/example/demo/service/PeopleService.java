package com.example.demo.service;

import com.example.demo.dao.BadRequestException;
import com.example.demo.dao.GroupRepository;
import com.example.demo.dao.PeopleRepository;
import com.example.demo.dto.PeopleCreateDto;
import com.example.demo.dto.PeopleDto;
import com.example.demo.entity.Group;
import com.example.demo.entity.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PeopleService implements IPeopleService {

    PeopleRepository peopleRepository;
    GroupRepository groupRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository, GroupRepository groupRepository) {
        this.peopleRepository = peopleRepository;
        this.groupRepository = groupRepository;
    }

    @Override
    public People savePerson(PeopleCreateDto peopleCreateDto) {
        People people = new People();

        people.setFname(peopleCreateDto.getFname());
        people.setLname(peopleCreateDto.getLname());
        people.setSex(peopleCreateDto.getSex());

        Optional<Group> groupIp = groupRepository.findById(peopleCreateDto.getGroupNumb());
        Group group = groupIp.orElseThrow(() -> new BadRequestException("I did not find any Group for new User"));
        people.setGroup(group);

        return peopleRepository.saveAndFlush(people);
    }

    @Override
    public List<PeopleDto> getAllPersons() {
        return peopleRepository.findAll().stream().map(people ->
                new PeopleDto(people.getLname(), people.getFname(), people.getGroup().getNumber(), people.getSex()))
                .collect(Collectors.toList());
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
