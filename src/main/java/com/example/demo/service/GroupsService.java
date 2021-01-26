package com.example.demo.service;

import com.example.demo.dao.BadRequestException;
import com.example.demo.dao.GroupRepository;
import com.example.demo.dao.PeopleRepository;
import com.example.demo.dto.GroupCreateDto;
import com.example.demo.dto.GroupDto;
import com.example.demo.entity.Group;
import com.example.demo.entity.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GroupsService implements IGroupService {

    private GroupRepository groupRepository;
    private PeopleRepository peopleRepository;

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
        return groupRepository.saveAndFlush(myGroups);
    }

    @Override
    public List<GroupDto> getAllGroups() {
        return groupRepository.findAll().stream().map(groups ->
                new GroupDto(groups.getNumber(), groups.getResponsible().getLname())).collect(Collectors.toList());
    }

    @Override
    public Group getSomeGroupById(int id) {
        return null;
    }

    @Override
    public void deleteGroup() {

    }

    @Override
    public Group updateGroup(Group group) {
        return null;
    }
}
