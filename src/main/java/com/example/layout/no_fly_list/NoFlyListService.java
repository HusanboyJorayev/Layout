package com.example.layout.no_fly_list;

import com.example.layout.dto.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NoFlyListService<Integer, NoFlyListDto> {
    ApiResponse<NoFlyListDto> create(NoFlyListDto dto);

    ApiResponse<NoFlyListDto> get(Integer id);

    ApiResponse<NoFlyListDto> delete(Integer id);
    ApiResponse<Page<NoFlyListDto>>getPage(Integer page, Integer count);

    ApiResponse<NoFlyListDto> update(NoFlyListDto dto, Integer id);

    ApiResponse<List<NoFlyListDto>> getAll();
}
