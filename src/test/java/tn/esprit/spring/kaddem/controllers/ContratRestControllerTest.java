package tn.esprit.spring.kaddem.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.services.IContratService;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
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
        Contrat contratToUpdate = new Contrat();
        Contrat expectedContrat = new Contrat();
        when(contratService.updateContrat(contratToUpdate)).thenReturn(expectedContrat);

        // Act
        Contrat result = contratRestController.updateContrat(contratToUpdate);

        // Assert
        assertEquals(expectedContrat, result);
        verify(contratService, times(1)).updateContrat(contratToUpdate);
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
