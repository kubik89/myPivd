package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.entity.People;

import java.util.List;

public interface IPeopleService {

    People savePerson(PeopleCreateDto people);

    //List<PeopleDto> getAllPersons();
    PeopleGetViewDto getAllPersons();

    //People getPersonById(int id);
    PeopleViewCurrentUserDto getPersonById(int id);

    void deletePerson(int id);

    People updatePerson(int id, People people);

    PeopleEldersAndHelpers getEldersOrHelpers();

    List<PeopleViewCurrentUserDto> getEldOrHelp();

    MeetTypesListDto getAllMeetTypes();

}
