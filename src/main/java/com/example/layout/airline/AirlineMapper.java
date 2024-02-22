package com.example.layout.airline;

import com.example.layout.flights.FlightsMapper;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring", imports = Collectors.class)
public abstract class AirlineMapper {
    @Autowired
    protected FlightsMapper flightsMapper;
    @Mapping(ignore = true,target = "flights")
    public abstract AirlineDto toDto(Airline airline);

    @Mapping(target = "flights",expression = "java(airline.getFlights().stream().map(this.flightsMapper::toDto).toList())")
    public abstract AirlineDto toDtoWithFlights(Airline airline);


    @Mapping(ignore = true,target = "id")
    @Mapping(ignore = true,target = "createdAt")
    @Mapping(ignore = true,target = "updatedAt")
    @Mapping(ignore = true,target = "deletedAt")
    @Mapping(ignore = true,target = "flights")
    public abstract Airline toEntity(AirlineDto dto);

    @Mapping(ignore = true,target = "flights")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget Airline airline, AirlineDto dto);


    public void view(Airline airline,AirlineDto dto){
        dto.setFlights(airline.getFlights().stream().map(this.flightsMapper::toDto).toList());
    }
}
