package tn.esprit.spring.kaddem.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.kaddem.dto.EtudiantDTO;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Option;
import tn.esprit.spring.kaddem.services.IEtudiantService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/etudiant")
public class EtudiantRestController {

	private final IEtudiantService etudiantService;

	@Autowired
	public EtudiantRestController(IEtudiantService etudiantService) {
		this.etudiantService = etudiantService;
	}

	// http://localhost:8089/Kaddem/etudiant/retrieve-all-etudiants
	@GetMapping("/retrieve-all-etudiants")
	public List<Etudiant> getEtudiants() {
		return etudiantService.retrieveAllEtudiants();
	}
	// http://localhost:8089/Kaddem/etudiant/retrieve-etudiant/8
	@GetMapping("/retrieve-etudiant/{etudiant-id}")
	public Etudiant retrieveEtudiant(@PathVariable("etudiant-id") Integer etudiantId) {
		return etudiantService.retrieveEtudiant(etudiantId);
	}

	// http://localhost:8089/Kaddem/etudiant/add-etudiant
	@PostMapping("/add-etudiant")
	public Etudiant addEtudiant(@RequestBody EtudiantDTO etudiantDTO) {
		Etudiant etudiant = new Etudiant();
		etudiant.setNomE(etudiantDTO.getNomE());
		etudiant.setPrenomE(etudiantDTO.getPrenomE());
		etudiant.setOp(Option.valueOf(etudiantDTO.getOp()));
		return etudiantService.addEtudiant(etudiant);
	}

	// http://localhost:8089/Kaddem/etudiant/remove-etudiant/1
	@DeleteMapping("/remove-etudiant/{etudiant-id}")
	public void removeEtudiant(@PathVariable("etudiant-id") Integer etudiantId) {
		etudiantService.removeEtudiant(etudiantId);
	}

	// http://localhost:8089/Kaddem/etudiant/update-etudiant
	public Etudiant updateEtudiant(@RequestBody EtudiantDTO etudiantDTO) {
		Etudiant etudiant = new Etudiant();
		etudiant.setIdEtudiant(etudiantDTO.getIdEtudiant());
		etudiant.setNomE(etudiantDTO.getNomE());
		etudiant.setPrenomE(etudiantDTO.getPrenomE());
		etudiant.setOp(Option.valueOf(etudiantDTO.getOp()));
		return etudiantService.updateEtudiant(etudiant);
	}

	//@PutMapping("/affecter-etudiant-departement")
	@PutMapping(value="/affecter-etudiant-departement/{etudiantId}/{departementId}")
	public void affecterEtudiantToDepartement(@PathVariable("etudiantId") Integer etudiantId, @PathVariable("departementId")Integer departementId){
		etudiantService.assignEtudiantToDepartement(etudiantId, departementId);
    }
	//addAndAssignEtudiantToEquipeAndContract(Etudiant e, Integer idContrat, Integer idEquipe)
    /* Ajouter un étudiant tout en lui affectant un contrat et une équipe */
    @PostMapping("/add-assign-Etudiant/{idContrat}/{idEquipe}")
	public Etudiant addEtudiantWithEquipeAndContract(
			@RequestBody EtudiantDTO etudiantDTO,
			@PathVariable("idContrat") Integer idContrat,
			@PathVariable("idEquipe") Integer idEquipe) {
		Etudiant etudiant = new Etudiant();
		etudiant.setNomE(etudiantDTO.getNomE());
		etudiant.setPrenomE(etudiantDTO.getPrenomE());
		etudiant.setOp(Option.valueOf(etudiantDTO.getOp()));
		return etudiantService.addAndAssignEtudiantToEquipeAndContract(etudiant, idContrat, idEquipe);
	}

	@GetMapping(value = "/getEtudiantsByDepartement/{idDepartement}")
	public List<Etudiant> getEtudiantsParDepartement(@PathVariable("idDepartement") Integer idDepartement) {

		return etudiantService.getEtudiantsByDepartement(idDepartement);
	}

}


