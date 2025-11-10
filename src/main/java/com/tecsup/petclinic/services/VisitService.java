package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.VisitDTO;

import java.util.List;

public interface VisitService {
    VisitDTO createVisit(VisitDTO visitDTO);
    List<VisitDTO> findAllVisits();
    VisitDTO findVisitById(Long id);
    VisitDTO updateVisit(Long id, VisitDTO visitDTO);
    void deleteVisit(Long id);
}
