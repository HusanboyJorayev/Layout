package com.example.layout.booking;

import com.example.layout.dto.ApiResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookingService<Integer,BookingDto> {
    ApiResponse<BookingDto> create(BookingDto dto);

    ApiResponse<BookingDto> get(Integer id);

    ApiResponse<BookingDto> delete(Integer id);

    ApiResponse<BookingDto> update(BookingDto dto, Integer id);

    ApiResponse<List<BookingDto>> getAll();
}
