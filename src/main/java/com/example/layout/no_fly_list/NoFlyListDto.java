package com.example.layout.no_fly_list;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoFlyListDto {

    private Integer id;
    private String activeFrom;
    private String activeTo;
    private String noFlyReason;
    private Integer passengerId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
