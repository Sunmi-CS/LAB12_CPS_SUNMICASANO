package com.tecsup.petclinic.exceptions;

public class OwnerNotFoundException extends RuntimeException {

    public OwnerNotFoundException(Integer id) {
        super("Owner not found with ID: " + id);
    }
}
