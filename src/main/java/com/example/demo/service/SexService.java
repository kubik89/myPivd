package com.example.demo.service;

import com.example.demo.dao.MeetsRepository;
import com.example.demo.dao.SexRepository;
import com.example.demo.dto.MeetTypesDto;
import com.example.demo.dto.SexDto;
import com.example.demo.dto.SexDtoList;
import com.example.demo.entity.Sex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SexService implements ISexService {

    SexRepository sexRepository;
    MeetsRepository meetsRepository;

    @Autowired
    public SexService(SexRepository sexRepository, MeetsRepository meetsRepository) {
        this.sexRepository = sexRepository;
        this.meetsRepository = meetsRepository;
    }

    @Override
    public SexDtoList getGenders() {
        List<Sex> sexList = sexRepository.getAllGenders();
        List<SexDto> sexDto = sexList.stream().map(sex -> new SexDto(sex.getId(), sex.getSexType()))
                .collect(Collectors.toList());
        return new SexDtoList(sexDto);
    }

    public List<MeetTypesDto> getAllMeets() {
        return meetsRepository.getAllMeets();
    }
}
