package com.example.layout.flight_manifest;

import com.example.layout.booking.Booking;
import com.example.layout.booking.BookingDto;
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
public class FlightManifestServiceImpl implements FlightManifestService<Integer, FlightManifestDto> {
    private final FlightManifestMapper flightManifestMapper;
    private final FlightManifestRepository flightManifestRepository;

    @Override
    public ApiResponse<FlightManifestDto> create(FlightManifestDto flightManifestDto) {
        var booking = this.flightManifestMapper.toEntity(flightManifestDto);
        booking.setCreatedAt(LocalDateTime.now());
        this.flightManifestRepository.save(booking);
        return ApiResponse.<FlightManifestDto>builder()
                .success(true)
                .message("Ok")
                .data(this.flightManifestMapper.toDto(booking))
                .build();
    }

    @Override
    public ApiResponse<FlightManifestDto> get(Integer id) {
        return this.flightManifestRepository.findByIdAndDeletedAtIsNull(id)
                .map(flightManifest -> ApiResponse.<FlightManifestDto>builder()
                        .success(true)
                        .message("Ok")
                        .data(this.flightManifestMapper.toDto(flightManifest))
                        .build())
                .orElse(ApiResponse.<FlightManifestDto>builder()
                        .code(-1)
                        .message("flightManifest is not found")
                        .build());
    }

    @Override
    public ApiResponse<FlightManifestDto> delete(Integer id) {
        return this.flightManifestRepository.findByIdAndDeletedAtIsNull(id)
                .map(booking -> {
                    booking.setDeletedAt(LocalDateTime.now());
                    this.flightManifestRepository.delete(booking);
                    return ApiResponse.<FlightManifestDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.flightManifestMapper.toDto(booking))
                            .build();
                })
                .orElse(ApiResponse.<FlightManifestDto>builder()
                        .code(-1)
                        .message("flightManifest is not found")
                        .build());
    }
    @Override
    public ApiResponse<Page<FlightManifestDto>> getPage(Integer page, Integer count) {
        Page<FlightManifest> airlinePage = this.flightManifestRepository.findAllByDeletedAtIsNull(PageRequest.of(page, count));
        if (airlinePage.isEmpty()) {
            return ApiResponse.<Page<FlightManifestDto>>builder()
                    .code(-1)
                    .message("Airlines are not found")
                    .build();
        }
        return ApiResponse.<Page<FlightManifestDto>>builder()
                .success(true)
                .message("Ok")
                .data(airlinePage.map(this.flightManifestMapper::toDto))
                .build();
    }
    @Override
    public ApiResponse<FlightManifestDto> update(FlightManifestDto flightManifestDto, Integer id) {
        return this.flightManifestRepository.findByIdAndDeletedAtIsNull(id)
                .map(booking -> {
                    booking.setUpdatedAt(LocalDateTime.now());
                    this.flightManifestMapper.update(booking, flightManifestDto);
                    this.flightManifestRepository.save(booking);
                    return ApiResponse.<FlightManifestDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.flightManifestMapper.toDto(booking))
                            .build();
                })
                .orElse(ApiResponse.<FlightManifestDto>builder()
                        .code(-1)
                        .message("flightManifest is not found")
                        .build());
    }

    @Override
    public ApiResponse<List<FlightManifestDto>> getAll() {
        return Optional.ofNullable(this.flightManifestRepository.getAll().stream())
                .map(booking -> ApiResponse.<List<FlightManifestDto>>builder()
                        .success(true)
                        .message("Ok")
                        .data(booking.map(this.flightManifestMapper::toDto).toList())
                        .build())
                .orElse(ApiResponse.<List<FlightManifestDto>>builder()
                        .code(-1)
                        .message("flightManifest are not found")
                        .build());
    }
}
