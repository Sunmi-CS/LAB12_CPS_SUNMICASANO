package com.tecsup.petclinic.webs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tecsup.petclinic.dtos.VetDTO;
import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.mapper.VetMapper;
import com.tecsup.petclinic.repositories.VetRepository;
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
 * Pruebas de integración para VetController.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class VetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private VetRepository vetRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private VetMapper mapper;

    private Vet testVet;

    @BeforeEach
    void setUp() {
        vetRepository.deleteAll();

        testVet = new Vet();
        testVet.setFirstName("John");
        testVet.setLastName("Doe");
        vetRepository.save(testVet);
    }

    @Test
    void testFindAllVets() throws Exception {
        mockMvc.perform(get("/vets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", not(empty())))
                .andExpect(jsonPath("$[0].firstName", is("John")))
                .andExpect(jsonPath("$[0].lastName", is("Doe")));
    }

    @Test
    void testFindVetById() throws Exception {
        mockMvc.perform(get("/vets/" + testVet.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("John")))
                .andExpect(jsonPath("$.lastName", is("Doe")));
    }

    @Test
    void testCreateVet() throws Exception {
        VetDTO newVet = new VetDTO();
        newVet.setFirstName("Jane");
        newVet.setLastName("Smith");

        mockMvc.perform(post("/vets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newVet)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", is("Jane")))
                .andExpect(jsonPath("$.lastName", is("Smith")));
    }

    @Test
    void testUpdateVet() throws Exception {
        testVet.setFirstName("Updated");

        mockMvc.perform(put("/vets/" + testVet.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mapper.toVetDTO(testVet))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("Updated")));
    }

    @Test
    void testDeleteVet() throws Exception {
        mockMvc.perform(delete("/vets/" + testVet.getId()))
                .andExpect(status().isOk());

        Optional<Vet> deleted = vetRepository.findById(testVet.getId());
        assert (deleted.isEmpty());
    }
}
