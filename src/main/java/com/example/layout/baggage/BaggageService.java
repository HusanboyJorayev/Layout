package com.example.layout.baggage;

import com.example.layout.dto.ApiResponse;

import java.util.List;

public interface BaggageService<Integer, BaggageDto> {
    ApiResponse<BaggageDto> create(BaggageDto dto);

    ApiResponse<BaggageDto> get(Integer id);

    ApiResponse<BaggageDto> delete(Integer id);

    ApiResponse<BaggageDto> update(BaggageDto dto, Integer id);

    ApiResponse<List<BaggageDto>> getAll();
}
