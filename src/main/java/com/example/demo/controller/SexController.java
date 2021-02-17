package com.example.demo.controller;

import com.example.demo.dto.SexDtoList;
import com.example.demo.service.ISexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/sex")
@RestController
public class SexController {

    @Autowired
    private ISexService iSexService;

    @GetMapping("/getGenders")
    public SexDtoList getGenders() {
        return iSexService.getGenders();
    }
}
