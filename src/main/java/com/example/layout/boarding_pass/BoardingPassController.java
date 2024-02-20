package com.example.layout.boarding_pass;

import com.example.layout.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v/boarding")
public class BoardingPassController implements BoardingPassService<Integer, BoardingPassDto> {
    private final BoardingPassServiceImpl boardingPassServiceImpl;

    @Override
    @PostMapping("/create")
    public ApiResponse<BoardingPassDto> create(@RequestBody BoardingPassDto dto) {
        return this.boardingPassServiceImpl.create(dto);
    }

    @Override
    @GetMapping("/get/{id}")
    public ApiResponse<BoardingPassDto> get(@PathVariable(value = "id") Integer id) {
        return this.boardingPassServiceImpl.get(id);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ApiResponse<BoardingPassDto> delete(@PathVariable(value = "id") Integer id) {
        return this.boardingPassServiceImpl.delete(id);
    }

    @Override
    @PutMapping("/update/{id}")
    public ApiResponse<BoardingPassDto> update(@RequestBody BoardingPassDto dto, @PathVariable(value = "id") Integer id) {
        return this.boardingPassServiceImpl.update(dto, id);
    }

    @Override
    @GetMapping("/getAll")
    public ApiResponse<List<BoardingPassDto>> getAll() {
        return this.boardingPassServiceImpl.getAll();
    }
}
