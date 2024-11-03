package tn.esprit.spring.kaddem.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.spring.kaddem.entities.DetailEquipe;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.repositories.DetailEquipeRepository;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;

import java.util.List;

@Service
@Slf4j
public class DetailEquipeServiceImpl implements IDetailEquipeService {
    private final EquipeRepository equipeRepository;
    private final DetailEquipeRepository detailEquipeRepository;

    public DetailEquipeServiceImpl(EquipeRepository equipeRepository, DetailEquipeRepository detailEquipeRepository) {
        this.equipeRepository = equipeRepository;
        this.detailEquipeRepository = detailEquipeRepository;
    }

    @Override
    public List<DetailEquipe> retrieveAllDetailEquipes() {
        log.info("Retrieving all DetailEquipes");
        List<DetailEquipe> detailEquipes = (List<DetailEquipe>) detailEquipeRepository.findAll();
        log.debug("Retrieved DetailEquipes: {}", detailEquipes);
        return detailEquipes;
    }

    @Override
    public DetailEquipe retrieveDetailEquipe(Integer idDetailEquipe) {
        log.info("Retrieving DetailEquipe with ID: {}", idDetailEquipe);
        return detailEquipeRepository.findById(idDetailEquipe)
                .orElseThrow(() -> {
                    log.error("DetailEquipe with ID {} not found", idDetailEquipe);
                    return new RuntimeException("DetailEquipe not found");
                });
    }

    @Override
    public void addAndAssignDetailEquipeToEquipe(DetailEquipe de, Integer idEquipe) {
        log.info("Adding DetailEquipe with details: {}", de);

        Equipe e = equipeRepository.findById(idEquipe).orElse(null);
        if (e == null) {
            log.error("Equipe not found. DetailEquipe ID: {}, Equipe ID: {}", de.getIdDetailEquipe(), idEquipe);
            return;
        }
        log.info("Equipe found with ID {}: {}", idEquipe, e);

        de.setEquipe(e);
        e.setDetailEquipe(de);

        log.info("Assigning DetailEquipe with ID {} to Equipe with ID {}", de.getIdDetailEquipe(), idEquipe);
        DetailEquipe savedDetailEquipe = detailEquipeRepository.save(de);
        equipeRepository.save(e);
        log.debug("DetailEquipe added: {}", savedDetailEquipe);

        log.info("Assigned DetailEquipe with ID {} to Equipe with ID {}", de.getIdDetailEquipe(), idEquipe);
    }

    @Override
    public void deleteDetailEquipe(Integer idDetailEquipe) {
        log.info("Deleting DetailEquipe with ID: {}", idDetailEquipe);
        try {
            DetailEquipe de = retrieveDetailEquipe(idDetailEquipe);

            // Find the Equipe that references this DetailEquipe
            Equipe equipe = equipeRepository.findById((de.getEquipe()).getIdEquipe()).get();

            // Set the Equipe's detailEquipe reference to null
            equipe.setDetailEquipe(null);
            equipeRepository.save(equipe);

            detailEquipeRepository.delete(de);
            log.debug("DetailEquipe with ID {} deleted successfully", idDetailEquipe);
        } catch (Exception e) {
            log.error("Error deleting DetailEquipe with ID {}: {}", idDetailEquipe, e.getMessage());
        }
    }

    @Override
    public DetailEquipe updateDetailEquipe(DetailEquipe de, Integer idDetailEquipe) {
        log.info("Updating DetailEquipe ID {} with details: {}", idDetailEquipe, de);
        DetailEquipe newDe = null;

        try {
            // Retrieve the existing DetailEquipe
            newDe = retrieveDetailEquipe(idDetailEquipe); // This method will throw an exception if not found
        } catch (RuntimeException e) {
            log.warn("DetailEquipe ID {} not found for update. Exception: {}", idDetailEquipe, e.getMessage());
            return null;  // Return null if the DetailEquipe is not found
        }

        // Update only non-null fields from de to newDe
        if (de.getSalle() != null) {
            newDe.setSalle(de.getSalle());
        }
        if (de.getThematique() != null) {
            newDe.setThematique(de.getThematique());
        }
        if (de.getEquipe() != null) {
            newDe.setEquipe(de.getEquipe());
        }

        // Save the updated DetailEquipe
        DetailEquipe updatedDetailEquipe = detailEquipeRepository.save(newDe);
        log.debug("DetailEquipe ID {} updated: {}", idDetailEquipe, updatedDetailEquipe);
        return updatedDetailEquipe;
    }

}