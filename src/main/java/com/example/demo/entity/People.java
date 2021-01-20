package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class People {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Пусте значення fName")
    private String fname;
    @NotBlank(message = "Пусте значення lName")
    private String lname;
    private String nadija_na;

//    @Min(message = "Group number must be min 1", value = 1)
    private int group_numb;
    private String streetName;
    private String street_building_number;
    private int flat_number;
    private int home_phone;
    private int mob_phone;
    private int sex;


}
