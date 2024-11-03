package tn.esprit.spring.kaddem.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.dto.UniversiteDTO;
import tn.esprit.spring.kaddem.dto.UpdateUniversiteDTO;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.services.IUniversiteService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UniversiteRestControllerTest {

    @InjectMocks
    private UniversiteRestController universiteRestController;

    @Mock
    private IUniversiteService universiteService;

    @Mock
    private DepartementRepository departementRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
     void testGetUniversites() {
        Universite universite1 = new Universite(1, "University A");
        Universite universite2 = new Universite(2, "University B");

        when(universiteService.retrieveAllUniversites()).thenReturn(Arrays.asList(universite1, universite2));

        List<Universite> universites = universiteRestController.getUniversites();

        assertEquals(2, universites.size());
        verify(universiteService, times(1)).retrieveAllUniversites();
    }

    @Test
    void testRetrieveUniversite() {
        Universite universite = new Universite(1, "University A");

        when(universiteService.retrieveUniversite(1)).thenReturn(universite);

        Universite result = universiteRestController.retrieveUniversite(1);

        assertEquals("University A", result.getNomUniv());
        verify(universiteService, times(1)).retrieveUniversite(1);
    }

    @Test
    void testAddUniversite() {
        UniversiteDTO universiteDTO = new UniversiteDTO();
        universiteDTO.setNomUniv("New University");

        Universite universite = new Universite("New University");
        when(universiteService.addUniversite(any(Universite.class))).thenReturn(universite);

        Universite result = universiteRestController.addUniversite(universiteDTO);

        assertEquals("New University", result.getNomUniv());
        verify(universiteService, times(1)).addUniversite(any(Universite.class));
    }

    @Test
    void testRemoveUniversite() {
        doNothing().when(universiteService).deleteUniversite(1);

        universiteRestController.removeUniversite(1);

        verify(universiteService, times(1)).deleteUniversite(1);
    }

    @Test
    void testUpdateUniversite() {
        UpdateUniversiteDTO universiteDTO = new UpdateUniversiteDTO();
        universiteDTO.setIdUniv(1);
        universiteDTO.setNomUniv("Updated University");
        universiteDTO.setDepartementIds(new HashSet<>(Arrays.asList(1, 2)));

        Universite universite = new Universite(1, "Original University");
        Departement departement1 = new Departement();
        Departement departement2 = new Departement();

        when(universiteService.retrieveUniversite(1)).thenReturn(universite);
        when(departementRepository.findById(1)).thenReturn(Optional.of(departement1));
        when(departementRepository.findById(2)).thenReturn(Optional.of(departement2));
        when(universiteService.updateUniversite(any(Universite.class))).thenReturn(universite);

        Universite result = universiteRestController.updateUniversite(universiteDTO);

        assertEquals("Updated University", result.getNomUniv());
        assertEquals(2, result.getDepartements().size());
        verify(universiteService, times(1)).updateUniversite(any(Universite.class));
    }

    @Test
    void testAffecterUniversiteToDepartement() {
        doNothing().when(universiteService).assignUniversiteToDepartement(1, 2);

        universiteRestController.affectertUniversiteToDepartement(1, 2);

        verify(universiteService, times(1)).assignUniversiteToDepartement(1, 2);
    }

    @Test
    void testListerDepartementsUniversite() {
        new Universite(1, "University A");
        Departement departement1 = new Departement();
        Departement departement2 = new Departement();
        Set<Departement> departements = new HashSet<>(Arrays.asList(departement1, departement2));

        when(universiteService.retrieveDepartementsByUniversite(1)).thenReturn(departements);

        Set<Departement> result = universiteRestController.listerDepartementsUniversite(1);

        assertEquals(2, result.size());
        verify(universiteService, times(1)).retrieveDepartementsByUniversite(1);
    }
    @Test
    void testUpdateUniversite_NomUnivIsNull() {
        // Arrange
        UpdateUniversiteDTO universiteDTO = new UpdateUniversiteDTO();
        universiteDTO.setIdUniv(1);
        universiteDTO.setNomUniv(null);  // Simulate a null name update

        Universite universite = new Universite(1, "Original University");

        // Mocking behavior
        when(universiteService.retrieveUniversite(1)).thenReturn(universite);
        when(universiteService.updateUniversite(any(Universite.class))).thenReturn(universite);

        // Act
        Universite result = universiteRestController.updateUniversite(universiteDTO);

        // Assert
        assertEquals("Original University", result.getNomUniv(), "Name should remain unchanged");
        verify(universiteService, times(1)).updateUniversite(any(Universite.class));
    }
    @Test
    void testUpdateUniversite_DepartementIdsIsNull() {
        // Arrange
        UpdateUniversiteDTO universiteDTO = new UpdateUniversiteDTO();
        universiteDTO.setIdUniv(1);
        universiteDTO.setNomUniv("Updated University");
        universiteDTO.setDepartementIds(null);  // Simulate null department IDs

        Universite universite = new Universite(1, "Original University");
        Set<Departement> originalDepartements = new HashSet<>();
        universite.setDepartements(originalDepartements);

        // Mocking behavior
        when(universiteService.retrieveUniversite(1)).thenReturn(universite);
        when(universiteService.updateUniversite(any(Universite.class))).thenReturn(universite);

        // Act
        Universite result = universiteRestController.updateUniversite(universiteDTO);

        // Assert
        assertEquals("Updated University", result.getNomUniv());
        assertEquals(originalDepartements, result.getDepartements(), "Departements should remain unchanged");
        verify(universiteService, times(1)).updateUniversite(any(Universite.class));
    }
    @Test
    void testUpdateUniversite_DepartementIsNull() {
        // Arrange
        UpdateUniversiteDTO universiteDTO = new UpdateUniversiteDTO();
        universiteDTO.setIdUniv(1);
        universiteDTO.setDepartementIds(new HashSet<>(Arrays.asList(1, 2)));

        Universite universite = new Universite(1, "University");
        Departement departement1 = new Departement(1, "Informatique");

        // Mock behavior: one department exists, the other does not
        when(universiteService.retrieveUniversite(1)).thenReturn(universite);
        when(departementRepository.findById(1)).thenReturn(Optional.of(departement1));
        when(departementRepository.findById(2)).thenReturn(Optional.empty());
        when(universiteService.updateUniversite(any(Universite.class))).thenReturn(universite);

        // Act
        Universite result = universiteRestController.updateUniversite(universiteDTO);

        // Assert
        assertEquals(1, result.getDepartements().size(), "Only one department should be added");
        assertTrue(result.getDepartements().contains(departement1), "The non-null department should be added");
        verify(universiteService, times(1)).updateUniversite(any(Universite.class));
    }

}