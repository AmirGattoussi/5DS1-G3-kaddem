package tn.esprit.spring.kaddem.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.spring.kaddem.entities.DetailEquipe;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.repositories.DetailEquipeRepository;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@SuppressWarnings("LoggingSimilarMessage")
@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
class DetailEquipeServiceImplTest {

    @Mock
    private EquipeRepository equipeRepository;

    @Mock
    private DetailEquipeRepository detailEquipeRepository;

    @InjectMocks
    private DetailEquipeServiceImpl detailEquipeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveAllDetailEquipes() {
        log.info("Running testRetrieveAllDetailEquipes");
        // Given
        DetailEquipe detailEquipe1 = new DetailEquipe();
        DetailEquipe detailEquipe2 = new DetailEquipe();
        when(detailEquipeRepository.findAll()).thenReturn(Arrays.asList(detailEquipe1, detailEquipe2));

        // When
        List<DetailEquipe> result = detailEquipeService.retrieveAllDetailEquipes();

        // Then
        log.debug("Retrieved DetailEquipes: {}", result);
        assertThat(result).hasSize(2);
        verify(detailEquipeRepository, times(1)).findAll();
    }

    @Test
    void testRetrieveDetailEquipe_Success() {
        log.info("Running testRetrieveDetailEquipe_Success");
        // Given
        DetailEquipe detailEquipe = new DetailEquipe();
        when(detailEquipeRepository.findById(anyInt())).thenReturn(Optional.of(detailEquipe));

        // When
        DetailEquipe result = detailEquipeService.retrieveDetailEquipe(1);

        // Then
        log.debug("Retrieved DetailEquipe: {}", result);
        assertThat(result).isEqualTo(detailEquipe);
        verify(detailEquipeRepository, times(1)).findById(1);
    }

    @Test
    void testRetrieveDetailEquipe_NotFound() {
        log.info("Running testRetrieveDetailEquipe_NotFound");
        // Given
        when(detailEquipeRepository.findById(anyInt())).thenReturn(Optional.empty());

        // When/Then
        Exception exception = assertThrows(RuntimeException.class, () -> detailEquipeService.retrieveDetailEquipe(1));
        log.error("Error occurred: {}", exception.getMessage());
        verify(detailEquipeRepository, times(1)).findById(1);
    }

    @Test
    void testAddAndAssignDetailEquipeToEquipe_Success() {
        log.info("Running testAddAndAssignDetailEquipeToEquipe_Success");
        // Given
        DetailEquipe detailEquipe = new DetailEquipe();
        Equipe equipe = new Equipe();
        when(equipeRepository.findById(anyInt())).thenReturn(Optional.of(equipe));
        when(detailEquipeRepository.save(any(DetailEquipe.class))).thenReturn(detailEquipe);

        // When
        detailEquipeService.addAndAssignDetailEquipeToEquipe(detailEquipe, 1);

        // Then
        log.debug("DetailEquipe assigned to Equipe: {}", detailEquipe);
        verify(equipeRepository, times(1)).findById(1);
        verify(detailEquipeRepository, times(1)).save(detailEquipe);
        assertThat(detailEquipe.getEquipe()).isEqualTo(equipe);
    }

    @Test
    void testAddAndAssignDetailEquipeToEquipe_EquipeNotFound() {
        log.info("Running testAddAndAssignDetailEquipeToEquipe_EquipeNotFound");
        // Given
        DetailEquipe detailEquipe = new DetailEquipe();
        when(equipeRepository.findById(anyInt())).thenReturn(Optional.empty());

        // When
        detailEquipeService.addAndAssignDetailEquipeToEquipe(detailEquipe, 1);

        // Then
        log.debug("Equipe not found, DetailEquipe not assigned: {}", detailEquipe);
        verify(equipeRepository, times(1)).findById(1);
        verify(detailEquipeRepository, times(0)).save(detailEquipe);
    }

    @Test
    void testDeleteDetailEquipe_Success() {
        log.info("Running testDeleteDetailEquipe_Success");
        // Given
        DetailEquipe detailEquipe = new DetailEquipe();
        when(detailEquipeRepository.findById(anyInt())).thenReturn(Optional.of(detailEquipe));

        // When
        detailEquipeService.deleteDetailEquipe(1);

        // Then
        log.debug("DetailEquipe deleted: {}", detailEquipe);
        verify(detailEquipeRepository, times(1)).findById(1);
        verify(detailEquipeRepository, times(1)).delete(detailEquipe);
    }

    @Test
    void testDeleteDetailEquipe_NotFound() {
        log.info("Running testDeleteDetailEquipe_NotFound");
        // Given
        when(detailEquipeRepository.findById(anyInt())).thenReturn(Optional.empty());

        // When
        detailEquipeService.deleteDetailEquipe(1);

        // Then
        log.debug("DetailEquipe not found, nothing deleted");
        verify(detailEquipeRepository, times(1)).findById(1);
        verify(detailEquipeRepository, times(0)).delete(any(DetailEquipe.class));
    }

    @Test
    void testUpdateDetailEquipe_Success() {
        log.info("Running testUpdateDetailEquipe_Success");
        // Given
        DetailEquipe detailEquipe = new DetailEquipe();
        when(detailEquipeRepository.existsById(anyInt())).thenReturn(true);
        when(detailEquipeRepository.save(any(DetailEquipe.class))).thenReturn(detailEquipe);

        // When
        DetailEquipe result = detailEquipeService.updateDetailEquipe(detailEquipe);

        // Then
        log.debug("DetailEquipe updated: {}", result);
        assertThat(result).isEqualTo(detailEquipe);
        verify(detailEquipeRepository, times(1)).existsById(detailEquipe.getIdDetailEquipe());
        verify(detailEquipeRepository, times(1)).save(detailEquipe);
    }

    @Test
    void testUpdateDetailEquipe_NotFound() {
        log.info("Running testUpdateDetailEquipe_NotFound");
        // Given
        DetailEquipe detailEquipe = new DetailEquipe();
        when(detailEquipeRepository.existsById(anyInt())).thenReturn(false);

        // When/Then
        Exception exception = assertThrows(RuntimeException.class, () -> detailEquipeService.updateDetailEquipe(detailEquipe));
        log.error("Error occurred: {}", exception.getMessage());
        verify(detailEquipeRepository, times(1)).existsById(detailEquipe.getIdDetailEquipe());
        verify(detailEquipeRepository, times(0)).save(any(DetailEquipe.class));
    }
}
