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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    void testRetrieveContratNotFound() {
        when(contratRepository.findById(1)).thenReturn(Optional.empty());

        Contrat result = contratService.retrieveContrat(1);
        assertNull(result);  // Assuming the method returns null if not found
        verify(contratRepository, times(1)).findById(1);
    }

    @Test
    void testRemoveContrat() {
        when(contratRepository.findById(1)).thenReturn(Optional.of(contrat));

        contratService.removeContrat(1);
        verify(contratRepository, times(1)).delete(contrat);
    }

    @Test
    void testRemoveContratNotFound() {
        when(contratRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> {
            contratService.removeContrat(1);
        });
        verify(contratRepository, never()).delete(any());
    }

    @Test
    void testAffectContratToEtudiant() {
        when(etudiantRepository.findByNomEAndPrenomE("Test", "User")).thenReturn(etudiant);
        when(contratRepository.findByIdContrat(1)).thenReturn(contrat);
        etudiant.setContrats(Set.of());  // Initialize empty contrats set for etudiant

        Contrat result = contratService.affectContratToEtudiant(1, "Test", "User");
        assertEquals(etudiant, result.getEtudiant());
        verify(contratRepository, times(1)).save(contrat);
    }

    @Test
    void testAffectContratToEtudiantNotFound() {
        when(etudiantRepository.findByNomEAndPrenomE("Test", "User")).thenReturn(null); // Student not found

        Contrat result = contratService.affectContratToEtudiant(1, "Test", "User");
        assertNull(result); // Assuming the method should return null or handle this case properly
        verify(contratRepository, never()).save(any());
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
    void testGetChiffreAffaireEntreDeuxDates() throws ParseException {
        // Arrange
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date startDate = dateFormat.parse("2024-01-01");
        Date endDate = dateFormat.parse("2024-03-31");
        List<Contrat> mockContrats = new ArrayList<>();

        // Create mock contracts with different specialties
        Contrat contrat1 = new Contrat(new Date(), new Date(), Specialite.IA, false, 1000);
        Contrat contrat2 = new Contrat(new Date(), new Date(), Specialite.CLOUD, false, 1100);

        // Add contracts to the list
        mockContrats.add(contrat1);
        mockContrats.add(contrat2);

        // Mock the repository behavior
        when(contratRepository.findAll()).thenReturn(mockContrats);

        // Act
        float result = contratService.getChiffreAffaireEntreDeuxDates(startDate, endDate);

        // Assert
        float expectedChiffreAffaire = 2100.0f;
        assertEquals(expectedChiffreAffaire, result, 0.01);
    }


    @Test
    void testRetrieveAndUpdateStatusContratWithNoContracts() {
        when(contratRepository.findAll()).thenReturn(Collections.emptyList());
        contratService.retrieveAndUpdateStatusContrat();
        verify(contratRepository, never()).save(any());
    }

    @Test
    void testGetChiffreAffaireEntreDeuxDatesNoContracts() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = dateFormat.parse("2024-01-01");
        Date endDate = dateFormat.parse("2024-03-31");

        when(contratRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        float result = contratService.getChiffreAffaireEntreDeuxDates(startDate, endDate);

        // Assert
        assertEquals(0.0f, result, 0.01); // Expecting zero revenue
    }

    @Test
    void testGetChiffreAffaireEntreDeuxDatesInvalidRange() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = dateFormat.parse("2024-03-31");
        Date endDate = dateFormat.parse("2024-01-01");

        float result = contratService.getChiffreAffaireEntreDeuxDates(startDate, endDate);
        assertEquals(0.0f, result, 0.01);
    }


    @Test
    void testRetrieveAndUpdateStatusContratNoContractsToUpdate() {
        when(contratRepository.findAll()).thenReturn(Collections.emptyList());

        contratService.retrieveAndUpdateStatusContrat();

        verify(contratRepository, never()).save(any());
    }

    @Test
    void testNbContratsValidesWithEdgeDates() {
        Date startDate = new Date();
        Date endDate = new Date();

        when(contratRepository.getnbContratsValides(startDate, endDate)).thenReturn(1);

        int result = contratService.nbContratsValides(startDate, endDate);
        assertEquals(1, result);
        verify(contratRepository, times(1)).getnbContratsValides(startDate, endDate);
    }

}
