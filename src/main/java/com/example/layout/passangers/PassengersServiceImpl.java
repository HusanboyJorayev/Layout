package com.example.layout.passangers;

import com.example.layout.dto.ApiResponse;

import com.example.layout.no_fly_list.NoFlyList;
import com.example.layout.no_fly_list.NoFlyListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public ApiResponse<PassengersDto> getWithBaggageCheck(Integer id) {
        return this.passengersRepository.findByIdAndDeletedAtIsNull(id)
                .map(passengers -> ApiResponse.<PassengersDto>builder()
                        .success(true)
                        .message("Ok")
                        .data(this.passengersMapper.toDtoWithBaggageCheck(passengers))
                        .build())
                .orElse(ApiResponse.<PassengersDto>builder()
                        .code(-1)
                        .message("flightManifest is not found")
                        .build());
    }

    @Override
    public ApiResponse<PassengersDto> getWithBooking(Integer id) {
        return this.passengersRepository.findByIdAndDeletedAtIsNull(id)
                .map(passengers -> ApiResponse.<PassengersDto>builder()
                        .success(true)
                        .message("Ok")
                        .data(this.passengersMapper.toDtoWithBooking(passengers))
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
    public ApiResponse<Page<PassengersDto>> getPage(Integer page, Integer count) {
        Page<Passengers> airlinePage = this.passengersRepository.findAllByDeletedAtIsNull(PageRequest.of(page, count));
        if (airlinePage.isEmpty()) {
            return ApiResponse.<Page<PassengersDto>>builder()
                    .code(-1)
                    .message("Airlines are not found")
                    .build();
        }
        return ApiResponse.<Page<PassengersDto>>builder()
                .success(true)
                .message("Ok")
                .data(airlinePage.map(this.passengersMapper::toDto))
                .build();
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
