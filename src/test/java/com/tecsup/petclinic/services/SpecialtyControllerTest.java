package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.SpecialtyDTO;
import com.tecsup.petclinic.services.SpecialtyService;
import com.tecsup.petclinic.webs.SpecialtyController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SpecialtyControllerTest {

    @Autowired
    private SpecialtyController specialtyController;

    @Autowired
    private SpecialtyService specialtyService;

    private SpecialtyDTO testSpecialty;

    @BeforeEach
    public void setUp() {
        testSpecialty = new SpecialtyDTO();
        testSpecialty.setName("Cardiology");
    }

    @Test
    public void testCreateSpecialty() {
        ResponseEntity<SpecialtyDTO> response = specialtyController.create(testSpecialty);
        SpecialtyDTO created = response.getBody();

        assertNotNull(created);
        assertNotNull(created.getId(), "El ID no debe ser null después de la creación");
        assertEquals("Cardiology", created.getName());

        testSpecialty = created;
    }

    @Test
    public void testFindAllSpecialties() {
        ResponseEntity<List<SpecialtyDTO>> response = specialtyController.findAllSpecialties();
        List<SpecialtyDTO> specialties = response.getBody();

        assertNotNull(specialties);
        assertTrue(specialties.size() > 0, "Debe haber al menos una specialty registrada");
    }

    @Test
    public void testFindSpecialtyById() {
        SpecialtyDTO created = specialtyService.create(testSpecialty);
        ResponseEntity<SpecialtyDTO> response = specialtyController.findById(created.getId());
        SpecialtyDTO found = response.getBody();

        assertNotNull(found);
        assertEquals(created.getId(), found.getId());
        assertEquals(created.getName(), found.getName());
    }

    @Test
    public void testUpdateSpecialty() {
        SpecialtyDTO created = specialtyService.create(testSpecialty);

        created.setName("Neurology");
        ResponseEntity<SpecialtyDTO> response = specialtyController.update(created, created.getId());
        SpecialtyDTO updated = response.getBody();

        assertNotNull(updated);
        assertEquals("Neurology", updated.getName());
    }

    @Test
    public void testDeleteSpecialty() {
        SpecialtyDTO created = specialtyService.create(testSpecialty);

        ResponseEntity<String> response = specialtyController.delete(created.getId());

        assertNotNull(response, "La respuesta no debe ser null");
        assertNotNull(response.getBody(), "El body no debe ser null");
        assertTrue(response.getBody().contains(String.valueOf(created.getId())),
                "El mensaje de eliminación debe contener el ID");

        // Verificar que ya no exista
        try {
            specialtyController.findById(created.getId());
            fail("Debe lanzar ResourceNotFoundException");
        } catch (Exception e) {
            assertTrue(e instanceof RuntimeException);
        }
    }
}
