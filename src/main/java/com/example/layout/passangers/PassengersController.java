package com.example.layout.passangers;

import com.example.layout.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v/passengers")
public class PassengersController implements PassengersService<Integer, PassengersDto> {
    private final PassengersServiceImpl passengersServiceImpl;

    @Override
    @PostMapping("/create")
    public ApiResponse<PassengersDto> create(@RequestBody PassengersDto dto) {
        return this.passengersServiceImpl.create(dto);
    }

    @Override
    @GetMapping("/get/{id}")
    public ApiResponse<PassengersDto> get(@PathVariable(value = "id") Integer id) {
        return this.passengersServiceImpl.get(id);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ApiResponse<PassengersDto> delete(@PathVariable(value = "id") Integer id) {
        return this.passengersServiceImpl.delete(id);
    }

    @Override
    @PutMapping("/update/{id}")
    public ApiResponse<PassengersDto> update(@RequestBody PassengersDto dto, @PathVariable(value = "id") Integer id) {
        return this.passengersServiceImpl.update(dto, id);
    }

    @Override
    @GetMapping("/getAll")
    public ApiResponse<List<PassengersDto>> getAll() {
        return this.passengersServiceImpl.getAll();
    }
}
