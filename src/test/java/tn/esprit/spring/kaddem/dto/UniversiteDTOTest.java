package tn.esprit.spring.kaddem.dto;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UniversiteDTOTest {

    @Test
    void testUniversiteDTO() {
        // Create UniversiteDTO instance using Lombok-generated constructor
        UniversiteDTO universiteDTO = new UniversiteDTO("Test University");

        // Verify the field values
        assertNotNull(universiteDTO);
        assertEquals("Test University", universiteDTO.getNomUniv());

        // Modify the value to test setters
        universiteDTO.setNomUniv("Updated University");
        assertEquals("Updated University", universiteDTO.getNomUniv());
    }

    @Test
    void testUpdateUniversiteDTO() {
        // Create a set of department IDs
        Set<Integer> departmentIds = new HashSet<>();
        departmentIds.add(1);
        departmentIds.add(2);

        // Create UpdateUniversiteDTO instance using Lombok-generated constructor
        UpdateUniversiteDTO updateUniversiteDTO = new UpdateUniversiteDTO(1, "Updated University", departmentIds);

        // Verify the field values
        assertNotNull(updateUniversiteDTO);
        assertEquals(1, updateUniversiteDTO.getIdUniv());
        assertEquals("Updated University", updateUniversiteDTO.getNomUniv());
        assertEquals(departmentIds, updateUniversiteDTO.getDepartementIds());

        // Modify the values to test setters
        updateUniversiteDTO.setNomUniv("New Name");
        updateUniversiteDTO.setIdUniv(2);

        assertEquals("New Name", updateUniversiteDTO.getNomUniv());
        assertEquals(2, updateUniversiteDTO.getIdUniv());
    }
}