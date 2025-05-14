package com.backend.dto;

import com.backend.model.ColombianCity;
import lombok.Data;

@Data
public class InfectionRequestDTO {
    private ColombianCity city;
    private Double radius;
    private String disease;
    private Integer cases;
    private String region;
}