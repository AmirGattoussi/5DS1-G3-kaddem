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

    @Test
    void testEqualsAndHashCode() {
        // Arrange
        DepartementDTO dept1 = new DepartementDTO(1, "Informatique");
        DepartementDTO dept2 = new DepartementDTO(1, "Informatique");
        DepartementDTO dept3 = new DepartementDTO(2, "Mathematics");

        // Act & Assert
        assertEquals(dept1, dept2); // Should be equal
        assertNotEquals(dept1, dept3); // Should not be equal
        assertEquals(dept1.hashCode(), dept2.hashCode()); // Hash codes should match for equal objects
        assertNotEquals(dept1.hashCode(), dept3.hashCode()); // Hash codes should differ for different objects
    }

    @Test
    void testToString() {
        // Arrange
        DepartementDTO departementDTO = new DepartementDTO(1, "Informatique");

        // Act
        String result = departementDTO.toString();

        // Assert
        assertTrue(result.contains("idDepart=1"));
        assertTrue(result.contains("nomDepart=Informatique"));
    }
}
