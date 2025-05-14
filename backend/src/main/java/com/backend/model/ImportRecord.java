package com.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "import")
@Data
public class ImportRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "city_exit")
    private ColombianCity cityExit;

    @Enumerated(EnumType.STRING)
    @Column(name = "city_entry")
    private ColombianCity cityEntry;

    @Column(name = "latitude")
    private Double lat;

    @Column(name = "longitude")
    private Double lng;

    @PrePersist
    @PreUpdate
    public void setLatLong() {
        if (cityEntry != null) {
            this.lat = cityEntry.getLatitude();
            this.lng = cityEntry.getLongitude();
        }
    }
}