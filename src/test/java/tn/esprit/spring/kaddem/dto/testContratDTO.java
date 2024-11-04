package tn.esprit.spring.kaddem.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import tn.esprit.spring.kaddem.entities.Specialite;

import java.io.IOException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ContratDTOTest {

    @Test
    void testContratDTOBuilder() {
        // Arrange
        Date startDate = new Date();
        Date endDate = new Date();

        // Act
        ContratDTO contratDTO = ContratDTO.builder()
                .idContrat(1)
                .dateDebutContrat(startDate)
                .dateFinContrat(endDate)
                .specialite(Specialite.CLOUD)
                .archive(false)
                .montantContrat(5000)
                .build();

        // Assert
        assertNotNull(contratDTO);
        assertEquals(1, contratDTO.getIdContrat());
        assertEquals(startDate, contratDTO.getDateDebutContrat());
        assertEquals(endDate, contratDTO.getDateFinContrat());
        assertEquals(Specialite.CLOUD, contratDTO.getSpecialite());
        assertEquals(false, contratDTO.getArchive());
        assertEquals(5000, contratDTO.getMontantContrat());
    }

    @Test
    void testSettersAndGetters() {
        // Arrange
        Date startDate = new Date();
        Date endDate = new Date();
        ContratDTO contratDTO = new ContratDTO();

        // Act
        contratDTO.setIdContrat(2);
        contratDTO.setDateDebutContrat(startDate);
        contratDTO.setDateFinContrat(endDate);
        contratDTO.setSpecialite(Specialite.IA);
        contratDTO.setArchive(true);
        contratDTO.setMontantContrat(3000);

        // Assert
        assertEquals(2, contratDTO.getIdContrat());
        assertEquals(startDate, contratDTO.getDateDebutContrat());
        assertEquals(endDate, contratDTO.getDateFinContrat());
        assertEquals(Specialite.IA, contratDTO.getSpecialite());
        assertEquals(true, contratDTO.getArchive());
        assertEquals(3000, contratDTO.getMontantContrat());
    }

    @Test
    void testEqualsAndHashCode() {
        // Arrange
        Date startDate = new Date();
        Date endDate = new Date();

        ContratDTO contratDTO1 = ContratDTO.builder()
                .idContrat(3)
                .dateDebutContrat(startDate)
                .dateFinContrat(endDate)
                .specialite(Specialite.RESEAUX)
                .archive(false)
                .montantContrat(2000)
                .build();

        ContratDTO contratDTO2 = ContratDTO.builder()
                .idContrat(3)
                .dateDebutContrat(startDate)
                .dateFinContrat(endDate)
                .specialite(Specialite.RESEAUX)
                .archive(false)
                .montantContrat(2000)
                .build();

        // Act & Assert
        assertEquals(contratDTO1, contratDTO2);
        assertEquals(contratDTO1.hashCode(), contratDTO2.hashCode());
    }

    @Test
    void testToString() {
        // Arrange
        Date startDate = new Date();
        Date endDate = new Date();

        ContratDTO contratDTO = ContratDTO.builder()
                .idContrat(4)
                .dateDebutContrat(startDate)
                .dateFinContrat(endDate)
                .specialite(Specialite.SECURITE)
                .archive(true)
                .montantContrat(1000)
                .build();

        // Act
        String result = contratDTO.toString();

        // Assert
        assertNotNull(result);
        System.out.println(result); // Optional: to view the output
    }

    @Test
    void testNullValues() {
        ContratDTO contratDTO = new ContratDTO();

        // Test setting null values
        assertThrows(NullPointerException.class, () -> {
            contratDTO.setSpecialite(null);
        });
    }

    @Test
    void testMontantContratBoundary() {
        ContratDTO contratDTO = new ContratDTO();

        contratDTO.setMontantContrat(0);
        assertEquals(0, contratDTO.getMontantContrat());

        assertThrows(IllegalArgumentException.class, () -> {
            contratDTO.setMontantContrat(-1000);
        });
    }

    @Test
    void testInvalidDateRange() {
        ContratDTO contratDTO = new ContratDTO();
        Date startDate = new Date(System.currentTimeMillis() + 100000); // Future date
        Date endDate = new Date(System.currentTimeMillis()); // Past date

        assertThrows(IllegalArgumentException.class, () -> {
            contratDTO.setDateDebutContrat(startDate);
            contratDTO.setDateFinContrat(endDate); // Assuming this should not be allowed
        });
    }

    @Test
    void testSerialization() throws IOException {
        ContratDTO contratDTO = ContratDTO.builder()
                .idContrat(1)
                .dateDebutContrat(new Date())
                .dateFinContrat(new Date())
                .specialite(Specialite.CLOUD)
                .archive(false)
                .montantContrat(5000)
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(contratDTO);

        ContratDTO deserializedDTO = objectMapper.readValue(jsonString, ContratDTO.class);
        assertEquals(contratDTO, deserializedDTO);
    }

    @Test
    void testDefaultValues() {
        ContratDTO contratDTO = new ContratDTO();

        assertEquals(0, contratDTO.getIdContrat()); // Assuming default ID is 0
        assertNull(contratDTO.getDateDebutContrat()); // Assuming null by default
        assertNull(contratDTO.getDateFinContrat());
        assertNull(contratDTO.getSpecialite());
        assertFalse(contratDTO.getArchive()); // Assuming default is false
        assertEquals(0, contratDTO.getMontantContrat()); // Assuming default is 0
    }



}
