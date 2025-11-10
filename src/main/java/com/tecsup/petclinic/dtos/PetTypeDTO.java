package com.tecsup.petclinic.dtos;

public class PetTypeDTO {

    private Integer id;
    private String name;

    public PetTypeDTO() {}

    public PetTypeDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    // getters y setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
