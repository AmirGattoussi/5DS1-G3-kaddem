package tn.esprit.spring.kaddem.services;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tn.esprit.spring.kaddem.controllers.DepartementRestController;
import tn.esprit.spring.kaddem.dto.DepartementDTO;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.services.IDepartementService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DepartementRestControllerTest {

    @Mock
    private IDepartementService departementService;

    @InjectMocks
    private DepartementRestController departementController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetDepartements() {
        // Arrange
        Departement dept1 = new Departement(1, "Informatique");
        Departement dept2 = new Departement(2, "Mathématiques");
        List<Departement> departements = Arrays.asList(dept1, dept2);

        when(departementService.retrieveAllDepartements()).thenReturn(departements);

        // Act
        List<Departement> result = departementController.getDepartements();

        // Assert
        assertEquals(2, result.size());
        verify(departementService, times(1)).retrieveAllDepartements();
    }

    @Test
    void testRetrieveDepartement() {
        // Arrange
        int deptId = 1;
        Departement dept = new Departement(deptId, "Géologie");

        when(departementService.retrieveDepartement(deptId)).thenReturn(dept);

        // Act
        Departement result = departementController.retrieveDepartement(deptId);

        // Assert
        assertEquals(deptId, result.getIdDepart());
        assertEquals("Géologie", result.getNomDepart());
        verify(departementService, times(1)).retrieveDepartement(deptId);
    }

    @Test
    void testAddDepartement() {
        // Arrange
        DepartementDTO deptDTO = new DepartementDTO(null, "Physique");
        Departement dept = new Departement();
        dept.setNomDepart(deptDTO.getNomDepart());

        when(departementService.addDepartement(any(Departement.class))).thenReturn(dept);

        // Act
        Departement result = departementController.addDepartement(deptDTO);

        // Assert
        assertEquals(deptDTO.getNomDepart(), result.getNomDepart());
        verify(departementService, times(1)).addDepartement(any(Departement.class));
    }

    @Test
    void testRemoveDepartement() {
        // Arrange
        int deptId = 1;

        // Act
        departementController.removeDepartement(deptId);

        // Assert
        verify(departementService, times(1)).deleteDepartement(deptId);
    }

    @Test
    void testUpdateDepartement() {
        // Arrange
        DepartementDTO deptDTO = new DepartementDTO(1, "Chimie");
        new Departement(deptDTO.getIdDepart(), deptDTO.getNomDepart());
        Departement updatedDept = new Departement(deptDTO.getIdDepart(), "Updated Chimie");

        when(departementService.updateDepartement(any(Departement.class))).thenReturn(updatedDept);

        // Act
        DepartementDTO result = departementController.updateDepartement(deptDTO);

        // Assert
        assertEquals("Updated Chimie", result.getNomDepart());
        verify(departementService, times(1)).updateDepartement(any(Departement.class));
    }
}

