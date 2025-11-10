package com.tecsup.petclinic.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for PetType
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetTypeDTO {

    private Integer id;
    private String name;
}
