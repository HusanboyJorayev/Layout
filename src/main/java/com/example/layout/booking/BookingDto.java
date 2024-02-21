package com.example.layout.booking;

import com.example.layout.baggage.Baggage;
import com.example.layout.baggage.BaggageDto;
import com.example.layout.baggage_check.BaggageCheck;
import com.example.layout.baggage_check.BaggageCheckDto;
import com.example.layout.boarding_pass.BoardingPass;
import com.example.layout.boarding_pass.BoardingPassDto;
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
public class BookingDto {
    private Integer id;
    private String status;
    private Integer flightId;
    private Integer passengerId;
    private String platform;

    private List<BaggageDto> baggage;
    private List<BaggageCheckDto>baggageCheck;
    private List<BoardingPassDto> boardingPass;




    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
