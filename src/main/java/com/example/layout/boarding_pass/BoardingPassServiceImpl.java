package com.example.layout.boarding_pass;

import com.example.layout.baggage_check.BaggageCheck;
import com.example.layout.baggage_check.BaggageCheckDto;
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
public class BoardingPassServiceImpl implements BoardingPassService<Integer, BoardingPassDto> {
    private final BoardingPassRepository boardingPassRepository;
    private final BoardingPassMapper boardingPassMapper;

    @Override
    public ApiResponse<BoardingPassDto> create(BoardingPassDto boardingPassDto) {
        var baggage = this.boardingPassMapper.toEntity(boardingPassDto);
        baggage.setCreatedAt(LocalDateTime.now());
        this.boardingPassRepository.save(baggage);
        return ApiResponse.<BoardingPassDto>builder()
                .success(true)
                .message("Ok")
                .data(this.boardingPassMapper.toDto(baggage))
                .build();
    }

    @Override
    public ApiResponse<BoardingPassDto> get(Integer id) {
        return this.boardingPassRepository.findByIdAndDeletedAtIsNull(id)
                .map(boardingPass -> ApiResponse.<BoardingPassDto>builder()
                        .success(true)
                        .message("Ok")
                        .data(this.boardingPassMapper.toDto(boardingPass))
                        .build())
                .orElse(ApiResponse.<BoardingPassDto>builder()
                        .code(-1)
                        .message("Boarding is not found")
                        .build());
    }

    @Override
    public ApiResponse<BoardingPassDto> delete(Integer id) {
        return this.boardingPassRepository.findByIdAndDeletedAtIsNull(id)
                .map(baggage -> {
                    baggage.setDeletedAt(LocalDateTime.now());
                    this.boardingPassRepository.delete(baggage);
                    return ApiResponse.<BoardingPassDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.boardingPassMapper.toDto(baggage))
                            .build();
                })
                .orElse(ApiResponse.<BoardingPassDto>builder()
                        .code(-1)
                        .message("Boarding is not found")
                        .build());
    }
    @Override
    public ApiResponse<Page<BoardingPassDto>> getPage(Integer page, Integer count) {
        Page<BoardingPass> airlinePage = this.boardingPassRepository.findAllByDeletedAtIsNull(PageRequest.of(page, count));
        if (airlinePage.isEmpty()) {
            return ApiResponse.<Page<BoardingPassDto>>builder()
                    .code(-1)
                    .message("Airlines are not found")
                    .build();
        }
        return ApiResponse.<Page<BoardingPassDto>>builder()
                .success(true)
                .message("Ok")
                .data(airlinePage.map(this.boardingPassMapper::toDto))
                .build();
    }

    @Override
    public ApiResponse<BoardingPassDto> update(BoardingPassDto boardingPassDto, Integer id) {
        return this.boardingPassRepository.findByIdAndDeletedAtIsNull(id)
                .map(baggage -> {
                    baggage.setUpdatedAt(LocalDateTime.now());
                    this.boardingPassMapper.update(baggage, boardingPassDto);
                    this.boardingPassRepository.save(baggage);
                    return ApiResponse.<BoardingPassDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.boardingPassMapper.toDto(baggage))
                            .build();
                })
                .orElse(ApiResponse.<BoardingPassDto>builder()
                        .code(-1)
                        .message("boarding is not found")
                        .build());
    }

    @Override
    public ApiResponse<List<BoardingPassDto>> getAll() {
        return Optional.ofNullable(this.boardingPassRepository.getAll().stream())
                .map(baggage -> ApiResponse.<List<BoardingPassDto>>builder()
                        .success(true)
                        .message("Ok")
                        .data(baggage.map(this.boardingPassMapper::toDto).toList())
                        .build())
                .orElse(ApiResponse.<List<BoardingPassDto>>builder()
                        .code(-1)
                        .message("boarding are not found")
                        .build());
    }
}
