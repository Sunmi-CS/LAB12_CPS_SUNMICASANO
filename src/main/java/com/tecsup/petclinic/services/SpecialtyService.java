package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.SpecialtyDTO;

import java.util.List;

public interface SpecialtyService {

    SpecialtyDTO create(SpecialtyDTO specialtyDTO);

    List<SpecialtyDTO> findAll();

    SpecialtyDTO findById(Integer id);

    SpecialtyDTO update(SpecialtyDTO specialtyDTO);

    void delete(Integer id);
}
