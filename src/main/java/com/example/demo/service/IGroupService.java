package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.entity.Group;

import java.util.List;

public interface IGroupService {

    Group saveGroup (GroupCreateDto groupDto);
    GroupGetViewDto getAllGroups();
    GroupDto getSomeGroupById(int id);
    ResponseContainer deleteGroup(int id);
    Group updateGroup(GroupCreateDto group);
    GroupMembersDto peopleInGroup(int groupId);

    Group findGroupById(int groupId);

    PeopleJustNameDto getResponsibleIdInGroup(int groupId);

    int getCountGroupMembers(int groupId);

    int getNextFreeGroupNumber();

}
