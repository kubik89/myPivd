package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.NamedEntityGraph;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GroupMembersCountDto {
    private int count;
}
