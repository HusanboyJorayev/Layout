package com.example.layout.flights;

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
public class FlightsDto {
    private Integer id;
    private String arrivingGate;
    private String departingGate;
    private Integer airlineId;
    private Integer departingAirportId;
    private Integer arrivingAirportId;

    private List<BookingDto> booking;


    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
