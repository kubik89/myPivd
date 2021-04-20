package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
//    @JsonIgnore
    private People people;
    private int hour;
    private int publication;
    private int video;
    private int p_v;
    private int b_learning;
    private String resultForDate;
    private String dateInput;
    private String comment;
}
