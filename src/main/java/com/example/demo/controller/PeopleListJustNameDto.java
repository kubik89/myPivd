package com.example.demo.controller;

import com.example.demo.dto.PeopleJustNameDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PeopleListJustNameDto {
    private List<PeopleJustNameDto> eldersOrHelpers;
}
