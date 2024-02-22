package com.example.layout.baggage_check;

import com.example.layout.baggage.BaggageDto;
import com.example.layout.dto.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v/baggageCheck")
@Tag(name = "Baggage_Check")
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
    @GetMapping("/getPage/{p}/{c}")
    public ApiResponse<Page<BaggageCheckDto>> getPage(@PathVariable(value = "p") Integer page, @PathVariable(value = "c") Integer count) {
        return this.baggageCheckServiceImpl.getPage(page, count);
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
