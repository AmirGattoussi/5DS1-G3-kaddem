package tn.esprit.spring.kaddem.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data

public class DepartementDTO {
    // Getters and Setters
    private Integer idDepart;
    private String nomDepart;

    // Constructors


    public DepartementDTO(Integer idDepart, String nomDepart) {
        this.idDepart = idDepart;
        this.nomDepart = nomDepart;
    }

}

