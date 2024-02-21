package com.example.layout.baggage;

import com.example.layout.airport.AirportDto;
import com.example.layout.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v/baggage")
public class BaggageController implements BaggageService<Integer, BaggageDto> {
    private final BaggageServiceImpl baggageServiceImpl;

    @Override
    @PostMapping("/create")
    public ApiResponse<BaggageDto> create(@RequestBody BaggageDto dto) {
        return this.baggageServiceImpl.create(dto);
    }

    @Override
    @GetMapping("/get/{id}")
    public ApiResponse<BaggageDto> get(@PathVariable(value = "id") Integer id) {
        return this.baggageServiceImpl.get(id);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ApiResponse<BaggageDto> delete(@PathVariable(value = "id") Integer id) {
        return this.baggageServiceImpl.delete(id);
    }

    @Override
    @GetMapping("/getPage/{p}/{c}")
    public ApiResponse<Page<BaggageDto>> getPage(@PathVariable(value = "p") Integer page, @PathVariable(value = "c") Integer count) {
        return this.baggageServiceImpl.getPage(page, count);
    }

    @Override
    @PutMapping("/update/{id}")
    public ApiResponse<BaggageDto> update(@RequestBody BaggageDto dto, @PathVariable(value = "id") Integer id) {
        return this.baggageServiceImpl.update(dto, id);
    }

    @Override
    @GetMapping("/getAll")
    public ApiResponse<List<BaggageDto>> getAll() {
        return this.baggageServiceImpl.getAll();
    }
}
