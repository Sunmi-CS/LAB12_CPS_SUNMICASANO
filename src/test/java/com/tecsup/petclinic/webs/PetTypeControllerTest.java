package com.tecsup.petclinic.webs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tecsup.petclinic.dtos.PetTypeDTO;
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
public class PetTypeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private PetTypeDTO petTypeDTO;

    @BeforeEach
    void setUp() {
        petTypeDTO = new PetTypeDTO();
        petTypeDTO.setName("Reptile");
    }

    @Test
    @DisplayName("Crear PetType correctamente")
    void testCreateType() throws Exception {
        MvcResult result = mockMvc.perform(post("/types")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(petTypeDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Reptile"))
                .andReturn();

        PetTypeDTO created = objectMapper.readValue(result.getResponse().getContentAsString(), PetTypeDTO.class);
        assertThat(created.getId()).isNotNull();
    }

    @Test
    @DisplayName("Listar todos los PetTypes")
    void testListTypes() throws Exception {
        mockMvc.perform(get("/types"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @DisplayName("Obtener PetType por ID")
    void testGetTypeById() throws Exception {
        MvcResult result = mockMvc.perform(post("/types")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(petTypeDTO)))
                .andExpect(status().isCreated())
                .andReturn();

        PetTypeDTO created = objectMapper.readValue(result.getResponse().getContentAsString(), PetTypeDTO.class);

        mockMvc.perform(get("/types/{id}", created.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Reptile"));
    }

    @Test
    @DisplayName("Actualizar PetType existente")
    void testUpdateType() throws Exception {
        MvcResult result = mockMvc.perform(post("/types")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(petTypeDTO)))
                .andExpect(status().isCreated())
                .andReturn();

        PetTypeDTO created = objectMapper.readValue(result.getResponse().getContentAsString(), PetTypeDTO.class);
        created.setName("Amphibian");

        mockMvc.perform(put("/types/{id}", created.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(created)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Amphibian"));
    }

    @Test
    @DisplayName("Eliminar PetType existente")
    void testDeleteType() throws Exception {
        MvcResult result = mockMvc.perform(post("/types")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(petTypeDTO)))
                .andExpect(status().isCreated())
                .andReturn();

        PetTypeDTO created = objectMapper.readValue(result.getResponse().getContentAsString(), PetTypeDTO.class);

        mockMvc.perform(delete("/types/{id}", created.getId()))
                .andExpect(status().isNoContent());
    }
}
