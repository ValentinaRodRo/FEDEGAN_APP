package com.backend.dto;

import lombok.Data;

@Data
public class InfectionRequestDTO {
    private Double lat;
    private Double lng;
    private Double radius;
    private String disease;
    private Integer cases;
    private String region;
}