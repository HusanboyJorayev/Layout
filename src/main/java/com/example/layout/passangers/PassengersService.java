package com.example.layout.passangers;

import com.example.layout.dto.ApiResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PassengersService<Integer, PassengersDto> {
    ApiResponse<PassengersDto> create(PassengersDto dto);

    ApiResponse<PassengersDto> get(Integer id);

    ApiResponse<PassengersDto> delete(Integer id);

    ApiResponse<PassengersDto> update(PassengersDto dto, Integer id);

    ApiResponse<List<PassengersDto>> getAll();
}
