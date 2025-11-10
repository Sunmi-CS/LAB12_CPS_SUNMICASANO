package com.tecsup.petclinic.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tecsup.petclinic.dtos.OwnerDTO;
import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.mapper.OwnerMapper;
import com.tecsup.petclinic.repositories.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Pruebas de integración para OwnerController.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class OwnerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OwnerMapper mapper;

    private Owner testOwner;

    @BeforeEach
    void setUp() {
        ownerRepository.deleteAll();

        testOwner = new Owner();
        testOwner.setFirstName("Carlos");
        testOwner.setLastName("Rojas");
        testOwner.setAddress("Av. Primavera 123");
        testOwner.setCity("Lima");
        testOwner.setTelephone("999888777");
        ownerRepository.save(testOwner);
    }

    @Test
    void testFindAllOwners() throws Exception {
        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", not(empty())))
                .andExpect(jsonPath("$[0].firstName", is("Carlos")));
    }

    @Test
    void testFindOwnerById() throws Exception {
        mockMvc.perform(get("/owners/" + testOwner.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("Carlos")))
                .andExpect(jsonPath("$.lastName", is("Rojas")));
    }

    @Test
    void testCreateOwner() throws Exception {
        OwnerDTO newOwner = new OwnerDTO();
        newOwner.setFirstName("Ana");
        newOwner.setLastName("Ramírez");
        newOwner.setAddress("Av. Los Olivos 456");
        newOwner.setCity("Arequipa");
        newOwner.setTelephone("988777666");

        mockMvc.perform(post("/owners")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newOwner)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", is("Ana")))
                .andExpect(jsonPath("$.lastName", is("Ramírez")));
    }

    @Test
    void testUpdateOwner() throws Exception {
        testOwner.setCity("Cusco");

        mockMvc.perform(put("/owners/" + testOwner.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mapper.toOwnerDTO(testOwner))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.city", is("Cusco")));
    }


    @Test
    void testDeleteOwner() throws Exception {
        mockMvc.perform(delete("/owners/" + testOwner.getId()))
                .andExpect(status().isOk());

        Optional<Owner> deleted = ownerRepository.findById(Math.toIntExact(testOwner.getId()));
        assert (deleted.isEmpty());
    }
}
