package com.example.demo.service;

import com.example.demo.dto.GroupCreateDto;
import com.example.demo.dto.GroupDto;
import com.example.demo.entity.Group;

import java.util.List;

public interface IGroupService {

    Group saveGroup (GroupCreateDto groupDto);
    List<GroupDto> getAllGroups();
    Group getSomeGroupById(int id);
    void deleteGroup(int id);
    Group updateGroup(int id, Group group);

}
