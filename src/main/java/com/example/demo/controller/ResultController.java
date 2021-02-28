package com.example.demo.controller;

import com.example.demo.entity.Result;
import com.example.demo.service.IResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/result")
public class ResultController {

    @Autowired
    IResultService iResultService;

    @GetMapping("/user/{userId}")
    public List<Result> getUserResult(@PathVariable int userId) {
        return iResultService.getAllResultsByUserId(userId);
    }

    @GetMapping("/group/{groupId}")
    public List<Result> getGroupResult(@PathVariable int groupId) {
        return iResultService.getAllResultsInGroupByGroupId(groupId);
    }

    @GetMapping("/meet")
    public List<Result> getAllResults() {
        return iResultService.getAllResultsInMeet();
    }

    @GetMapping("/meet/pioneers")
    public List<Result> getAllPioneerResults() {
        return iResultService.getAllResultsPioneers();
    }

    @GetMapping("/meet/month/{month}")
    public List<Result> getAllPioneerResults(@PathVariable int month) throws ParseException {
        return iResultService.getAllResultsInMonth(month);
    }

}
