package com.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "infection")
@Data
public class InfectionRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double lat;
    private Double lng;
    private Double radius;
    private String disease;
    private Integer cases;
    private String region;
}
