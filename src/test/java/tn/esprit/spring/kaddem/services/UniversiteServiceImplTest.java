package tn.esprit.spring.kaddem.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.repositories.UniversiteRepository;
class UniversiteServiceImplTest {

    @InjectMocks
    private UniversiteServiceImpl universiteService;

    @Mock
    private UniversiteRepository universiteRepository; // Assuming you have a repository interface

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddUniversite() {
        Universite u = new Universite("ESPRIT");
        when(universiteRepository.save(u)).thenReturn(u); // Mocking the save method

        Universite result = universiteService.addUniversite(u);

        assertNotNull(result);
        assertEquals("ESPRIT", result.getNomUniv());
        verify(universiteRepository, times(1)).save(u); // Verify that save was called
    }
}
