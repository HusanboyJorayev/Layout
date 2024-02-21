package com.example.layout.passangers;

import com.example.layout.dto.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PassengersService<Integer, PassengersDto> {
    ApiResponse<PassengersDto> create(PassengersDto dto);

    ApiResponse<PassengersDto> get(Integer id);
    ApiResponse<PassengersDto> getWithBaggageCheck(Integer id);
    ApiResponse<PassengersDto> getWithBooking(Integer id);

    ApiResponse<PassengersDto> delete(Integer id);
    ApiResponse<Page<PassengersDto>>getPage(Integer page, Integer count);


    ApiResponse<PassengersDto> update(PassengersDto dto, Integer id);

    ApiResponse<List<PassengersDto>> getAll();
}
