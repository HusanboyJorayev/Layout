package com.example.layout.passangers;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PassengersDto {
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
