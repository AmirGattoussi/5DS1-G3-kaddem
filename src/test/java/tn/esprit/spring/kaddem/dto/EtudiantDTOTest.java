package tn.esprit.spring.kaddem.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EtudiantDTOTest {

    @Test
    void testNoArgsConstructor() {
        // Given
        EtudiantDTO etudiantDTO = new EtudiantDTO();

        // Then
        assertThat(etudiantDTO).isNotNull();
        assertThat(etudiantDTO.getIdEtudiant()).isNull();
        assertThat(etudiantDTO.getNomE()).isNull();
        assertThat(etudiantDTO.getPrenomE()).isNull();
        assertThat(etudiantDTO.getOp()).isNull();
    }

    @Test
    void testAllArgsConstructor() {
        // Given
        Integer expectedId = 1;
        String expectedNom = "Doe";
        String expectedPrenom = "John";
        String expectedOp = "Some Operation";

        // When
        EtudiantDTO etudiantDTO = new EtudiantDTO(expectedId, expectedNom, expectedPrenom, expectedOp);

        // Then
        assertThat(etudiantDTO.getIdEtudiant()).isEqualTo(expectedId);
        assertThat(etudiantDTO.getNomE()).isEqualTo(expectedNom);
        assertThat(etudiantDTO.getPrenomE()).isEqualTo(expectedPrenom);
        assertThat(etudiantDTO.getOp()).isEqualTo(expectedOp);
    }

    @Test
    void testSettersAndGetters() {
        // Given
        EtudiantDTO etudiantDTO = new EtudiantDTO();
        etudiantDTO.setIdEtudiant(1);
        etudiantDTO.setNomE("Doe");
        etudiantDTO.setPrenomE("John");
        etudiantDTO.setOp("Some Operation");

        // Then
        assertThat(etudiantDTO.getIdEtudiant()).isEqualTo(1);
        assertThat(etudiantDTO.getNomE()).isEqualTo("Doe");
        assertThat(etudiantDTO.getPrenomE()).isEqualTo("John");
        assertThat(etudiantDTO.getOp()).isEqualTo("Some Operation");
    }

    @Test
    void testToString() {
        // Given
        EtudiantDTO etudiantDTO = new EtudiantDTO(1, "Doe", "John", "Some Operation");

        // When
        String toStringResult = etudiantDTO.toString();

        // Then
        assertThat(toStringResult).contains(
                "idEtudiant=1",
                "nomE=Doe",
                "prenomE=John",
                "op=Some Operation"
        );
    }
}
