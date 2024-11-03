package tn.esprit.spring.kaddem.dto;

import org.junit.jupiter.api.Test;


import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UniversiteDTOTest {

    @Test
    void testUniversiteDTO() {
        // Create UniversiteDTO instance
        UniversiteDTO universiteDTO = new UniversiteDTO("Test University");

        // Verify the field value
        assertEquals("Test University", universiteDTO.getNomUniv());

        // Test setter and getter
        universiteDTO.setNomUniv("Updated University");
        assertEquals("Updated University", universiteDTO.getNomUniv());

        // Test toString
        assertNotNull(universiteDTO.toString());

        // Test equals and hashCode
        UniversiteDTO universiteDTO2 = new UniversiteDTO("Updated University");
        assertEquals(universiteDTO, universiteDTO2);
        assertEquals(universiteDTO.hashCode(), universiteDTO2.hashCode());
    }

    @Test
    void testUpdateUniversiteDTO() {
        // Create a set of department IDs
        Set<Integer> departmentIds = new HashSet<>();
        departmentIds.add(1);
        departmentIds.add(2);

        // Create UpdateUniversiteDTO instance
        UpdateUniversiteDTO updateUniversiteDTO = new UpdateUniversiteDTO(1, "Updated University", departmentIds);

        // Verify the field values
        assertEquals(1, updateUniversiteDTO.getIdUniv());
        assertEquals("Updated University", updateUniversiteDTO.getNomUniv());
        assertEquals(departmentIds, updateUniversiteDTO.getDepartementIds());

        // Test setters and getters
        updateUniversiteDTO.setNomUniv("New Name");
        updateUniversiteDTO.setIdUniv(2);
        assertEquals("New Name", updateUniversiteDTO.getNomUniv());
        assertEquals(2, updateUniversiteDTO.getIdUniv());

        // Test toString
        assertNotNull(updateUniversiteDTO.toString());

        // Test equals and hashCode
        UpdateUniversiteDTO updateUniversiteDTO2 = new UpdateUniversiteDTO(2, "New Name", departmentIds);
        assertEquals(updateUniversiteDTO, updateUniversiteDTO2);
        assertEquals(updateUniversiteDTO.hashCode(), updateUniversiteDTO2.hashCode());
    }
}