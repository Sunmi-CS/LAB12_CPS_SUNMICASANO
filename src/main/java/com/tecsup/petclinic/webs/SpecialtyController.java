package com.tecsup.petclinic.webs;

import com.tecsup.petclinic.dtos.SpecialtyDTO;
import com.tecsup.petclinic.services.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/specialties")
public class SpecialtyController {

    @Autowired
    private SpecialtyService specialtyService;

    @PostMapping
    public ResponseEntity<SpecialtyDTO> createSpecialty(@RequestBody SpecialtyDTO specialtyDTO) {
        SpecialtyDTO newSpecialty = specialtyService.create(specialtyDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newSpecialty);
    }

    @GetMapping
    public ResponseEntity<List<SpecialtyDTO>> listSpecialties() {
        return ResponseEntity.ok(specialtyService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecialtyDTO> getSpecialty(@PathVariable Integer id) {
        SpecialtyDTO specialty = specialtyService.findById(id);
        return ResponseEntity.ok(specialty);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpecialtyDTO> updateSpecialty(@PathVariable Integer id, @RequestBody SpecialtyDTO specialtyDTO) {
        SpecialtyDTO updated = specialtyService.update(id, specialtyDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpecialty(@PathVariable Integer id) {
        specialtyService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
