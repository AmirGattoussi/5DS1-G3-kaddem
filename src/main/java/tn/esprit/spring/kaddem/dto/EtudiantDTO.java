package tn.esprit.spring.kaddem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EtudiantDTO {
    private Integer idEtudiant;
    private String nomE;
    private String prenomE;
    private String op;
}
