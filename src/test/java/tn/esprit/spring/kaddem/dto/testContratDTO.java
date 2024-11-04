package tn.esprit.spring.kaddem.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tn.esprit.spring.kaddem.entities.Specialite;

import java.io.IOException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ContratDTOTest {

    private ContratDTO contratDTO;
    private Date startDate;
    private Date endDate;

    @BeforeEach
    void setUp() {
        startDate = new Date();
        endDate = new Date(System.currentTimeMillis() + 100000); // Future date for testing
        contratDTO = ContratDTO.builder()
                .idContrat(1)
                .dateDebutContrat(startDate)
                .dateFinContrat(endDate)
                .specialite(Specialite.CLOUD)
                .archive(false)
                .montantContrat(5000)
                .build();
    }

    @Test
    void testContratDTOBuilder() {
        assertNotNull(contratDTO);
        assertEquals(1, contratDTO.getIdContrat());
        assertEquals(startDate, contratDTO.getDateDebutContrat());
        assertEquals(endDate, contratDTO.getDateFinContrat());
        assertEquals(Specialite.CLOUD, contratDTO.getSpecialite());
        assertFalse(contratDTO.getArchive());
        assertEquals(5000, contratDTO.getMontantContrat());
    }

    @Test
    void testSettersAndGetters() {
        contratDTO.setIdContrat(2);
        contratDTO.setDateDebutContrat(startDate);
        contratDTO.setDateFinContrat(endDate);
        contratDTO.setSpecialite(Specialite.IA);
        contratDTO.setArchive(true);
        contratDTO.setMontantContrat(3000);

        assertEquals(2, contratDTO.getIdContrat());
        assertEquals(startDate, contratDTO.getDateDebutContrat());
        assertEquals(endDate, contratDTO.getDateFinContrat());
        assertEquals(Specialite.IA, contratDTO.getSpecialite());
        assertTrue(contratDTO.getArchive());
        assertEquals(3000, contratDTO.getMontantContrat());
    }

    @Test
    void testEqualsAndHashCode() {
        ContratDTO contratDTO2 = ContratDTO.builder()
                .idContrat(1)
                .dateDebutContrat(startDate)
                .dateFinContrat(endDate)
                .specialite(Specialite.CLOUD)
                .archive(false)
                .montantContrat(5000)
                .build();

        assertEquals(contratDTO, contratDTO2);
        assertEquals(contratDTO.hashCode(), contratDTO2.hashCode());
    }

    @Test
    void testToString() {
        String result = contratDTO.toString();
        assertNotNull(result);
        System.out.println(result); // Optional: to view the output
    }

    @Test
    void testSerialization() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(contratDTO);
        ContratDTO deserializedDTO = objectMapper.readValue(jsonString, ContratDTO.class);
        assertEquals(contratDTO, deserializedDTO);
    }

    @Test
    void testSerializationWithNullValues() throws IOException {
        contratDTO.setDateFinContrat(null);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(contratDTO);
        ContratDTO deserializedDTO = objectMapper.readValue(jsonString, ContratDTO.class);
        assertEquals(contratDTO, deserializedDTO);
        assertNull(deserializedDTO.getDateFinContrat());
    }

    @Test
    void testNegativeMontantContrat() {
        contratDTO.setMontantContrat(-1000);
        assertEquals(-1000, contratDTO.getMontantContrat(), "Montant should be able to hold negative values if business rules allow it.");
    }

    @Test
    void testNullSpecialite() {
        contratDTO.setSpecialite(null);
        assertNull(contratDTO.getSpecialite(), "Specialite should allow null if no specialty is assigned.");
    }

    @Test
    void testDefaultConstructorAndBuilder() {
        ContratDTO emptyDTO = new ContratDTO();
        assertNull(emptyDTO.getIdContrat());
        assertNull(emptyDTO.getDateDebutContrat());
        assertNull(emptyDTO.getDateFinContrat());
        assertNull(emptyDTO.getSpecialite());
        assertNull(emptyDTO.getArchive());
        assertNull(emptyDTO.getMontantContrat());
    }
}
