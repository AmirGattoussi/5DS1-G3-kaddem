package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DepartementServiceImplTest {

    private static final Logger logger = LogManager.getLogger(DepartementServiceImplTest.class);

    @Mock
    private DepartementRepository departementRepository;

    @InjectMocks
    private DepartementServiceImpl departementService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        logger.info("Setting up mocks for DepartementServiceImplTest");
    }

    @Test
    void testRetrieveAllDepartements() {
        logger.info("Testing retrieveAllDepartements method");

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

        logger.info("Successfully tested retrieveAllDepartements with {} departements", result.size());
    }

    @Test
    void testAddDepartement() {
        logger.info("Testing addDepartement method");

        // Arrange
        Departement dept = new Departement("Physique");
        when(departementRepository.save(dept)).thenReturn(dept);

        // Act
        Departement result = departementService.addDepartement(dept);

        // Assert
        assertNotNull(result);
        assertEquals("Physique", result.getNomDepart());
        verify(departementRepository, times(1)).save(dept);

        logger.info("Successfully added departement: {}", result.getNomDepart());
    }

    @Test
    void testUpdateDepartement() {
        logger.info("Testing updateDepartement method");

        // Arrange
        Departement dept = new Departement(1, "Chimie");
        when(departementRepository.save(dept)).thenReturn(dept);

        // Act
        Departement result = departementService.updateDepartement(dept);

        // Assert
        assertNotNull(result);
        assertEquals("Chimie", result.getNomDepart());
        verify(departementRepository, times(1)).save(dept);

        logger.info("Successfully updated departement: {}", result.getNomDepart());
    }

    @Test
    void testRetrieveDepartement() {
        logger.info("Testing retrieveDepartement method");

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

        logger.info("Successfully retrieved departement: {} with ID: {}", result.getNomDepart(), result.getIdDepart());
    }

    @Test
    void testDeleteDepartement() {
        logger.info("Testing deleteDepartement method");

        // Arrange
        int deptId = 1;
        Departement dept = new Departement(deptId, "Astronomie");
        when(departementRepository.findById(deptId)).thenReturn(Optional.of(dept));

        // Act
        departementService.deleteDepartement(deptId);

        // Assert
        verify(departementRepository, times(1)).findById(deptId);
        verify(departementRepository, times(1)).delete(dept);

        logger.info("Successfully deleted departement with ID: {}", deptId);
    }
}
