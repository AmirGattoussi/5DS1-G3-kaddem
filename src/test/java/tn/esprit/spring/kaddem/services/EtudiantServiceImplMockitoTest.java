package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EtudiantServiceImplMockitoTest {

    @Mock
    private EtudiantRepository etudiantRepository;

    @Mock
    private ContratRepository contratRepository;

    @Mock
    private EquipeRepository equipeRepository;

    @Mock
    private DepartementRepository departementRepository;

    @InjectMocks
    private EtudiantServiceImpl etudiantService;

    private Etudiant etudiant;
    private Contrat contrat;
    private Equipe equipe;

    @BeforeEach
    void setUp() {
        etudiant = new Etudiant("John", "Doe");
        etudiant.setIdEtudiant(1);

        contrat = new Contrat();
        contrat.setIdContrat(1);

        equipe = new Equipe();
        equipe.setIdEquipe(1);
    }

    @Test
    void testRetrieveEtudiant() {
        when(etudiantRepository.findById(anyInt())).thenReturn(Optional.of(etudiant));

        Etudiant retrievedEtudiant = etudiantService.retrieveEtudiant(1);

        assertNotNull(retrievedEtudiant);
        assertEquals("John", retrievedEtudiant.getNomE());
        verify(etudiantRepository).findById(1);
    }

    @Test
    void testAddEtudiant() {
        when(etudiantRepository.save(any(Etudiant.class))).thenReturn(etudiant);

        Etudiant createdEtudiant = etudiantService.addEtudiant(etudiant);

        assertNotNull(createdEtudiant);
        assertEquals("John", createdEtudiant.getNomE());
        verify(etudiantRepository).save(etudiant);
    }

    @Test
    void testUpdateEtudiant() {
        when(etudiantRepository.save(any(Etudiant.class))).thenReturn(etudiant);

        Etudiant updatedEtudiant = etudiantService.updateEtudiant(etudiant);

        assertNotNull(updatedEtudiant);
        assertEquals("John", updatedEtudiant.getNomE());
        verify(etudiantRepository).save(etudiant);
    }

    @Test
    void testRemoveEtudiant() {
        when(etudiantRepository.findById(anyInt())).thenReturn(Optional.of(etudiant));

        etudiantService.removeEtudiant(1);

        verify(etudiantRepository).delete(etudiant);
    }

    @Test
    void testAssignEtudiantToDepartement() {
        Departement departement = new Departement();
        departement.setIdDepart(1);

        when(etudiantRepository.findById(anyInt())).thenReturn(Optional.of(etudiant));
        when(departementRepository.findById(anyInt())).thenReturn(Optional.of(departement));

        etudiantService.assignEtudiantToDepartement(1, 1);

        verify(etudiantRepository).save(etudiant);
        assertEquals(departement, etudiant.getDepartement());
    }

    @Test
    void testRetrieveAllEtudiants() {
        List<Etudiant> etudiants = new ArrayList<>();
        etudiants.add(etudiant);
        when(etudiantRepository.findAll()).thenReturn(etudiants);

        List<Etudiant> retrievedEtudiants = etudiantService.retrieveAllEtudiants();

        assertEquals(1, retrievedEtudiants.size());
        assertEquals("John", retrievedEtudiants.get(0).getNomE());
    }

    @Test
    void testGetEtudiantsByDepartement() {
        List<Etudiant> etudiants = new ArrayList<>();
        etudiants.add(etudiant);
        when(etudiantRepository.findEtudiantsByDepartement_IdDepart(anyInt())).thenReturn(etudiants);

        List<Etudiant> retrievedEtudiants = etudiantService.getEtudiantsByDepartement(1);

        assertEquals(1, retrievedEtudiants.size());
        assertEquals("John", retrievedEtudiants.get(0).getNomE());
    }
}
