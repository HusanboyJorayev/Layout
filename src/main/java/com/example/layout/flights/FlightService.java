package com.example.layout.flights;

import com.example.layout.dto.ApiResponse;

import java.util.List;

public interface FlightService <Integer,FlightsDto>{
    ApiResponse<FlightsDto> create(FlightsDto dto);

    ApiResponse<FlightsDto> get(Integer id);

    ApiResponse<FlightsDto> delete(Integer id);

    ApiResponse<FlightsDto> update(FlightsDto dto, Integer id);

    ApiResponse<List<FlightsDto>> getAll();
}
