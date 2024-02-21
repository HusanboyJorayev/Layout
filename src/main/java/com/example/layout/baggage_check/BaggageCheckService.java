package com.example.layout.baggage_check;

import com.example.layout.dto.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BaggageCheckService<Integer, BaggageCheckDto> {
    ApiResponse<BaggageCheckDto> create(BaggageCheckDto dto);

    ApiResponse<BaggageCheckDto> get(Integer id);

    ApiResponse<BaggageCheckDto> delete(Integer id);
    ApiResponse<Page<BaggageCheckDto>>getPage(Integer page, Integer count);


    ApiResponse<BaggageCheckDto> update(BaggageCheckDto dto, Integer id);

    ApiResponse<List<BaggageCheckDto>> getAll();
}
