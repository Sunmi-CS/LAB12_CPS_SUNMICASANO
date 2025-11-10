package com.tecsup.petclinic.mapper;

import com.tecsup.petclinic.dtos.OwnerDTO;
import com.tecsup.petclinic.entities.Owner;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OwnerMapper {

    OwnerDTO toOwnerDTO(Owner owner);

    Owner toOwnerEntity(OwnerDTO dto);

    List<OwnerDTO> mapToDtoList(List<Owner> owners);
}
