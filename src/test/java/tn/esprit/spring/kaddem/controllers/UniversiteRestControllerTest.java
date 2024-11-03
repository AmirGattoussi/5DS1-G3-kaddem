package tn.esprit.spring.kaddem.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.dto.UniversiteDTO;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.services.IUniversiteService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;


import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
     void testUpdateUniversite_withNomUnivAndDepartementIds() {
        // Scenario where nomUniv and departementIds are provided
        UniversiteDTO universiteDTO = new UniversiteDTO(1, "Updated University", new HashSet<>(Arrays.asList(1, 2)));
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
    }

    @Test
     void testUpdateUniversite_withoutNomUniv() {
        // Scenario where nomUniv is null but departementIds is provided
        UniversiteDTO universiteDTO = new UniversiteDTO(1, null, new HashSet<>(Arrays.asList(1)));
        Universite universite = new Universite(1, "Original University");
        Departement departement1 = new Departement();

        when(universiteService.retrieveUniversite(1)).thenReturn(universite);
        when(departementRepository.findById(1)).thenReturn(Optional.of(departement1));
        when(universiteService.updateUniversite(any(Universite.class))).thenReturn(universite);

        Universite result = universiteRestController.updateUniversite(universiteDTO);

        assertEquals("Original University", result.getNomUniv());
        assertEquals(1, result.getDepartements().size());
    }

    @Test
     void testUpdateUniversite_withNonexistentDepartement() {
        // Scenario where one of the departement IDs does not exist in the repository
        UniversiteDTO universiteDTO = new UniversiteDTO(1, "Updated University", new HashSet<>(Arrays.asList(1, 2)));
        Universite universite = new Universite(1, "Original University");
        Departement departement1 = new Departement();

        when(universiteService.retrieveUniversite(1)).thenReturn(universite);
        when(departementRepository.findById(1)).thenReturn(Optional.of(departement1));
        when(departementRepository.findById(2)).thenReturn(Optional.empty()); // Nonexistent department
        when(universiteService.updateUniversite(any(Universite.class))).thenReturn(universite);

        Universite result = universiteRestController.updateUniversite(universiteDTO);

        assertEquals("Updated University", result.getNomUniv());
        assertEquals(1, result.getDepartements().size()); // Only 1 valid department added
    }

    @Test
    void testUpdateUniversite_withoutDepartementIds() {
       // Scenario where departementIds is null
       UniversiteDTO universiteDTO = new UniversiteDTO(1, "Updated University", null);
       Universite universite = new Universite(1, "Original University");

       when(universiteService.retrieveUniversite(1)).thenReturn(universite);
       when(universiteService.updateUniversite(any(Universite.class))).thenReturn(universite);

       Universite result = universiteRestController.updateUniversite(universiteDTO);

       assertEquals("Updated University", result.getNomUniv());
       assertEquals(0, result.getDepartements() == null ? 0 : result.getDepartements().size()); // Check for null
    }
}
