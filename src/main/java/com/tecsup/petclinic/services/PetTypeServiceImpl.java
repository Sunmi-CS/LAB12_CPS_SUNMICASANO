package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.PetTypeDTO;
import com.tecsup.petclinic.entities.PetType;
import com.tecsup.petclinic.exceptions.ResourceNotFoundException;
import com.tecsup.petclinic.mapper.PetTypeMapper;
import com.tecsup.petclinic.repositories.PetTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetTypeServiceImpl implements PetTypeService {

    @Autowired
    private PetTypeRepository petTypeRepository;

    @Autowired
    private PetTypeMapper petTypeMapper;

    @Override
    public PetTypeDTO create(PetTypeDTO petTypeDTO) {
        PetType type = petTypeMapper.toEntity(petTypeDTO);
        PetType saved = petTypeRepository.save(type);
        return petTypeMapper.toDTO(saved);
    }

    @Override
    public PetTypeDTO update(Integer id, PetTypeDTO petTypeDTO) {
        PetType existing = petTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PetType not found with id: " + id));

        existing.setName(petTypeDTO.getName());
        PetType updated = petTypeRepository.save(existing);
        return petTypeMapper.toDTO(updated);
    }

    @Override
    public void delete(Integer id) {
        PetType existing = petTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PetType not found with id: " + id));
        petTypeRepository.delete(existing);
    }

    @Override
    public PetTypeDTO findById(Integer id) {
        PetType type = petTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PetType not found with id: " + id));
        return petTypeMapper.toDTO(type);
    }

    @Override
    public List<PetTypeDTO> findAll() {
        return petTypeRepository.findAll()
                .stream()
                .map(petTypeMapper::toDTO)
                .collect(Collectors.toList());
    }
}
