package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.SpecialtyDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VetSpecialtyControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String getUrl(String path) {
        return "http://localhost:" + port + "/specialties" + path;
    }

    @Test
    void testCreateAndGetVetSpecialty() {
        SpecialtyDTO specialty = new SpecialtyDTO();
        specialty.setName("Surgery");

        ResponseEntity<SpecialtyDTO> postResponse = restTemplate.postForEntity(getUrl(""), specialty, SpecialtyDTO.class);
        assertNotNull(postResponse.getBody(), "El objeto creado no debe ser null");
        assertEquals("Surgery", postResponse.getBody().getName(), "El nombre debe coincidir");

        Integer id = postResponse.getBody().getId();
        SpecialtyDTO getResponse = restTemplate.getForObject(getUrl("/" + id), SpecialtyDTO.class);
        assertNotNull(getResponse, "El objeto recuperado no debe ser null");
        assertEquals("Surgery", getResponse.getName(), "El nombre debe coincidir");
    }

    @Test
    void testDeleteVetSpecialty() {
        SpecialtyDTO specialty = new SpecialtyDTO();
        specialty.setName("Dentistry");

        SpecialtyDTO created = restTemplate.postForObject(getUrl(""), specialty, SpecialtyDTO.class);
        assertNotNull(created, "El objeto creado no debe ser null");
        Integer id = created.getId();

        restTemplate.delete(getUrl("/" + id));

        Exception exception = assertThrows(Exception.class, () -> {
            restTemplate.getForObject(getUrl("/" + id), SpecialtyDTO.class);
        });

        assertTrue(exception.getMessage().contains("404"), "Debe lanzar error 404 al buscar eliminado");
    }
}
