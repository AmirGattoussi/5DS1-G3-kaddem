package tn.esprit.spring.kaddem.dto;

import lombok.*;
import tn.esprit.spring.kaddem.entities.Equipe;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetailEquipeDTO {
    private Integer idDetailEquipe;
    private Integer salle;
    private String thematique;
    private Equipe equipe;

    @Override
    public String toString() {
        return "DetailEquipeDTO{" +
                "idDetailEquipe = " + idDetailEquipe +
                ", salle = '" + salle + '\'' +
                ", thematique = '" + thematique + '\'' +
                ", equipe = '" + equipe + '\'' +
                '}';
    }
}