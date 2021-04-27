package com.example.demo.service;

import com.example.demo.dao.PeopleRepository;
import com.example.demo.dao.ResultRepository;
import com.example.demo.dto.DateMonthYearDto;
import com.example.demo.dto.ResultViewCreteDto;
import com.example.demo.dto.ResultViewWithPersonName;
import com.example.demo.entity.People;
import com.example.demo.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class ResultService implements IResultService {

    ResultRepository resultRepository;
    PeopleRepository peopleRepository;

    @Autowired
    public ResultService(ResultRepository resultRepository,
                         PeopleRepository peopleRepository) {
        this.resultRepository = resultRepository;
        this.peopleRepository = peopleRepository;
    }

    @Override
    public Result getResultMonthlyByUserId(int userId, int month) {
        return null;
    }

    @Override
    public List<Result> getAllResultsByUserId(int userId) {
        List<Result> resultList = new ArrayList<>();
        resultRepository.findAll().forEach(result -> {
            if (result.getPeople().getId() == userId) {
                resultList.add(result);
            }
        });
        return resultList;
    }

    @Override
    public List<Result> getAllResultsByUserId(int userId, String fromDate) {
        int year = Integer.parseInt(fromDate.substring(0, 4));
        int month = Integer.parseInt(fromDate.substring(5, 7));
        return resultRepository.findAll().stream().filter(
                result -> result.getPeople().getId() == userId &&
                        Integer.parseInt(result.getResultForDate().substring(0, 4)) >= year &&
                        Integer.parseInt(result.getResultForDate().substring(5, 7)) >= month)
                .collect(Collectors.toList());
    }

    @Override
    public List<Result> getAllResultsInGroupByGroupId(int groupId) {
        return null;
    }

    @Override
    public List<Result> getAllResultsInGroupByDate(String forDate) {
        int year = Integer.parseInt(forDate.substring(0, 4));
        int month = Integer.parseInt(forDate.substring(5, 7));

        List<Result> list = resultRepository.findAll().stream().filter(
                result -> Integer.parseInt(result.getResultForDate().substring(0, 4)) == year &&
                        Integer.parseInt(result.getResultForDate().substring(5, 7)) == month)
                .collect(Collectors.toList());

        return list;
    }

    @Override
    public List<Result> getAllResultsInGroupByDate(String forDate, boolean pio) {
        int year = Integer.parseInt(forDate.substring(0, 4));
        int month = Integer.parseInt(forDate.substring(5, 7));

        List<Result> list = resultRepository.findAll().stream().filter(
                result -> Integer.parseInt(result.getResultForDate().substring(0, 4)) == year &&
                        Integer.parseInt(result.getResultForDate().substring(5, 7)) == month&&
                result.getPeople().getPriv_service().getId()==1)
                .collect(Collectors.toList());

        return list;
    }

    @Override
    public List<Result> getAllResultsInGroupByDate(int groupId, String forDate) {
        int year = Integer.parseInt(forDate.substring(0, 4));
        int month = Integer.parseInt(forDate.substring(5, 7));

        List<Result> list = resultRepository.findAll().stream().filter(
                result -> result.getPeople().getGroup_numb().getGroup_number() == groupId &&
                        Integer.parseInt(result.getResultForDate().substring(0, 4)) == year &&
                        Integer.parseInt(result.getResultForDate().substring(5, 7)) == month)
                .collect(Collectors.toList());

        return list;
    }

    @Override
    public List<Result> getAllResultsPioneers() {
        List<Result> resultList = new ArrayList<>();
        resultRepository.findAll().forEach(result -> {
            if (result.getPeople().getPriv_service().getId() == 1) {
                resultList.add(result);
            }
        });
        return resultList;
    }

    @Override
    public List<Result> getAllResultsInMeet() {
        List<Result> resultList = new ArrayList<>();
        resultList.addAll(resultRepository.findAll());
        return resultList;
    }

//    @Override
//    public List<Result> getAllResultsInMonth(String forDate) {
//
//        List<Result> resultList = new ArrayList<>();
//        resultRepository.findAll().forEach(result -> {
//            String sDate1 = result.getResultForDate();
//            LocalDate date = LocalDate.parse(sDate1);
//            if (date.getMonth().getValue() == month) {
//                resultList.add(result);
//            }
//        });
//        return resultList;
//    }

    @Override
    public DateMonthYearDto getValueOfLastResultMonth() {

//        сьогоднішню дату повну в стрічці перетворити в мин.місяць і окремо витягнути номер місяця й рік
        LocalDate lastDayOfLastMonth = LocalDate.now().withDayOfMonth(1).minusDays(1);
        String date = lastDayOfLastMonth.toString();
        String monthName = lastDayOfLastMonth.getMonth().name();
        int year = lastDayOfLastMonth.getYear();
        return new DateMonthYearDto(date, monthName, year);
    }

    @Override
    public Result insert(ResultViewCreteDto result) {
        Result newResult = new Result();
        if (peopleRepository.existsById(result.getPeople_id())) {
            People people = peopleRepository.findById(result.getPeople_id()).get();
            newResult.setPeople(people);
            newResult.setHour(result.getHour());
            newResult.setPublication(result.getPublication());
            newResult.setVideo(result.getVideo());
            newResult.setP_v(result.getP_v());
            newResult.setB_learning(result.getB_learning());
            newResult.setResultForDate(result.getResultForDate());
            newResult.setDateInput(result.getDateInput());
            newResult.setComment(result.getComment());
            resultRepository.saveAndFlush(newResult);
        }
        return newResult;
    }
}
