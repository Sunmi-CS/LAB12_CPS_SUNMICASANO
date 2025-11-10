package com.tecsup.petclinic.services;

import java.util.List;
import com.tecsup.petclinic.dtos.PetTypeDTO;
import com.tecsup.petclinic.entities.PetType;

public interface PetTypeService {

    List<PetType> findAllPetTypes();

    PetType findPetTypeById(Long id);

    PetType createPetType(PetType petType);

    PetType updatePetType(Long id, PetType petTypeDetails);

    void deletePetType(Long id);

    List<PetTypeDTO> listPetTypes();

    PetTypeDTO findPetTypeById(Integer id);

    PetTypeDTO savePetType(PetTypeDTO petTypeDTO);

    void deletePetType(Integer id);
}
