package tn.esprit.spring.kaddem.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Niveau;
import tn.esprit.spring.kaddem.services.IEquipeService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EquipeRestControllerTest {

    @InjectMocks
    private EquipeRestController equipeRestController;

    @Mock
    private IEquipeService equipeService;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
    }

    @Test
    void testGetEquipes() {
        Equipe equipe1 = new Equipe(1, "Equipe A", Niveau.JUNIOR);
        Equipe equipe2 = new Equipe(2, "Equipe B", Niveau.SENIOR);
        when(equipeService.retrieveAllEquipes()).thenReturn(Arrays.asList(equipe1, equipe2));

        List<Equipe> equipes = equipeRestController.getEquipes();

        verify(equipeService).retrieveAllEquipes();
        assert equipes.size() == 2;
    }

    @Test
    void testAddEquipe() {
        Equipe equipe = new Equipe("Equipe A", Niveau.JUNIOR);
        when(equipeService.addEquipe(any(Equipe.class))).thenReturn(equipe);

        Equipe createdEquipe = equipeRestController.addEquipe(equipe);

        verify(equipeService).addEquipe(any(Equipe.class));
        assert createdEquipe.getNomEquipe().equals("Equipe A");
    }

    // Add similar tests for other controller methods
}
