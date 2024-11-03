package tn.esprit.spring.kaddem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;
import java.util.HashSet;

import javax.persistence.*;

@Setter
@Getter
@Entity

public class Universite implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idUniv;
    private String nomUniv;
    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Departement> departements = new HashSet<>(); // Initialize here
    public Universite() {
    }

    public Universite(String nomUniv) {
        super();
        this.nomUniv = nomUniv;
        this.departements = new HashSet<>(); // Initialize to avoid NullPointerException
    }

    public Universite(Integer idUniv, String nomUniv) {
        super();
        this.idUniv = idUniv;
        this.nomUniv = nomUniv;
        this.departements = new HashSet<>(); // Initialize to avoid NullPointerException
    }

}
