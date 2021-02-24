package com.example.demo.dto;

import com.example.demo.entity.Hope;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HopeTypesDto {
    private List<Hope> hopeList;
}
