package com.tecsup.petclinic.webs;

import com.tecsup.petclinic.dtos.PetTypeDTO;
import com.tecsup.petclinic.services.PetTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/types")
public class PetTypeController {

    @Autowired
    private PetTypeService petTypeService;

    @PostMapping
    public ResponseEntity<PetTypeDTO> createType(@RequestBody PetTypeDTO petTypeDTO) {
        PetTypeDTO created = petTypeService.create(petTypeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<PetTypeDTO>> listTypes() {
        return ResponseEntity.ok(petTypeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetTypeDTO> getTypeById(@PathVariable Integer id) {
        return ResponseEntity.ok(petTypeService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PetTypeDTO> updateType(@PathVariable Integer id, @RequestBody PetTypeDTO petTypeDTO) {
        PetTypeDTO updated = petTypeService.update(id, petTypeDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteType(@PathVariable Integer id) {
        petTypeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
