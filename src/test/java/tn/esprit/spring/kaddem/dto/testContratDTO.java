package tn.esprit.spring.kaddem.dto;

import org.junit.jupiter.api.Test;
import tn.esprit.spring.kaddem.entities.Specialite;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
}
