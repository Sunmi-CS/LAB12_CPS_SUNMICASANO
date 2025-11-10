package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.SpecialtyDTO;
import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.exceptions.ResourceNotFoundException;
import com.tecsup.petclinic.mapper.SpecialtyMapper;
import com.tecsup.petclinic.repositories.SpecialtyRepository;
import com.tecsup.petclinic.services.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpecialtyServiceImpl implements SpecialtyService {

    @Autowired
    private SpecialtyRepository specialtyRepository;

    @Autowired
    private SpecialtyMapper specialtyMapper;

    @Override
    public SpecialtyDTO create(SpecialtyDTO specialtyDTO) {
        Specialty entity = specialtyMapper.toEntity(specialtyDTO);
        Specialty saved = specialtyRepository.save(entity);
        return specialtyMapper.toDTO(saved);
    }

    @Override
    public List<SpecialtyDTO> findAll() {
        return specialtyRepository.findAll()
                .stream()
                .map(specialtyMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SpecialtyDTO findById(Integer id) {
        Specialty specialty = specialtyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Specialty not found with ID " + id));
        return specialtyMapper.toDTO(specialty);
    }

    @Override
    public SpecialtyDTO update(SpecialtyDTO specialtyDTO) {
        Specialty existing = specialtyRepository.findById(specialtyDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Specialty not found with ID " + specialtyDTO.getId()));
        existing.setName(specialtyDTO.getName());
        Specialty updated = specialtyRepository.save(existing);
        return specialtyMapper.toDTO(updated);
    }

    @Override
    public void delete(Integer id) {
        Specialty existing = specialtyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Specialty not found with ID " + id));
        specialtyRepository.delete(existing);
    }
}
