package tn.esprit.spring.kaddem.services;

import tn.esprit.spring.kaddem.entities.DetailEquipe;

import java.util.List;

public interface IDetailEquipeService {

    public List<DetailEquipe> retrieveAllDetailEquipes();
    public DetailEquipe retrieveDetailEquipe(Integer idDetailEquipe);
    public void addAndAssignDetailEquipeToEquipe(DetailEquipe de, Integer idEquipe);
    public  void deleteDetailEquipe(Integer idDetailEquipe);
    public DetailEquipe updateDetailEquipe(DetailEquipe de, Integer idDetailEquipe);

}
