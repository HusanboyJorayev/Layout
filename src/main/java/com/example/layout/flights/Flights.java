package com.example.layout.flights;

import com.example.layout.booking.Booking;
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
@Table(name = "flights")
public class Flights {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String arrivingGate;
    private String departingGate;
    private Integer airlineId;
    private Integer departingAirportId;
    private Integer arrivingAirportId;

    @OneToMany(mappedBy = "flightId",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Booking>booking;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

}
