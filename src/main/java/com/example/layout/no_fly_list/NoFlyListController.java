package com.example.layout.no_fly_list;

import com.example.layout.dto.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v/noFlyList")
@Tag(name = "No_Fly_List")
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
    @GetMapping("/getPage/{p}/{c}")
    public ApiResponse<Page<NoFlyListDto>> getPage(@PathVariable(value = "p") Integer page, @PathVariable(value = "c") Integer count) {
        return this.noFlyListServiceImpl.getPage(page, count);
    }

    @Override
    @GetMapping("/getAll")
    public ApiResponse<List<NoFlyListDto>> getAll() {
        return this.noFlyListServiceImpl.getAll();
    }
}
