package com.example.layout.airport;

import com.example.layout.airline.AirlineDto;
import com.example.layout.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v/airport")
public class AirportController implements AirportService<Integer, AirportDto> {
    private final AirportServiceImpl airportServiceImpl;

    @Override
    @PostMapping("/create")
    public ApiResponse<AirportDto> create(@RequestBody AirportDto dto) {
        return this.airportServiceImpl.create(dto);
    }

    @Override
    @GetMapping("/get/{id}")
    public ApiResponse<AirportDto> get(@PathVariable(value = "id") Integer id) {
        return this.airportServiceImpl.get(id);
    }

    @Override
    @GetMapping("/getWithArrivingAirport/{id}")
    public ApiResponse<AirportDto> getWithArrivingAirport(@PathVariable(value = "id") Integer id) {
        return this.airportServiceImpl.getWithArrivingAirport(id);
    }

    @Override
    @GetMapping("/getWithDepartingAirport/{id}")
    public ApiResponse<AirportDto> getWithDepartingAirport(@PathVariable(value = "id") Integer id) {
        return this.airportServiceImpl.getWithDepartingAirport(id);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ApiResponse<AirportDto> delete(@PathVariable(value = "id") Integer id) {
        return this.airportServiceImpl.delete(id);
    }

    @Override
    @GetMapping("/getPage/{p}/{c}")
    public ApiResponse<Page<AirportDto>> getPage(@PathVariable(value = "p") Integer page, @PathVariable(value = "c") Integer count) {
        return this.airportServiceImpl.getPage(page, count);
    }

    @Override
    @PutMapping("/update/{id}")
    public ApiResponse<AirportDto> update(@RequestBody AirportDto dto, @PathVariable(value = "id") Integer id) {
        return this.airportServiceImpl.update(dto, id);
    }

    @Override
    @GetMapping("/getAll")
    public ApiResponse<List<AirportDto>> getAll() {
        return this.airportServiceImpl.getAll();
    }
}
