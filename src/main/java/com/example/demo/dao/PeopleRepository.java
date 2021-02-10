package com.example.demo.dao;

import com.example.demo.entity.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PeopleRepository extends JpaRepository<People, Integer> {

    @Query("select person from People person " +
            "where person.group_numb.group_number = :groupNumber order by person.lname")
    List<People> findAllByGroup_number(int groupNumber);

}
