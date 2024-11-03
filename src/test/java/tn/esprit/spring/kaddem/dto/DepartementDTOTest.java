package tn.esprit.spring.kaddem.dto;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DepartementDTOTest {

    @Test
    void testConstructorAndGetters() {
        // Arrange
        Integer id = 1;
        String name = "Informatique";

        // Act
        DepartementDTO departementDTO = new DepartementDTO(id, name);

        // Assert
        assertEquals(id, departementDTO.getIdDepart());
        assertEquals(name, departementDTO.getNomDepart());
    }

    @Test
    void testSetters() {
        // Arrange
        DepartementDTO departementDTO = new DepartementDTO(null, null);
        Integer id = 2;
        String name = "Mathematics";

        // Act
        departementDTO.setIdDepart(id);
        departementDTO.setNomDepart(name);

        // Assert
        assertEquals(id, departementDTO.getIdDepart());
        assertEquals(name, departementDTO.getNomDepart());
    }

    @Test
    void testNoArgsConstructor() {
        // Act
        DepartementDTO departementDTO = new DepartementDTO(null, null);

        // Assert
        assertNull(departementDTO.getIdDepart());
        assertNull(departementDTO.getNomDepart());
    }
}
