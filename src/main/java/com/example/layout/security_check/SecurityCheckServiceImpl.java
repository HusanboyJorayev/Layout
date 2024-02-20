package com.example.layout.security_check;

import com.example.layout.dto.ApiResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SecurityCheckServiceImpl implements SecurityCheckService<Integer, SecurityCheckDto> {
    private final SecurityCheckMapper securityCheckMapper;
    private final SecurityCheckRepository securityCheckRepository;

    @Override
    public ApiResponse<SecurityCheckDto> create(SecurityCheckDto securityCheckDto) {
        var securityCheck = this.securityCheckMapper.toEntity(securityCheckDto);
        securityCheck.setCreatedAt(LocalDateTime.now());
        this.securityCheckRepository.save(securityCheck);
        return ApiResponse.<SecurityCheckDto>builder()
                .success(true)
                .message("Ok")
                .data(this.securityCheckMapper.toDto(securityCheck))
                .build();
    }

    @Override
    public ApiResponse<SecurityCheckDto> get(Integer id) {
        return this.securityCheckRepository.findByIdAndDeletedAtIsNull(id)
                .map(securityCheck -> ApiResponse.<SecurityCheckDto>builder()
                        .success(true)
                        .message("Ok")
                        .data(this.securityCheckMapper.toDto(securityCheck))
                        .build())
                .orElse(ApiResponse.<SecurityCheckDto>builder()
                        .code(-1)
                        .message("flightManifest is not found")
                        .build());
    }

    @Override
    public ApiResponse<SecurityCheckDto> delete(Integer id) {
        return this.securityCheckRepository.findByIdAndDeletedAtIsNull(id)
                .map(securityCheck -> {
                    securityCheck.setDeletedAt(LocalDateTime.now());
                    this.securityCheckRepository.delete(securityCheck);
                    return ApiResponse.<SecurityCheckDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.securityCheckMapper.toDto(securityCheck))
                            .build();
                })
                .orElse(ApiResponse.<SecurityCheckDto>builder()
                        .code(-1)
                        .message("flightManifest is not found")
                        .build());
    }

    @Override
    public ApiResponse<SecurityCheckDto> update(SecurityCheckDto securityCheckDto, Integer id) {
        return this.securityCheckRepository.findByIdAndDeletedAtIsNull(id)
                .map(securityCheck -> {
                    securityCheck.setUpdatedAt(LocalDateTime.now());
                    this.securityCheckMapper.update(securityCheck, securityCheckDto);
                    this.securityCheckRepository.save(securityCheck);
                    return ApiResponse.<SecurityCheckDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.securityCheckMapper.toDto(securityCheck))
                            .build();
                })
                .orElse(ApiResponse.<SecurityCheckDto>builder()
                        .code(-1)
                        .message("flightManifest is not found")
                        .build());
    }

    @Override
    public ApiResponse<List<SecurityCheckDto>> getAll() {
        return Optional.ofNullable(this.securityCheckRepository.getAll().stream())
                .map(securityCheck -> ApiResponse.<List<SecurityCheckDto>>builder()
                        .success(true)
                        .message("Ok")
                        .data(securityCheck.map(this.securityCheckMapper::toDto).toList())
                        .build())
                .orElse(ApiResponse.<List<SecurityCheckDto>>builder()
                        .code(-1)
                        .message("flightManifest are not found")
                        .build());
    }
}
