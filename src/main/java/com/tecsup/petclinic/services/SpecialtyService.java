package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Specialty;
import java.util.List;

public interface SpecialtyService {

    Specialty createSpecialty(Specialty specialty);

    Specialty getSpecialtyById(Integer id);

    List<Specialty> getAllSpecialties();

    void deleteSpecialty(Integer id);
}
