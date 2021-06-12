package com.example.demo.dao;

import com.example.demo.entity.Sex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SexRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Sex> getAllGenders() {
        String SQL = "SELECT * from SEX";
        return jdbcTemplate.query(SQL, new RowMapper<Sex>() {
            public Sex mapRow(ResultSet rs, int rowNum) throws SQLException {
                Sex sex = new Sex();
                sex.setId(rs.getInt("id"));
                sex.setSexType(rs.getString("sex_type"));

                return sex;
            }
        });
    }
}
