package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class PeopleCreateDto {
    private int id;
    private String fname;
    private String lname;
    private int sex;
    private int groupNumb;
    private int priv_meet;
    private int serviceInS;
    private String street_name;
    private String street_building_number;
    private int flat_number;
    private int home_phone;
    private int mob_phone;
    private int hope_id;


//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private String birthday;
//
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private String date_chreshchennja;

}
