package com.example.layout.booking;

import com.example.layout.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v/booking")
public class BookingController implements BookingService<Integer, BookingDto> {
    private final BookingServiceImpl bookingServiceImpl;

    @Override
    @PostMapping("/create")
    public ApiResponse<BookingDto> create(@RequestBody BookingDto dto) {
        return this.bookingServiceImpl.create(dto);
    }

    @Override
    @GetMapping("/get/{id}")
    public ApiResponse<BookingDto> get(@PathVariable(value = "id") Integer id) {
        return this.bookingServiceImpl.get(id);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ApiResponse<BookingDto> delete(@PathVariable(value = "id") Integer id) {
        return this.bookingServiceImpl.delete(id);
    }

    @Override
    @PutMapping("/update/{id}")
    public ApiResponse<BookingDto> update(@RequestBody BookingDto dto, @PathVariable(value = "id") Integer id) {
        return this.bookingServiceImpl.update(dto, id);
    }

    @Override
    @GetMapping("/getAll")
    public ApiResponse<List<BookingDto>> getAll() {
        return this.bookingServiceImpl.getAll();
    }
}
