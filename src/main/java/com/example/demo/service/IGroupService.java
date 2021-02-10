package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.entity.Group;

import java.util.List;

public interface IGroupService {

    Group saveGroup (GroupCreateDto groupDto);
    List<GroupDto> getAllGroups();
    GroupDto getSomeGroupById(int id);
    void deleteGroup(int id);
    Group updateGroup(int id, Group group);
    GroupMembersDto peopleInGroup(int groupId);

    Group findGroupById(int groupId);

    PeopleJustNameDto getResonsibleIdInGroup(int groupId);

    int getCountGroupMembers(int groupId);
}
