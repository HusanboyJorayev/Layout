package com.example.layout.airport;

import com.example.layout.airline.Airline;
import com.example.layout.airline.AirlineDto;
import com.example.layout.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public ApiResponse<AirportDto> getWithDepartingAirport(Integer id) {
        return this.airportRepository.findByIdAndDeletedAtIsNull(id)
                .map(airport -> ApiResponse.<AirportDto>builder()
                        .success(true)
                        .message("Ok")
                        .data(this.airportMapper.toDtoWithDepartingAirport(airport))
                        .build())
                .orElse(ApiResponse.<AirportDto>builder()
                        .code(-1)
                        .message("Airline is not found")
                        .build());
    }
    @Override
    public ApiResponse<AirportDto> getWithArrivingAirport(Integer id) {
        return this.airportRepository.findByIdAndDeletedAtIsNull(id)
                .map(airport -> ApiResponse.<AirportDto>builder()
                        .success(true)
                        .message("Ok")
                        .data(this.airportMapper.toDtoWithArrivingAirport(airport))
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
    public ApiResponse<Page<AirportDto>> getPage(Integer page, Integer count) {
        Page<Airport> airlinePage = this.airportRepository.findAllByDeletedAtIsNull(PageRequest.of(page, count));
        if (airlinePage.isEmpty()) {
            return ApiResponse.<Page<AirportDto>>builder()
                    .code(-1)
                    .message("Airlines are not found")
                    .build();
        }
        return ApiResponse.<Page<AirportDto>>builder()
                .success(true)
                .message("Ok")
                .data(airlinePage.map(this.airportMapper::toDto))
                .build();
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
