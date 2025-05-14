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

    @Enumerated(EnumType.STRING)
    @Column(name = "city")
    private ColombianCity city;

    @Column(name = "latitude")
    private Double lat;

    @Column(name = "longitude")
    private Double lng;

    private Double radius;
    private String disease;
    private Integer cases;
    private String region;

    @PrePersist
    @PreUpdate
    public void setLatLong() {
        if (city != null) {
            this.lat = city.getLatitude();
            this.lng = city.getLongitude();
        }
    }
}
