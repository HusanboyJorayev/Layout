package com.example.layout.baggage_check;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaggageCheckDto {
    private Integer id;
    private String result;
    private Integer bookingId;
    private Integer passengerId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
