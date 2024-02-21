package com.example.layout.passangers;

import com.example.layout.baggage_check.BaggageCheckMapper;
import com.example.layout.booking.BookingMapper;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring", imports = Collectors.class)
public abstract class PassengersMapper {

    @Autowired
    protected BaggageCheckMapper baggageCheckMapper;

    @Autowired
    protected BookingMapper bookingMapper;

    @Mapping(ignore = true, target = "baggageCheck")
    @Mapping(ignore = true, target = "booking")
    public abstract PassengersDto toDto(Passengers passengers);

    @Mapping(ignore = true, target = "baggageCheck")
    @Mapping(target = "booking",expression = "java(passengers.getBooking().stream().map(this.bookingMapper::toDto).toList())")
    public abstract PassengersDto toDtoWithBooking(Passengers passengers);


    @Mapping(ignore = true, target = "booking")
    @Mapping(target = "baggageCheck",expression = "java(passengers.getBaggageCheck().stream().map(this.baggageCheckMapper::toDto).toList())")
    public abstract PassengersDto toDtoWithBaggageCheck(Passengers passengers);


    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "createdAt")
    @Mapping(ignore = true, target = "updatedAt")
    @Mapping(ignore = true, target = "deletedAt")
    @Mapping(ignore = true, target = "baggageCheck")
    @Mapping(ignore = true, target = "booking")
    public abstract Passengers toEntity(PassengersDto dto);


    @Mapping(ignore = true, target = "baggageCheck")
    @Mapping(ignore = true, target = "booking")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget Passengers passengers, PassengersDto dto);


    public void view(Passengers passengers,PassengersDto dto){
        dto.setBaggageCheck(passengers.getBaggageCheck().stream().map(this.baggageCheckMapper::toDto).toList());
        dto.setBooking(passengers.getBooking().stream().map(this.bookingMapper::toDto).toList());
    }
}
