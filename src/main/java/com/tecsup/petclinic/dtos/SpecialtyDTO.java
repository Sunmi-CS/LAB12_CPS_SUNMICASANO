package com.tecsup.petclinic.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for Specialty
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpecialtyDTO {

    private Integer id;
    private String name;
}
