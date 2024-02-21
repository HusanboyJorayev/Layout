package com.example.layout.baggage_check;

import com.example.layout.baggage.Baggage;
import com.example.layout.baggage.BaggageDto;
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
public class BaggageCheckServiceImpl implements BaggageCheckService<Integer, BaggageCheckDto> {
    private final BaggageCheckRepository baggageCheckRepository;
    private final BaggageCheckMapper baggageCheckMapper;

    @Override
    public ApiResponse<BaggageCheckDto> create(BaggageCheckDto baggageCheckDto) {
        var baggageCheck = this.baggageCheckMapper.toEntity(baggageCheckDto);
        baggageCheck.setCreatedAt(LocalDateTime.now());
        this.baggageCheckRepository.save(baggageCheck);
        return ApiResponse.<BaggageCheckDto>builder()
                .success(true)
                .message("Ok")
                .data(this.baggageCheckMapper.toDto(baggageCheck))
                .build();
    }

    @Override
    public ApiResponse<BaggageCheckDto> get(Integer id) {
        return this.baggageCheckRepository.findByIdAndDeletedAtIsNull(id)
                .map(baggageCheck -> ApiResponse.<BaggageCheckDto>builder()
                        .success(true)
                        .message("Ok")
                        .data(this.baggageCheckMapper.toDto(baggageCheck))
                        .build())
                .orElse(ApiResponse.<BaggageCheckDto>builder()
                        .code(-1)
                        .message("Airline is not found")
                        .build());
    }

    @Override
    public ApiResponse<BaggageCheckDto> delete(Integer id) {
        return this.baggageCheckRepository.findByIdAndDeletedAtIsNull(id)
                .map(baggageCheck -> {
                    baggageCheck.setDeletedAt(LocalDateTime.now());
                    this.baggageCheckRepository.delete(baggageCheck);
                    return ApiResponse.<BaggageCheckDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.baggageCheckMapper.toDto(baggageCheck))
                            .build();
                })
                .orElse(ApiResponse.<BaggageCheckDto>builder()
                        .code(-1)
                        .message("Airline is not found")
                        .build());
    }

    @Override
    public ApiResponse<Page<BaggageCheckDto>> getPage(Integer page, Integer count) {
        Page<BaggageCheck> airlinePage = this.baggageCheckRepository.findAllByDeletedAtIsNull(PageRequest.of(page, count));
        if (airlinePage.isEmpty()) {
            return ApiResponse.<Page<BaggageCheckDto>>builder()
                    .code(-1)
                    .message("Airlines are not found")
                    .build();
        }
        return ApiResponse.<Page<BaggageCheckDto>>builder()
                .success(true)
                .message("Ok")
                .data(airlinePage.map(this.baggageCheckMapper::toDto))
                .build();
    }

    @Override
    public ApiResponse<BaggageCheckDto> update(BaggageCheckDto baggageCheckDto, Integer id) {
        return this.baggageCheckRepository.findByIdAndDeletedAtIsNull(id)
                .map(baggageCheck -> {
                    baggageCheck.setUpdatedAt(LocalDateTime.now());
                    this.baggageCheckMapper.update(baggageCheck, baggageCheckDto);
                    this.baggageCheckRepository.save(baggageCheck);
                    return ApiResponse.<BaggageCheckDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.baggageCheckMapper.toDto(baggageCheck))
                            .build();
                })
                .orElse(ApiResponse.<BaggageCheckDto>builder()
                        .code(-1)
                        .message("airline is not found")
                        .build());
    }

    @Override
    public ApiResponse<List<BaggageCheckDto>> getAll() {
        return Optional.ofNullable(this.baggageCheckRepository.getAll().stream())
                .map(baggageCheckStream -> ApiResponse.<List<BaggageCheckDto>>builder()
                        .success(true)
                        .message("Ok")
                        .data(baggageCheckStream.map(this.baggageCheckMapper::toDto).toList())
                        .build())
                .orElse(ApiResponse.<List<BaggageCheckDto>>builder()
                        .code(-1)
                        .message("Airlines are not found")
                        .build());
    }
}
