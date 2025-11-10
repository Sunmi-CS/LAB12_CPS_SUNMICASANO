package com.tecsup.petclinic.webs;

import com.tecsup.petclinic.dtos.OwnerDTO;
import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exceptions.OwnerNotFoundException;
import com.tecsup.petclinic.mapper.OwnerMapper;
import com.tecsup.petclinic.services.OwnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la entidad Owner.
 */
@RestController
@Slf4j
public class OwnerController {

    private final OwnerService ownerService;
    private final OwnerMapper mapper;

    public OwnerController(OwnerService ownerService, OwnerMapper mapper) {
        this.ownerService = ownerService;
        this.mapper = mapper;
    }

    /**
     * Listar todos los propietarios
     */
    @GetMapping("/owners")
    public ResponseEntity<List<OwnerDTO>> findAllOwners() {
        List<Owner> owners = ownerService.findAll();
        log.info("Owners encontrados: {}", owners);

        List<OwnerDTO> ownersDTO = mapper.mapToDtoList(owners);
        return ResponseEntity.ok(ownersDTO);
    }

    /**
     * Buscar propietario por ID
     */
    @GetMapping("/owners/{id}")
    public ResponseEntity<OwnerDTO> findById(@PathVariable Integer id) {
        try {
            OwnerDTO ownerDTO = ownerService.findById(id);
            return ResponseEntity.ok(ownerDTO);
        } catch (OwnerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Crear nuevo propietario
     */
    @PostMapping("/owners")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<OwnerDTO> create(@RequestBody OwnerDTO ownerDTO) {
        OwnerDTO newOwner = ownerService.create(ownerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newOwner);
    }

    /**
     * Actualizar propietario existente
     */
    @PutMapping("/owners/{id}")
    public ResponseEntity<OwnerDTO> update(@RequestBody OwnerDTO ownerDTO, @PathVariable Integer id) {
        try {
            OwnerDTO existingOwner = ownerService.findById(id);

            existingOwner.setFirstName(ownerDTO.getFirstName());
            existingOwner.setLastName(ownerDTO.getLastName());
            existingOwner.setAddress(ownerDTO.getAddress());
            existingOwner.setCity(ownerDTO.getCity());
            existingOwner.setTelephone(ownerDTO.getTelephone());

            OwnerDTO updatedOwner = ownerService.update(existingOwner);
            return ResponseEntity.ok(updatedOwner);
        } catch (OwnerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Eliminar propietario
     */
    @DeleteMapping("/owners/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        try {
            ownerService.delete(id);
            return ResponseEntity.ok("Deleted Owner with ID: " + id);
        } catch (OwnerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
