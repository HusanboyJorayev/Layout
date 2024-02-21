package com.example.layout.security_check;

import com.example.layout.dto.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SecurityCheckService<Integer, SecurityCheckDto> {
    ApiResponse<SecurityCheckDto> create(SecurityCheckDto dto);

    ApiResponse<SecurityCheckDto> get(Integer id);

    ApiResponse<SecurityCheckDto> delete(Integer id);
    ApiResponse<Page<SecurityCheckDto>>getPage(Integer page, Integer count);


    ApiResponse<SecurityCheckDto> update(SecurityCheckDto dto, Integer id);

    ApiResponse<List<SecurityCheckDto>> getAll();
}
