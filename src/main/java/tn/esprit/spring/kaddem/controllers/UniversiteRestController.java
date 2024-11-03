package tn.esprit.spring.kaddem.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.services.IUniversiteService;
import tn.esprit.spring.kaddem.dto.UniversiteDTO; // Import the DTO

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/universite")
public class UniversiteRestController {
	private final IUniversiteService universiteService;
	private DepartementRepository departementRepository;
	// http://localhost:8089/Kaddem/universite/retrieve-all-universites
	@GetMapping("/retrieve-all-universites")
	public List<Universite> getUniversites() {
		return  universiteService.retrieveAllUniversites();
	}
	// http://localhost:8089/Kaddem/universite/retrieve-universite/8
	@GetMapping("/retrieve-universite/{universite-id}")
	public Universite retrieveUniversite(@PathVariable("universite-id") Integer universiteId) {
		return universiteService.retrieveUniversite(universiteId);
	}

	// http://localhost:8089/Kaddem/universite/add-universite
	@PostMapping("/add-universite")
	public Universite addUniversite(@RequestBody UniversiteDTO universiteDTO) {
		// Convert DTO to Entity
		Universite universite = new Universite();
		universite.setNomUniv(universiteDTO.getNomUniv());

		// Save the entity using the service
		return universiteService.addUniversite(universite);
	}

	// http://localhost:8089/Kaddem/universite/remove-universite/1
	@DeleteMapping("/remove-universite/{universite-id}")
	public void removeUniversite(@PathVariable("universite-id") Integer universiteId) {
		universiteService.deleteUniversite(universiteId);
	}

	// http://localhost:8089/Kaddem/universite/update-universite
	@PutMapping("/update-universite")
	public Universite updateUniversite(@RequestBody UniversiteDTO universiteDTO) {
		// Fetch the existing university
		Universite universite = universiteService.retrieveUniversite(universiteDTO.getIdUniv());

		// Update the university name if provided
		if (universiteDTO.getNomUniv() != null) {
			universite.setNomUniv(universiteDTO.getNomUniv());
		}

		// Update departments if new IDs are provided
		if (universiteDTO.getDepartementIds() != null) {
			Set<Departement> departements = new HashSet<>();
			for (Integer id : universiteDTO.getDepartementIds()) {
				Departement departement = departementRepository.findById(id).orElse(null);
				if (departement != null) {
					departements.add(departement);
				}
			}
			universite.setDepartements(departements);
		}

		// Save and return the updated university
		return universiteService.updateUniversite(universite);
	}

	//@PutMapping("/affecter-etudiant-departement")
	@PutMapping(value="/affecter-universite-departement/{universiteId}/{departementId}")
	public void affectertUniversiteToDepartement(@PathVariable("universiteId") Integer universiteId, @PathVariable("departementId")Integer departementId){
		universiteService.assignUniversiteToDepartement(universiteId, departementId);
	}

	@GetMapping(value = "/listerDepartementsUniversite/{idUniversite}")
	public Set<Departement> listerDepartementsUniversite(@PathVariable("idUniversite") Integer idUniversite) {

		return universiteService.retrieveDepartementsByUniversite(idUniversite);
	}

}


