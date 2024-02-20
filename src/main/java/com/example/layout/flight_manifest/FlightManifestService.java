package com.example.layout.flight_manifest;

import com.example.layout.dto.ApiResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FlightManifestService <Integer,FlightManifestDto>{
    ApiResponse<FlightManifestDto> create(FlightManifestDto dto);

    ApiResponse<FlightManifestDto> get(Integer id);

    ApiResponse<FlightManifestDto> delete(Integer id);

    ApiResponse<FlightManifestDto> update(FlightManifestDto dto, Integer id);

    ApiResponse<List<FlightManifestDto>> getAll();
}
