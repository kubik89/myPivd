package com.example.demo.dto;

import com.example.demo.entity.People;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResultViewCreteDto {
    private int people_id;
    private int hour;
    private int publication;
    private int video;
    private int p_v;
    private int b_learning;
    private String resultForDate;
    private String dateInput;
}