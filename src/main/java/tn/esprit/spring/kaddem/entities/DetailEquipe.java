package tn.esprit.spring.kaddem.entities;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import javax.persistence.*;

@Setter
@Getter
@Entity
public class DetailEquipe implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idDetailEquipe;

    private Integer salle;

    private String thematique;

    @OneToOne(mappedBy="detailEquipe")
    private Equipe equipe;

    public DetailEquipe() {
    }

    public DetailEquipe(Integer salle, String thematique) {
        super();
        this.salle = salle;
        this.thematique = thematique;
    }

    public DetailEquipe(Integer idDetailEquipe, Integer salle, String thematique) {
        super();
        this.idDetailEquipe = idDetailEquipe;
        this.salle = salle;
        this.thematique = thematique;
    }

}
