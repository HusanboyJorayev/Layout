package com.example.layout.passangers;

import com.example.layout.baggage_check.BaggageCheck;
import com.example.layout.baggage_check.BaggageCheckDto;
import com.example.layout.booking.Booking;
import com.example.layout.booking.BookingDto;
import com.example.layout.no_fly_list.NoFlyList;
import com.example.layout.no_fly_list.NoFlyListDto;
import com.example.layout.security_check.SecurityCheck;
import com.example.layout.security_check.SecurityCheckDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
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
    private List<SecurityCheckDto>securityCheck;
    private List<NoFlyListDto>noFlyList;



    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
