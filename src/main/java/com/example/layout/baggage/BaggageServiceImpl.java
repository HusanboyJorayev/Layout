package com.example.layout.baggage;

import com.example.layout.airport.Airport;
import com.example.layout.airport.AirportDto;
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
public class BaggageServiceImpl implements BaggageService<Integer, BaggageDto> {
    private final BaggageRepository baggageRepository;
    private final BaggageMapper baggageMapper;

    @Override
    public ApiResponse<BaggageDto> create(BaggageDto baggageDto) {
        Baggage baggage = this.baggageMapper.toEntity(baggageDto);
        baggage.setCreatedAt(LocalDateTime.now());
        this.baggageRepository.save(baggage);
        return ApiResponse.<BaggageDto>builder()
                .success(true)
                .message("Ok")
                .data(this.baggageMapper.toDto(baggage))
                .build();
    }

    @Override
    public ApiResponse<BaggageDto> get(Integer id) {
        return this.baggageRepository.findByIdAndDeletedAtIsNull(id)
                .map(baggage -> ApiResponse.<BaggageDto>builder()
                        .success(true)
                        .message("Ok")
                        .data(this.baggageMapper.toDto(baggage))
                        .build())
                .orElse(ApiResponse.<BaggageDto>builder()
                        .code(-1)
                        .message("baggage is not found")
                        .build());
    }

    @Override
    public ApiResponse<BaggageDto> delete(Integer id) {
        return this.baggageRepository.findByIdAndDeletedAtIsNull(id)
                .map(baggage -> {
                    baggage.setDeletedAt(LocalDateTime.now());
                    this.baggageRepository.delete(baggage);
                    return ApiResponse.<BaggageDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.baggageMapper.toDto(baggage))
                            .build();
                })
                .orElse(ApiResponse.<BaggageDto>builder()
                        .code(-1)
                        .message("baggage is not found")
                        .build());
    }

    @Override
    public ApiResponse<Page<BaggageDto>> getPage(Integer page, Integer count) {
        Page<Baggage> airlinePage = this.baggageRepository.findAllByDeletedAtIsNull(PageRequest.of(page, count));
        if (airlinePage.isEmpty()) {
            return ApiResponse.<Page<BaggageDto>>builder()
                    .code(-1)
                    .message("Airlines are not found")
                    .build();
        }
        return ApiResponse.<Page<BaggageDto>>builder()
                .success(true)
                .message("Ok")
                .data(airlinePage.map(this.baggageMapper::toDto))
                .build();
    }

    @Override
    public ApiResponse<BaggageDto> update(BaggageDto baggageDto, Integer id) {
        return this.baggageRepository.findByIdAndDeletedAtIsNull(id)
                .map(baggage -> {
                    baggage.setUpdatedAt(LocalDateTime.now());
                    this.baggageMapper.update(baggage, baggageDto);
                    this.baggageRepository.save(baggage);
                    return ApiResponse.<BaggageDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.baggageMapper.toDto(baggage))
                            .build();
                })
                .orElse(ApiResponse.<BaggageDto>builder()
                        .code(-1)
                        .message("baggage is not found")
                        .build());
    }

    @Override
    public ApiResponse<List<BaggageDto>> getAll() {
        return Optional.ofNullable(this.baggageRepository.getAll().stream())
                .map(baggage -> ApiResponse.<List<BaggageDto>>builder()
                        .success(true)
                        .message("Ok")
                        .data(baggage.map(this.baggageMapper::toDto).toList())
                        .build())
                .orElse(ApiResponse.<List<BaggageDto>>builder()
                        .code(-1)
                        .message("baggage are not found")
                        .build());
    }
}
