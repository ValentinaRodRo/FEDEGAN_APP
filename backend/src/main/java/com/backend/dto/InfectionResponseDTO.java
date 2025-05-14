package com.backend.dto;

import lombok.Data;

@Data
public class InfectionResponseDTO {
    private Long id;
    private String city;
    private Double lat;
    private Double lng;
    private Double radius;
    private String disease;
    private Integer cases;
    private String region;
}