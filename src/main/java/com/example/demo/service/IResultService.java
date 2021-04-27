package com.example.demo.service;

import com.example.demo.dto.DateMonthYearDto;
import com.example.demo.dto.ResultViewCreteDto;
import com.example.demo.entity.Result;

import java.text.ParseException;
import java.util.List;

public interface IResultService {

    Result getResultMonthlyByUserId(int userId, int month);
    List<Result> getAllResultsByUserId(int userId);
    List<Result> getAllResultsByUserId(int userId, String date);
    List<Result> getAllResultsInGroupByGroupId(int groupId);
    List<Result> getAllResultsPioneers();
    List<Result> getAllResultsInMeet();
//    List<Result> getAllResultsInMonth(int month) throws ParseException;
    List<Result> getAllResultsInGroupByDate(String forDate);
    List<Result> getAllResultsInGroupByDate(String forDate, boolean pio);
    List<Result> getAllResultsInGroupByDate(int groupId, String forDate);
    DateMonthYearDto getValueOfLastResultMonth();

    Result insert (ResultViewCreteDto result);


}
