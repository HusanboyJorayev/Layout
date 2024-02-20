package com.example.layout.airline;

import com.example.layout.dto.ApiResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AirlineService<Integer, AirlineDto> {

    ApiResponse<AirlineDto> create(AirlineDto dto);

    ApiResponse<AirlineDto> get(Integer id);
    ApiResponse<AirlineDto> delete(Integer id);

    ApiResponse<AirlineDto> update(AirlineDto dto, Integer id);

    ApiResponse<List<AirlineDto>>getAll();
    ApiResponse<List<Integer>>getAllAirlinesCode();

}
