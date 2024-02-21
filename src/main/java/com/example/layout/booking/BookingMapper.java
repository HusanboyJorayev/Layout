package com.example.layout.booking;

import com.example.layout.baggage.BaggageMapper;
import com.example.layout.baggage_check.BaggageCheckMapper;
import com.example.layout.boarding_pass.BoardingPassMapper;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring", imports = Collectors.class)
public abstract class BookingMapper {

    @Autowired
    protected BaggageMapper baggageMapper;

    @Autowired
    protected BaggageCheckMapper baggageCheckMapper;

    @Autowired
    protected BoardingPassMapper boardingPassMapper;

    @Mapping(ignore = true, target = "baggage")
    @Mapping(ignore = true, target = "baggageCheck")
    @Mapping(ignore = true, target = "boardingPass")
    public abstract BookingDto toDto(Booking booking);


    @Mapping(ignore = true, target = "baggage")
    @Mapping(ignore = true, target = "baggageCheck")
    @Mapping(target = "boardingPass",expression = "java(booking.getBoardingPass().stream().map(this.boardingPassMapper::toDto).toList())")
    public abstract BookingDto toDtoWithBoardingPass(Booking booking);

    @Mapping(ignore = true, target = "baggage")
    @Mapping(ignore = true, target = "boardingPass")
    @Mapping(target = "baggageCheck",expression = "java(booking.getBaggageCheck().stream().map(this.baggageCheckMapper::toDto).toList())")
    public abstract BookingDto toDtoWithBaggageCheck(Booking booking);


    @Mapping(ignore = true, target = "baggageCheck")
    @Mapping(ignore = true, target = "boardingPass")
    @Mapping(target = "baggage", expression = "java(booking.getBaggage().stream().map(this.baggageMapper::toDto).toList())")
    public abstract BookingDto toDtoWithBaggage(Booking booking);


    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "createdAt")
    @Mapping(ignore = true, target = "updatedAt")
    @Mapping(ignore = true, target = "deletedAt")
    @Mapping(ignore = true, target = "baggage")
    @Mapping(ignore = true, target = "baggageCheck")
    @Mapping(ignore = true, target = "boardingPass")
    public abstract Booking toEntity(BookingDto dto);


    @Mapping(ignore = true, target = "baggage")
    @Mapping(ignore = true, target = "baggageCheck")
    @Mapping(ignore = true, target = "boardingPass")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget Booking booking, BookingDto dto);

    public void view(Booking booking, BookingDto dto) {
        dto.setBaggage(booking.getBaggage().stream().map(this.baggageMapper::toDto).toList());
        dto.setBaggageCheck(booking.getBaggageCheck().stream().map(this.baggageCheckMapper::toDto).toList());
        dto.setBoardingPass(booking.getBoardingPass().stream().map(this.boardingPassMapper::toDto).toList());
    }
}
