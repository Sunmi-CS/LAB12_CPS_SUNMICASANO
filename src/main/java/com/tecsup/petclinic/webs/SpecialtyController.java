package com.tecsup.petclinic.controllers;

import com.tecsup.petclinic.dtos.SpecialtyDTO;
import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.services.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/specialties")
public class SpecialtyController {

    @Autowired
    private SpecialtyService specialtyService;

    @PostMapping
    public SpecialtyDTO createSpecialty(@RequestBody SpecialtyDTO dto) {
        Specialty specialty = new Specialty();
        specialty.setName(dto.getName());

        Specialty saved = specialtyService.createSpecialty(specialty);

        dto.setId(saved.getId());
        return dto;
    }

    @GetMapping("/{id}")
    public SpecialtyDTO getSpecialty(@PathVariable Integer id) {
        Specialty specialty = specialtyService.getSpecialtyById(id);
        if (specialty == null) return null;

        SpecialtyDTO dto = new SpecialtyDTO();
        dto.setId(specialty.getId());
        dto.setName(specialty.getName());
        return dto;
    }

    @GetMapping
    public List<SpecialtyDTO> getAllSpecialties() {
        return specialtyService.getAllSpecialties().stream().map(s -> {
            SpecialtyDTO dto = new SpecialtyDTO();
            dto.setId(s.getId());
            dto.setName(s.getName());
            return dto;
        }).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void deleteSpecialty(@PathVariable Integer id) {
        specialtyService.deleteSpecialty(id);
    }
}
