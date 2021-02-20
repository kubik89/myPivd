package com.example.demo.dao;

import com.example.demo.dto.GroupCountResp;
import com.example.demo.dto.MeetTypesDto;
import com.example.demo.entity.MeetServices;
import com.example.demo.entity.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PeopleRepository extends JpaRepository<People, Integer> {

    @Query("select person from People person " +
            "where person.group_numb.group_number = :groupNumber order by person.lname")
    List<People> findAllByGroup_number(int groupNumber);

    @Query("select person from People person order by person.group_numb.group_number, person.lname")
    List<People> findAllPersons();

    @Query("select p from People p where p.priv_meet.id BETWEEN 2 and 3")
    List<People> getAllResp();

    @Query("select ms from MeetServices ms")
    List<MeetServices> getAllPriv_meet();

//    @Query("select person.lname, person.fname, person.group_numb.group_number, person.sex " +
//            "from People person group by person.group_numb.group_number")

}
