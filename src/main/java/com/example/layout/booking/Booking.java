package com.example.layout.booking;

import com.example.layout.baggage.Baggage;
import com.example.layout.baggage_check.BaggageCheck;
import com.example.layout.boarding_pass.BoardingPass;
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
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String status;
    private Integer flightId;
    private Integer passengerId;
    private String platform;

    @OneToMany(mappedBy = "bookingId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Baggage> baggage;

    @OneToMany(mappedBy = "bookingId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<BaggageCheck> baggageCheck;

    @OneToMany(mappedBy = "bookingId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<BoardingPass> boardingPass;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
