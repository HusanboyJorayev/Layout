package com.example.layout.baggage_check;

import org.mapstruct.*;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring", imports = Collectors.class)
public abstract class BaggageCheckMapper {
    public abstract BaggageCheckDto toDto(BaggageCheck baggageCheck);


    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "createdAt")
    @Mapping(ignore = true, target = "updatedAt")
    @Mapping(ignore = true, target = "deletedAt")
    public abstract BaggageCheck toEntity(BaggageCheckDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget BaggageCheck baggageCheck, BaggageCheckDto dto);
}
