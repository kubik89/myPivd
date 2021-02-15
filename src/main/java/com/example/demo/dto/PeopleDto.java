package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PeopleDto {
    private String lname;
    private String fname;
//    private String respInGroup;
    private int groupNumb;
    private String sex;
    private String service_priv;
}
