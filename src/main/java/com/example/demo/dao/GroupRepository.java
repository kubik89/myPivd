package com.example.demo.dao;

import com.example.demo.dto.GroupCountResp;
import com.example.demo.dto.GroupNrRespLFname;
import com.example.demo.entity.Group;
import com.example.demo.entity.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Integer> {

    @Query("select myGroup from Group myGroup where myGroup.group_number = :grnumber")
    Group findGroupById(int grnumber);

    @Query("select resp.responsible_name.id from Group resp where resp.group_number = :number")
    int findResponsibleInGroup(int number);

    @Query("SELECT COUNT(person.lname) from People person where person.group_numb.group_number = :id")
    int getCountOfGroupMembers(int id);

//    @Query("select person from People person where person.group_numb.group_number = :groupNumber order by person.lname")
//    List<People> findAllByGroup_number(int groupNumber);


}
