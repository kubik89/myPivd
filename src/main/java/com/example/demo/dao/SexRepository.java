package com.example.demo.dao;

import com.example.demo.entity.Sex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SexRepository extends JpaRepository<Sex, Integer> {

    @Query("select s from Sex s")
    List<Sex> getAllGenders();
}
