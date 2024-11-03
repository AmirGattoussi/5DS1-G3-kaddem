package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Specialite;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;
import tn.esprit.spring.kaddem.services.ContratServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContratServiceImplTest {

    @Mock
    private ContratRepository contratRepository;

    @Mock
    private EtudiantRepository etudiantRepository;

    @InjectMocks
    private ContratServiceImpl contratService;

    private Contrat contrat;
    private Etudiant etudiant;

    @BeforeEach
    void setUp() {
        contrat = new Contrat(new Date(), new Date(), Specialite.IA, false, 1000);
        etudiant = new Etudiant();
        etudiant.setNomE("Test");
        etudiant.setPrenomE("User");
    }

    @Test
    void testRetrieveAllContrats() {
        List<Contrat> contrats = new ArrayList<>();
        contrats.add(contrat);
        when(contratRepository.findAll()).thenReturn(contrats);

        List<Contrat> result = contratService.retrieveAllContrats();
        assertEquals(1, result.size());
        verify(contratRepository, times(1)).findAll();
    }

    @Test
    void testAddContrat() {
        when(contratRepository.save(contrat)).thenReturn(contrat);

        Contrat result = contratService.addContrat(contrat);
        assertEquals(contrat, result);
        verify(contratRepository, times(1)).save(contrat);
    }

    @Test
    void testUpdateContrat() {
        when(contratRepository.save(contrat)).thenReturn(contrat);

        Contrat result = contratService.updateContrat(contrat);
        assertEquals(contrat, result);
        verify(contratRepository, times(1)).save(contrat);
    }

    @Test
    void testRetrieveContrat() {
        when(contratRepository.findById(1)).thenReturn(Optional.of(contrat));

        Contrat result = contratService.retrieveContrat(1);
        assertNotNull(result);
        assertEquals(contrat, result);
        verify(contratRepository, times(1)).findById(1);
    }

    @Test
    void testRemoveContrat() {
        when(contratRepository.findById(1)).thenReturn(Optional.of(contrat));

        contratService.removeContrat(1);
        verify(contratRepository, times(1)).delete(contrat);
    }

    @Test
    void testAffectContratToEtudiant() {
        // Mocking etudiant repository
        when(etudiantRepository.findByNomEAndPrenomE("Test", "User")).thenReturn(etudiant);
        when(contratRepository.findByIdContrat(1)).thenReturn(contrat);
        etudiant.setContrats(Set.of());  // Initialize empty contrats set for etudiant

        Contrat result = contratService.affectContratToEtudiant(1, "Test", "User");
        assertEquals(etudiant, result.getEtudiant());
        verify(contratRepository, times(1)).save(contrat);
    }

    @Test
    void testNbContratsValides() {
        Date startDate = new Date();
        Date endDate = new Date();
        when(contratRepository.getnbContratsValides(startDate, endDate)).thenReturn(5);

        int result = contratService.nbContratsValides(startDate, endDate);
        assertEquals(5, result);
        verify(contratRepository, times(1)).getnbContratsValides(startDate, endDate);
    }

    @Test
    void testGetChiffreAffaireEntreDeuxDates() {
        Date startDate = new Date();
        Date endDate = new Date();
        Contrat contratIA = new Contrat(new Date(), new Date(), Specialite.IA, false, 1000);
        Contrat contratCloud = new Contrat(new Date(), new Date(), Specialite.CLOUD, false, 1000);

        List<Contrat> contrats = List.of(contratIA, contratCloud);
        when(contratRepository.findAll()).thenReturn(contrats);

        float result = contratService.getChiffreAffaireEntreDeuxDates(startDate, endDate);
        assertTrue(result > 0);
        verify(contratRepository, times(1)).findAll();
    }
}

