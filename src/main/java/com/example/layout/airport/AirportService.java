package com.example.layout.airport;

import com.example.layout.dto.ApiResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AirportService<Integer, AirportDto> {
    ApiResponse<AirportDto> create(AirportDto dto);

    ApiResponse<AirportDto> get(Integer id);

    ApiResponse<AirportDto> delete(Integer id);

    ApiResponse<AirportDto> update(AirportDto dto, Integer id);

    ApiResponse<List<AirportDto>> getAll();
}
