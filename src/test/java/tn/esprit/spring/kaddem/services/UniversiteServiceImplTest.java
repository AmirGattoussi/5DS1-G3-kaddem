package tn.esprit.spring.kaddem.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Universite;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
class UniversiteServiceImplTest {

    @Autowired
    private IUniversiteService universiteService;

    private Universite universite;

    @BeforeEach
    public void setUp() {
        universite = new Universite("Esprit University");
    }

    @Test
    void testAddUniversite() {
        Universite savedUniversite = universiteService.addUniversite(universite);
        log.info("Added Universite: {}", savedUniversite);

        assertNotNull(savedUniversite.getIdUniv());
        assertEquals("Esprit University", savedUniversite.getNomUniv());

    }

    @Test
    void testRetrieveAllUniversites() {
        List<Universite> universites = universiteService.retrieveAllUniversites();
        log.info("All Universites: {}", universites);

        assertNotNull(universites);
        assertTrue(universites.size() >= 0);
    }

    @Test
    void testRetrieveUniversite() {
        Universite savedUniversite = universiteService.addUniversite(universite);
        Universite foundUniversite = universiteService.retrieveUniversite(savedUniversite.getIdUniv());
        log.info("Retrieved Universite: {}", foundUniversite);

        assertNotNull(foundUniversite);
        assertEquals(savedUniversite.getIdUniv(), foundUniversite.getIdUniv());

    }

    @Test
    void testDeleteUniversite() {
        Universite savedUniversite = universiteService.addUniversite(universite);
        universiteService.deleteUniversite(savedUniversite.getIdUniv());
        Universite deletedUniversite = universiteService.retrieveUniversite(savedUniversite.getIdUniv());

        assertNull(deletedUniversite);
        log.info("Successfully deleted Universite with ID: {}", savedUniversite.getIdUniv());
    }

    @Test
    void testAssignUniversiteToDepartement() {
        Universite savedUniversite = universiteService.addUniversite(universite);
        Departement departement = new Departement("Computer Science");
        universiteService.assignUniversiteToDepartement(savedUniversite.getIdUniv(), departement.getIdDepart());

        Set<Departement> departements = universiteService.retrieveDepartementsByUniversite(savedUniversite.getIdUniv());
        log.info("Assigned Departements: {}", departements);

        assertNotNull(departements);
        assertTrue(departements.contains(departement));

    }
}
