package com.example.demo.service;

import com.example.demo.controller.GroupsController;
import com.example.demo.dao.*;
import com.example.demo.dto.*;
import com.example.demo.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.Collections;
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

    String MAN_GENDER_VALUE_BIG = "Ч";
    String MAN_GENDER_VALUE_SMALL = "ч";
    String WOMAN_GENDER_VALUE_BIG = "Ж";
    String WOMAN_GENDER_VALUE_SMALL = "ж";

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

    private static Logger logger = LoggerFactory.getLogger(GroupsController.class);

    @Override
    public People savePerson(PeopleCreateDto peopleCreateDto) {
        People people = new People();
        String x = "dsds";

        if (peopleCreateDto != null) {
            people.setFname(peopleCreateDto.getFname());
            people.setLname(peopleCreateDto.getLname());
            people.setBirthday(peopleCreateDto.getBirthday());
            people.setStreet_name(peopleCreateDto.getStreet_name());
            people.setStreet_building_number(peopleCreateDto.getStreet_building_number());
            people.setFlat_number(peopleCreateDto.getFlat_number());
            people.setHome_phone(peopleCreateDto.getHome_phone());
            people.setMob_phone(peopleCreateDto.getMob_phone());


//        if (people.getDate_chreshchennja().isEmpty())
//        if (people.getDate_chreshchennja().isEmpty() | people.getDate_chreshchennja().equals("") | people.getDate_chreshchennja() == null) {
//            throw new NullPointerException("Дата хрещення = empty or ''? = " + peopleCreateDto.getServiceInS());
//            System.out.println("Дата хрещення = empty or ''? = " + peopleCreateDto.getServiceInS());
//            people.setDate_chreshchennja(peopleCreateDto.getDate_chreshchennja());
//            people.setDate_chreshchennja(null);
//        } else if (peopleCreateDto.getServiceInS() == 4) {
//            System.out.println("Дата хрещення =4 ? = " + peopleCreateDto.getServiceInS());
//            people.setDate_chreshchennja(null);
//        }
//        else {
//            System.out.println("Дата хрещення = else ? = " + peopleCreateDto.getServiceInS());
//            people.setDate_chreshchennja(null);
//        }

            people.setDate_chreshchennja(peopleCreateDto.getDate_chreshchennja());
        }
//
        Optional<MeetServices> byId = meetRepository.findById(peopleCreateDto.getPriv_meet());
        MeetServices meetServices = byId.orElseThrow(() -> new BadRequestException("Did not find any services in meet " +
                "for new User " + peopleCreateDto.getLname() + " " + peopleCreateDto.getFname()));
        people.setPriv_meet(meetServices);

        Optional<ServiceInS> byId1 = serviceInSRepository.findById(peopleCreateDto.getServiceInS());
        ServiceInS serviceInS = byId1.orElseThrow(() -> new BadRequestException("Did not find any services " +
                "for new User " + peopleCreateDto.getLname() + " " + peopleCreateDto.getFname()));
        people.setPriv_service(serviceInS);

        Optional<Hope> byId2 = hopeRepository.findById(peopleCreateDto.getHope_id());
        Hope hope = byId2.orElseThrow(() -> new BadRequestException("Did not find any hope for new User " + peopleCreateDto.getLname()
                + " " + peopleCreateDto.getFname()));
        people.setNadija_na(hope);

        try {
            Group groupByNumb = groupRepository.findGroupById(peopleCreateDto.getGroupNumb());
            if (groupByNumb == null)
                return null;
            else people.setGroup_numb(groupByNumb);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }

        Sex sexById = sexRepository.getGenderById(peopleCreateDto.getSex());
        if (sexById == null) {
            logger.warn("Current sex type did not find or null");
            return null;
        }
        people.setSex(sexById);

        return peopleRepository.saveAndFlush(people);
    }

    @Override
    public PeopleGetViewDto getAllPersons() {

//        List<People> allPersons = peopleRepository.findAllPersons();
//        List<PeopleDto> peopleDtoList = allPersons.stream().map(people ->
//                new PeopleDto(people.getId(), people.getLname(), people.getFname(), people.getGroup_numb().getGroup_number(),
//                        people.getSex().getSexType(), people.getBirthday())).collect(Collectors.toList());

        List<PeopleDto> allPersons = peopleRepository.findAllPersonsAsPeopleDto();
        List<PeopleDto> peopleDtoList = new ArrayList<>();

        if (!CollectionUtils.isEmpty(allPersons)) {
            peopleDtoList = allPersons.stream().map(people ->
                    new PeopleDto(people.getId(), people.getLname(), people.getFname(), people.getGroupNumb(),
                            people.getSex(), people.getBirthday())).collect(Collectors.toList());
        }
        return new PeopleGetViewDto(peopleDtoList);
    }

    @Override
    public List<PeopleJustNameDto> getAllPersonName() {
        List<PeopleJustNameDto> list = new ArrayList<>();
        try {
            peopleRepository.findAll().forEach(people ->
                    list.add(new PeopleJustNameDto(people.getId(), people.getLname(), people.getFname())));
        } catch (Exception ex) {
            logger.warn("Can not find all persons from repository", ex);
        }

        return list;
    }

    @Override
    public PeopleViewCurrentUserDto getPersonById(int id) {
        People person = peopleRepository.getOne(id);
        return convertToPeopleViewCurrentUserDto(person);
    }

    @Override
    public void deletePerson(int id) {
        try {
            peopleRepository.deleteById(id);
        } catch (Exception ex) {
            logger.warn("Can delete person with ID: {}", id, ex);
        }
    }

    @Override
    public People updatePerson(PeopleViewCurrentUserDto people) {
        People person = new People();

        if (peopleRepository.existsById(people.getId())) {
            person.setId(people.getId());
        } else {
            throw new IllegalArgumentException("Person with ID: " + people.getId() + " - not found");
        }

        person.setFname(people.getFname());
        person.setLname(people.getLname());
        person.setBirthday(people.getBirthday());
        person.setStreet_name(people.getStreet_name());
        person.setStreet_building_number(people.getStreet_building_number());
        person.setFlat_number(people.getFlat_number());
        person.setHome_phone(people.getHome_phone());
        person.setMob_phone(people.getMob_phone());

        if (people.getDateChreshchennja().equals("")) {
            people.setDateChreshchennja(null);
        } else {
            person.setDate_chreshchennja(people.getDateChreshchennja());
        }

        try {
            meetRepository.findAll().forEach(meetServices -> {
                if (people.getPrivInMeet() == meetServices.getId()) {
                    person.setPriv_meet(meetServices);
                }
            });
        } catch (Exception ex) {
            logger.warn("Can find meet Services from DB", ex);
        }

        try {
            serviceInSRepository.findAll().forEach(servServices -> {
                if (people.getPrivInService() == servServices.getId()) {
                    person.setPriv_service(servServices);
                }
            });
        } catch (Exception ex) {
            logger.warn("Can find Services in service from DB", ex);
        }

        try {
            hopeRepository.findAll().forEach(hope -> {
                if (people.getHope_on() == hope.getId()) {
                    person.setNadija_na(hope);
                }
            });
        } catch (Exception ex) {
            logger.warn("Can find hopes from DB", ex);
        }

        try {
            Group groupByNumb = groupRepository.findGroupById(people.getGroup());

            if (groupByNumb != null)
                person.setGroup_numb(groupByNumb);
            else {
                logger.warn("Can not find group with number: {}", people.getGroup());
                return null;
            }
        } catch (Exception ex) {
            logger.warn("Can not find groups from DB", ex);
        }

        if (people.getSex().startsWith(MAN_GENDER_VALUE_BIG) || people.getSex().startsWith(MAN_GENDER_VALUE_SMALL)) {
            try {
                sexRepository.getAllGenders().forEach(sexType -> {
                    if (sexType.getSexType().startsWith(MAN_GENDER_VALUE_BIG)) {
                        person.setSex(sexType);
                    }
                });
            } catch (Exception ex) {
                logger.warn("Can not get Genders for MAN from DB", ex);
            }
        } else if (people.getSex().startsWith(WOMAN_GENDER_VALUE_BIG) || people.getSex().startsWith(WOMAN_GENDER_VALUE_SMALL)) {
            try {
                sexRepository.getAllGenders().forEach(sexType -> {
                    if (sexType.getSexType().startsWith(WOMAN_GENDER_VALUE_BIG)) {
                        person.setSex(sexType);
                    }
                });
            } catch (Exception ex) {
                logger.warn("Can not get Genders for WOMAN from DB", ex);
            }
        }
        return peopleRepository.saveAndFlush(person);
    }

    @Override
    public PeopleEldersAndHelpers getEldersOrHelpers() {

        List<PeopleJustNameDto> nameDtoList = new ArrayList<>();

        try {
            List<People> allPersons = peopleRepository.getAllResp();
            if (!CollectionUtils.isEmpty(allPersons)) {
                nameDtoList = allPersons.stream().map(people ->
                        new PeopleJustNameDto(people.getId(), people.getLname(), people.getFname())).collect(Collectors.toList());
            }
        } catch (Exception ex) {
            logger.warn("Can not get responsible persons from DB", ex);
        }
        return new PeopleEldersAndHelpers(nameDtoList);
    }

    @Override
    public List<PeopleViewCurrentUserDto> getEldOrHelp() {
        List<PeopleViewCurrentUserDto> list = new ArrayList<>();

        try {
            List<People> allPersons = peopleRepository.getAllResp();

            if (!CollectionUtils.isEmpty(allPersons)) {
                allPersons.forEach(people ->
                        list.add(convertToPeopleViewCurrentUserDto(people)));
            }
        } catch (Exception ex) {
            logger.warn("Can not get responsible persons from DB", ex);
        }
        return list;
    }

    @Override
    public MeetTypesListDto getAllMeetTypes() {
        List<MeetTypesDto> meetTypesDtoList = new ArrayList<>();
        try {
            List<MeetServices> allPriv_meet = peopleRepository.getAllPriv_meet();
            if (!CollectionUtils.isEmpty(allPriv_meet)) {
                meetTypesDtoList = allPriv_meet.stream().map(meetServices ->
                        new MeetTypesDto(meetServices.getId(), meetServices.getType())).collect(Collectors.toList());
            }
        } catch (Exception ex) {
            logger.warn("Can not get privileges from DB", ex);
        }
        return new MeetTypesListDto(meetTypesDtoList);
    }

    @Override
    public ServiceTypesDto getAllServiceTypes() {
        List<ServiceInS> collect = new ArrayList<>();
        try {
            collect = serviceInSRepository.findAll().stream().collect(Collectors.toList());
        } catch (Exception ex) {
            logger.warn("Can not services from DB", ex);
        }
        return new ServiceTypesDto(collect);
    }

    @Override
    public HopeTypesDto getAllHopes() {
        List<Hope> hopeList = new ArrayList<>();
        try {
            hopeList = hopeRepository.findAll().stream().collect(Collectors.toList());
        } catch (Exception ex) {
            logger.warn("Can not hopes from DB", ex);
        }
        return new HopeTypesDto(hopeList);
    }

    public PeopleViewCurrentUserDto convertToPeopleViewCurrentUserDto(People people) {
        if (people != null) {

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
        return null;
    }
}
