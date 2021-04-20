package com.example.demo.dao;

import com.example.demo.dto.ResultViewWithPersonName;
import com.example.demo.entity.People;
import com.example.demo.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.lang.annotation.Native;
import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Integer> {
}
