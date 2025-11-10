package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.VetDTO;
import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.exceptions.VetNotFoundException;
import com.tecsup.petclinic.mapper.VetMapper;
import com.tecsup.petclinic.repositories.VetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementación del servicio Vet
 */
@Service
@Slf4j
public class VetServiceImpl implements VetService {

    private final VetRepository vetRepository;
    private final VetMapper mapper;

    public VetServiceImpl(VetRepository vetRepository, VetMapper mapper) {
        this.vetRepository = vetRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Vet> findAll() {
        log.info("Buscando todos los veterinarios...");
        return vetRepository.findAll();
    }

    @Override
    public VetDTO findById(Integer id) throws VetNotFoundException {
        Optional<Vet> optionalVet = vetRepository.findById(id);

        if (optionalVet.isEmpty()) {
            throw new VetNotFoundException(id);
        }

        Vet vet = optionalVet.get();
        log.info("Vet encontrado: {}", vet);
        return mapper.toVetDTO(vet);
    }

    @Override
    public VetDTO create(VetDTO vetDTO) {
        Vet vet = mapper.toVetEntity(vetDTO);
        Vet newVet = vetRepository.save(vet);
        log.info("Vet creado: {}", newVet);
        return mapper.toVetDTO(newVet);
    }

    @Override
    public VetDTO update(VetDTO vetDTO) throws VetNotFoundException {
        if (!vetRepository.existsById(vetDTO.getId())) {
            throw new VetNotFoundException(vetDTO.getId());
        }

        Vet vet = mapper.toVetEntity(vetDTO);
        Vet updatedVet = vetRepository.save(vet);
        log.info("Vet actualizado: {}", updatedVet);
        return mapper.toVetDTO(updatedVet);
    }

    @Override
    public void delete(Integer id) throws VetNotFoundException {
        if (!vetRepository.existsById(id)) {
            throw new VetNotFoundException(id);
        }

        vetRepository.deleteById(id);
        log.info("Vet eliminado con ID: {}", id);
    }
}
