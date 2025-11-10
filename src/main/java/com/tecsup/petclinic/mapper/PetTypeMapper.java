package com.tecsup.petclinic.mapper;

import com.tecsup.petclinic.dtos.PetTypeDTO;
import com.tecsup.petclinic.entities.PetType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PetTypeMapper {

    PetTypeMapper INSTANCE = Mappers.getMapper(PetTypeMapper.class);

    PetTypeDTO toDTO(PetType petType);

    PetType toEntity(PetTypeDTO petTypeDTO);
}
