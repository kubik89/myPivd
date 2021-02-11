package com.example.demo.dao;

import com.example.demo.dto.GroupCountResp;
import com.example.demo.entity.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PeopleRepository extends JpaRepository<People, Integer> {

    @Query("select person from People person " +
            "where person.group_numb.group_number = :groupNumber order by person.lname")
    List<People> findAllByGroup_number(int groupNumber);

    @Query("select person from People person order by person.group_numb.group_number")
    List<People> findAllPersons();

//    @Query("select person.lname, person.fname, person.group_numb.group_number, person.sex " +
//            "from People person group by person.group_numb.group_number")

}
