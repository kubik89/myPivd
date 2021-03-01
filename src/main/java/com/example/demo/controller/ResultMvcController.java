package com.example.demo.controller;

import com.example.demo.dto.DateMonthYearDto;
import com.example.demo.dto.GroupCreateDto;
import com.example.demo.dto.PeopleJustNameDto;
import com.example.demo.dto.ResultViewCreteDto;
import com.example.demo.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/result")
public class ResultMvcController {

    @Autowired
    @Qualifier(value = "groupRestTemplate")
    private RestTemplate restTemlpate;

    String getAllNames = "http://localhost:8081/people/justName";
    String insertResult = "http://localhost:8081/res/create";
    String getLastMonthLastDay = "http://localhost:8081/res/lastMonthValue";

    @GetMapping("/")
    public String result(Model model) {

        ResponseEntity<PeopleJustNameDto[]> justNameEntity = restTemlpate.exchange(getAllNames, HttpMethod.GET,
                HttpEntity.EMPTY, PeopleJustNameDto[].class);
        model.addAttribute("allPersonName", justNameEntity.getBody());

        ResponseEntity<DateMonthYearDto> dateLastMonth = restTemlpate.exchange(getLastMonthLastDay, HttpMethod.GET,
                HttpEntity.EMPTY, DateMonthYearDto.class);
        model.addAttribute("dateLastMonth", dateLastMonth.getBody());

        model.addAttribute("currentDate", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));


        model.addAttribute("newResult", new ResultViewCreteDto());

        return "resultForm";
    }

    @PostMapping("/create")
    public String createResult(ResultViewCreteDto newResult) {
        HttpEntity<ResultViewCreteDto> httpEntity = new HttpEntity<>(newResult, HttpHeaders.EMPTY);
        restTemlpate.exchange(insertResult, HttpMethod.POST, httpEntity, Result.class);

        System.out.println(newResult);

        return "redirect:/result/";
    }


}
