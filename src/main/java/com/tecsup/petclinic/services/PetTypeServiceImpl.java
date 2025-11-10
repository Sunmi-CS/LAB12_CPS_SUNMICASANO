package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.PetTypeDTO;
import com.tecsup.petclinic.entities.PetType;
import com.tecsup.petclinic.repositories.PetTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PetTypeServiceImpl implements PetTypeService {

    @Autowired
    private PetTypeRepository petTypeRepository;

    @Override
    public List<PetType> findAllPetTypes() {
        return petTypeRepository.findAll();
    }

    @Override
    public PetType findPetTypeById(Long id) {
        return petTypeRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "PetType not found"));
    }

    @Override
    public PetType createPetType(PetType petType) {
        return petTypeRepository.save(petType);
    }

    @Override
    public PetType updatePetType(Long id, PetType petTypeDetails) {
        PetType existing = findPetTypeById(id);
        existing.setName(petTypeDetails.getName());
        return petTypeRepository.save(existing);
    }

    @Override
    public void deletePetType(Long id) {
        PetType existing = findPetTypeById(id);
        petTypeRepository.delete(existing);
    }

    @Override
    public List<PetTypeDTO> listPetTypes() {
        return List.of();
    }

    @Override
    public PetTypeDTO findPetTypeById(Integer id) {
        return null;
    }

    @Override
    public PetTypeDTO savePetType(PetTypeDTO petTypeDTO) {
        return null;
    }

    @Override
    public void deletePetType(Integer id) {

    }
}
