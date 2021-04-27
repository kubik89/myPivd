package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PeopleDto {
    private int id;
    private String lname;
    private String fname;
//    private String respInGroup;
    private int groupNumb;
    private String sex;
//    private String service_priv;
    private String birthday;


}
