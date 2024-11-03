package tn.esprit.spring.kaddem.controllers;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Option;
import tn.esprit.spring.kaddem.services.IEtudiantService;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class EtudiantRestControllerTest {

    @Mock
    private IEtudiantService etudiantService;

    @InjectMocks
    private EtudiantRestController etudiantRestController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(etudiantRestController).build();
    }

    @Test
    void testGetEtudiants() throws Exception {
        List<Etudiant> etudiants = Arrays.asList(new Etudiant(1, "John", "Doe", Option.SE));
        when(etudiantService.retrieveAllEtudiants()).thenReturn(etudiants);

        mockMvc.perform(get("/etudiant/retrieve-all-etudiants"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testRetrieveEtudiant() throws Exception {
        Etudiant etudiant = new Etudiant(1, "John", "Doe", Option.SE);
        when(etudiantService.retrieveEtudiant(anyInt())).thenReturn(etudiant);

        mockMvc.perform(get("/etudiant/retrieve-etudiant/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
    @Test
    void testRemoveEtudiant() throws Exception {
        doNothing().when(etudiantService).removeEtudiant(anyInt());

        mockMvc.perform(delete("/etudiant/remove-etudiant/1"))
                .andExpect(status().isOk());

        verify(etudiantService, times(1)).removeEtudiant(1);
    }

    @Test
    void testAffecterEtudiantToDepartement() throws Exception {
        doNothing().when(etudiantService).assignEtudiantToDepartement(anyInt(), anyInt());

        mockMvc.perform(put("/etudiant/affecter-etudiant-departement/1/1"))
                .andExpect(status().isOk());

        verify(etudiantService, times(1)).assignEtudiantToDepartement(1, 1);
    }

    @Test
    void testGetEtudiantsByDepartement() throws Exception {
        List<Etudiant> etudiants = Arrays.asList(new Etudiant(1, "John", "Doe", Option.SE));
        when(etudiantService.getEtudiantsByDepartement(anyInt())).thenReturn(etudiants);

        mockMvc.perform(get("/etudiant/getEtudiantsByDepartement/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
