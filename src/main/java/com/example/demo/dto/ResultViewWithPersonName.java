package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResultViewWithPersonName {
        private String personFName;
        private String personLName;
        private int hour;
        private int publication;
        private int video;
        private int p_v;
        private int b_learning;
        private String resultForDate;
        private String dateInput;
        private String comment;
    }
