package com.example.demo.dao;


import com.example.demo.entity.MeetServices;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetRepository extends JpaRepository<MeetServices, Integer> {
}
