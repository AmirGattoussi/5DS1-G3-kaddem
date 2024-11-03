package tn.esprit.spring.kaddem.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.dto.ContratDTO;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Specialite;
import tn.esprit.spring.kaddem.services.IContratService;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ContratRestControllerTest {

    @Mock
    private IContratService contratService;

    @InjectMocks
    private ContratRestController contratRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetContrats() {
        // Arrange
        List<Contrat> expectedContrats = Arrays.asList(new Contrat(), new Contrat());
        when(contratService.retrieveAllContrats()).thenReturn(expectedContrats);

        // Act
        List<Contrat> result = contratRestController.getContrats();

        // Assert
        assertEquals(expectedContrats, result);
        verify(contratService, times(1)).retrieveAllContrats();
    }

    @Test
    void testRetrieveContrat() {
        // Arrange
        Contrat expectedContrat = new Contrat();
        when(contratService.retrieveContrat(1)).thenReturn(expectedContrat);

        // Act
        Contrat result = contratRestController.retrieveContrat(1);

        // Assert
        assertEquals(expectedContrat, result);
        verify(contratService, times(1)).retrieveContrat(1);
    }

    @Test
    void testAddContrat() {
        // Arrange
        Contrat contratToAdd = new Contrat();
        Contrat expectedContrat = new Contrat();
        when(contratService.addContrat(contratToAdd)).thenReturn(expectedContrat);

        // Act
        Contrat result = contratRestController.addContrat(contratToAdd);

        // Assert
        assertEquals(expectedContrat, result);
        verify(contratService, times(1)).addContrat(contratToAdd);
    }

    @Test
    void testRemoveContrat() {
        // Arrange
        Integer contratId = 1;

        // Act
        contratRestController.removeContrat(contratId);

        // Assert
        verify(contratService, times(1)).removeContrat(contratId);
    }

    @Test
    void testUpdateContrat() {
        // Arrange
        ContratDTO contratToUpdate = new ContratDTO();
        contratToUpdate.setIdContrat(1); // Set necessary fields
        contratToUpdate.setDateDebutContrat(new Date());
        contratToUpdate.setDateFinContrat(new Date());
        contratToUpdate.setSpecialite(Specialite.IA);
        contratToUpdate.setArchive(false);
        contratToUpdate.setMontantContrat(1000);

        Contrat expectedContrat = new Contrat();
        expectedContrat.setIdContrat(1); // Ensure these fields match the expected output
        expectedContrat.setDateDebutContrat(contratToUpdate.getDateDebutContrat());
        expectedContrat.setDateFinContrat(contratToUpdate.getDateFinContrat());
        expectedContrat.setSpecialite(contratToUpdate.getSpecialite());
        expectedContrat.setArchive(contratToUpdate.getArchive());
        expectedContrat.setMontantContrat(contratToUpdate.getMontantContrat());

        when(contratService.updateContrat(any(Contrat.class))).thenReturn(expectedContrat);

        // Act
        Contrat result = contratRestController.updateContrat(contratToUpdate);

        // Assert
        assertEquals(expectedContrat, result);
        verify(contratService, times(1)).updateContrat(argThat(contrat ->
                contrat.getIdContrat().equals(contratToUpdate.getIdContrat()) &&
                        contrat.getDateDebutContrat().equals(contratToUpdate.getDateDebutContrat()) &&
                        contrat.getDateFinContrat().equals(contratToUpdate.getDateFinContrat()) &&
                        contrat.getSpecialite().equals(contratToUpdate.getSpecialite()) &&
                        contrat.getArchive().equals(contratToUpdate.getArchive()) &&
                        contrat.getMontantContrat().equals(contratToUpdate.getMontantContrat())
        ));
    }

    @Test
    void testAssignContratToEtudiant() {
        // Arrange
        Integer idContrat = 1;
        String nomE = "John";
        String prenomE = "Doe";
        Contrat expectedContrat = new Contrat();
        when(contratService.affectContratToEtudiant(idContrat, nomE, prenomE)).thenReturn(expectedContrat);

        // Act
        Contrat result = contratRestController.assignContratToEtudiant(idContrat, nomE, prenomE);

        // Assert
        assertEquals(expectedContrat, result);
        verify(contratService, times(1)).affectContratToEtudiant(idContrat, nomE, prenomE);
    }

    @Test
    void testGetNbContratsValides() {
        // Arrange
        Date startDate = new Date();
        Date endDate = new Date();
        Integer expectedNbContrats = 5;
        when(contratService.nbContratsValides(startDate, endDate)).thenReturn(expectedNbContrats);

        // Act
        Integer result = contratRestController.getnbContratsValides(startDate, endDate);

        // Assert
        assertEquals(expectedNbContrats, result);
        verify(contratService, times(1)).nbContratsValides(startDate, endDate);
    }

    @Test
    void testMajStatusContrat() {
        // Act
        contratRestController.majStatusContrat();

        // Assert
        verify(contratService, times(1)).retrieveAndUpdateStatusContrat();
    }

    @Test
    void testCalculChiffreAffaireEntreDeuxDates() {
        // Arrange
        Date startDate = new Date();
        Date endDate = new Date();
        float expectedChiffreAffaire = 10000.0f;
        when(contratService.getChiffreAffaireEntreDeuxDates(startDate, endDate)).thenReturn(expectedChiffreAffaire);

        // Act
        float result = contratRestController.calculChiffreAffaireEntreDeuxDates(startDate, endDate);

        // Assert
        assertEquals(expectedChiffreAffaire, result);
        verify(contratService, times(1)).getChiffreAffaireEntreDeuxDates(startDate, endDate);
    }
}
