package tn.esprit.spring.kaddem.dto;

import lombok.*;

import java.util.Set;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UniversiteDTO {
    // Getters and Setters
    private Integer idUniv;
    private String nomUniv;
    private Set<Integer> departementIds;  // Optional: Updated list of department IDs

    // Constructors



}