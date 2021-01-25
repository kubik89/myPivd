package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

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
//    @Min(message = "Group number must be min 1", value = 1)
    private Group group;

    private String fname;

    @NotBlank(message = "Пусте значення lName")
    private String lname;
    private String nadija_na;
    private String streetName;
    private String street_building_number;
    private int flat_number;
    private int home_phone;
    private int mob_phone;
    private int sex;


}
