package tn.esprit.spring.kaddem.dto;

import org.junit.jupiter.api.Test;
import tn.esprit.spring.kaddem.entities.Equipe;

import static org.assertj.core.api.Assertions.assertThat;

class DetailEquipeDTOTest {

    @Test
    void testNoArgsConstructor() {
        // Given
        DetailEquipeDTO detailEquipeDTO = new DetailEquipeDTO();

        // Then
        assertThat(detailEquipeDTO).isNotNull();
        assertThat(detailEquipeDTO.getIdDetailEquipe()).isNull();
        assertThat(detailEquipeDTO.getSalle()).isNull();
        assertThat(detailEquipeDTO.getThematique()).isNull();
        assertThat(detailEquipeDTO.getEquipe()).isNull();
    }

    @Test
    void testAllArgsConstructor() {
        // Given
        Integer expectedId = 1;
        Integer expectedSalle = 101;
        String expectedThematique = "Thematique A";
        Equipe expectedEquipe = new Equipe();

        // When
        DetailEquipeDTO detailEquipeDTO = new DetailEquipeDTO();
        detailEquipeDTO.setIdDetailEquipe(expectedId);
        detailEquipeDTO.setSalle(expectedSalle);
        detailEquipeDTO.setThematique(expectedThematique);
        detailEquipeDTO.setEquipe(expectedEquipe);

        // Then
        assertThat(detailEquipeDTO.getIdDetailEquipe()).isEqualTo(expectedId);
        assertThat(detailEquipeDTO.getSalle()).isEqualTo(expectedSalle);
        assertThat(detailEquipeDTO.getThematique()).isEqualTo(expectedThematique);
        assertThat(detailEquipeDTO.getEquipe()).isEqualTo(expectedEquipe);
    }

    @Test
    void testSettersAndGetters() {
        // Given
        DetailEquipeDTO detailEquipeDTO = new DetailEquipeDTO();
        detailEquipeDTO.setIdDetailEquipe(1);
        detailEquipeDTO.setSalle(101);
        detailEquipeDTO.setThematique("Thematique A");
        detailEquipeDTO.setEquipe(new Equipe());

        // Then
        assertThat(detailEquipeDTO.getIdDetailEquipe()).isEqualTo(1);
        assertThat(detailEquipeDTO.getSalle()).isEqualTo(101);
        assertThat(detailEquipeDTO.getThematique()).isEqualTo("Thematique A");
        assertThat(detailEquipeDTO.getEquipe()).isNotNull();
    }

    @Test
    void testToString() {
        // Given
        Equipe equipe = new Equipe();
        DetailEquipeDTO detailEquipeDTO = new DetailEquipeDTO();
        detailEquipeDTO.setIdDetailEquipe(1);
        detailEquipeDTO.setSalle(101);
        detailEquipeDTO.setThematique("Thematique A");
        detailEquipeDTO.setEquipe(equipe);

        // When
        String toStringResult = detailEquipeDTO.toString();

        //Then
        assertThat(toStringResult).contains(
                "idDetailEquipe", "salle", "thematique", "equipe"
        );
    }
}
