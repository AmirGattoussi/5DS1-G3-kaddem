package tn.esprit.spring.kaddem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
public class EtudiantServiceImpl implements IEtudiantService {

	@Autowired
	EtudiantRepository etudiantRepository;

	@Autowired
	ContratRepository contratRepository;

	@Autowired
	EquipeRepository equipeRepository;

	@Autowired
	DepartementRepository departementRepository;

	public List<Etudiant> retrieveAllEtudiants() {
		log.info("Retrieving all students");
		List<Etudiant> etudiants = (List<Etudiant>) etudiantRepository.findAll();
		log.debug("Retrieved {} students", etudiants.size());
		return etudiants;
	}

	public Etudiant addEtudiant(Etudiant e) {
		log.info("Adding a new student: {}", e);
		Etudiant savedEtudiant = etudiantRepository.save(e);
		log.debug("Added student with ID {}", savedEtudiant.getIdEtudiant());
		return savedEtudiant;
	}

	public Etudiant updateEtudiant(Etudiant e) {
		log.info("Updating student with ID: {}", e.getIdEtudiant());
		Etudiant updatedEtudiant = etudiantRepository.save(e);
		log.debug("Updated student: {}", updatedEtudiant);
		return updatedEtudiant;
	}

	public Etudiant retrieveEtudiant(Integer idEtudiant) {
		log.info("Retrieving student with ID: {}", idEtudiant);
		Etudiant etudiant = etudiantRepository.findById(idEtudiant).orElse(null);
		if (etudiant == null) {
			log.warn("Student with ID {} not found", idEtudiant);
		} else {
			log.debug("Retrieved student: {}", etudiant);
		}
		return etudiant;
	}

	public void removeEtudiant(Integer idEtudiant) {
		log.info("Removing student with ID: {}", idEtudiant);
		Etudiant e = retrieveEtudiant(idEtudiant);
		if (e != null) {
			etudiantRepository.delete(e);
			log.debug("Removed student with ID {}", idEtudiant);
		} else {
			log.warn("Student with ID {} not found, could not remove", idEtudiant);
		}
	}

	public void assignEtudiantToDepartement(Integer etudiantId, Integer departementId) {
		log.info("Assigning student with ID {} to department with ID {}", etudiantId, departementId);
		Etudiant etudiant = etudiantRepository.findById(etudiantId).orElse(null);
		Departement departement = departementRepository.findById(departementId).orElse(null);

		if (etudiant != null && departement != null) {
			etudiant.setDepartement(departement);
			etudiantRepository.save(etudiant);
			log.debug("Assigned student with ID {} to department {}", etudiantId, departementId);
		} else {
			log.warn("Failed to assign student to department: student or department not found");
		}
	}

	@Transactional
	public Etudiant addAndAssignEtudiantToEquipeAndContract(Etudiant e, Integer idContrat, Integer idEquipe) {
		log.info("Adding and assigning student to team and contract");
		Contrat c = contratRepository.findById(idContrat).orElse(null);
		Equipe eq = equipeRepository.findById(idEquipe).orElse(null);

		if (c != null && eq != null) {
			c.setEtudiant(e);
			eq.getEtudiants().add(e);
			log.debug("Assigned contract ID {} and team ID {} to student {}", idContrat, idEquipe, e);
		} else {
			log.warn("Failed to assign student: contract or team not found");
		}
		return e;
	}

	public List<Etudiant> getEtudiantsByDepartement(Integer idDepartement) {
		log.info("Retrieving students by department ID: {}", idDepartement);
		List<Etudiant> etudiants = etudiantRepository.findEtudiantsByDepartement_IdDepart(idDepartement);
		log.debug("Retrieved {} students for department ID {}", etudiants.size(), idDepartement);
		return etudiants;
	}
}
