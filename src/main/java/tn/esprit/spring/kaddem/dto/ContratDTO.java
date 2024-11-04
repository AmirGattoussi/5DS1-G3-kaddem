package tn.esprit.spring.kaddem.dto;

import lombok.*;
import tn.esprit.spring.kaddem.entities.Specialite;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ContratDTO {
    private Integer idContrat;
    private Date dateDebutContrat;
    private Date dateFinContrat;
    private Specialite specialite;
    private Boolean archive;
    private Integer montantContrat;


public ContratDTO(int id, Date dateFinContrat, Date dateDebutContrat, Specialite specialite, boolean someFlag, int amount) {
    this.idContrat = id;
    this.dateFinContrat = dateFinContrat;
    this.dateDebutContrat = dateDebutContrat;
    this.specialite = specialite;
    this.archive = someFlag;
    this.montantContrat = amount;
}

    public ContratDTO(Boolean archiveStatus) {
        this.archive = archiveStatus;
    }

}
