package tn.esprit.spring.kaddem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;

import java.util.*;

@Slf4j
@Service
public class ContratServiceImpl implements IContratService {
	ContratRepository contratRepository;
	EtudiantRepository etudiantRepository;

	public List<Contrat> retrieveAllContrats() {
		return contratRepository.findAll();
	}

	public Contrat updateContrat(Contrat ce) {
		return contratRepository.save(ce);
	}

	public Contrat addContrat(Contrat ce) {
		return contratRepository.save(ce);
	}

	public Contrat retrieveContrat(Integer idContrat) {
		return contratRepository.findById(idContrat).orElse(null);
	}

	public void removeContrat(Integer idContrat) {
		Optional<Contrat> contrat = contratRepository.findById(idContrat);
		if (!contrat.isPresent()) {
			throw new NoSuchElementException("Contract not found with id: " + idContrat);
		}
		contratRepository.delete(contrat.get());
	}

	public Contrat affectContratToEtudiant(Integer idContrat, String nomE, String prenomE) {
		Etudiant e = etudiantRepository.findByNomEAndPrenomE(nomE, prenomE);
		if (e == null) {
			return null; // or throw new EntityNotFoundException("Etudiant not found");
		}

		Contrat ce = contratRepository.findByIdContrat(idContrat);
		if (ce == null) {
			return null; // or throw new EntityNotFoundException("Contrat not found");
		}

		Set<Contrat> contrats = e.getContrats();
		Integer nbContratsActifs = 0;

		for (Contrat contrat : contrats) {
			if (contrat.getArchive() == null || contrat.getArchive()) {
				nbContratsActifs++;
			}
		}

		if (nbContratsActifs <= 4) {
			ce.setEtudiant(e);
			contratRepository.save(ce);
		}
		return ce;
	}

	public Integer nbContratsValides(Date startDate, Date endDate) {
		return contratRepository.getnbContratsValides(startDate, endDate);
	}

	public void retrieveAndUpdateStatusContrat() {
		List<Contrat> contrats = contratRepository.findAll();
		List<Contrat> contrats15j = new ArrayList<>();
		List<Contrat> contratsAarchiver = new ArrayList<>();

		for (Contrat contrat : contrats) {
			Date dateSysteme = new Date();
			if (!contrat.getArchive()) {
				long differenceInTime = dateSysteme.getTime() - contrat.getDateFinContrat().getTime();
				long differenceInDays = (differenceInTime / (1000 * 60 * 60 * 24)) % 365;

				if (differenceInDays == 15) {
					contrats15j.add(contrat);
					log.info("Contrat: " + contrat);
				}
				if (differenceInDays == 0) {
					contratsAarchiver.add(contrat);
					contrat.setArchive(true);
					contratRepository.save(contrat);
				}
			}
		}
	}

	public float getChiffreAffaireEntreDeuxDates(Date startDate, Date endDate) {
		long differenceInTime = endDate.getTime() - startDate.getTime();
		float differenceInDays = (float) differenceInTime / (1000 * 60 * 60 * 24) % 365;
		float differenceInMonths = differenceInDays / 30;

		List<Contrat> contrats = contratRepository.findAll();
		float chiffreAffaireEntreDeuxDates = 0;

		for (Contrat contrat : contrats) {
			switch (contrat.getSpecialite()) {
				case IA:
					chiffreAffaireEntreDeuxDates += (differenceInMonths * 300);
					break;
				case CLOUD:
					chiffreAffaireEntreDeuxDates += (differenceInMonths * 400);
					break;
				case RESEAUX:
					chiffreAffaireEntreDeuxDates += (differenceInMonths * 350);
					break;
				default:
					chiffreAffaireEntreDeuxDates += (differenceInMonths * 450);
					break;
			}
		}
		return chiffreAffaireEntreDeuxDates;
	}
}