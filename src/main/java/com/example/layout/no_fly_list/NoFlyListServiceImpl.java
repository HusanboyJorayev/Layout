package com.example.layout.no_fly_list;

import com.example.layout.dto.ApiResponse;
import com.example.layout.flights.Flights;
import com.example.layout.flights.FlightsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class NoFlyListServiceImpl implements NoFlyListService<Integer, NoFlyListDto> {
    private final NoFlyListMapper noFlyListMapper;
    private final NoFlyListRepository noFlyListRepository;

    @Override
    public ApiResponse<NoFlyListDto> create(NoFlyListDto noFlyListDto) {
        var flights = this.noFlyListMapper.toEntity(noFlyListDto);
        flights.setCreatedAt(LocalDateTime.now());
        this.noFlyListRepository.save(flights);
        return ApiResponse.<NoFlyListDto>builder()
                .success(true)
                .message("Ok")
                .data(this.noFlyListMapper.toDto(flights))
                .build();
    }

    @Override
    public ApiResponse<NoFlyListDto> get(Integer id) {
        return this.noFlyListRepository.findByIdAndDeletedAtIsNull(id)
                .map(flights -> ApiResponse.<NoFlyListDto>builder()
                        .success(true)
                        .message("Ok")
                        .data(this.noFlyListMapper.toDto(flights))
                        .build())
                .orElse(ApiResponse.<NoFlyListDto>builder()
                        .code(-1)
                        .message("flightManifest is not found")
                        .build());
    }

    @Override
    public ApiResponse<NoFlyListDto> delete(Integer id) {
        return this.noFlyListRepository.findByIdAndDeletedAtIsNull(id)
                .map(flights -> {
                    flights.setDeletedAt(LocalDateTime.now());
                    this.noFlyListRepository.delete(flights);
                    return ApiResponse.<NoFlyListDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.noFlyListMapper.toDto(flights))
                            .build();
                })
                .orElse(ApiResponse.<NoFlyListDto>builder()
                        .code(-1)
                        .message("flightManifest is not found")
                        .build());
    }

    @Override
    public ApiResponse<Page<NoFlyListDto>> getPage(Integer page, Integer count) {
        Page<NoFlyList> airlinePage = this.noFlyListRepository.findAllByDeletedAtIsNull(PageRequest.of(page, count));
        if (airlinePage.isEmpty()) {
            return ApiResponse.<Page<NoFlyListDto>>builder()
                    .code(-1)
                    .message("Airlines are not found")
                    .build();
        }
        return ApiResponse.<Page<NoFlyListDto>>builder()
                .success(true)
                .message("Ok")
                .data(airlinePage.map(this.noFlyListMapper::toDto))
                .build();
    }
    @Override
    public ApiResponse<NoFlyListDto> update(NoFlyListDto noFlyListDto, Integer id) {
        return this.noFlyListRepository.findByIdAndDeletedAtIsNull(id)
                .map(flights -> {
                    flights.setUpdatedAt(LocalDateTime.now());
                    this.noFlyListMapper.update(flights, noFlyListDto);
                    this.noFlyListRepository.save(flights);
                    return ApiResponse.<NoFlyListDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.noFlyListMapper.toDto(flights))
                            .build();
                })
                .orElse(ApiResponse.<NoFlyListDto>builder()
                        .code(-1)
                        .message("flightManifest is not found")
                        .build());
    }
    @Override
    public ApiResponse<List<NoFlyListDto>> getAll() {
        return Optional.ofNullable(this.noFlyListRepository.getAll().stream())
                .map(flights -> ApiResponse.<List<NoFlyListDto>>builder()
                        .success(true)
                        .message("Ok")
                        .data(flights.map(this.noFlyListMapper::toDto).toList())
                        .build())
                .orElse(ApiResponse.<List<NoFlyListDto>>builder()
                        .code(-1)
                        .message("flightManifest are not found")
                        .build());
    }
}
