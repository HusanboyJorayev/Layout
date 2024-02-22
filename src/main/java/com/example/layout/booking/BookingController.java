package com.example.layout.booking;

import com.example.layout.boarding_pass.BoardingPassDto;
import com.example.layout.dto.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v/booking")
@Tag(name = "Booking")
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
    @GetMapping("/getWithAllRelationShip/{id}")
    public ApiResponse<BookingDto> getWithAllRelationShip(@PathVariable(value = "id") Integer id) {
        return this.bookingServiceImpl.getWithAllRelationShip(id);
    }

    @Override
    @GetMapping("/getWithBoardingPass/{id}")
    public ApiResponse<BookingDto> getWithBoardingPass(@PathVariable(value = "id") Integer id) {
        return this.bookingServiceImpl.getWithBoardingPass(id);
    }

    @Override
    @GetMapping("/getWithBaggage/{id}")
    public ApiResponse<BookingDto> getWithBaggage(@PathVariable(value = "id") Integer id) {
        return this.bookingServiceImpl.getWithBaggage(id);
    }

    @Override
    @GetMapping("/getWithBaggageCheck/{id}")
    public ApiResponse<BookingDto> getWithBaggageCheck(@PathVariable(value = "id") Integer id) {
        return this.bookingServiceImpl.getWithBaggageCheck(id);
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
    @GetMapping("/getPage/{p}/{c}")
    public ApiResponse<Page<BookingDto>> getPage(@PathVariable(value = "p") Integer page, @PathVariable(value = "c") Integer count) {
        return this.bookingServiceImpl.getPage(page, count);
    }

    @Override
    @GetMapping("/getAll")
    public ApiResponse<List<BookingDto>> getAll() {
        return this.bookingServiceImpl.getAll();
    }
}
