package com.example.layout.airport;

import com.example.layout.flights.FlightsMapper;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring", imports = Collectors.class)
public abstract class AirportMapper {
    @Autowired
    protected FlightsMapper flightsMapper;


    @Mapping(ignore = true, target = "departingAirport")
    @Mapping(ignore = true, target = "arrivingAirport")
    public abstract AirportDto toDto(Airport airport);

    @Mapping(target = "departingAirport", expression = "java(airport.getDepartingAirport().stream().map(this.flightsMapper::toDto).toList())")
    @Mapping(target = "arrivingAirport", expression = "java(airport.getArrivingAirport().stream().map(this.flightsMapper::toDto).toList())")
    public abstract AirportDto toDtoWithAllRelationShip(Airport airport);


    @Mapping(ignore = true, target = "departingAirport")
    @Mapping(target = "arrivingAirport", expression = "java(airport.getArrivingAirport().stream().map(this.flightsMapper::toDto).toList())")
    public abstract AirportDto toDtoWithArrivingAirport(Airport airport);


    @Mapping(target = "departingAirport", expression = "java(airport.getDepartingAirport().stream().map(this.flightsMapper::toDto).toList())")
    @Mapping(ignore = true, target = "arrivingAirport")
    public abstract AirportDto toDtoWithDepartingAirport(Airport airport);


    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "createdAt")
    @Mapping(ignore = true, target = "updatedAt")
    @Mapping(ignore = true, target = "deletedAt")
    @Mapping(ignore = true, target = "departingAirport")
    @Mapping(ignore = true, target = "arrivingAirport")
    public abstract Airport toEntity(AirportDto dto);


    @Mapping(ignore = true, target = "departingAirport")
    @Mapping(ignore = true, target = "arrivingAirport")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget Airport airport, AirportDto dto);

    public void view(Airport airport, AirportDto dto) {
        dto.setArrivingAirport(airport.getArrivingAirport().stream().map(this.flightsMapper::toDto).toList());
        dto.setDepartingAirport(airport.getDepartingAirport().stream().map(this.flightsMapper::toDto).toList());
    }
}
