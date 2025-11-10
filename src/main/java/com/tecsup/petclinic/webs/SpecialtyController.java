package com.tecsup.petclinic.webs;

import com.tecsup.petclinic.dtos.SpecialtyDTO;
import com.tecsup.petclinic.services.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/specialties")
public class SpecialtyController {

    @Autowired
    private SpecialtyService specialtyService;

    @PostMapping
    public ResponseEntity<SpecialtyDTO> create(@RequestBody SpecialtyDTO specialtyDTO) {
        SpecialtyDTO created = specialtyService.create(specialtyDTO);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<SpecialtyDTO>> findAllSpecialties() {
        return ResponseEntity.ok(specialtyService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecialtyDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(specialtyService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpecialtyDTO> update(@RequestBody SpecialtyDTO specialtyDTO, @PathVariable Integer id) {
        specialtyDTO.setId(id);
        return ResponseEntity.ok(specialtyService.update(specialtyDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        specialtyService.delete(id);
        return ResponseEntity.ok("Specialty eliminado con ID: " + id);
    }
}
