package com.example.layout.no_fly_list;

import com.example.layout.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v/noFlyList")
public class NoFlyListController implements NoFlyListService<Integer, NoFlyListDto> {
    private final NoFlyListServiceImpl noFlyListServiceImpl;

    @Override
    @PostMapping("/create")
    public ApiResponse<NoFlyListDto> create(@RequestBody NoFlyListDto dto) {
        return this.noFlyListServiceImpl.create(dto);
    }

    @Override
    @GetMapping("/get/{id}")
    public ApiResponse<NoFlyListDto> get(@PathVariable(value = "id") Integer id) {
        return this.noFlyListServiceImpl.get(id);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ApiResponse<NoFlyListDto> delete(@PathVariable(value = "id") Integer id) {
        return this.noFlyListServiceImpl.delete(id);
    }

    @Override
    @PutMapping("/update/{id}")
    public ApiResponse<NoFlyListDto> update(@RequestBody NoFlyListDto dto, @PathVariable(value = "id") Integer id) {
        return this.noFlyListServiceImpl.update(dto, id);
    }

    @Override
    @GetMapping("/getAll")
    public ApiResponse<List<NoFlyListDto>> getAll() {
        return this.noFlyListServiceImpl.getAll();
    }
}
