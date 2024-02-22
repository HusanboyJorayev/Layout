package com.example.layout.passangers;

import com.example.layout.baggage_check.BaggageCheckMapper;
import com.example.layout.booking.BookingMapper;
import com.example.layout.no_fly_list.NoFlyListMapper;
import com.example.layout.security_check.SecurityCheckMapper;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring", imports = Collectors.class)
public abstract class PassengersMapper {

    @Autowired
    protected BaggageCheckMapper baggageCheckMapper;

    @Autowired
    protected BookingMapper bookingMapper;
    @Autowired
    protected SecurityCheckMapper securityCheckMapper;
    @Autowired
    protected NoFlyListMapper noFlyListMapper;

    @Mapping(ignore = true, target = "baggageCheck")
    @Mapping(ignore = true, target = "booking")
    @Mapping(ignore = true, target = "securityCheck")
    @Mapping(ignore = true, target = "noFlyList")
    public abstract PassengersDto toDto(Passengers passengers);


    @Mapping(target = "baggageCheck",expression = "java(passengers.getBaggageCheck().stream().map(this.baggageCheckMapper::toDto).toList())")
    @Mapping(target = "booking",expression = "java(passengers.getBooking().stream().map(this.bookingMapper::toDto).toList())")
    @Mapping(target = "securityCheck",expression = "java(passengers.getSecurityCheck().stream().map(this.securityCheckMapper::toDto).toList())")
    @Mapping(target = "noFlyList",expression = "java(passengers.getNoFlyList().stream().map(this.noFlyListMapper::toDto).toList())")
    public abstract PassengersDto toDtoWithAllRelationShip(Passengers passengers);

    @Mapping(ignore = true, target = "baggageCheck")
    @Mapping(ignore = true, target = "booking")
    @Mapping(ignore = true, target = "noFlyList")
    @Mapping(target = "securityCheck",expression = "java(passengers.getSecurityCheck().stream().map(this.securityCheckMapper::toDto).toList())")
    public abstract PassengersDto toDtoWithSecurityCheck(Passengers passengers);

    @Mapping(ignore = true, target = "baggageCheck")
    @Mapping(ignore = true, target = "booking")
    @Mapping(ignore = true, target = "securityCheck")
    @Mapping(target = "noFlyList",expression = "java(passengers.getNoFlyList().stream().map(this.noFlyListMapper::toDto).toList())")
    public abstract PassengersDto toDtoWithNoFlyList(Passengers passengers);

    @Mapping(ignore = true, target = "baggageCheck")
    @Mapping(ignore = true, target = "securityCheck")
    @Mapping(ignore = true, target = "noFlyList")
    @Mapping(target = "booking",expression = "java(passengers.getBooking().stream().map(this.bookingMapper::toDto).toList())")
    public abstract PassengersDto toDtoWithBooking(Passengers passengers);


    @Mapping(ignore = true, target = "booking")
    @Mapping(ignore = true, target = "securityCheck")
    @Mapping(ignore = true, target = "noFlyList")
    @Mapping(target = "baggageCheck",expression = "java(passengers.getBaggageCheck().stream().map(this.baggageCheckMapper::toDto).toList())")
    public abstract PassengersDto toDtoWithBaggageCheck(Passengers passengers);


    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "createdAt")
    @Mapping(ignore = true, target = "updatedAt")
    @Mapping(ignore = true, target = "deletedAt")
    @Mapping(ignore = true, target = "securityCheck")
    @Mapping(ignore = true, target = "noFlyList")
    @Mapping(ignore = true, target = "baggageCheck")
    @Mapping(ignore = true, target = "booking")
    public abstract Passengers toEntity(PassengersDto dto);


    @Mapping(ignore = true, target = "baggageCheck")
    @Mapping(ignore = true, target = "booking")
    @Mapping(ignore = true, target = "securityCheck")
    @Mapping(ignore = true, target = "noFlyList")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget Passengers passengers, PassengersDto dto);


    public void view(Passengers passengers,PassengersDto dto){
        dto.setBaggageCheck(passengers.getBaggageCheck().stream().map(this.baggageCheckMapper::toDto).toList());
        dto.setBooking(passengers.getBooking().stream().map(this.bookingMapper::toDto).toList());
        dto.setSecurityCheck(passengers.getSecurityCheck().stream().map(this.securityCheckMapper::toDto).toList());
        dto.setNoFlyList(passengers.getNoFlyList().stream().map(this.noFlyListMapper::toDto).toList());
    }
}
