package tn.esprit.spring.kaddem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.esprit.spring.kaddem.entities.Niveau;

import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipeDTO implements Serializable {
    private Integer idEquipe;
    private String nomEquipe;
    private Niveau niveau;
    private Set<Integer> etudiantIds; // Assuming you want to keep track of student IDs instead of the entire set
    private Integer detailEquipeId; // Assuming you want to refer to DetailEquipe by its ID
}
