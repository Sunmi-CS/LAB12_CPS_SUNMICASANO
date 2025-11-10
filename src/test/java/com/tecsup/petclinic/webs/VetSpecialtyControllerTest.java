package com.tecsup.petclinic.webs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tecsup.petclinic.dtos.SpecialtyDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class VetSpecialtyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private SpecialtyDTO specialtyDTO;

    @BeforeEach
    void setUp() {
        specialtyDTO = new SpecialtyDTO();
        specialtyDTO.setName("Dentistry");
    }

    @Test
    @DisplayName("Crear Specialty correctamente")
    void testCreateSpecialty() throws Exception {

        MvcResult result = mockMvc.perform(post("/specialties")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(specialtyDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("Dentistry"))
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        SpecialtyDTO created = objectMapper.readValue(responseBody, SpecialtyDTO.class);

        assertThat(created.getId()).isNotNull();
    }

    @Test
    @DisplayName("Listar todas las Specialties")
    void testListSpecialties() throws Exception {
        mockMvc.perform(get("/specialties")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @DisplayName("Obtener Specialty por ID")
    void testGetSpecialtyById() throws Exception {
        // Primero creamos una
        MvcResult result = mockMvc.perform(post("/specialties")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(specialtyDTO)))
                .andExpect(status().isCreated())
                .andReturn();

        SpecialtyDTO created = objectMapper.readValue(result.getResponse().getContentAsString(), SpecialtyDTO.class);

        // Luego la buscamos
        mockMvc.perform(get("/specialties/{id}", created.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(created.getId()))
                .andExpect(jsonPath("$.name").value("Dentistry"));
    }

    @Test
    @DisplayName("Actualizar Specialty existente")
    void testUpdateSpecialty() throws Exception {
        // Crear
        MvcResult result = mockMvc.perform(post("/specialties")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(specialtyDTO)))
                .andExpect(status().isCreated())
                .andReturn();

        SpecialtyDTO created = objectMapper.readValue(result.getResponse().getContentAsString(), SpecialtyDTO.class);
        created.setName("Surgery");

        // Actualizar
        mockMvc.perform(put("/specialties/{id}", created.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(created)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Surgery"));
    }

    @Test
    @DisplayName("Eliminar Specialty existente")
    void testDeleteSpecialty() throws Exception {
        // Crear
        MvcResult result = mockMvc.perform(post("/specialties")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(specialtyDTO)))
                .andExpect(status().isCreated())
                .andReturn();

        SpecialtyDTO created = objectMapper.readValue(result.getResponse().getContentAsString(), SpecialtyDTO.class);

        // Eliminar
        mockMvc.perform(delete("/specialties/{id}", created.getId()))
                .andExpect(status().isNoContent());
    }
}
