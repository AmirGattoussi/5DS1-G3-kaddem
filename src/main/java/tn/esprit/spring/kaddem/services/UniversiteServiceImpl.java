package tn.esprit.spring.kaddem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.UniversiteRepository;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class UniversiteServiceImpl implements IUniversiteService{
    private final UniversiteRepository universiteRepository;
    private final DepartementRepository departementRepository;
    // Constructor injection
    @Autowired
    public UniversiteServiceImpl(UniversiteRepository universiteRepository, DepartementRepository departementRepository) {
        this.universiteRepository = universiteRepository;
        this.departementRepository = departementRepository;
    }
  public   List<Universite> retrieveAllUniversites(){
return (List<Universite>) universiteRepository.findAll();
    }

 public    Universite addUniversite (Universite  u){
return  (universiteRepository.save(u));
    }

 public    Universite updateUniversite (Universite  u){
     return  (universiteRepository.save(u));
    }

  public Universite retrieveUniversite (Integer idUniversite){
      return universiteRepository.findById(idUniversite)
              .orElse(null);
    }
    public  void deleteUniversite(Integer idUniversite){
        universiteRepository.delete(retrieveUniversite(idUniversite));
    }

    public void assignUniversiteToDepartement(Integer idUniversite, Integer idDepartement){
        Universite u = universiteRepository.findById(idUniversite).orElse(null);
        Departement d = departementRepository.findById(idDepartement).orElse(null);

        if (u == null || d == null) {
            throw new IllegalArgumentException("Invalid Universite or Departement ID");
        }

        u.getDepartements().add(d);
        universiteRepository.save(u);
    }



    public Set<Departement> retrieveDepartementsByUniversite(Integer idUniversite) {
        Universite u = universiteRepository.findById(idUniversite).orElse(null);
        if (u == null) {
            return Collections.emptySet(); // or throw an exception if preferred
        }
        return u.getDepartements();
    }
}
