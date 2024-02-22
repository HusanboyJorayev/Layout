package com.example.layout.airline;

import com.example.layout.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v/airline")
public class AirlineController implements AirlineService<Integer, AirlineDto> {
    private final AirlineServiceImpl airlineServiceImpl;

    @Override
    @PostMapping("/create")
    public ApiResponse<AirlineDto> create(@RequestBody AirlineDto dto) {
        return this.airlineServiceImpl.create(dto);
    }

    @Override
    @GetMapping("/get/{id}")
    public ApiResponse<AirlineDto> get(@PathVariable(value = "id") Integer id) {
        return this.airlineServiceImpl.get(id);
    }

    @Override
    @GetMapping("/getWithFlights/{id}")
    public ApiResponse<AirlineDto> getWithFlights(@PathVariable(value = "id") Integer id) {
        return this.airlineServiceImpl.getWithFlights(id);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ApiResponse<AirlineDto> delete(@PathVariable(value = "id") Integer id) {
        return this.airlineServiceImpl.delete(id);
    }

    @Override
    @GetMapping("/getPage/{p}/{c}")
    public ApiResponse<Page<AirlineDto>> getPage(@PathVariable(value = "p") Integer page, @PathVariable(value = "c") Integer count) {
        return this.airlineServiceImpl.getPage(page, count);
    }

    @Override
    @PutMapping("/update/{id}")
    public ApiResponse<AirlineDto> update(@RequestBody AirlineDto dto, @PathVariable(value = "id") Integer id) {
        return this.airlineServiceImpl.update(dto, id);
    }

    @Override
    @GetMapping("/getAll")
    public ApiResponse<List<AirlineDto>> getAll() {
        return this.airlineServiceImpl.getAll();
    }

    @Override
    @GetMapping("/getAllCodes")
    public ApiResponse<List<Integer>> getAllAirlinesCode() {
        return this.airlineServiceImpl.getAllAirlinesCode();
    }
}
