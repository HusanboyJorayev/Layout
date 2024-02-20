package com.example.layout.security_check;

import com.example.layout.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v/securityCheck")
public class SecurityCheckController implements SecurityCheckService<Integer, SecurityCheckDto> {
    private final SecurityCheckServiceImpl securityCheckServiceImpl;

    @Override
    @PostMapping("/create")
    public ApiResponse<SecurityCheckDto> create(@RequestBody SecurityCheckDto dto) {
        return this.securityCheckServiceImpl.create(dto);
    }

    @Override
    @GetMapping("/get/{id}")
    public ApiResponse<SecurityCheckDto> get(@PathVariable(value = "id") Integer id) {
        return this.securityCheckServiceImpl.get(id);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ApiResponse<SecurityCheckDto> delete(@PathVariable(value = "id") Integer id) {
        return this.securityCheckServiceImpl.delete(id);
    }

    @Override
    @PutMapping("/update/{id}")
    public ApiResponse<SecurityCheckDto> update(@RequestBody SecurityCheckDto dto, @PathVariable(value = "id") Integer id) {
        return this.securityCheckServiceImpl.update(dto, id);
    }

    @Override
    @GetMapping("/getAll")
    public ApiResponse<List<SecurityCheckDto>> getAll() {
        return this.securityCheckServiceImpl.getAll();
    }
}
