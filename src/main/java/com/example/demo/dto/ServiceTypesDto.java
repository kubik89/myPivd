package com.example.demo.dto;

import com.example.demo.entity.ServiceInS;
import com.example.demo.entity.ServiceInS;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ServiceTypesDto {
    private List<ServiceInS> serviceList;
}
