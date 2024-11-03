package tn.esprit.spring.kaddem.dto;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;



class DepartementDTOTest {

    @Test
    void testConstructorAndGetters() {
        Integer id = 1;
        String name = "Informatique";
        DepartementDTO departementDTO = new DepartementDTO(id, name);

        assertEquals(id, departementDTO.getIdDepart());
        assertEquals(name, departementDTO.getNomDepart());
    }

    @Test
    void testSetters() {
        DepartementDTO departementDTO = new DepartementDTO(null, null);
        Integer id = 2;
        String name = "Mathematics";

        departementDTO.setIdDepart(id);
        departementDTO.setNomDepart(name);

        assertEquals(id, departementDTO.getIdDepart());
        assertEquals(name, departementDTO.getNomDepart());
    }

    @Test
    void testNoArgsConstructor() {
        DepartementDTO departementDTO = new DepartementDTO(null, null);

        assertNull(departementDTO.getIdDepart());
        assertNull(departementDTO.getNomDepart());
    }

    @Test
    void testEqualsAndHashCode() {
        DepartementDTO dept1 = new DepartementDTO(1, "Informatique");
        DepartementDTO dept2 = new DepartementDTO(1, "Informatique");
        DepartementDTO dept3 = new DepartementDTO(2, "Mathematics");

        assertEquals(dept1, dept2); // Should be equal
        assertNotEquals(dept1, dept3); // Should not be equal
        assertEquals(dept1.hashCode(), dept2.hashCode()); // Hash codes should match for equal objects
        assertNotEquals(dept1.hashCode(), dept3.hashCode()); // Hash codes should differ for different objects

        // Additional cases for completeness
        assertNotEquals(null, dept1); // Should not be equal to null
        assertNotEquals(new Object(), dept1); // Should not be equal to an object of another type
        assertEquals(dept1, dept1); // Should be equal to itself
    }

    @Test
    void testToString() {
        DepartementDTO departementDTO = new DepartementDTO(1, "Informatique");

        String expected = "DepartementDTO(idDepart=1, nomDepart=Informatique)";
        assertEquals(expected, departementDTO.toString());
    }
}
