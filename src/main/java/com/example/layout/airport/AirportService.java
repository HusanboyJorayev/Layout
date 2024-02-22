package com.example.layout.airport;

import com.example.layout.dto.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AirportService<Integer, AirportDto> {
    ApiResponse<AirportDto> create(AirportDto dto);

    ApiResponse<AirportDto> get(Integer id);
    ApiResponse<AirportDto> getWithAllRelationShip(Integer id);

    ApiResponse<AirportDto> getWithArrivingAirport(Integer id);

    ApiResponse<AirportDto> getWithDepartingAirport(Integer id);

    ApiResponse<AirportDto> delete(Integer id);

    ApiResponse<Page<AirportDto>> getPage(Integer page, Integer count);

    ApiResponse<AirportDto> update(AirportDto dto, Integer id);

    ApiResponse<List<AirportDto>> getAll();
}
