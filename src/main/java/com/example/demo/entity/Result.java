package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Result {
    @Id
    private int id;
    @ManyToOne
    @JsonIgnore
    private People people;
    private int hour;
    private int publication;
    private int video;
    private int p_v;
    private int b_learning;
    private String resultForDate;
}
