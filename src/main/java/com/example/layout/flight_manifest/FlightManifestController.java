package com.example.layout.flight_manifest;


import com.example.layout.booking.BookingDto;
import com.example.layout.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v/flightManifest")
public class FlightManifestController implements FlightManifestService<Integer, FlightManifestDto> {
    private final FlightManifestServiceImpl flightManifestServiceImpl;

    @Override
    @PostMapping("/create")
    public ApiResponse<FlightManifestDto> create(@RequestBody FlightManifestDto dto) {
        return this.flightManifestServiceImpl.create(dto);
    }

    @Override
    @GetMapping("/get/{id}")
    public ApiResponse<FlightManifestDto> get(@PathVariable(value = "id") Integer id) {
        return this.flightManifestServiceImpl.get(id);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ApiResponse<FlightManifestDto> delete(@PathVariable(value = "id") Integer id) {
        return this.flightManifestServiceImpl.delete(id);
    }

    @Override
    @PutMapping("/update/{id}")
    public ApiResponse<FlightManifestDto> update(@RequestBody FlightManifestDto dto, @PathVariable(value = "id") Integer id) {
        return this.flightManifestServiceImpl.update(dto, id);
    }

    @Override
    @GetMapping("/getPage/{p}/{c}")
    public ApiResponse<Page<FlightManifestDto>> getPage(@PathVariable(value = "p") Integer page, @PathVariable(value = "c") Integer count) {
        return this.flightManifestServiceImpl.getPage(page, count);
    }

    @Override
    @GetMapping("/getAll")
    public ApiResponse<List<FlightManifestDto>> getAll() {
        return this.flightManifestServiceImpl.getAll();
    }
}
