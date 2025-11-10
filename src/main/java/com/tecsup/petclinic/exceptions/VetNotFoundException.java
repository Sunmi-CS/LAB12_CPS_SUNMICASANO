package com.tecsup.petclinic.exceptions;

public class VetNotFoundException extends RuntimeException {
    public VetNotFoundException(Integer id) {
        super("Vet not found with ID: " + id);
    }
}
