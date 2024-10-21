package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.UniversiteRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UniversiteServiceImplMockitoTest {

    @Mock
    UniversiteRepository universiteRepository;

    @Mock
    DepartementRepository departementRepository;

    @InjectMocks
    UniversiteServiceImpl universiteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddUniversite() {
        Universite universite = new Universite("Mock University");
        when(universiteRepository.save(universite)).thenReturn(universite);

        Universite savedUniversite = universiteService.addUniversite(universite);
        assertNotNull(savedUniversite);
        assertEquals("Mock University", savedUniversite.getNomUniv());
    }

    @Test
    void testRetrieveUniversite() {
        Universite universite = new Universite("Mock Retrieve");
        universite.setIdUniv(1);
        when(universiteRepository.findById(1)).thenReturn(Optional.of(universite));

        Universite foundUniversite = universiteService.retrieveUniversite(1);
        assertNotNull(foundUniversite);
        assertEquals(1, foundUniversite.getIdUniv());
    }

    @Test
    void testDeleteUniversite() {
        Universite universite = new Universite("Mock Delete");
        universite.setIdUniv(2);
        when(universiteRepository.findById(2)).thenReturn(Optional.of(universite));

        universiteService.deleteUniversite(2);
        verify(universiteRepository).delete(universite);
    }

    @Test
    void testAssignUniversiteToDepartement() {
        Universite universite = new Universite("University for Mock Dept");
        Departement departement = new Departement("Mock Department");
        when(universiteRepository.findById(1)).thenReturn(Optional.of(universite));
        when(departementRepository.findById(2)).thenReturn(Optional.of(departement));

        universiteService.assignUniversiteToDepartement(1, 2);
        assertTrue(universite.getDepartements().contains(departement));
        verify(universiteRepository).save(universite);
    }
}
