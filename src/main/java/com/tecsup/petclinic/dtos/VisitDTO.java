package com.tecsup.petclinic.dtos;

import lombok.Data;
import java.time.LocalDate;

@Data
public class VisitDTO {
    private Long id;
    private LocalDate visitDate;
    private String description;
    private Long petId; // referenciamos la mascota
}
