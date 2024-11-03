package tn.esprit.spring.kaddem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EtudiantDTO {
    private Integer idEtudiant;
    private String nomE;
    private String prenomE;
    private String op;

    @Override
    public String toString() {
        return "EtudiantDTO{" +
                "idEtudiant=" + idEtudiant +
                ", nomE='" + nomE + '\'' +
                ", prenomE='" + prenomE + '\'' +
                ", op='" + op + '\'' +
                '}';
    }
}
