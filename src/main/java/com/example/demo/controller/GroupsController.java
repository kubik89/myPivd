package com.example.demo.controller;

import com.example.demo.dto.GroupCreateDto;
import com.example.demo.dto.GroupDto;
import com.example.demo.dto.PeopleInGroups;
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
    public List<GroupDto> getAllGroups() {
        return igroupService.getAllGroups();
    }

    @GetMapping("/{id}")
    public GroupDto getGroupById(@PathVariable int id) {
        return igroupService.getSomeGroupById(id);
    }

    @GetMapping("/members/{groupId}")
    public List<PeopleInGroups> peopleInGroup(@PathVariable int groupId) {
        return igroupService.peopleInGroup(groupId);
    }

    @PostMapping
    public Group saveGroup(@RequestBody GroupCreateDto group) {
        return igroupService.saveGroup(group);
    }

    @PutMapping(value = "/{id}")
    public Group updateGroup(@PathVariable int id, @RequestBody Group group) {
        return igroupService.updateGroup(id, group);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteGroup(@PathVariable int id) {
        igroupService.deleteGroup(id);
    }
}
