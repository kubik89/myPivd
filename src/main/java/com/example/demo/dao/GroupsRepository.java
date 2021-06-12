package com.example.demo.dao;

import com.example.demo.dto.MeetTypesDto;
import com.example.demo.entity.Group;
import com.example.demo.entity.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class GroupsRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Group findGroupById(int grNumber) {
        String SQL = "select * from groupss where group_number = ?";
        Group allGroupsResponce = jdbcTemplate.queryForObject(SQL, new Object[]{grNumber}, GroupsRepository::getAllGrous);
        return allGroupsResponce;
    }
    public static Group getAllGrous(ResultSet result, int rowNum) throws SQLException {
        Group group = new Group();
        group.setId(result.getInt("id"));
        group.setGroup_number(result.getInt("group_number"));
        group.setResponsible_name(result.getObject("responsible_name_id", People.class));
        People people = new People();
//        people.setId(result.get);

        return group;
    }
}
