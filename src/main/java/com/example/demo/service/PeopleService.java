package com.example.demo.service;

import com.example.demo.dao.*;
import com.example.demo.dto.*;
import com.example.demo.entity.Group;
import com.example.demo.entity.MeetServices;
import com.example.demo.entity.People;
import com.example.demo.entity.Sex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PeopleService implements IPeopleService {

    PeopleRepository peopleRepository;
    GroupRepository groupRepository;
    SexRepository sexRepository;
    MeetRepository meetRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository, GroupRepository groupRepository, SexRepository sexRepository,
                         MeetRepository meetRepository) {
        this.peopleRepository = peopleRepository;
        this.groupRepository = groupRepository;
        this.sexRepository = sexRepository;
        this.meetRepository = meetRepository;
    }

    @Override
    public People savePerson(PeopleCreateDto peopleCreateDto) {
        People people = new People();

        people.setFname(peopleCreateDto.getFname());
        people.setLname(peopleCreateDto.getLname());
        people.setBirthday(peopleCreateDto.getBirthday());

        Optional<MeetServices> byId = meetRepository.findById(peopleCreateDto.getPriv_meet());
        MeetServices meetServices = byId.orElseThrow(() -> new BadRequestException("I did not find any services in meet " +
                "for new User"));
        people.setPriv_meet(meetServices);

        Group groupByNumb = groupRepository.findGroupById(peopleCreateDto.getGroupNumb());
        people.setGroup_numb(groupByNumb);

//        Optional<Group> groupIp = groupRepository.findById(peopleCreateDto.getGroupNumb());
//        Group group = groupIp.orElseThrow(() -> new BadRequestException("I did not find any Group for new User"));
//        people.setGroup_numb(group);

        Optional<Sex> sexById = sexRepository.findById(peopleCreateDto.getSex());
        Sex sex = sexById.orElseThrow(() -> new BadRequestException("Current sex type did not find"));
        people.setSex(sex);

        return peopleRepository.saveAndFlush(people);
    }

    @Override
    public PeopleGetViewDto getAllPersons() {

        List<People> allPersons = peopleRepository.findAllPersons();

//        List<People> peopleList = peopleRepository.findAll();
        List<PeopleDto> peopleDtoList = allPersons.stream().map(people ->
                new PeopleDto(people.getLname(), people.getFname(), people.getGroup_numb().getGroup_number(),
//                        people.getSex().getSexType(), people.getPriv_service().getType())).collect(Collectors.toList());
                        people.getSex().getSexType(), people.getBirthday())).collect(Collectors.toList());
        return new PeopleGetViewDto(peopleDtoList);
    }

    @Override
    public PeopleViewCurrentUserDto getPersonById(int id) {
        String fname = peopleRepository.getOne(id).getFname();
        String lname = peopleRepository.getOne(id).getLname();
        int group_number = peopleRepository.getOne(id).getGroup_numb().getGroup_number();
        String sex = peopleRepository.getOne(id).getSex().getSexType();
        String street_name = peopleRepository.getOne(id).getStreet_name();
        String street_building_number = peopleRepository.getOne(id).getStreet_building_number();
        int flat_number = peopleRepository.getOne(id).getFlat_number();
        int home_phone = peopleRepository.getOne(id).getHome_phone();
        int mob_phone = peopleRepository.getOne(id).getMob_phone();
        String birthday = peopleRepository.getOne(id).getBirthday();
        return new PeopleViewCurrentUserDto(fname, lname, group_number, street_name, street_building_number, flat_number,
                home_phone, mob_phone, sex, birthday);
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

    @Override
    public PeopleEldersAndHelpers getEldersOrHelpers() {
        List<People> allPersons = peopleRepository.getAllResp();
        List<PeopleJustNameDto> nameDtoList = allPersons.stream().map(people ->
                new PeopleJustNameDto(people.getId(), people.getLname(), people.getFname())).collect(Collectors.toList());
        return new PeopleEldersAndHelpers(nameDtoList);
    }

    @Override
    public List<PeopleViewCurrentUserDto> getEldOrHelp() {
        List<People> allPersons = peopleRepository.getAllResp();
        List<PeopleViewCurrentUserDto> list = new ArrayList<>();
        allPersons.forEach(people ->
                list.add(new PeopleViewCurrentUserDto(people.getFname(),
                        people.getLname(), people.getGroup_numb().getGroup_number(),
                        people.getStreet_name(), people.getStreet_building_number(), people.getFlat_number(),
                        people.getHome_phone(), people.getMob_phone(), people.getSex().getSexType(), people.getBirthday())));
        return list;
    }

    @Override
    public MeetTypesListDto getAllMeetTypes() {
        List<MeetServices> allPriv_meet = peopleRepository.getAllPriv_meet();
        List<MeetTypesDto> meetTypesDtoList = allPriv_meet.stream().map(meetServices ->
                new MeetTypesDto(meetServices.getId() ,meetServices.getType())).collect(Collectors.toList());
        return new MeetTypesListDto(meetTypesDtoList);
    }

}
