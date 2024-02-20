package com.example.layout.no_fly_list;

import org.mapstruct.*;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring", imports = Collectors.class)
public abstract class NoFlyListMapper {
    public abstract NoFlyListDto toDto(NoFlyList noFlyList);


    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "createdAt")
    @Mapping(ignore = true, target = "updatedAt")
    @Mapping(ignore = true, target = "deletedAt")
    public abstract NoFlyList toEntity(NoFlyListDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget NoFlyList noFlyList, NoFlyListDto dto);
}
