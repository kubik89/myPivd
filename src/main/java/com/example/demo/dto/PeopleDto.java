package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PeopleDto {
    private String lName;
    private String fName;
    private String respInGroup;
    private int groupNumb;
}
