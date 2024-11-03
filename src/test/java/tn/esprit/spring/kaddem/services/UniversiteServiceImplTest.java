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
        Universite uni1 = new Universite(1, "ESPRIT");
        Universite uni2 = new Universite(2, "ENIT");
        List<Universite> universites = Arrays.asList(uni1, uni2);

        when(universiteRepository.findAll()).thenReturn(universites);

        List<Universite> result = universiteService.retrieveAllUniversites();

        assertEquals(2, result.size());
        verify(universiteRepository, times(1)).findAll();
    }

    @Test
    void testAddUniversite() {
        Universite uni = new Universite("Sup'Com");

        when(universiteRepository.save(uni)).thenReturn(uni);

        Universite result = universiteService.addUniversite(uni);

        assertEquals("Sup'Com", result.getNomUniv());
        verify(universiteRepository, times(1)).save(uni);
    }

    @Test
    void testUpdateUniversite() {
        Universite uni = new Universite(1, "ENIT");

        when(universiteRepository.save(uni)).thenReturn(uni);

        Universite result = universiteService.updateUniversite(uni);

        assertEquals("ENIT", result.getNomUniv());
        verify(universiteRepository, times(1)).save(uni);
    }

    @Test
    void testRetrieveUniversiteWithNonExistentId() {
        Integer id = 1;

        when(universiteRepository.findById(id)).thenReturn(Optional.empty());

        Universite result = universiteService.retrieveUniversite(id);

        assertNull(result);
        verify(universiteRepository, times(1)).findById(id);
    }

    @Test
    void testDeleteUniversiteWithNonExistentId() {
        Integer id = 1;

        when(universiteRepository.findById(id)).thenReturn(Optional.empty());

        universiteService.deleteUniversite(id);

        verify(universiteRepository, times(1)).findById(id);
        verify(universiteRepository, never()).delete(any(Universite.class));
    }

    @Test
    void testAssignUniversiteToDepartementWithNonExistentIds() {
        Integer uniId = 1;
        Integer deptId = 1;

        // Only mock the university repository to return empty
        when(universiteRepository.findById(uniId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            universiteService.assignUniversiteToDepartement(uniId, deptId);
        });

        assertEquals("Invalid Universite ID: " + uniId, exception.getMessage());
        verify(universiteRepository, times(1)).findById(uniId);
        verify(departementRepository, never()).findById(deptId); // Verify departementRepository is never called
    }

    @Test
    void testRetrieveDepartementsByUniversiteWithNonExistentId() {
        Integer uniId = 1;

        when(universiteRepository.findById(uniId)).thenReturn(Optional.empty());

        Set<Departement> result = universiteService.retrieveDepartementsByUniversite(uniId);

        assertTrue(result.isEmpty());
        verify(universiteRepository, times(1)).findById(uniId);
    }

    @Test
    void testRetrieveDepartementsByUniversite() {
        Integer uniId = 1;
        Departement dept1 = new Departement(1, "Informatique");
        Departement dept2 = new Departement(2, "Mathématiques");
        Set<Departement> departements = new HashSet<>(Arrays.asList(dept1, dept2));
        Universite uni = new Universite(uniId, "Sup'Com");
        uni.setDepartements(departements);

        when(universiteRepository.findById(uniId)).thenReturn(Optional.of(uni));

        Set<Departement> result = universiteService.retrieveDepartementsByUniversite(uniId);

        assertEquals(2, result.size());
        assertTrue(result.contains(dept1));
        assertTrue(result.contains(dept2));
        verify(universiteRepository, times(1)).findById(uniId);
    }
}
