package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.VisitDTO;
import com.tecsup.petclinic.entities.Pet;
import com.tecsup.petclinic.entities.Visit;
import com.tecsup.petclinic.mapper.VisitMapper;
import com.tecsup.petclinic.repositories.PetRepository;
import com.tecsup.petclinic.repositories.VisitRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VisitServiceImpl implements VisitService {

    private final VisitRepository visitRepository;
    private final PetRepository petRepository;
    private final VisitMapper visitMapper;

    public VisitServiceImpl(VisitRepository visitRepository, PetRepository petRepository, VisitMapper visitMapper) {
        this.visitRepository = visitRepository;
        this.petRepository = petRepository;
        this.visitMapper = visitMapper;
    }

    @Override
    public VisitDTO createVisit(VisitDTO visitDTO) {
        Visit visit = visitMapper.toEntity(visitDTO);
        Pet pet = petRepository.findById(Math.toIntExact(visitDTO.getPetId()))
                .orElseThrow(() -> new RuntimeException("Pet not found"));
        visit.setPet(pet);
        return visitMapper.toDTO(visitRepository.save(visit));
    }

    @Override
    public List<VisitDTO> findAllVisits() {
        return visitRepository.findAll().stream()
                .map(visitMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public VisitDTO findVisitById(Long id) {
        return visitRepository.findById(id)
                .map(visitMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Visit not found"));
    }

    @Override
    public VisitDTO updateVisit(Long id, VisitDTO visitDTO) {
        Visit visit = visitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Visit not found"));
        visit.setDescription(visitDTO.getDescription());
        visit.setVisitDate(visitDTO.getVisitDate());
        return visitMapper.toDTO(visitRepository.save(visit));
    }

    @Override
    public void deleteVisit(Long id) {
        visitRepository.deleteById(id);
    }
}
