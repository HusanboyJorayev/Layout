package com.example.layout.airline;

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
public class AirlineServiceImpl implements AirlineService<Integer, AirlineDto> {

    private final AirlineRepository airlineRepository;
    private final AirlineMapper airlineMapper;

    @Override
    public ApiResponse<AirlineDto> create(AirlineDto airlineDto) {
        Airline airline = this.airlineMapper.toEntity(airlineDto);
        airline.setCreatedAt(LocalDateTime.now());
        this.airlineRepository.save(airline);
        return ApiResponse.<AirlineDto>builder()
                .success(true)
                .message("Ok")
                .data(this.airlineMapper.toDto(airline))
                .build();
    }

    @Override
    public ApiResponse<AirlineDto> get(Integer id) {
        return this.airlineRepository.findByIdAndDeletedAtIsNull(id)
                .map(airline -> ApiResponse.<AirlineDto>builder()
                        .success(true)
                        .message("Ok")
                        .data(this.airlineMapper.toDto(airline))
                        .build())
                .orElse(ApiResponse.<AirlineDto>builder()
                        .code(-1)
                        .message("Airline is not found")
                        .build());
    }

    @Override
    public ApiResponse<AirlineDto> getWithFlights(Integer id) {
        return this.airlineRepository.findByIdAndDeletedAtIsNull(id)
                .map(airline -> ApiResponse.<AirlineDto>builder()
                        .success(true)
                        .message("Ok")
                        .data(this.airlineMapper.toDtoWithFlights(airline))
                        .build())
                .orElse(ApiResponse.<AirlineDto>builder()
                        .code(-1)
                        .message("Airline is not found")
                        .build());
    }

    @Override
    public ApiResponse<AirlineDto> delete(Integer id) {
        return this.airlineRepository.findByIdAndDeletedAtIsNull(id)
                .map(airline -> {
                    airline.setDeletedAt(LocalDateTime.now());
                    this.airlineRepository.delete(airline);
                    return ApiResponse.<AirlineDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.airlineMapper.toDto(airline))
                            .build();
                })
                .orElse(ApiResponse.<AirlineDto>builder()
                        .code(-1)
                        .message("Airline is not found")
                        .build());
    }

    @Override
    public ApiResponse<Page<AirlineDto>> getPage(Integer page, Integer count) {
        Page<Airline> airlinePage = this.airlineRepository.findAllByDeletedAtIsNull(PageRequest.of(page, count));
        if (airlinePage.isEmpty()) {
            return ApiResponse.<Page<AirlineDto>>builder()
                    .code(-1)
                    .message("Airlines are not found")
                    .build();
        }
        return ApiResponse.<Page<AirlineDto>>builder()
                .success(true)
                .message("Ok")
                .data(airlinePage.map(this.airlineMapper::toDto))
                .build();
    }

    @Override
    public ApiResponse<AirlineDto> update(AirlineDto airlineDto, Integer id) {
        return this.airlineRepository.findByIdAndDeletedAtIsNull(id)
                .map(airline -> {
                    airline.setUpdatedAt(LocalDateTime.now());
                    this.airlineMapper.update(airline, airlineDto);
                    this.airlineRepository.save(airline);
                    return ApiResponse.<AirlineDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.airlineMapper.toDto(airline))
                            .build();
                })
                .orElse(ApiResponse.<AirlineDto>builder()
                        .code(-1)
                        .message("airline is not found")
                        .build());
    }

    @Override
    public ApiResponse<List<AirlineDto>> getAll() {
        return Optional.ofNullable(this.airlineRepository.getAll().stream())
                .map(airline -> ApiResponse.<List<AirlineDto>>builder()
                        .success(true)
                        .message("Ok")
                        .data(airline.map(this.airlineMapper::toDto).toList())
                        .build())
                .orElse(ApiResponse.<List<AirlineDto>>builder()
                        .code(-1)
                        .message("Airlines are not found")
                        .build());

    }

    @Override
    public ApiResponse<List<Integer>> getAllAirlinesCode() {
        return Optional.ofNullable(this.airlineRepository.getAllAirlinesCode().stream())
                .map(integer -> ApiResponse.<List<Integer>>builder()
                        .success(true)
                        .message("Ok")
                        .data(integer.toList())
                        .build())
                .orElse(ApiResponse.<List<Integer>>builder()
                        .code(-1)
                        .message("Airlines are not found")
                        .build());
    }
}
