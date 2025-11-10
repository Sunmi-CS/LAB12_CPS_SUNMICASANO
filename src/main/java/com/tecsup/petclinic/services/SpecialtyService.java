package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.SpecialtyDTO;

import java.util.List;

public interface SpecialtyService {

    SpecialtyDTO create(SpecialtyDTO specialtyDTO);

    SpecialtyDTO update(Integer id, SpecialtyDTO specialtyDTO);

    void delete(Integer id);

    SpecialtyDTO findById(Integer id);

    List<SpecialtyDTO> findAll();
}
