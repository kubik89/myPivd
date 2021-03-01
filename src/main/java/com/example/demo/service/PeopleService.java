package com.example.demo.service;

import com.example.demo.dao.*;
import com.example.demo.dto.*;
import com.example.demo.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    ServiceInSRepository serviceInSRepository;
    HopeRepository hopeRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository, GroupRepository groupRepository, SexRepository sexRepository,
                         MeetRepository meetRepository, ServiceInSRepository serviceInSRepository, HopeRepository hopeRepository) {
        this.peopleRepository = peopleRepository;
        this.groupRepository = groupRepository;
        this.sexRepository = sexRepository;
        this.meetRepository = meetRepository;
        this.serviceInSRepository = serviceInSRepository;
        this.hopeRepository = hopeRepository;
    }

    @Override
    public People savePerson(PeopleCreateDto peopleCreateDto) {
        People people = new People();

        people.setFname(peopleCreateDto.getFname());
        people.setLname(peopleCreateDto.getLname());
        people.setBirthday(peopleCreateDto.getBirthday());
        people.setDate_chreshchennja(peopleCreateDto.getDate_chreshchennja());
        people.setStreet_name(peopleCreateDto.getStreet_name());
        people.setStreet_building_number(peopleCreateDto.getStreet_building_number());
        people.setFlat_number(peopleCreateDto.getFlat_number());
        people.setHome_phone(peopleCreateDto.getHome_phone());
        people.setMob_phone(peopleCreateDto.getMob_phone());
//
        Optional<MeetServices> byId = meetRepository.findById(peopleCreateDto.getPriv_meet());
        MeetServices meetServices = byId.orElseThrow(() -> new BadRequestException("I did not find any services in meet " +
                "for new User"));
        people.setPriv_meet(meetServices);

        Optional<ServiceInS> byId1 = serviceInSRepository.findById(peopleCreateDto.getServiceInS());
        ServiceInS serviceInS = byId1.orElseThrow(() -> new BadRequestException("I did not find any services in S " +
                "for new User"));
        people.setPriv_service(serviceInS);

        Optional<Hope> byId2 = hopeRepository.findById(peopleCreateDto.getHope_id());
        Hope hope = byId2.orElseThrow(() -> new BadRequestException("I did not find any hope for new User"));
        people.setNadija_na(hope);

        Group groupByNumb = groupRepository.findGroupById(peopleCreateDto.getGroupNumb());
        people.setGroup_numb(groupByNumb);

        Optional<Sex> sexById = sexRepository.findById(peopleCreateDto.getSex());
        Sex sex = sexById.orElseThrow(() -> new BadRequestException("Current sex type did not find"));
        people.setSex(sex);

        return peopleRepository.saveAndFlush(people);
    }

    @Override
    public PeopleGetViewDto getAllPersons() {

        List<People> allPersons = peopleRepository.findAllPersons();

        List<PeopleDto> peopleDtoList = allPersons.stream().map(people ->
                new PeopleDto(people.getLname(), people.getFname(), people.getGroup_numb().getGroup_number(),
                        people.getSex().getSexType(), people.getBirthday())).collect(Collectors.toList());
        return new PeopleGetViewDto(peopleDtoList);
    }

    @Override
    public List<PeopleJustNameDto> getAllPersonName() {
        List<PeopleJustNameDto> list = new ArrayList<>();
        peopleRepository.findAll().forEach(people ->
            list.add(new PeopleJustNameDto(people.getId(), people.getLname(), people.getFname())));

        return list;
    }

    @Override
    public PeopleViewCurrentUserDto getPersonById(int id) {
        People person = peopleRepository.getOne(id);
        return convertToPeopleViewCurrentUserDto(person);
    }

    @Override
    public void deletePerson(int id) {
        peopleRepository.deleteById(id);
    }

    @Override
    public People updatePerson(PeopleViewCurrentUserDto people) {
        People people1 = new People();

        if (peopleRepository.existsById(people.getId())) {
            people1.setId(people.getId());
        } else {
            throw new IllegalArgumentException("Person not found");
        }

        people1.setFname(people.getFname());
        people1.setLname(people.getLname());
        people1.setBirthday(people.getBirthday());
        people1.setStreet_name(people.getStreet_name());
        people1.setStreet_building_number(people.getStreet_building_number());
        people1.setFlat_number(people.getFlat_number());
        people1.setHome_phone(people.getHome_phone());
        people1.setMob_phone(people.getMob_phone());
        people1.setDate_chreshchennja(people.getDateChreshchennja());

        meetRepository.findAll().forEach(meetServices -> {
            if (people.getPrivInMeet() == meetServices.getId()) {
                people1.setPriv_meet(meetServices);
            }
        });

        serviceInSRepository.findAll().forEach(servServices -> {
            if (people.getPrivInService() == servServices.getId()) {
                people1.setPriv_service(servServices);
            }
        });

        hopeRepository.findAll().forEach(hope -> {
            if (people.getHope_on() == hope.getId()) {
                people1.setNadija_na(hope);
            }
        });

        Group groupByNumb = groupRepository.findGroupById(people.getGroup());
        people1.setGroup_numb(groupByNumb);

        if (people.getSex().startsWith("Ч") || people.getSex().startsWith("ч")) {
            sexRepository.findAll().forEach(sexType -> {
                if (sexType.getSexType().startsWith("Ч")) {
                    people1.setSex(sexType);
                }
            });
        } else if (people.getSex().startsWith("Ж") || people.getSex().startsWith("ж")) {
            sexRepository.findAll().forEach(sexType -> {
                if (sexType.getSexType().startsWith("Ж")) {
                    people1.setSex(sexType);
                }
            });
        }
        return peopleRepository.saveAndFlush(people1);
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
                list.add(convertToPeopleViewCurrentUserDto(people)));
        return list;
    }

    @Override
    public MeetTypesListDto getAllMeetTypes() {
        List<MeetServices> allPriv_meet = peopleRepository.getAllPriv_meet();
        List<MeetTypesDto> meetTypesDtoList = allPriv_meet.stream().map(meetServices ->
                new MeetTypesDto(meetServices.getId(), meetServices.getType())).collect(Collectors.toList());
        return new MeetTypesListDto(meetTypesDtoList);
    }

    @Override
    public ServiceTypesDto getAllServiceTypes() {
        List<ServiceInS> collect = serviceInSRepository.findAll().stream().collect(Collectors.toList());
        return new ServiceTypesDto(collect);
    }

    @Override
    public HopeTypesDto getAllHopes() {
        List<Hope> hopeList = hopeRepository.findAll().stream().collect(Collectors.toList());
        return new HopeTypesDto(hopeList);
    }

    public PeopleViewCurrentUserDto convertToPeopleViewCurrentUserDto(People people) {
        return new PeopleViewCurrentUserDto(
                people.getId(),
                people.getFname(),
                people.getLname(),
                people.getGroup_numb().getGroup_number(),
                people.getNadija_na().getId(),
                people.getStreet_name(),
                people.getStreet_building_number(),
                people.getFlat_number(),
                people.getHome_phone(),
                people.getMob_phone(),
                people.getSex().getSexType(),
                people.getBirthday(),
                people.getDate_chreshchennja(),
                people.getPriv_meet().getId(),
                people.getPriv_service().getId());
    }
}
