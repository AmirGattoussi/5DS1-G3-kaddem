package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.UniversiteRepository;
import tn.esprit.spring.kaddem.services.UniversiteServiceImpl;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UniversiteServiceImplTest {

    @Autowired
    UniversiteRepository universiteRepository;

    @Autowired
    DepartementRepository departementRepository;

    UniversiteServiceImpl universiteService;

    @BeforeEach
    void setUp() {
        universiteService = new UniversiteServiceImpl(universiteRepository, departementRepository);
    }

    @Test
    void testAddUniversite() {
        Universite universite = new Universite("Test University");
        Universite savedUniversite = universiteService.addUniversite(universite);

        assertNotNull(savedUniversite);
        assertEquals("Test University", savedUniversite.getNomUniv());
    }

    @Test
    void testRetrieveAllUniversites() {
        List<Universite> universites = universiteService.retrieveAllUniversites();
        assertNotNull(universites);
        assertTrue(universites.size() >= 0);  // Adjust depending on initial DB state
    }

    @Test
    void testRetrieveUniversite() {
        Universite universite = universiteService.addUniversite(new Universite("Retrieve University"));
        Universite foundUniversite = universiteService.retrieveUniversite(universite.getIdUniv());

        assertNotNull(foundUniversite);
        assertEquals(universite.getIdUniv(), foundUniversite.getIdUniv());
    }

    @Test
    void testDeleteUniversite() {
        Universite universite = universiteService.addUniversite(new Universite("To be deleted"));
        universiteService.deleteUniversite(universite.getIdUniv());

        Universite deletedUniversite = universiteService.retrieveUniversite(universite.getIdUniv());
        assertNull(deletedUniversite);
    }

    @Test
    void testAssignUniversiteToDepartement() {
        Universite universite = universiteService.addUniversite(new Universite("University for Dept"));
        Departement departement = departementRepository.save(new Departement("Test Department"));

        universiteService.assignUniversiteToDepartement(universite.getIdUniv(), departement.getIdDepart());

        Set<Departement> departements = universiteService.retrieveDepartementsByUniversite(universite.getIdUniv());
        assertTrue(departements.contains(departement));
    }
}
