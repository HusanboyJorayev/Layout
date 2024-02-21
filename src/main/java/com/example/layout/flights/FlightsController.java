package com.example.layout.flights;

import com.example.layout.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v/flights")
public class FlightsController implements FlightService<Integer, FlightsDto> {
    private final FlightsServiceImpl flightsServiceImpl;

    @Override
    @PostMapping("/create")
    public ApiResponse<FlightsDto> create(@RequestBody FlightsDto dto) {
        return this.flightsServiceImpl.create(dto);
    }

    @Override
    @GetMapping("/get/{id}")
    public ApiResponse<FlightsDto> get(@PathVariable(value = "id") Integer id) {
        return this.flightsServiceImpl.get(id);
    }

    @Override
    @GetMapping("/getWithBooking/{id}")
    public ApiResponse<FlightsDto> getWithBooking(@PathVariable(value = "id") Integer id) {
        return this.flightsServiceImpl.getWithBooking(id);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ApiResponse<FlightsDto> delete(@PathVariable(value = "id") Integer id) {
        return this.flightsServiceImpl.delete(id);
    }

    @Override
    @PutMapping("/update/{id}")
    public ApiResponse<FlightsDto> update(@RequestBody FlightsDto dto, @PathVariable(value = "id") Integer id) {
        return this.flightsServiceImpl.update(dto, id);
    }

    @Override
    @GetMapping("/getPage/{p}/{c}")
    public ApiResponse<Page<FlightsDto>> getPage(@PathVariable(value = "p") Integer page, @PathVariable(value = "c") Integer count) {
        return this.flightsServiceImpl.getPage(page, count);
    }

    @Override
    @GetMapping("/getAll")
    public ApiResponse<List<FlightsDto>> getAll() {
        return this.flightsServiceImpl.getAll();
    }
}
