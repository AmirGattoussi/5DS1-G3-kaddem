package tn.esprit.spring.kaddem.controllers;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.services.IDetailEquipeService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tn.esprit.spring.kaddem.dto.DetailEquipeDTO;
import tn.esprit.spring.kaddem.entities.DetailEquipe;
import tn.esprit.spring.kaddem.entities.Equipe;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class DetailEquipeRestControllerTest {

    @Mock
    private IDetailEquipeService detailEquipeService;

    @InjectMocks
    private DetailEquipeRestController detailEquipeRestController;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(detailEquipeRestController).build();
    }

    @Test
    void testGetDetailEquipes() throws Exception {
        List<DetailEquipe> detailEquipes = List.of(new DetailEquipe(1, 101, "Thematique A", new Equipe()));
        when(detailEquipeService.retrieveAllDetailEquipes()).thenReturn(detailEquipes);

        mockMvc.perform(get("/detailEquipe/retrieve-all-detailEquipes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].idDetailEquipe").value(detailEquipes.get(0).getIdDetailEquipe()))
                .andExpect(jsonPath("$[0].salle").value(detailEquipes.get(0).getSalle()))
                .andExpect(jsonPath("$[0].thematique").value(detailEquipes.get(0).getThematique()));
    }

    @Test
    void testRetrieveDetailEquipe() throws Exception {
        DetailEquipe detailEquipe = new DetailEquipe(1, 101, "Thematique A", new Equipe());
        when(detailEquipeService.retrieveDetailEquipe(anyInt())).thenReturn(detailEquipe);

        mockMvc.perform(get("/detailEquipe/retrieve-detailEquipe/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.idDetailEquipe").value(detailEquipe.getIdDetailEquipe()))
                .andExpect(jsonPath("$.salle").value(detailEquipe.getSalle()))
                .andExpect(jsonPath("$.thematique").value(detailEquipe.getThematique()));
    }

    @Test
    void testRemoveDetailEquipe() throws Exception {
        doNothing().when(detailEquipeService).deleteDetailEquipe(anyInt());

        mockMvc.perform(delete("/detailEquipe/remove-detailEquipe/1"))
                .andExpect(status().isOk());

        verify(detailEquipeService, times(1)).deleteDetailEquipe(1);
    }

    @Test
    void testUpdateDetailEquipe() throws Exception {
        // Create a DetailEquipeDTO for the request
        DetailEquipeDTO detailEquipeDTO = new DetailEquipeDTO();
        detailEquipeDTO.setIdDetailEquipe(1);
        detailEquipeDTO.setSalle(101);
        detailEquipeDTO.setThematique("Thematique A");

        // Create the corresponding DetailEquipe object
        DetailEquipe detailEquipe = new DetailEquipe();
        detailEquipe.setIdDetailEquipe(detailEquipeDTO.getIdDetailEquipe());
        detailEquipe.setSalle(detailEquipeDTO.getSalle());
        detailEquipe.setThematique(detailEquipeDTO.getThematique());
        detailEquipe.setEquipe(new Equipe()); // Assuming you want to set the Equipe too

        // Mock the service method call, specifying the parameters it expects
        when(detailEquipeService.updateDetailEquipe(any(DetailEquipe.class), eq(detailEquipeDTO.getIdDetailEquipe())))
                .thenReturn(detailEquipe);

        // Perform the PUT request and expect the response
        mockMvc.perform(put("/detailEquipe/update-detailEquipe/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"idDetailEquipe\": 1, \"salle\": 101, \"thematique\": \"Thematique A\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idDetailEquipe").value(detailEquipe.getIdDetailEquipe()))
                .andExpect(jsonPath("$.salle").value(detailEquipe.getSalle()))
                .andExpect(jsonPath("$.thematique").value(detailEquipe.getThematique()));

        // Verify that the service method was called with the correct parameters
        verify(detailEquipeService, times(1)).updateDetailEquipe(any(DetailEquipe.class), eq(detailEquipeDTO.getIdDetailEquipe()));
    }


    @Test
    void testAddAndAssignDetailEquipeToEquipe() throws Exception {
        DetailEquipeDTO detailEquipeDTO = new DetailEquipeDTO();
        detailEquipeDTO.setIdDetailEquipe(1);
        detailEquipeDTO.setSalle(101);
        detailEquipeDTO.setThematique("Thematique A");

        doNothing().when(detailEquipeService).addAndAssignDetailEquipeToEquipe(any(DetailEquipe.class), anyInt());

        mockMvc.perform(post("/detailEquipe/add-assign-detailEquipe-equipe/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"idDetailEquipe\": 1, \"salle\": 101, \"thematique\": \"Thematique A\"}"))
                .andExpect(status().isOk());

        verify(detailEquipeService, times(1)).addAndAssignDetailEquipeToEquipe(any(DetailEquipe.class), eq(1));
    }

    @Test
    void testConvertToEntity() {
        DetailEquipeDTO detailEquipeDTO = new DetailEquipeDTO();
        detailEquipeDTO.setIdDetailEquipe(1);
        detailEquipeDTO.setSalle(101);
        detailEquipeDTO.setThematique("Thematique A");
        detailEquipeDTO.setEquipe(new Equipe());

        DetailEquipe detailEquipe = detailEquipeRestController.convertToEntity(detailEquipeDTO);

        assertEquals(detailEquipeDTO.getIdDetailEquipe(), detailEquipe.getIdDetailEquipe());
        assertEquals(detailEquipeDTO.getSalle(), detailEquipe.getSalle());
        assertEquals(detailEquipeDTO.getThematique(), detailEquipe.getThematique());
        assertEquals(detailEquipeDTO.getEquipe(), detailEquipe.getEquipe());
    }
}
