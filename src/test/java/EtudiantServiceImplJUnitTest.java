import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;
import tn.esprit.spring.kaddem.services.EtudiantServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class EtudiantServiceImplJUnitTest {

    private EtudiantRepository etudiantRepository;
    private EtudiantServiceImpl etudiantService;
    private Etudiant etudiant;

    @BeforeEach
    void setUp() {
        etudiant = new Etudiant("John", "Doe");
    }

    @Test
    void testRetrieveEtudiant() {
        etudiantService.addEtudiant(etudiant);

        Etudiant retrievedEtudiant = etudiantService.retrieveEtudiant(1);

        assertNotNull(retrievedEtudiant);
        assertEquals("John", retrievedEtudiant.getNomE());
    }

    @Test
    void testRetrieveEtudiantNotFound() {
        Etudiant retrievedEtudiant = etudiantService.retrieveEtudiant(999);
        assertNull(retrievedEtudiant);
    }

    @Test
    void testFindByNomE() {
        etudiantService.addEtudiant(etudiant);

        List<Etudiant> etudiantsByLastName = etudiantRepository.findByNomE("Doe");

        assertNotNull(etudiantsByLastName);
        assertEquals(1, etudiantsByLastName.size());
        assertEquals("John", etudiantsByLastName.get(0).getNomE());
    }

    @Test
    void testFindByNomENotFound() {
        List<Etudiant> etudiantsByLastName = etudiantRepository.findByNomE("Smith");

        assertNotNull(etudiantsByLastName);
        assertTrue(etudiantsByLastName.isEmpty());
    }
}
