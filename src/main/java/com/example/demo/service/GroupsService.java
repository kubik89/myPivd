package com.example.demo.service;

import com.example.demo.controller.GroupsController;
import com.example.demo.dao.BadRequestException;
import com.example.demo.dao.GroupRepository;
import com.example.demo.dao.PeopleRepository;
import com.example.demo.dto.GroupCreateDto;
import com.example.demo.dto.GroupDto;
import com.example.demo.entity.Group;
import com.example.demo.entity.People;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        Group myGroups = new Group();

        myGroups.setNumber(group.getNumber());

        Optional<People> responsibleID = peopleRepository.findById(group.getRespId());
        People people = responsibleID.orElseThrow(() ->
                new BadRequestException("I did not find any responsible in DB"));
        myGroups.setResponsible(people);
        logger.info("New group {} created ", group.getNumber());
        return groupRepository.saveAndFlush(myGroups);
    }

    @Override
    public List<GroupDto> getAllGroups() {
        return groupRepository.findAll().stream().map(groups ->
                new GroupDto(groups.getNumber(), groups.getResponsible().getLname())).collect(Collectors.toList());
    }

    @Override
    public Group getSomeGroupById(int id) {
        Optional<Group> groupId = groupRepository.findById(id);
        Group group = groupId.orElseThrow(() -> new BadRequestException(messageNoGroup));
        return group;
    }

    @Override
    public void deleteGroup(int id) {
        if (groupRepository.existsById(id)) {
            groupRepository.deleteById(id);
            logger.info("Group with id {} deleted", id);
        } else {
            throw new BadRequestException(messageNoGroup);
        }
    }

    @Override
    public Group updateGroup(int id, Group group) {
        if (groupRepository.existsById(id)) {
            group.setId(id);
            logger.info("Group id {} updated", group.getId());
            return groupRepository.saveAndFlush(group);
        } else {
            throw new BadRequestException(messageNoGroup);
        }
    }
}
