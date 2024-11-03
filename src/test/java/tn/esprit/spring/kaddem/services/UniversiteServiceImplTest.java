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

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UniversiteServiceImplTest {

    @Mock
    private UniversiteRepository universiteRepository;

    @Mock
    private DepartementRepository departementRepository;

    @InjectMocks
    private UniversiteServiceImpl universiteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveAllUniversites() {
        // Arrange
        Universite uni1 = new Universite(1, "ESPRIT");
        Universite uni2 = new Universite(2, "ENIT");
        List<Universite> universites = Arrays.asList(uni1, uni2);

        when(universiteRepository.findAll()).thenReturn(universites);

        // Act
        List<Universite> result = universiteService.retrieveAllUniversites();

        // Assert
        assertEquals(2, result.size());
        verify(universiteRepository, times(1)).findAll();
    }

    @Test
    void testAddUniversite() {
        // Arrange
        Universite uni = new Universite("Sup'Com");

        when(universiteRepository.save(uni)).thenReturn(uni);

        // Act
        Universite result = universiteService.addUniversite(uni);

        // Assert
        assertEquals("Sup'Com", result.getNomUniv());
        verify(universiteRepository, times(1)).save(uni);
    }

    @Test
    void testUpdateUniversite() {
        // Arrange
        Universite uni = new Universite(1, "ENIT");

        when(universiteRepository.save(uni)).thenReturn(uni);

        // Act
        Universite result = universiteService.updateUniversite(uni);

        // Assert
        assertEquals("ENIT", result.getNomUniv());
        verify(universiteRepository, times(1)).save(uni);
    }

    @Test
    void testRetrieveUniversite() {
        // Arrange
        Integer id = 1;
        Universite uni = new Universite(id, "ESPRIT");

        when(universiteRepository.findById(id)).thenReturn(Optional.of(uni));

        // Act
        Universite result = universiteService.retrieveUniversite(id);

        // Assert
        assertNotNull(result);
        assertEquals("ESPRIT", result.getNomUniv());
        verify(universiteRepository, times(1)).findById(id);
    }

    @Test
    void testDeleteUniversite() {
        // Arrange
        Integer id = 1;
        Universite uni = new Universite(id, "Sup'Com");

        when(universiteRepository.findById(id)).thenReturn(Optional.of(uni));

        // Act
        universiteService.deleteUniversite(id);

        // Assert
        verify(universiteRepository, times(1)).findById(id);
        verify(universiteRepository, times(1)).delete(uni);
    }

    @Test
    void testAssignUniversiteToDepartement() {
        // Arrange
        Integer uniId = 1;
        Integer deptId = 1;
        Universite uni = new Universite(uniId, "ENIT");
        Departement dept = new Departement(deptId, "Informatique");
        uni.setDepartements(new HashSet<>());

        when(universiteRepository.findById(uniId)).thenReturn(Optional.of(uni));
        when(departementRepository.findById(deptId)).thenReturn(Optional.of(dept));
        when(universiteRepository.save(uni)).thenReturn(uni);

        // Act
        universiteService.assignUniversiteToDepartement(uniId, deptId);

        // Assert
        assertTrue(uni.getDepartements().contains(dept));
        verify(universiteRepository, times(1)).findById(uniId);
        verify(departementRepository, times(1)).findById(deptId);
        verify(universiteRepository, times(1)).save(uni);
    }

    @Test
    void testRetrieveDepartementsByUniversite() {
        // Arrange
        Integer uniId = 1;
        Departement dept1 = new Departement(1, "Informatique");
        Departement dept2 = new Departement(2, "Mathématiques");
        Set<Departement> departements = new HashSet<>(Arrays.asList(dept1, dept2));
        Universite uni = new Universite(uniId, "Sup'Com");
        uni.setDepartements(departements);

        when(universiteRepository.findById(uniId)).thenReturn(Optional.of(uni));

        // Act
        Set<Departement> result = universiteService.retrieveDepartementsByUniversite(uniId);

        // Assert
        assertEquals(2, result.size());
        assertTrue(result.contains(dept1));
        assertTrue(result.contains(dept2));
        verify(universiteRepository, times(1)).findById(uniId);
    }
}