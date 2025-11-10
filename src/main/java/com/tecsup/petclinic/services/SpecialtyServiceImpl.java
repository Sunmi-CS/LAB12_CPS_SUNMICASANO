package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.repositories.SpecialtyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialtyServiceImpl implements SpecialtyService {

    @Autowired
    private SpecialtyRepository specialtyRepository;

    @Override
    public Specialty createSpecialty(Specialty specialty) {
        return specialtyRepository.save(specialty);
    }

    @Override
    public Specialty getSpecialtyById(Integer id) {
        return specialtyRepository.findById(id).orElse(null);
    }

    @Override
    public List<Specialty> getAllSpecialties() {
        return specialtyRepository.findAll();
    }

    @Override
    public void deleteSpecialty(Integer id) {
        specialtyRepository.deleteById(id);
    }
}
