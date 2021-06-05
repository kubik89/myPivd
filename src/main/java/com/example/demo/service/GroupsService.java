package com.example.demo.service;

import com.example.demo.controller.GroupsController;
import com.example.demo.dao.BadRequestException;
import com.example.demo.dao.GroupRepository;
import com.example.demo.dao.PeopleRepository;
import com.example.demo.dto.*;
import com.example.demo.entity.Group;
import com.example.demo.entity.People;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class GroupsService implements IGroupService {

    private GroupRepository groupRepository;
    private PeopleRepository peopleRepository;
    String messageNoGroup = "We did not find any group";

    private static Logger logger = LoggerFactory.getLogger(GroupsController.class);

    @Autowired
    public GroupsService(GroupRepository groupRepository, PeopleRepository peopleRepository) {
        this.groupRepository = groupRepository;
        this.peopleRepository = peopleRepository;
    }

    @Override
    public Group saveGroup(GroupCreateDto group) {

        if (groupRepository.findGroupById(group.getNumber()) != null) {
            logger.error("group is exist and can not be added as new");
            return new Group();
        }

        Group myGroups = new Group();
        myGroups.setGroup_number(group.getNumber());

        Optional<People> responsibleID = peopleRepository.findById(group.getRespId());
        People people = responsibleID.orElseThrow(() ->
                new BadRequestException("Did not find any responsible in DB"));

        myGroups.setResponsible_name(people);
        logger.info("New group {} creating ", group.getNumber());
        return groupRepository.saveAndFlush(myGroups);
    }

    @Override
    public GroupGetViewDto getAllGroups() {
        List<GroupDto> list = groupRepository.findAll().stream().map(groups ->
                new GroupDto(groups.getGroup_number(), groups.getResponsible_name().getFname(),
                        groups.getResponsible_name().getLname()))
                .collect(Collectors.toList());
        return new GroupGetViewDto(list);
    }

    @Override
    public GroupDto getSomeGroupById(int id) {

        Group group = groupRepository.findGroupById(id);
        return new GroupDto(group.getGroup_number(), group.getResponsible_name().getFname(),
                group.getResponsible_name().getLname());
    }

    @Override
    public void deleteGroup(int id) {
        if (id <= 0) {
            logger.error("Can not delete group - incoming groupId equal or less than 0");
            return;
        }

        Group groupById = null;
        try {
            groupById = groupRepository.findGroupById(id);
            if (groupById != null) {
                groupRepository.delete(groupById);
                logger.info("group {} deleted successfully", groupById.getGroup_number());
            } else logger.error("Group did not find in DB, can not be deleted");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public Group updateGroup(GroupCreateDto group) {
        if (group.getNumber() <= 0) {
            logger.error("Group can not equal or less than 0");
            return new Group();
        }
        if (group.getRespId() == 0) {
            logger.error("Responsible person can not equal 0");
            return new Group();
        }
        try {
            Group groupByNumber = groupRepository.findGroupById(group.getNumber());
            if (groupByNumber == null) {
                logger.error("Group not found");
                return new Group();
            } else {
                try {
                    People respId = peopleRepository.getOne(group.getRespId());
                    groupByNumber.setResponsible_name(respId);

                    logger.info("Group {} updating, new responsible - {} {}", groupByNumber.getGroup_number(), respId.getLname(), respId.getFname());
                    return groupRepository.saveAndFlush(groupByNumber);
                } catch (Exception ex) {
                    logger.error(ex.getMessage());
                    return new Group();
                }
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return new Group();
        }
    }

    @Override
    public Group findGroupById(int groupId) {
        return groupRepository.findGroupById(groupId);
    }

    public GroupMembersDto peopleInGroup(int groupId) {
        List<People> allByGroup_number = peopleRepository.findAllByGroup_number(groupId);
        List<PeopleInGroups> list = new ArrayList<>();
        allByGroup_number.forEach(people ->
                list.add(new PeopleInGroups(people.getId(), people.getLname(), people.getFname())));
        return new GroupMembersDto(list);
    }

    public PeopleJustNameDto getResonsibleIdInGroup(int groupId) {
        int responsibleInGroup = groupRepository.findResponsibleInGroup(groupId);

        int id = peopleRepository.findById(responsibleInGroup).get().getId();
        String lname = peopleRepository.findById(responsibleInGroup).get().getLname();
        String fname = peopleRepository.findById(responsibleInGroup).get().getFname();
        return new PeopleJustNameDto(id, lname, fname);
    }

    public int getCountGroupMembers(int groupId) {
        return groupRepository.getCountOfGroupMembers(groupId);
    }

    public int getNextFreeGroupNumber() {
        List<Integer> grNumbers = new ArrayList<>();
        groupRepository.findAll().stream().forEach(group -> grNumbers.add(group.getGroup_number()));
        grNumbers.sort((o1, o2) -> o2 - o1);
        return grNumbers.get(0) + 1;
    }

}
