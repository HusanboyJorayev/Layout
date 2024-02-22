package com.example.layout.airport;

import com.example.layout.flights.Flights;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "airport")
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String state;
    private String country;
    private String city;

    @OneToMany(mappedBy = "departingAirportId",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Flights>departingAirport;

    @OneToMany(mappedBy = "arrivingAirportId",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Flights>arrivingAirport;


    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
