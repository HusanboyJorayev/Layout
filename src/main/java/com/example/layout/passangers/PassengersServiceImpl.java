package com.example.layout.passangers;

import com.example.layout.dto.ApiResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PassengersServiceImpl implements PassengersService<Integer, PassengersDto> {
    private final PassengersMapper passengersMapper;
    private final PassengersRepository passengersRepository;

    @Override
    public ApiResponse<PassengersDto> create(PassengersDto passengersDto) {
        var passengers = this.passengersMapper.toEntity(passengersDto);
        passengers.setCreatedAt(LocalDateTime.now());
        this.passengersRepository.save(passengers);
        return ApiResponse.<PassengersDto>builder()
                .success(true)
                .message("Ok")
                .data(this.passengersMapper.toDto(passengers))
                .build();
    }

    @Override
    public ApiResponse<PassengersDto> get(Integer id) {
        return this.passengersRepository.findByIdAndDeletedAtIsNull(id)
                .map(passengers -> ApiResponse.<PassengersDto>builder()
                        .success(true)
                        .message("Ok")
                        .data(this.passengersMapper.toDto(passengers))
                        .build())
                .orElse(ApiResponse.<PassengersDto>builder()
                        .code(-1)
                        .message("flightManifest is not found")
                        .build());
    }

    @Override
    public ApiResponse<PassengersDto> delete(Integer id) {
        return this.passengersRepository.findByIdAndDeletedAtIsNull(id)
                .map(flights -> {
                    flights.setDeletedAt(LocalDateTime.now());
                    this.passengersRepository.delete(flights);
                    return ApiResponse.<PassengersDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.passengersMapper.toDto(flights))
                            .build();
                })
                .orElse(ApiResponse.<PassengersDto>builder()
                        .code(-1)
                        .message("flightManifest is not found")
                        .build());
    }

    @Override
    public ApiResponse<PassengersDto> update(PassengersDto passengersDto, Integer id) {
        return this.passengersRepository.findByIdAndDeletedAtIsNull(id)
                .map(passengers -> {
                    passengers.setUpdatedAt(LocalDateTime.now());
                    this.passengersMapper.update(passengers, passengersDto);
                    this.passengersRepository.save(passengers);
                    return ApiResponse.<PassengersDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.passengersMapper.toDto(passengers))
                            .build();
                })
                .orElse(ApiResponse.<PassengersDto>builder()
                        .code(-1)
                        .message("flightManifest is not found")
                        .build());
    }

    @Override
    public ApiResponse<List<PassengersDto>> getAll() {
        return Optional.ofNullable(this.passengersRepository.getAll().stream())
                .map(passengers -> ApiResponse.<List<PassengersDto>>builder()
                        .success(true)
                        .message("Ok")
                        .data(passengers.map(this.passengersMapper::toDto).toList())
                        .build())
                .orElse(ApiResponse.<List<PassengersDto>>builder()
                        .code(-1)
                        .message("flightManifest are not found")
                        .build());
    }
}
