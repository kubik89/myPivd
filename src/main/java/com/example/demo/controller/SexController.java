package com.example.demo.controller;

import com.example.demo.dto.MeetTypesDto;
import com.example.demo.dto.SexDtoList;
import com.example.demo.service.ISexService;
import com.example.demo.service.SexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RequestMapping(value = "/sex")
@RestController
public class SexController {

    @Autowired
    private ISexService iSexService;

    @Autowired
    private SexService sexService;

    @GetMapping("/getGenders")
    public SexDtoList getGenders() {
        return iSexService.getGenders();
    }

    @GetMapping("/meets")
    public List<MeetTypesDto> getAllMeets() {
        return sexService.getAllMeets();
    }
}
