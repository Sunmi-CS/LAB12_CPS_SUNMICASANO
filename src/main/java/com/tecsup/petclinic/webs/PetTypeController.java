package com.tecsup.petclinic.webs;

import com.tecsup.petclinic.entities.PetType;
import com.tecsup.petclinic.services.PetTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/types")
public class PetTypeController {

    @Autowired
    private PetTypeService petTypeService;

    @GetMapping
    public List<PetType> listAll() {
        return petTypeService.findAllPetTypes();
    }

    @GetMapping("/{id}")
    public PetType getById(@PathVariable Long id) {
        return petTypeService.findPetTypeById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PetType create(@RequestBody PetType petType) {
        return petTypeService.createPetType(petType);
    }

    @PutMapping("/{id}")
    public PetType update(@PathVariable Long id, @RequestBody PetType petType) {
        return petTypeService.updatePetType(id, petType);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        petTypeService.deletePetType(id);
    }
}
