package com.backend.dto;

import lombok.Data;

@Data
public class ImportResponseDTO {
    private Long id;
    private Integer amount;
    private String cityExit;
    private String cityEntry;
    private Double lat;
    private Double lng;
}
