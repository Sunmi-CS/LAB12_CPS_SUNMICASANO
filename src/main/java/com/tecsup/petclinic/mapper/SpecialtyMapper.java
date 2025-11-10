package com.tecsup.petclinic.mapper;

import com.tecsup.petclinic.dtos.SpecialtyDTO;
import com.tecsup.petclinic.entities.Specialty;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SpecialtyMapper {

    SpecialtyDTO toDTO(Specialty specialty);

    Specialty toEntity(SpecialtyDTO dto);

    List<SpecialtyDTO> toDTOList(List<Specialty> specialties);
}
