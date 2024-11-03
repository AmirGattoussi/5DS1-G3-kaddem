package tn.esprit.spring.kaddem.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.kaddem.dto.DetailEquipeDTO;
import tn.esprit.spring.kaddem.entities.DetailEquipe;
import tn.esprit.spring.kaddem.services.IDetailEquipeService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/detailEquipe")
public class DetailEquipeRestController {
    IDetailEquipeService detailEquipeService;

    @GetMapping("/retrieve-all-detailEquipes")
    public List<DetailEquipe> getDetailEquipes() {
        return detailEquipeService.retrieveAllDetailEquipes();
    }
    // http://localhost:8089/Kaddem/detailEquipe/retrieve-detailEquipe/1
    @GetMapping("/retrieve-detailEquipe/{detailEquipe-id}")
    public DetailEquipe retrieveDetailEquipe(@PathVariable("detailEquipe-id") Integer detailEquipeId) {
        return detailEquipeService.retrieveDetailEquipe(detailEquipeId);
    }

    // http://localhost:8089/Kaddem/detailEquipe/remove-detailEquipe/1
    @DeleteMapping("/remove-detailEquipe/{detailEquipe-id}")
    public void removeDetailEquipe(@PathVariable("detailEquipe-id") Integer detailEquipeId) {
        detailEquipeService.deleteDetailEquipe(detailEquipeId);
    }

    // http://localhost:8089/Kaddem/detailEquipe/update-detailEquipe
    @PutMapping("/update-detailEquipe")
    public DetailEquipe updateDetailEquipe(@RequestBody DetailEquipeDTO detailEquipeDTO) {
        DetailEquipe detailEquipe = convertToEntity(detailEquipeDTO);
        return detailEquipeService.updateDetailEquipe(detailEquipe);
    }

    // http://localhost:8089/Kaddem/detailEquipe/affecter-detailEquipe-Equipe/1/1
    @PostMapping(value="/add-assign-detailEquipe-Equipe/{equipeId}")
    public void addAndAssignDetailEquipeToEquipe(@RequestBody DetailEquipeDTO detailEquipeDTO, @PathVariable("equipeId")Integer equipeId){
        DetailEquipe detailEquipe = convertToEntity(detailEquipeDTO);
        detailEquipeService.addAndAssignDetailEquipeToEquipe(detailEquipe, equipeId);
    }

    public DetailEquipe convertToEntity(DetailEquipeDTO detailEquipeDTO) {
        DetailEquipe detailEquipe = new DetailEquipe();
        detailEquipe.setIdDetailEquipe(detailEquipeDTO.getIdDetailEquipe());
        detailEquipe.setSalle(detailEquipeDTO.getSalle());
        detailEquipe.setThematique(detailEquipeDTO.getThematique());
        detailEquipe.setEquipe(detailEquipeDTO.getEquipe());
        return detailEquipe;
    }

}
