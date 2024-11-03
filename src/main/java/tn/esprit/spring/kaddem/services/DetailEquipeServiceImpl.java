package tn.esprit.spring.kaddem.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.DetailEquipe;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Universite;
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

        log.info("Assigning DetailEquipe with ID {} to Equipe with ID {}", de.getIdDetailEquipe(), idEquipe);
        DetailEquipe savedDetailEquipe = detailEquipeRepository.save(de);
        log.debug("DetailEquipe added: {}", savedDetailEquipe);

        log.info("Assigned DetailEquipe with ID {} to Equipe with ID {}", savedDetailEquipe.getIdDetailEquipe(), idEquipe);
    }

    @Override
    public void deleteDetailEquipe(Integer idDetailEquipe) {
        log.info("Deleting DetailEquipe with ID: {}", idDetailEquipe);
        try {
            DetailEquipe de = retrieveDetailEquipe(idDetailEquipe);
            detailEquipeRepository.delete(de);
            log.debug("DetailEquipe with ID {} deleted successfully", idDetailEquipe);
        } catch (Exception e) {
            log.error("Error deleting DetailEquipe with ID {}: {}", idDetailEquipe, e.getMessage());
        }
    }

    @Override
    public DetailEquipe updateDetailEquipe(DetailEquipe de) {
        log.info("Updating DetailEquipe with details: {}", de);
        DetailEquipe updatedDetailEquipe = detailEquipeRepository.save(de);
        log.debug("DetailEquipe updated: {}", updatedDetailEquipe);
        return updatedDetailEquipe;
    }

}