package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PeopleDto {
    private String lName;
    private String fName;
//    private String respInGroup;
    private int groupNumb;
    private int sex;
}
