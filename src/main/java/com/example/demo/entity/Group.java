package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "groupss")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @Min(value = 1)
    private int group_number;

    @ManyToOne
    @JsonIgnore
    private People responsible_name;

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", group_number=" + group_number +
                ", responsible_name=" + responsible_name +
                '}';
    }
}
