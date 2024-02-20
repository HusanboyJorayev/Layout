package com.example.layout.security_check;

import org.mapstruct.*;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring", imports = Collectors.class)
public abstract class SecurityCheckMapper {
    public abstract SecurityCheckDto toDto(SecurityCheck securityCheck);

    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "createdAt")
    @Mapping(ignore = true, target = "updatedAt")
    @Mapping(ignore = true, target = "deletedAt")
    public abstract SecurityCheck toEntity(SecurityCheckDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget SecurityCheck securityCheck, SecurityCheckDto dto);
}
