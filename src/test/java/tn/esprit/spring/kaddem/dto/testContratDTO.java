package tn.esprit.spring.kaddem.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tn.esprit.spring.kaddem.entities.Specialite;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
    void testDifferentSpecialites() {
        contratDTO.setSpecialite(Specialite.SECURITE);
        assertEquals(Specialite.SECURITE, contratDTO.getSpecialite());
        contratDTO.setSpecialite(Specialite.CLOUD);
        assertEquals(Specialite.CLOUD, contratDTO.getSpecialite());
        contratDTO.setSpecialite(Specialite.RESEAUX);
        assertEquals(Specialite.RESEAUX, contratDTO.getSpecialite());
        contratDTO.setSpecialite(Specialite.IA);
        assertEquals(Specialite.IA, contratDTO.getSpecialite());
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

    @Test
    void testInvalidId() {
        ContratDTO invalidIdDTO = new ContratDTO(-1, startDate, endDate, Specialite.CLOUD, false, 5000);
        assertEquals(-1, invalidIdDTO.getIdContrat(), "Id should allow negative values, but verify if any business rules apply.");
    }

    @Test
    void testExpiredContractDates() {
        Date pastDate = new Date(System.currentTimeMillis() - 174000000); // Date in the past
        Date startDate = new Date(System.currentTimeMillis() + 100000); // Date in the future
        ContratDTO expiredContractDTO = new ContratDTO(3, pastDate, startDate, Specialite.CLOUD, false, 5000);

        // Print values for debugging
        System.out.println("Date Fin Contrat: " + expiredContractDTO.getDateFinContrat());
        System.out.println("Date Debut Contrat: " + expiredContractDTO.getDateDebutContrat());

        assertTrue(expiredContractDTO.getDateFinContrat().before(expiredContractDTO.getDateDebutContrat()),
                "DateFinContrat should be before DateDebutContrat for an expired contract.");
    }

    @Test
    void testExtremeValuesMontantContrat() {
        ContratDTO extremeMontantDTO = new ContratDTO(4, startDate, endDate, Specialite.IA, false, Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, extremeMontantDTO.getMontantContrat(), "Montant should accept extreme high values.");

        extremeMontantDTO.setMontantContrat(Integer.MIN_VALUE);
        assertEquals(Integer.MIN_VALUE, extremeMontantDTO.getMontantContrat(), "Montant should accept extreme low values.");
    }

    @Test
    void testSerializationEdgeCases() throws IOException {
        Date maxDate = new Date(Long.MAX_VALUE); // Maximum date value
        ContratDTO edgeCaseDTO = new ContratDTO(5, startDate, maxDate, Specialite.CLOUD, false, 5000);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(edgeCaseDTO);
        ContratDTO deserializedDTO = objectMapper.readValue(jsonString, ContratDTO.class);

        assertEquals(edgeCaseDTO, deserializedDTO, "Serialization and deserialization should maintain state even for edge case dates.");
    }

    @Test
    void testImmutableStateAfterCreation() {
        ContratDTO immutableDTO = ContratDTO.builder()
                .idContrat(6)
                .dateDebutContrat(startDate)
                .dateFinContrat(endDate)
                .specialite(Specialite.CLOUD)
                .archive(false)
                .montantContrat(6000)
                .build();

        // Verify initial state
        assertEquals(6, immutableDTO.getIdContrat());
        assertEquals(Specialite.CLOUD, immutableDTO.getSpecialite());

        // Attempt to change state after creation
        immutableDTO.setMontantContrat(7000);
        assertEquals(7000, immutableDTO.getMontantContrat(), "Montant should reflect change, but check if immutability rules apply.");
    }

    @Test
    void testMultipleSpecialites() {
        ContratDTO multiSpecialiteDTO = new ContratDTO();

        multiSpecialiteDTO.setSpecialite(Specialite.CLOUD);
        assertEquals(Specialite.CLOUD, multiSpecialiteDTO.getSpecialite());

        multiSpecialiteDTO.setSpecialite(Specialite.SECURITE);
        assertEquals(Specialite.SECURITE, multiSpecialiteDTO.getSpecialite());

        multiSpecialiteDTO.setSpecialite(Specialite.IA);
        assertEquals(Specialite.IA, multiSpecialiteDTO.getSpecialite());

        multiSpecialiteDTO.setSpecialite(Specialite.RESEAUX);
        assertEquals(Specialite.RESEAUX, multiSpecialiteDTO.getSpecialite());
    }

}
