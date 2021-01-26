package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Groupss")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Min(value = 1)
    private int number;

    @ManyToOne
    @JsonIgnore
    private People responsible;
}
