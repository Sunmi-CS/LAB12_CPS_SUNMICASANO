package com.tecsup.petclinic.webs;

import com.tecsup.petclinic.dtos.VisitDTO;
import com.tecsup.petclinic.services.VisitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/visits")
public class VisitController {

    private final VisitService visitService;

    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @PostMapping
    public ResponseEntity<VisitDTO> createVisit(@RequestBody VisitDTO visitDTO) {
        return ResponseEntity.ok(visitService.createVisit(visitDTO));
    }

    @GetMapping
    public ResponseEntity<List<VisitDTO>> findAllVisits() {
        return ResponseEntity.ok(visitService.findAllVisits());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VisitDTO> findVisitById(@PathVariable Long id) {
        return ResponseEntity.ok(visitService.findVisitById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VisitDTO> updateVisit(@PathVariable Long id, @RequestBody VisitDTO visitDTO) {
        return ResponseEntity.ok(visitService.updateVisit(id, visitDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVisit(@PathVariable Long id) {
        visitService.deleteVisit(id);
        return ResponseEntity.noContent().build();
    }
}
