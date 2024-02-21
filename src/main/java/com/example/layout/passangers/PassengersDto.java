package com.example.layout.passangers;

import com.example.layout.baggage_check.BaggageCheck;
import com.example.layout.baggage_check.BaggageCheckDto;
import com.example.layout.booking.Booking;
import com.example.layout.booking.BookingDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

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

    private List<BaggageCheckDto> baggageCheck;
    private List<BookingDto>booking;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
