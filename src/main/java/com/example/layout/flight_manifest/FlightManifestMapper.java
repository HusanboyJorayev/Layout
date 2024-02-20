package com.example.layout.flight_manifest;

import org.mapstruct.*;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring", imports = Collectors.class)
public abstract class FlightManifestMapper {

    public abstract FlightManifestDto toDto(FlightManifest flightManifest);


    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "createdAt")
    @Mapping(ignore = true, target = "updatedAt")
    @Mapping(ignore = true, target = "deletedAt")
    public abstract FlightManifest toEntity(FlightManifestDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget FlightManifest flightManifest, FlightManifestDto dto);
}
