package tn.esprit.spring.kaddem.dto;

import org.junit.jupiter.api.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.junit.jupiter.api.Assertions.*;

class DepartementDTOTest {

    private static final Logger logger = LogManager.getLogger(DepartementDTOTest.class);

    @Test
    void testConstructorAndGetters() {
        logger.info("Testing constructor and getters");

        Integer id = 1;
        String name = "Informatique";
        DepartementDTO departementDTO = new DepartementDTO(id, name);

        assertEquals(id, departementDTO.getIdDepart());
        assertEquals(name, departementDTO.getNomDepart());

        logger.info("Constructor and getters test passed with id: {}, name: {}", id, name);
    }

    @Test
    void testSetters() {
        logger.info("Testing setters");

        DepartementDTO departementDTO = new DepartementDTO(null, null);
        Integer id = 2;
        String name = "Mathematics";

        departementDTO.setIdDepart(id);
        departementDTO.setNomDepart(name);

        assertEquals(id, departementDTO.getIdDepart());
        assertEquals(name, departementDTO.getNomDepart());

        logger.info("Setters test passed with id: {}, name: {}", id, name);
    }

    @Test
    void testNoArgsConstructor() {
        logger.info("Testing no-args constructor");

        DepartementDTO departementDTO = new DepartementDTO(null, null);

        assertNull(departementDTO.getIdDepart());
        assertNull(departementDTO.getNomDepart());

        logger.info("No-args constructor test passed");
    }

    @Test
    void testEqualsAndHashCode() {
        logger.info("Testing equals and hashCode");

        DepartementDTO dept1 = new DepartementDTO(1, "Informatique");
        DepartementDTO dept2 = new DepartementDTO(1, "Informatique");
        DepartementDTO dept3 = new DepartementDTO(2, "Mathematics");

        assertEquals(dept1, dept2); // Should be equal
        assertNotEquals(dept1, dept3); // Should not be equal
        assertEquals(dept1.hashCode(), dept2.hashCode()); // Hash codes should match for equal objects
        assertNotEquals(dept1.hashCode(), dept3.hashCode()); // Hash codes should differ for different objects

        assertNotEquals(null, dept1); // Should not be equal to null
        assertNotEquals(new Object(), dept1); // Should not be equal to an object of another type
        assertEquals(dept1, dept1); // Should be equal to itself

        logger.info("Equals and hashCode test passed");
    }

    @Test
    void testToString() {
        logger.info("Testing toString");

        DepartementDTO departementDTO = new DepartementDTO(1, "Informatique");

        String expected = "DepartementDTO(idDepart=1, nomDepart=Informatique)";
        assertEquals(expected, departementDTO.toString());

        logger.info("ToString test passed with output: {}", departementDTO);
    }
}
