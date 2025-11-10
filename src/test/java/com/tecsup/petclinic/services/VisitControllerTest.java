package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.VisitDTO;
import com.tecsup.petclinic.entities.Pet;
import com.tecsup.petclinic.repositories.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VisitControllerTest {

    @LocalServerPort
    private int port;

    private String baseUrl;
    private RestTemplate restTemplate;

    @BeforeEach
    public void setUp() {
        baseUrl = "http://localhost:" + port + "/visits";
        restTemplate = new RestTemplate();
    }

    @Test
    public void testCreateVisit() {
        VisitDTO visit = new VisitDTO();
        visit.setVisitDate(LocalDate.now());
        visit.setDescription("Checkup visit");
        visit.setPetId(1L); // Debe existir Pet con id=1

        ResponseEntity<VisitDTO> response = restTemplate.postForEntity(baseUrl, visit, VisitDTO.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Checkup visit", response.getBody().getDescription());
    }
}
