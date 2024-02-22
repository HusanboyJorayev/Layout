package com.example.layout.passangers;

import com.example.layout.baggage_check.BaggageCheck;
import com.example.layout.booking.Booking;
import com.example.layout.no_fly_list.NoFlyList;
import com.example.layout.security_check.SecurityCheck;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.*;
import jdk.jfr.DataAmount;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "passengers")
public class Passengers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstname;
    private String lastname;
    private String dateOfBirth;
    private String countryOfCitizenShip;
    private String countryOfResidence;
    private String passportNumber;

    @OneToMany(mappedBy = "passengerId",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<BaggageCheck>baggageCheck;

    @OneToMany(mappedBy = "passengerId",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Booking>booking;

    @OneToMany(mappedBy = "passengerId",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<NoFlyList>noFlyList;

    @OneToMany(mappedBy = "passengerId",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<SecurityCheck>securityCheck;


    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
