package com.tecsup.petclinic.mapper;

import com.tecsup.petclinic.dtos.PetTypeDTO;
import com.tecsup.petclinic.entities.PetType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PetTypeMapper {

    PetTypeDTO toDTO(PetType entity);

    PetType toEntity(PetTypeDTO dto);
}
