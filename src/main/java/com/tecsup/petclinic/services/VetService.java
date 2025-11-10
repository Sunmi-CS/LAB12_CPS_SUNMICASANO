package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.VetDTO;
import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.exceptions.VetNotFoundException;

import java.util.List;

/**
 * Servicio para la gestión de veterinarios (Vet)
 */
public interface VetService {

    List<Vet> findAll();

    VetDTO findById(Integer id) throws VetNotFoundException;

    VetDTO create(VetDTO vetDTO);

    VetDTO update(VetDTO vetDTO) throws VetNotFoundException;

    void delete(Integer id) throws VetNotFoundException;
}
