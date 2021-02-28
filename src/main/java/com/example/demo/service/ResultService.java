package com.example.demo.service;

import com.example.demo.dao.ResultRepository;
import com.example.demo.entity.Result;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResultService implements IResultService {

    ResultRepository resultRepository;

    public ResultService(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    @Override
    public Result getResultMonthlyByUserId(int userId, int month) {
        return null;
    }

    @Override
    public List<Result> getAllResultsByUserId(int userId) {
        List<Result> resultList = new ArrayList<>();
        resultRepository.findAll().stream().forEach(result -> {
            if (result.getPeople().getId() == userId) {
                resultList.add(result);
            }
        });
        return resultList;
    }

    @Override
    public List<Result> getAllResultsInGroupByGroupId(int groupId) {
        List<Result> resultList = new ArrayList<>();
        resultRepository.findAll().stream().forEach(result -> {
            if (result.getPeople().getGroup_numb().getGroup_number() == groupId) {
                resultList.add(result);
            }
        });
        return resultList;
    }

    @Override
    public List<Result> getAllResultsPioneers() {
        List<Result> resultList = new ArrayList<>();
        resultRepository.findAll().stream().forEach(result -> {
            if (result.getPeople().getPriv_service().getId() == 1) {
                resultList.add(result);
            }
        });
        return resultList;
    }

    @Override
    public List<Result> getAllResultsInMeet() {
        List<Result> resultList = new ArrayList<>();
        resultRepository.findAll().stream().forEach(result -> resultList.add(result));
        return resultList;
    }

    @Override
    public List<Result> getAllResultsInMonth(int month) {

        List<Result> resultList = new ArrayList<>();
        resultRepository.findAll().stream().forEach(result -> {
            String sDate1 = result.getResultForDate();
            try {
                Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(sDate1);
                System.out.println(date1.getYear());
                if (date1.getYear() == month) {
                    resultList.add(result);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
        return resultList;
    }
}
