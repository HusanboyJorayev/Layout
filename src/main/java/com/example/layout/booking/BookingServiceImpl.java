package com.example.layout.booking;

import com.example.layout.boarding_pass.BoardingPass;
import com.example.layout.boarding_pass.BoardingPassDto;
import com.example.layout.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService<Integer, BookingDto> {
    private final BookingMapper bookingMapper;
    private final BookingRepository bookingRepository;

    @Override
    public ApiResponse<BookingDto> create(BookingDto bookingDto) {
        var booking = this.bookingMapper.toEntity(bookingDto);
        booking.setCreatedAt(LocalDateTime.now());
        this.bookingRepository.save(booking);
        return ApiResponse.<BookingDto>builder()
                .success(true)
                .message("Ok")
                .data(this.bookingMapper.toDto(booking))
                .build();
    }

    @Override
    public ApiResponse<BookingDto> get(Integer id) {
        return this.bookingRepository.findByIdAndDeletedAtIsNull(id)
                .map(booking -> ApiResponse.<BookingDto>builder()
                        .success(true)
                        .message("Ok")
                        .data(this.bookingMapper.toDto(booking))
                        .build())
                .orElse(ApiResponse.<BookingDto>builder()
                        .code(-1)
                        .message("Boarding is not found")
                        .build());
    }

    @Override
    public ApiResponse<BookingDto> delete(Integer id) {
        return this.bookingRepository.findByIdAndDeletedAtIsNull(id)
                .map(booking -> {
                    booking.setDeletedAt(LocalDateTime.now());
                    this.bookingRepository.delete(booking);
                    return ApiResponse.<BookingDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.bookingMapper.toDto(booking))
                            .build();
                })
                .orElse(ApiResponse.<BookingDto>builder()
                        .code(-1)
                        .message("Boarding is not found")
                        .build());
    }

    @Override
    public ApiResponse<BookingDto> update(BookingDto bookingDto, Integer id) {
        return this.bookingRepository.findByIdAndDeletedAtIsNull(id)
                .map(booking -> {
                    booking.setUpdatedAt(LocalDateTime.now());
                    this.bookingMapper.update(booking, bookingDto);
                    this.bookingRepository.save(booking);
                    return ApiResponse.<BookingDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.bookingMapper.toDto(booking))
                            .build();
                })
                .orElse(ApiResponse.<BookingDto>builder()
                        .code(-1)
                        .message("boarding is not found")
                        .build());
    }

    @Override
    public ApiResponse<Page<BookingDto>> getPage(Integer page, Integer count) {
        Page<Booking> airlinePage = this.bookingRepository.findAllByDeletedAtIsNull(PageRequest.of(page, count));
        if (airlinePage.isEmpty()) {
            return ApiResponse.<Page<BookingDto>>builder()
                    .code(-1)
                    .message("Airlines are not found")
                    .build();
        }
        return ApiResponse.<Page<BookingDto>>builder()
                .success(true)
                .message("Ok")
                .data(airlinePage.map(this.bookingMapper::toDto))
                .build();
    }

    @Override
    public ApiResponse<List<BookingDto>> getAll() {
        return Optional.ofNullable(this.bookingRepository.getAll().stream())
                .map(booking -> ApiResponse.<List<BookingDto>>builder()
                        .success(true)
                        .message("Ok")
                        .data(booking.map(this.bookingMapper::toDto).toList())
                        .build())
                .orElse(ApiResponse.<List<BookingDto>>builder()
                        .code(-1)
                        .message("boarding are not found")
                        .build());
    }
}
