package tn.esprit.spring.kaddem.dto;

import org.junit.jupiter.api.Test;
import tn.esprit.spring.kaddem.entities.Niveau;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EquipeDTOTest {

    @Test
    void testEquipeDTO() {
        EquipeDTO equipeDTO = new EquipeDTO(1, "Equipe A", Niveau.JUNIOR, null, null);

        assertEquals(1, equipeDTO.getIdEquipe());
        assertEquals("Equipe A", equipeDTO.getNomEquipe());
        assertEquals(Niveau.JUNIOR, equipeDTO.getNiveau());
    }

    // Additional tests can be added for edge cases
}
