package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PeopleViewCurrentUserDto {
    private int id;
    private String fname;
    private String lname;
    private int group;
////    private int nadija_na;
    private String street_name;
    private String street_building_number;
    private int flat_number;
    private int home_phone;
    private int mob_phone;
    private String sex;
    private String birthday;

}


