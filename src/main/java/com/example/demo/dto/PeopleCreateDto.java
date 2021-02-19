package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class PeopleCreateDto {
    private String fname;
    private String lname;
    private int sex;
    private int groupNumb;
    private Date birthday;
}
