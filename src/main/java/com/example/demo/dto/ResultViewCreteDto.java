package com.example.demo.dto;

import com.example.demo.entity.People;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResultViewCreteDto {
    private int people_id;
    @Min(value = 1, message = "can not be 0")
    private int hour;
    private int publication;
    private int video;
    private int p_v;
    private int b_learning;
    private String resultForDate;
    private String dateInput;
    private String comment;
}
