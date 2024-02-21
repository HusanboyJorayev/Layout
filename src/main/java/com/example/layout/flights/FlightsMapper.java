package com.example.layout.flights;

import com.example.layout.booking.BookingMapper;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring", imports = Collectors.class)
public abstract class FlightsMapper {

    @Autowired
    protected BookingMapper bookingMapper;

    @Mapping(ignore = true, target = "booking")
    public abstract FlightsDto toDto(Flights flights);

    @Mapping(target = "booking",expression = "java(flights.getBooking().stream().map(this.bookingMapper::toDto).toList())")
    public abstract FlightsDto toDtoWithBooking(Flights flights);


    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "createdAt")
    @Mapping(ignore = true, target = "updatedAt")
    @Mapping(ignore = true, target = "deletedAt")
    @Mapping(ignore = true, target = "booking")
    public abstract Flights toEntity(FlightsDto dto);

    @Mapping(ignore = true, target = "booking")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget Flights flights, FlightsDto dto);

    public void view(Flights flights,FlightsDto dto){
        dto.setBooking(flights.getBooking().stream().map(this.bookingMapper::toDto).toList());
    }
}
