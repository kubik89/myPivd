package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter

public class PeopleCreateDto {
    private String fName;
    private String lName;
    private int sex;
//    private int groupNumb;
}
