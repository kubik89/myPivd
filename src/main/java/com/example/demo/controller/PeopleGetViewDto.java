package com.example.demo.controller;

import com.example.demo.dto.PeopleDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PeopleGetViewDto {
    private List<PeopleDto> people;
}
