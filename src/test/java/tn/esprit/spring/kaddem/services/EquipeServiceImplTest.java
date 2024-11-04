package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Niveau;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EquipeServiceImplTest {

    @InjectMocks
    private EquipeServiceImpl equipeService;

    @Mock
    private EquipeRepository equipeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveAllEquipes() {
        Equipe equipe1 = new Equipe(1, "Equipe A", Niveau.JUNIOR);
        Equipe equipe2 = new Equipe(2, "Equipe B", Niveau.SENIOR);
        when(equipeRepository.findAll()).thenReturn(Arrays.asList(equipe1, equipe2));

        List<Equipe> equipes = equipeService.retrieveAllEquipes();

        assert equipes.size() == 2;
        verify(equipeRepository).findAll();
    }

    @Test
    void testAddEquipe() {
        Equipe equipe = new Equipe("Equipe A", Niveau.JUNIOR);
        when(equipeRepository.save(any(Equipe.class))).thenReturn(equipe);

        Equipe createdEquipe = equipeService.addEquipe(equipe);

        assertEquals("Equipe A", createdEquipe.getNomEquipe());
        verify(equipeRepository).save(any(Equipe.class));
    }

    // Add similar tests for delete, update, and retrieve methods
}
//testttt