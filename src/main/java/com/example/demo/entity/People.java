package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class People {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JsonProperty(value = "group_numb_id")
//    @Min(message = "Group number must be min 1", value = 1)
    private Group group_numb;

    @NotBlank(message = "Пусте значення Ім'я")
    private String fname;

    @NotBlank(message = "Пусте значення Прізвище")
    private String lname;
    private String street_name;
    private String street_building_number;
    private int flat_number;
    private int home_phone;
    private int mob_phone;
    private String birthday;
    private String date_chreshchennja;

    @ManyToOne
    private Hope nadija_na;

    @ManyToOne
    private ServiceInS priv_service;

    @ManyToOne
    private MeetServices priv_meet;

    @ManyToOne
    @JsonProperty(value = "sex")
    private Sex sex;



}
