package com.example.layout.flights;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightsDto {
    private Integer id;
    private String arrivingGate;
    private String departingGate;
    private Integer airlineId;
    private Integer departingAirportId;
    private Integer arrivingAirportId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
