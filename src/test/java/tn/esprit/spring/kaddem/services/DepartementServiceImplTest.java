package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DepartementServiceImplTest {

    @Mock
    private DepartementRepository departementRepository;

    @InjectMocks
    private DepartementServiceImpl departementService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveAllDepartements() {
        // Arrange
        Departement dept1 = new Departement(1, "Informatique");
        Departement dept2 = new Departement(2, "Mathématiques");
        List<Departement> departements = Arrays.asList(dept1, dept2);

        when(departementRepository.findAll()).thenReturn(departements);

        // Act
        List<Departement> result = departementService.retrieveAllDepartements();

        // Assert
        assertEquals(2, result.size());
        verify(departementRepository, times(1)).findAll();
    }

    @Test
    void testAddDepartement() {
        // Arrange
        Departement dept = new Departement("Physique");
        when(departementRepository.save(dept)).thenReturn(dept);

        // Act
        Departement result = departementService.addDepartement(dept);

        // Assert
        assertNotNull(result);
        assertEquals("Physique", result.getNomDepart());
        verify(departementRepository, times(1)).save(dept);
    }

    @Test
    void testUpdateDepartement() {
        // Arrange
        Departement dept = new Departement(1, "Chimie");
        when(departementRepository.save(dept)).thenReturn(dept);

        // Act
        Departement result = departementService.updateDepartement(dept);

        // Assert
        assertNotNull(result);
        assertEquals("Chimie", result.getNomDepart());
        verify(departementRepository, times(1)).save(dept);
    }

    @Test
    void testRetrieveDepartement() {
        // Arrange
        int deptId = 1;
        Departement dept = new Departement(deptId, "Géologie");
        when(departementRepository.findById(deptId)).thenReturn(Optional.of(dept));

        // Act
        Departement result = departementService.retrieveDepartement(deptId);

        // Assert
        assertNotNull(result);
        assertEquals(deptId, result.getIdDepart());
        assertEquals("Géologie", result.getNomDepart());
        verify(departementRepository, times(1)).findById(deptId);
    }

    @Test
    void testDeleteDepartement() {
        // Arrange
        int deptId = 1;
        Departement dept = new Departement(deptId, "Astronomie");
        when(departementRepository.findById(deptId)).thenReturn(Optional.of(dept));

        // Act
        departementService.deleteDepartement(deptId);

        // Assert
        verify(departementRepository, times(1)).findById(deptId);
        verify(departementRepository, times(1)).delete(dept);
    }
}

