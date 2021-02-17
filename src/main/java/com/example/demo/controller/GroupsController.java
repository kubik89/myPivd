package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.Group;
import com.example.demo.service.IGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/groups")
public class GroupsController {

    @Autowired
    private IGroupService igroupService;

    @GetMapping
    public GroupGetViewDto getAllGroups() {
        return igroupService.getAllGroups();
    }

    @GetMapping("/{id}")
    public GroupDto getGroupById(@PathVariable int id) {
        return igroupService.getSomeGroupById(id);
    }

//    @GetMapping("/members/{groupId}")
//    public GroupMembersDto peopleInGroup(@PathVariable int groupId) {
//        return igroupService.peopleInGroup(groupId);
//    }

    @GetMapping("/members/")
    public GroupMembersDto peopleInGroup(@RequestParam int groupId) {
        return igroupService.peopleInGroup(groupId);
    }

    @PostMapping
    public Group saveGroup(@RequestBody GroupCreateDto group) {
        return igroupService.saveGroup(group);
    }

    @PutMapping
    public Group updateGroup(@RequestBody GroupCreateDto group) {
        return igroupService.updateGroup(group);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteGroup(@PathVariable int id) {
        igroupService.deleteGroup(id);
    }

    @GetMapping("/byid/{id}")
    public Group findGroupById(@PathVariable int id) {
       return igroupService.findGroupById(id);
    }

    @GetMapping("/resp/{id}")
    public PeopleJustNameDto getResponsibleIdInGroup(@PathVariable int id) {
        return igroupService.getResonsibleIdInGroup(id);
    }

    @GetMapping("/count/{id}")
    public int getCountGroupMembers(@PathVariable int id) {
        return igroupService.getCountGroupMembers(id);
    }

}
