package com.tecsup.petclinic.webs;

import com.tecsup.petclinic.dtos.VetDTO;
import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.exceptions.VetNotFoundException;
import com.tecsup.petclinic.mapper.VetMapper;
import com.tecsup.petclinic.services.VetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la entidad Vet.
 * Maneja las operaciones CRUD con DTOs y capa de servicio.
 *
 * @author
 */
@RestController
@Slf4j
public class VetController {

    private final VetService vetService;
    private final VetMapper mapper;

    public VetController(VetService vetService, VetMapper mapper) {
        this.vetService = vetService;
        this.mapper = mapper;
    }

    /**
     * Obtener todos los veterinarios
     */
    @GetMapping("/vets")
    public ResponseEntity<List<VetDTO>> findAllVets() {

        List<Vet> vets = vetService.findAll();
        log.info("Vets: {}", vets);

        List<VetDTO> vetDTOs = mapper.mapToDtoList(vets);
        log.info("VetDTOs: {}", vetDTOs);

        return ResponseEntity.ok(vetDTOs);
    }

    /**
     * Buscar un veterinario por ID
     */
    @GetMapping("/vets/{id}")
    public ResponseEntity<VetDTO> findById(@PathVariable Integer id) {
        try {
            VetDTO vetDTO = vetService.findById(id);
            return ResponseEntity.ok(vetDTO);
        } catch (VetNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Crear un nuevo veterinario
     */
    @PostMapping("/vets")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<VetDTO> create(@RequestBody VetDTO vetDTO) {
        VetDTO newVetDTO = vetService.create(vetDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newVetDTO);
    }

    /**
     * Actualizar un veterinario existente
     */
    @PutMapping("/vets/{id}")
    public ResponseEntity<VetDTO> update(@RequestBody VetDTO vetDTO, @PathVariable Integer id) {
        try {
            VetDTO existingVetDTO = vetService.findById(id);

            existingVetDTO.setFirstName(vetDTO.getFirstName());
            existingVetDTO.setLastName(vetDTO.getLastName());

            VetDTO updatedVetDTO = vetService.update(existingVetDTO);
            return ResponseEntity.ok(updatedVetDTO);
        } catch (VetNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Eliminar un veterinario por ID
     */
    @DeleteMapping("/vets/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        try {
            vetService.delete(id);
            return ResponseEntity.ok("Deleted Vet with ID: " + id);
        } catch (VetNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
