package com.example.layout.boarding_pass;

import org.mapstruct.*;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring", imports = Collectors.class)
public abstract class BoardingPassMapper {
    public abstract BoardingPassDto toDto(BoardingPass boardingPass);


    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "createdAt")
    @Mapping(ignore = true, target = "updatedAt")
    @Mapping(ignore = true, target = "deletedAt")
    public abstract BoardingPass toEntity(BoardingPassDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget BoardingPass boardingPass, BoardingPassDto dto);
}
