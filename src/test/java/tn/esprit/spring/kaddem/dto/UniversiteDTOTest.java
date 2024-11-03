package tn.esprit.spring.kaddem.dto;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

 class UniversiteDTOTest {

    @Test
     void testNoArgsConstructor() {
        // Test the no-argument constructor
        UniversiteDTO universiteDTO = new UniversiteDTO();

        // Verify that fields are initialized to null
        assertNull(universiteDTO.getIdUniv());
        assertNull(universiteDTO.getNomUniv());
        assertNull(universiteDTO.getDepartementIds());
    }

    @Test
     void testAllArgsConstructor() {
        // Create sample data
        Integer id = 1;
        String name = "Test University";
        Set<Integer> departmentIds = new HashSet<>(Arrays.asList(1, 2)); // Changed here

        // Test the all-args constructor
        UniversiteDTO universiteDTO = new UniversiteDTO(id, name, departmentIds);

        // Verify the values
        assertEquals(id, universiteDTO.getIdUniv());
        assertEquals(name, universiteDTO.getNomUniv());
        assertEquals(departmentIds, universiteDTO.getDepartementIds());
    }

    @Test
     void testSettersAndGetters() {
        // Create a new UniversiteDTO
        UniversiteDTO universiteDTO = new UniversiteDTO();

        // Set values using setters
        universiteDTO.setIdUniv(1);
        universiteDTO.setNomUniv("Updated University");

        Set<Integer> departmentIds = new HashSet<>(Arrays.asList(1, 2)); // Changed here
        universiteDTO.setDepartementIds(departmentIds);

        // Verify values using getters
        assertEquals(1, universiteDTO.getIdUniv());
        assertEquals("Updated University", universiteDTO.getNomUniv());
        assertEquals(departmentIds, universiteDTO.getDepartementIds());
    }

    @Test
     void testEqualsAndHashCode() {
        UniversiteDTO universiteDTO1 = new UniversiteDTO(1, "University A", new HashSet<>(Arrays.asList(1, 2))); // Changed here
        UniversiteDTO universiteDTO2 = new UniversiteDTO(1, "University A", new HashSet<>(Arrays.asList(1, 2))); // Changed here
        UniversiteDTO universiteDTO3 = new UniversiteDTO(2, "University B", new HashSet<>(Arrays.asList(3, 4))); // Changed here

        // Verify that equal objects have the same hash code and are considered equal
        assertEquals(universiteDTO1, universiteDTO2);
        assertEquals(universiteDTO1.hashCode(), universiteDTO2.hashCode());

        // Verify that different objects have different hash codes and are not considered equal
        assertNotEquals(universiteDTO1, universiteDTO3);
        assertNotEquals(universiteDTO1.hashCode(), universiteDTO3.hashCode());
    }

    @Test
     void testToString() {
        UniversiteDTO universiteDTO = new UniversiteDTO(1, "Test University", new HashSet<>(Arrays.asList(1, 2))); // Changed here

        // Verify that toString does not return null and contains expected values
        String toString = universiteDTO.toString();
        assertNotNull(toString);
        assertTrue(toString.contains("idUniv=1"));
        assertTrue(toString.contains("nomUniv=Test University"));
        assertTrue(toString.contains("departementIds=[1, 2]"));
    }
}
