package com.example.layout.passangers;

import com.example.layout.dto.ApiResponse;
import com.example.layout.no_fly_list.NoFlyListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    @GetMapping("/getPage/{p}/{c}")
    public ApiResponse<Page<PassengersDto>> getPage(@PathVariable(value = "p") Integer page, @PathVariable(value = "c") Integer count) {
        return this.passengersServiceImpl.getPage(page, count);
    }

    @Override
    @GetMapping("/getAll")
    public ApiResponse<List<PassengersDto>> getAll() {
        return this.passengersServiceImpl.getAll();
    }
}
