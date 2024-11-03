package tn.esprit.spring.kaddem.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import tn.esprit.spring.kaddem.entities.Equipe;

@Data
@Getter
@Setter
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