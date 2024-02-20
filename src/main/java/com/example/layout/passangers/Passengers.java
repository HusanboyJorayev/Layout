package com.example.layout.passangers;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.*;
import jdk.jfr.DataAmount;
import lombok.*;

import java.time.LocalDateTime;

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

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
