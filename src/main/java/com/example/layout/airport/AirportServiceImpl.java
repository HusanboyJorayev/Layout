package com.example.layout.airport;

import com.example.layout.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AirportServiceImpl implements AirportService<Integer, AirportDto> {
    private final AirportRepository airportRepository;
    private final AirportMapper airportMapper;

    @Override
    public ApiResponse<AirportDto> create(AirportDto airportDto) {
        Airport airport = this.airportMapper.toEntity(airportDto);
        airport.setCreatedAt(LocalDateTime.now());
        this.airportRepository.save(airport);
        return ApiResponse.<AirportDto>builder()
                .success(true)
                .message("Ok")
                .data(this.airportMapper.toDto(airport))
                .build();
    }

    @Override
    public ApiResponse<AirportDto> get(Integer id) {
        return this.airportRepository.findByIdAndDeletedAtIsNull(id)
                .map(airport -> ApiResponse.<AirportDto>builder()
                        .success(true)
                        .message("Ok")
                        .data(this.airportMapper.toDto(airport))
                        .build())
                .orElse(ApiResponse.<AirportDto>builder()
                        .code(-1)
                        .message("Airline is not found")
                        .build());
    }

    @Override
    public ApiResponse<AirportDto> delete(Integer id) {
        return this.airportRepository.findByIdAndDeletedAtIsNull(id)
                .map(airport -> {
                    airport.setDeletedAt(LocalDateTime.now());
                    this.airportRepository.delete(airport);
                    return ApiResponse.<AirportDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.airportMapper.toDto(airport))
                            .build();
                })
                .orElse(ApiResponse.<AirportDto>builder()
                        .code(-1)
                        .message("Airline is not found")
                        .build());
    }

    @Override
    public ApiResponse<AirportDto> update(AirportDto airportDto, Integer id) {
        return this.airportRepository.findByIdAndDeletedAtIsNull(id)
                .map(airport -> {
                    airport.setUpdatedAt(LocalDateTime.now());
                    this.airportMapper.update(airport, airportDto);
                    this.airportRepository.save(airport);
                    return ApiResponse.<AirportDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.airportMapper.toDto(airport))
                            .build();
                })
                .orElse(ApiResponse.<AirportDto>builder()
                        .code(-1)
                        .message("airline is not found")
                        .build());
    }

    @Override
    public ApiResponse<List<AirportDto>> getAll() {
        return Optional.ofNullable(this.airportRepository.getAll().stream())
                .map(airport -> ApiResponse.<List<AirportDto>>builder()
                        .success(true)
                        .message("Ok")
                        .data(airport.map(this.airportMapper::toDto).toList())
                        .build())
                .orElse(ApiResponse.<List<AirportDto>>builder()
                        .code(-1)
                        .message("Airlines are not found")
                        .build());
    }
}
