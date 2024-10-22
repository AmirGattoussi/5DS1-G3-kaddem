package tn.esprit.spring.kaddem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUniversiteDTO {
    private Integer idUniv;  // ID of the university to update
    private String nomUniv;  // Updated name
    private Set<Integer> departementIds;  // Optional: Updated list of department IDs
}
