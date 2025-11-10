package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.PetTypeDTO;

import java.util.List;

public interface PetTypeService {

    PetTypeDTO create(PetTypeDTO petTypeDTO);

    PetTypeDTO update(Integer id, PetTypeDTO petTypeDTO);

    void delete(Integer id);

    PetTypeDTO findById(Integer id);

    List<PetTypeDTO> findAll();
}
