package com.example.demo.controller;

import com.example.demo.dto.DateMonthYearDto;
import com.example.demo.dto.ResultViewCreteDto;
import com.example.demo.entity.Result;
import com.example.demo.service.IResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/res")
public class ResultController {

    @Autowired
    IResultService iResultService;

    @GetMapping("/user/{userId}")
    public List<Result> getUserResult(@PathVariable int userId) {
        return iResultService.getAllResultsByUserId(userId);
    }

    @GetMapping("/user/{userId}/{date}")
    public List<Result> getUserResult(@PathVariable int userId, @PathVariable String date) {
        return iResultService.getAllResultsByUserId(userId, date);
    }

    @GetMapping("/group/{groupId}")
    public List<Result> getGroupResult(@PathVariable int groupId) {
        return iResultService.getAllResultsInGroupByGroupId(groupId);
    }

    @GetMapping("/{forDate}")
    public List<Result> getGrResultsByDate(@PathVariable String forDate) {
        return iResultService.getAllResultsInGroupByDate(forDate);
    }

    @GetMapping("/{forDate}/pio={pio}")
    public List<Result> getGrResultsByDate(@PathVariable String forDate, @PathVariable boolean pio) {
        return iResultService.getAllResultsInGroupByDate(forDate, pio);
    }

    @GetMapping("/{groupId}/{forDate}")
    public List<Result> getGrResultsByDate(@PathVariable int groupId, @PathVariable String forDate) {
        return iResultService.getAllResultsInGroupByDate(groupId, forDate);
    }

    @GetMapping("/meet")
    public List<Result> getAllResults() {
        return iResultService.getAllResultsInMeet();
    }

    @GetMapping("/meet/pioneers")
    public List<Result> getAllPioneerResults() {
        return iResultService.getAllResultsPioneers();
    }

//    @GetMapping("/meet/month/{month}")
//    public List<Result> getAllPioneerResults(@PathVariable int month) throws ParseException {
//        return iResultService.getAllResultsInMonth(month);
//    }

    @GetMapping("/lastMonthValue")
    public DateMonthYearDto getValueOfLastMonth() {
        return iResultService.getValueOfLastResultMonth();
    }

    @PostMapping()
    public Result insert (@RequestBody @Valid ResultViewCreteDto result) {
        return iResultService.insert(result);
    }

}
