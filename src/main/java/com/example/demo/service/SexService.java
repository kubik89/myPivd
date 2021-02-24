package com.example.demo.service;

import com.example.demo.dao.SexRepository;
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

    @Autowired
    public SexService(SexRepository sexRepository) {
        this.sexRepository = sexRepository;
    }

    @Override
    public SexDtoList getGenders() {
        List<Sex> sexList = sexRepository.getAllGenders();
        List<SexDto> sexDto = sexList.stream().map(sex -> new SexDto(sex.getId(), sex.getSexType().substring(0,1)))
                .collect(Collectors.toList());
        return new SexDtoList(sexDto);
    }
}
