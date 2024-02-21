package com.example.layout.boarding_pass;

import com.example.layout.dto.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BoardingPassService<Integer, BoardingPassDto> {
    ApiResponse<BoardingPassDto> create(BoardingPassDto dto);

    ApiResponse<BoardingPassDto> get(Integer id);

    ApiResponse<BoardingPassDto> delete(Integer id);
    ApiResponse<Page<BoardingPassDto>>getPage(Integer page, Integer count);


    ApiResponse<BoardingPassDto> update(BoardingPassDto dto, Integer id);

    ApiResponse<List<BoardingPassDto>> getAll();
}
