package com.example.demo.controller;

import com.example.demo.dto.GroupCreateDto;
import com.example.demo.dto.GroupDto;
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

    private static Logger logger = LoggerFactory.getLogger(GroupsController.class);

    @GetMapping
    public List<GroupDto> getAllGroups() {
        return igroupService.getAllGroups();
    }

    @PostMapping
    public Group saveGroup(@RequestBody GroupCreateDto group) {
        logger.info("New group created: {}", group.getNumber());
        return igroupService.saveGroup(group);
    }
}
