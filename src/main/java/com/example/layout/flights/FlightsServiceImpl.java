package com.example.layout.flights;

import com.example.layout.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FlightsServiceImpl implements FlightService<Integer, FlightsDto> {
    private final FlightsMapper flightsMapper;
    private final FlightsRepository flightsRepository;

    @Override
    public ApiResponse<FlightsDto> create(FlightsDto flightsDto) {
        var flights = this.flightsMapper.toEntity(flightsDto);
        flights.setCreatedAt(LocalDateTime.now());
        this.flightsRepository.save(flights);
        return ApiResponse.<FlightsDto>builder()
                .success(true)
                .message("Ok")
                .data(this.flightsMapper.toDto(flights))
                .build();
    }

    @Override
    public ApiResponse<FlightsDto> get(Integer id) {
        return this.flightsRepository.findByIdAndDeletedAtIsNull(id)
                .map(flights -> ApiResponse.<FlightsDto>builder()
                        .success(true)
                        .message("Ok")
                        .data(this.flightsMapper.toDto(flights))
                        .build())
                .orElse(ApiResponse.<FlightsDto>builder()
                        .code(-1)
                        .message("flightManifest is not found")
                        .build());
    }

    @Override
    public ApiResponse<FlightsDto> delete(Integer id) {
        return this.flightsRepository.findByIdAndDeletedAtIsNull(id)
                .map(flights -> {
                    flights.setDeletedAt(LocalDateTime.now());
                    this.flightsRepository.delete(flights);
                    return ApiResponse.<FlightsDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.flightsMapper.toDto(flights))
                            .build();
                })
                .orElse(ApiResponse.<FlightsDto>builder()
                        .code(-1)
                        .message("flightManifest is not found")
                        .build());
    }

    @Override
    public ApiResponse<FlightsDto> update(FlightsDto flightsDto, Integer id) {
        return this.flightsRepository.findByIdAndDeletedAtIsNull(id)
                .map(flights -> {
                    flights.setUpdatedAt(LocalDateTime.now());
                    this.flightsMapper.update(flights, flightsDto);
                    this.flightsRepository.save(flights);
                    return ApiResponse.<FlightsDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.flightsMapper.toDto(flights))
                            .build();
                })
                .orElse(ApiResponse.<FlightsDto>builder()
                        .code(-1)
                        .message("flightManifest is not found")
                        .build());
    }

    @Override
    public ApiResponse<List<FlightsDto>> getAll() {
        return Optional.ofNullable(this.flightsRepository.getAll().stream())
                .map(flights -> ApiResponse.<List<FlightsDto>>builder()
                        .success(true)
                        .message("Ok")
                        .data(flights.map(this.flightsMapper::toDto).toList())
                        .build())
                .orElse(ApiResponse.<List<FlightsDto>>builder()
                        .code(-1)
                        .message("flightManifest are not found")
                        .build());
    }
}
