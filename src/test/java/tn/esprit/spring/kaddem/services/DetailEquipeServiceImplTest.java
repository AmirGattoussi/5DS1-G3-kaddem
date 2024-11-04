package tn.esprit.spring.kaddem.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.DetailEquipe;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.repositories.DetailEquipeRepository;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@Slf4j
@SpringBootTest
class DetailEquipeServiceImplTest {

    @Mock
    private EquipeRepository equipeRepository;

    @Mock
    private DetailEquipeRepository detailEquipeRepository;

    @InjectMocks
    private DetailEquipeServiceImpl detailEquipeService;

    private DetailEquipe detailEquipe;
    private Equipe equipe;

    @BeforeEach
    void setUp() {
        detailEquipe = new DetailEquipe();
        detailEquipe.setIdDetailEquipe(1);
        detailEquipe.setSalle(101);
        detailEquipe.setThematique("AI");

        equipe = new Equipe();
        equipe.setIdEquipe(1);
        equipe.setNomEquipe("Development Team");
    }

    @Test
    void testRetrieveDetailEquipe_Success() {
        when(detailEquipeRepository.findById(1)).thenReturn(Optional.of(detailEquipe));

        DetailEquipe result = detailEquipeService.retrieveDetailEquipe(1);

        assertNotNull(result);
        assertEquals(101, result.getSalle());
        verify(detailEquipeRepository, times(1)).findById(1);
    }

    @Test
    void testRetrieveDetailEquipe_NotFound() {
        when(detailEquipeRepository.findById(anyInt())).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> detailEquipeService.retrieveDetailEquipe(1));

        assertEquals("DetailEquipe not found", exception.getMessage());
        verify(detailEquipeRepository, times(1)).findById(1);
    }

    @Test
    void testAddAndAssignDetailEquipeToEquipe_Success() {
        when(equipeRepository.findById(1)).thenReturn(Optional.of(equipe));
        when(detailEquipeRepository.save(any(DetailEquipe.class))).thenReturn(detailEquipe);

        detailEquipeService.addAndAssignDetailEquipeToEquipe(detailEquipe, 1);

        assertEquals(equipe, detailEquipe.getEquipe());
        verify(equipeRepository, times(1)).findById(1);
        verify(detailEquipeRepository, times(1)).save(detailEquipe);
        verify(equipeRepository, times(1)).save(equipe);
    }

    @Test
    void testAddAndAssignDetailEquipeToEquipe_EquipeNotFound() {
        when(equipeRepository.findById(anyInt())).thenReturn(Optional.empty());

        detailEquipeService.addAndAssignDetailEquipeToEquipe(detailEquipe, 1);

        verify(equipeRepository, times(1)).findById(1);
        verify(detailEquipeRepository, never()).save(any(DetailEquipe.class));
    }

    @Test
    void testDeleteDetailEquipe_Success() {
        when(detailEquipeRepository.findById(1)).thenReturn(Optional.of(detailEquipe));
        when(equipeRepository.findById(1)).thenReturn(Optional.of(equipe));

        detailEquipe.setEquipe(equipe);
        equipe.setDetailEquipe(detailEquipe);

        detailEquipeService.deleteDetailEquipe(1);

        assertNull(equipe.getDetailEquipe());
        verify(detailEquipeRepository, times(1)).findById(1);
        verify(equipeRepository, times(1)).save(equipe);
        verify(detailEquipeRepository, times(1)).delete(detailEquipe);
    }

    @Test
    void testDeleteDetailEquipe_NotFound() {
        when(detailEquipeRepository.findById(anyInt())).thenReturn(Optional.empty());

        detailEquipeService.deleteDetailEquipe(1);

        verify(detailEquipeRepository, times(1)).findById(1);
        verify(detailEquipeRepository, never()).delete(any(DetailEquipe.class));
    }

    @Test
    void testUpdateDetailEquipe_Success() {
        when(detailEquipeRepository.findById(1)).thenReturn(Optional.of(detailEquipe));
        when(detailEquipeRepository.save(any(DetailEquipe.class))).thenReturn(detailEquipe);

        DetailEquipe updatedDetailEquipe = new DetailEquipe();
        updatedDetailEquipe.setSalle(102);
        updatedDetailEquipe.setThematique("ML");

        DetailEquipe result = detailEquipeService.updateDetailEquipe(updatedDetailEquipe, 1);

        assertNotNull(result);
        assertEquals(102, result.getSalle());
        assertEquals("ML", result.getThematique());
        verify(detailEquipeRepository, times(1)).findById(1);
        verify(detailEquipeRepository, times(1)).save(detailEquipe);
    }

    @Test
    void testUpdateDetailEquipe_NotFound() {
        when(detailEquipeRepository.findById(anyInt())).thenReturn(Optional.empty());

        DetailEquipe updatedDetailEquipe = new DetailEquipe();
        updatedDetailEquipe.setSalle(102);

        DetailEquipe result = detailEquipeService.updateDetailEquipe(updatedDetailEquipe, 1);

        assertNull(result);
        verify(detailEquipeRepository, times(1)).findById(1);
        verify(detailEquipeRepository, never()).save(any(DetailEquipe.class));
    }
}