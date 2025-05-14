package com.backend.model;

import lombok.Getter;

@Getter
public enum ColombianCity {
    BOGOTA(4.7110, -74.0721),
    MEDELLIN(6.2442, -75.5812),
    CALI(3.4517, -76.5320),
    BARRANQUILLA(10.9685, -74.7813),
    CARTAGENA(10.4005, -75.5148),
    CUCUTA(7.8939, -72.5078),
    BUCARAMANGA(7.1254, -73.1198),
    MANIZALES(5.0700, -75.5180),
    PEREIRA(4.8133, -75.6962),
    VALLEDUPAR(10.4631, -73.2532),
    SANTA_MARTA(11.2408, -74.1990),
    NEIVA(2.9350, -75.2891),
    IBAGUE(4.4383, -75.2322),
    VILLAVICENCIO(4.1420, -73.6266),
    POPAYAN(2.4469, -76.6147),
    PASTO(1.2130, -77.2772),
    MONTERIA(8.7652, -75.8814),
    SINCELEJO(9.3039, -75.3958),
    RIOHACHA(11.5449, -72.9072),
    LETICIA(-4.2150, -69.9406),
    FLORENCIA(1.6178, -75.6054),
    SAN_ANDRES(12.5847, -81.7006),
    ARMENIA(4.5333, -75.6833),
    TUNJA(5.5369, -73.3677),
    QUIBDO(5.6925, -76.6628),
    YOPAL(5.5558, -72.3931),
    SANTA_MARTA_INT(11.1275, -74.2151),
    MIAMI(25.7617, -80.1918),
    HOUSTON(29.7604, -95.3698),
    HAMBURG(53.5511, 9.9937),
    MADRID(40.4168, -3.7038),
    BUENOS_AIRES(-34.6037, -58.3816);

    private final double latitude;
    private final double longitude;

    ColombianCity(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

}