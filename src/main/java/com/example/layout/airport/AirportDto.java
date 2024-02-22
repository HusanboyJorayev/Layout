package com.example.layout.airport;

import com.example.layout.flights.Flights;
import com.example.layout.flights.FlightsDto;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AirportDto {
    private Integer id;
    private String name;
    private String state;
    private String country;
    private String city;

    private List<FlightsDto> departingAirport;
    private List<FlightsDto> arrivingAirport;


    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
