package com.example.layout.baggage_check;

import com.example.layout.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v/baggageCheck")
public class BaggageCheckController implements BaggageCheckService<Integer, BaggageCheckDto> {
    private final BaggageCheckServiceImpl baggageCheckServiceImpl;

    @Override
    @PostMapping("/create")
    public ApiResponse<BaggageCheckDto> create(@RequestBody BaggageCheckDto dto) {
        return this.baggageCheckServiceImpl.create(dto);
    }

    @Override
    @GetMapping("/get/{id}")
    public ApiResponse<BaggageCheckDto> get(@PathVariable(value = "id") Integer id) {
        return this.baggageCheckServiceImpl.get(id);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ApiResponse<BaggageCheckDto> delete(@PathVariable(value = "id") Integer id) {
        return this.baggageCheckServiceImpl.delete(id);
    }

    @Override
    @PutMapping("/update/{id}")
    public ApiResponse<BaggageCheckDto> update(@RequestBody BaggageCheckDto dto, @PathVariable(value = "id") Integer id) {
        return this.baggageCheckServiceImpl.update(dto, id);
    }

    @Override
    @GetMapping("/getAll")
    public ApiResponse<List<BaggageCheckDto>> getAll() {
        return this.baggageCheckServiceImpl.getAll();
    }
}
