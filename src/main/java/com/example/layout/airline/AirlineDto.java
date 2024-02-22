package com.example.layout.airline;

import com.example.layout.flights.Flights;
import com.example.layout.flights.FlightsDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AirlineDto {
    private Integer id;
    private String code;
    private Integer name;
    private String country;

    private List<FlightsDto> flights;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
