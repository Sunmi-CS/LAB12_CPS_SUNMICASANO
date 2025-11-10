package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.PetType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PetTypeControllerTest {

    @LocalServerPort
    private int port;

    private final TestRestTemplate restTemplate = new TestRestTemplate();

    private String baseUrl() {
        return "http://localhost:" + port + "/types";
    }

    @Test
    public void testCreateAndGetPetType() {
        PetType petType = new PetType();
        petType.setName("Tigre");

        ResponseEntity<PetType> created = restTemplate.postForEntity(baseUrl(), petType, PetType.class);
        assertNotNull(created.getBody());
        assertEquals("Tigre", created.getBody().getName());

        ResponseEntity<PetType> fetched = restTemplate.getForEntity(baseUrl() + "/" + created.getBody().getId(), PetType.class);
        assertEquals("Tigre", fetched.getBody().getName());
    }

    @Test
    public void testDeletePetType() {
        PetType petType = new PetType();
        petType.setName("León");
        PetType created = restTemplate.postForEntity(baseUrl(), petType, PetType.class).getBody();
        assertNotNull(created);

        restTemplate.delete(baseUrl() + "/" + created.getId());

        // Debe devolver 404 después de eliminar
        ResponseEntity<String> response = restTemplate.getForEntity(baseUrl() + "/" + created.getId(), String.class);
        assertEquals(404, response.getStatusCodeValue());
    }

    // Puedes agregar más tests de update/listar aquí
}
