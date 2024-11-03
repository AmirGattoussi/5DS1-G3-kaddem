package tn.esprit.spring.kaddem.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;

class EtudiantTest {

    @Test
    void testNoArgsConstructor() {
        // Test the no-args constructor
        Etudiant etudiant = new Etudiant();
        assertNull(etudiant.getIdEtudiant());
        assertNull(etudiant.getNomE());
        assertNull(etudiant.getPrenomE());
        assertNull(etudiant.getOp());
        assertNull(etudiant.getContrats());
        assertNull(etudiant.getDepartement());
        assertNull(etudiant.getEquipes());
    }

    @Test
    void testAllArgsConstructor() {
        Etudiant etudiant = new Etudiant(1, "John", "Doe", Option.SE);
        assertEquals(1, etudiant.getIdEtudiant());
        assertEquals("John", etudiant.getNomE());
        assertEquals("Doe", etudiant.getPrenomE());
        assertEquals(Option.SE, etudiant.getOp());
        assertNull(etudiant.getContrats());
        assertNull(etudiant.getDepartement());
        assertNull(etudiant.getEquipes());
    }

    @Test
    void testConstructorsWithFields() {
        Etudiant etudiant1 = new Etudiant("Jane", "Smith");
        assertEquals("Jane", etudiant1.getNomE());
        assertEquals("Smith", etudiant1.getPrenomE());

        Etudiant etudiant2 = new Etudiant("Jane", "Smith", Option.SE);
        assertEquals("Jane", etudiant2.getNomE());
        assertEquals("Smith", etudiant2.getPrenomE());
        assertEquals(Option.SE, etudiant2.getOp());
    }

    @Test
    void testGettersAndSetters() {
        // Test getters and setters
        Etudiant etudiant = new Etudiant();

        etudiant.setIdEtudiant(2);
        assertEquals(2, etudiant.getIdEtudiant());

        etudiant.setNomE("Alice");
        assertEquals("Alice", etudiant.getNomE());

        etudiant.setPrenomE("Johnson");
        assertEquals("Johnson", etudiant.getPrenomE());

        etudiant.setOp(Option.SE);
        assertEquals(Option.SE, etudiant.getOp());

        etudiant.setContrats(new HashSet<>());
        assertNotNull(etudiant.getContrats());
        assertTrue(etudiant.getContrats().isEmpty());

        etudiant.setDepartement(new Departement());
        assertNotNull(etudiant.getDepartement());

        etudiant.setEquipes(Arrays.asList(new Equipe()));
        assertNotNull(etudiant.getEquipes());
        assertEquals(1, etudiant.getEquipes().size());
    }
}
